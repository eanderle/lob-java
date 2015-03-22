package com.lob.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.lob.Lob;
import com.lob.protocol.request.JobRequest;
import com.lob.protocol.response.JobResponse;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.AsyncHttpClientConfig.Builder;
import com.ning.http.client.Realm;
import com.ning.http.client.Realm.AuthScheme;
import com.ning.http.client.Response;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.google.common.base.Preconditions.*;

public class AsyncLobClient implements LobClient {
    private final static ObjectMapper MAPPER = new ObjectMapper();
    private final AsyncHttpClient httpClient;
    private final String baseUrl;
    private final String apiVersion;

    private final ExecutorService callbackExecutorService;

    private AsyncLobClient(
            final AsyncHttpClient httpClient,
            final String baseUrl,
            final String apiVersion,
            final ExecutorService callbackExecutorService) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
        this.apiVersion = apiVersion;
        this.callbackExecutorService = callbackExecutorService;
    }

    public static LobClient createDefault(final String apiKey) {
        final Realm realm = new Realm.RealmBuilder()
            .setPrincipal(checkNotNull(apiKey))
            .setScheme(AuthScheme.BASIC)
            .build();

        final AsyncHttpClientConfig.Builder builder = new Builder();
        builder.setRealm(realm);

        return new AsyncLobClient(
            new AsyncHttpClient(builder.build()),
            Lob.getBaseUrl(),
            Lob.getApiVersion(),
            Executors.newCachedThreadPool());
    }

    @Override
    public ListenableFuture<JobResponse> createJob(final JobRequest jobRequest) {
        return execute(JobResponse.class, post(Router.JOBS, jobRequest.toParamMap()), this.callbackExecutorService);
    }

    private BoundRequestBuilder get() {
        return this.httpClient.prepareGet(this.baseUrl);
    }

    private BoundRequestBuilder post(final String resourceUrl, final Map<String, List<String>> paramMap) {
        return this.httpClient.preparePost(this.baseUrl + resourceUrl).setFormParams(paramMap);
    }

    private static <T> ListenableFuture<T> execute(
        final Class<T> clazz,
        final BoundRequestBuilder request,
        final ExecutorService callbackExecutorService) {
        final SettableFuture<T> guavaFut = SettableFuture.create();
        request.execute(new GuavaFutureConverter<T>(clazz, guavaFut, callbackExecutorService));
        return guavaFut;
    }

    private static class GuavaFutureConverter<T> extends AsyncCompletionHandler<T> {
        final Class<T> clazz;
        final SettableFuture<T> guavaFut;
        final ExecutorService callbackExecutorService;

        public GuavaFutureConverter(
                final Class<T> clazz,
                final SettableFuture<T> guavaFut,
                final ExecutorService callbackExecutorService) {
            this.clazz = clazz;
            this.guavaFut = guavaFut;
            this.callbackExecutorService = callbackExecutorService;
        }

        @Override
        public T onCompleted(final Response response) throws Exception {
            final T value = MAPPER.readValue(response.getResponseBody(), clazz);
            // Execute setting the guava future in a separate thread so any callbacks
            // executed on the guava future don't block the ning IO threads.
            this.callbackExecutorService.submit(
                new Runnable() {
                    @Override
                    public void run() {
                        guavaFut.set(value);
                    }
                });
            return value;
        }
    }
}
