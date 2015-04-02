package com.lob.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.lob.Lob;
import com.lob.MoneyDeserializer;
import com.lob.id.AddressId;
import com.lob.id.AreaMailId;
import com.lob.id.BankAccountId;
import com.lob.id.CheckId;
import com.lob.id.JobId;
import com.lob.id.LobId;
import com.lob.id.LobObjectId;
import com.lob.id.PostcardId;
import com.lob.protocol.request.AddressRequest;
import com.lob.protocol.request.AreaMailRequest;
import com.lob.protocol.request.BankAccountRequest;
import com.lob.protocol.request.CheckRequest;
import com.lob.protocol.request.JobRequest;
import com.lob.protocol.request.LobObjectRequest;
import com.lob.protocol.request.ParamMappable;
import com.lob.protocol.request.PostcardRequest;
import com.lob.protocol.response.*;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.AsyncHttpClientConfig.Builder;
import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.Realm;
import com.ning.http.client.Realm.AuthScheme;
import com.ning.http.client.Response;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.google.common.base.Preconditions.*;

public class AsyncLobClient implements LobClient {
    private final static ObjectMapper MAPPER = new ObjectMapper()
        .registerModule(new JodaModule())
        .registerModule(new SimpleModule().addDeserializer(Money.class, new MoneyDeserializer(CurrencyUnit.USD)))
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

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
            .setUsePreemptiveAuth(true)
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
        return execute(JobResponse.class, post(Router.JOBS, jobRequest), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<JobResponse> getJob(final JobId id) {
        return execute(JobResponse.class, get(Router.JOBS, id), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<JobResponseList> getAllJobs() {
        return execute(JobResponseList.class, get(Router.JOBS), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<JobResponseList> getJobs(final int count) {
        return execute(JobResponseList.class, get(Router.JOBS, count), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<JobResponseList> getJobs(final int count, final int offset) {
        return execute(JobResponseList.class, get(Router.JOBS, count, offset), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<AddressResponse> createAddress(final AddressRequest addressRequest) {
        return execute(AddressResponse.class, post(Router.ADDRESSES, addressRequest), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<AddressResponse> getAddress(final AddressId id) {
        return execute(AddressResponse.class, get(Router.ADDRESSES, id), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<AddressResponseList> getAllAddresses() {
        return execute(AddressResponseList.class, get(Router.ADDRESSES), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<AddressResponseList> getAddresses(final int count) {
        return execute(AddressResponseList.class, get(Router.ADDRESSES, count), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<AddressResponseList> getAddresses(final int count, final int offset) {
        return execute(AddressResponseList.class, get(Router.ADDRESSES, count, offset), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<LobObjectResponse> createLobObject(final LobObjectRequest lobObjectRequest) {
        return execute(LobObjectResponse.class, post(Router.OBJECTS, lobObjectRequest), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<LobObjectResponse> getLobObject(final LobObjectId id) {
        return execute(LobObjectResponse.class, get(Router.OBJECTS, id), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<LobObjectResponseList> getAllLobObjects() {
        return execute(LobObjectResponseList.class, get(Router.OBJECTS), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<LobObjectResponseList> getLobObjects(final int count) {
        return execute(LobObjectResponseList.class, get(Router.OBJECTS, count), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<LobObjectResponseList> getLobObjects(final int count, final int offset) {
        return execute(LobObjectResponseList.class, get(Router.OBJECTS, count, offset), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<SettingResponseList> getAllSettings() {
        return execute(SettingResponseList.class, get(Router.SETTINGS), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<ServiceResponseList> getAllServices() {
        return execute(ServiceResponseList.class, get(Router.SERVICES), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<PostcardResponse> createPostcard(final PostcardRequest postcardRequest) {
        return execute(PostcardResponse.class, post(Router.POSTCARDS, postcardRequest), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<PostcardResponse> getPostcard(final PostcardId id) {
        return execute(PostcardResponse.class, get(Router.POSTCARDS, id), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<PostcardResponseList> getAllPostcards() {
        return execute(PostcardResponseList.class, get(Router.POSTCARDS), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<PostcardResponseList> getPostcards(final int count) {
        return execute(PostcardResponseList.class, get(Router.POSTCARDS, count), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<PostcardResponseList> getPostcards(final int count, final int offset) {
        return execute(PostcardResponseList.class, get(Router.POSTCARDS, count, offset), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<CheckResponse> createCheck(final CheckRequest checkRequest) {
        return execute(CheckResponse.class, post(Router.CHECKS, checkRequest), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<CheckResponse> getCheck(final CheckId id) {
        return execute(CheckResponse.class, get(Router.CHECKS, id), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<CheckResponseList> getAllChecks() {
        return execute(CheckResponseList.class, get(Router.CHECKS), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<CheckResponseList> getChecks(final int count) {
        return execute(CheckResponseList.class, get(Router.CHECKS, count), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<CheckResponseList> getChecks(final int count, final int offset) {
        return execute(CheckResponseList.class, get(Router.CHECKS, count, offset), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<BankAccountResponse> createBankAccount(final BankAccountRequest bankAccountRequest) {
        return execute(BankAccountResponse.class, post(Router.BANK_ACCOUNTS, bankAccountRequest), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<BankAccountResponse> getBankAccount(final BankAccountId id) {
        return execute(BankAccountResponse.class, get(Router.BANK_ACCOUNTS, id), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<BankAccountResponseList> getAllBankAccounts() {
        return execute(BankAccountResponseList.class, get(Router.BANK_ACCOUNTS), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<BankAccountResponseList> getBankAccounts(final int count) {
        return execute(BankAccountResponseList.class, get(Router.BANK_ACCOUNTS, count), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<BankAccountResponseList> getBankAccounts(final int count, final int offset) {
        return execute(BankAccountResponseList.class, get(Router.BANK_ACCOUNTS, count, offset), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<AreaMailResponse> createAreaMail(final AreaMailRequest areaMailRequest) {
        return execute(AreaMailResponse.class, post(Router.AREA_MAIL, areaMailRequest), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<AreaMailResponse> getAreaMail(final AreaMailId id) {
        return execute(AreaMailResponse.class, get(Router.AREA_MAIL, id), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<AreaMailResponseList> getAllAreaMails() {
        return execute(AreaMailResponseList.class, get(Router.AREA_MAIL), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<AreaMailResponseList> getAreaMails(final int count) {
        return execute(AreaMailResponseList.class, get(Router.AREA_MAIL, count), this.callbackExecutorService);
    }

    @Override
    public ListenableFuture<AreaMailResponseList> getAreaMails(final int count, final int offset) {
        return execute(AreaMailResponseList.class, get(Router.AREA_MAIL, count, offset), this.callbackExecutorService);
    }

    private BoundRequestBuilder get(final String resourceUrl) {
        return get(resourceUrl, new FluentStringsMap());
    }

    private BoundRequestBuilder get(final String resourceUrl, final LobId id) {
        return get(resourceUrl + "/" + id.value(), new FluentStringsMap());
    }

    private BoundRequestBuilder get(final String resourceUrl, final int count) {
        return get(resourceUrl, new FluentStringsMap().add("count", Integer.toString(count)));
    }

    private BoundRequestBuilder get(final String resourceUrl, final int count, final int offset) {
        return get(
            resourceUrl,
            new FluentStringsMap()
                .add("count", Integer.toString(count))
                .add("offset", Integer.toString(offset)));
    }

    private BoundRequestBuilder get(final String resourceUrl, final FluentStringsMap params) {
        return this.httpClient.prepareGet(this.baseUrl + resourceUrl).setQueryParameters(params);
    }

    private BoundRequestBuilder post(final String resourceUrl, final ParamMappable request) {
        return this.httpClient.preparePost(this.baseUrl + resourceUrl).setParameters(request.toParamMap());
    }

    private static <T> ListenableFuture<T> execute(
        final Class<T> clazz,
        final BoundRequestBuilder request,
        final ExecutorService callbackExecutorService) {
        final SettableFuture<T> guavaFut = SettableFuture.create();
        try {
            request.execute(new GuavaFutureConverter<T>(clazz, guavaFut, callbackExecutorService));
        }
        catch (final IOException e) {
            guavaFut.setException(e);
        }
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
