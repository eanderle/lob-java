package com.lob.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ListenableFuture;
import com.lob.Lob;
import com.lob.protocol.request.JobRequest;
import com.lob.protocol.response.JobResponse;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.AsyncHttpClientConfig.Builder;
import com.ning.http.client.Realm;
import com.ning.http.client.Realm.AuthScheme;
import com.ning.http.client.RequestBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

public class AsyncLobClient implements LobClient {
    private final static ObjectMapper MAPPER = new ObjectMapper();
    private final AsyncHttpClient httpClient;
    private final String baseUrl;
    private final String apiVersion;

    private AsyncLobClient(
            final AsyncHttpClient httpClient,
            final String baseUrl,
            final String apiVersion) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
        this.apiVersion = apiVersion;
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
            Lob.getApiVersion());
    }

    private BoundRequestBuilder get() {
        return this.httpClient.prepareGet(this.baseUrl);
    }

    private BoundRequestBuilder post() {
        return this.httpClient.preparePost(this.baseUrl);
    }

    @Override
    public ListenableFuture<JobResponse> createJob(final JobRequest jobRequest) {
        post().setFormParams(jobRequest.toParamMap());
        return null;
    }
}
