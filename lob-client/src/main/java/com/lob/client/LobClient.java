package com.lob.client;

import com.google.common.util.concurrent.ListenableFuture;
import com.lob.id.AddressId;
import com.lob.id.AreaMailId;
import com.lob.id.BankAccountId;
import com.lob.id.CheckId;
import com.lob.id.JobId;
import com.lob.id.LobObjectId;
import com.lob.id.PostcardId;
import com.lob.protocol.request.AddressRequest;
import com.lob.protocol.request.AreaMailRequest;
import com.lob.protocol.request.BankAccountRequest;
import com.lob.protocol.request.CheckRequest;
import com.lob.protocol.request.JobRequest;
import com.lob.protocol.request.LobObjectRequest;
import com.lob.protocol.request.PostcardRequest;
import com.lob.protocol.response.*;

public interface LobClient {

    // Job methods
    public ListenableFuture<JobResponse> createJob(final JobRequest jobRequest);

    public ListenableFuture<JobResponse> getJob(final JobId id);

    public ListenableFuture<JobResponseList> getAllJobs();

    public ListenableFuture<JobResponseList> getJobs(final int count);

    public ListenableFuture<JobResponseList> getJobs(final int count, final int offset);

    // Address methods
    public ListenableFuture<AddressResponse> createAddress(final AddressRequest addressRequest);

    public ListenableFuture<AddressResponse> getAddress(final AddressId id);

    public ListenableFuture<AddressResponseList> getAllAddresses();

    public ListenableFuture<AddressResponseList> getAddresses(final int count);

    public ListenableFuture<AddressResponseList> getAddresses(final int count, final int offset);

    // Object methods
    public ListenableFuture<LobObjectResponse> createLobObject(final LobObjectRequest lobObjectRequest);

    public ListenableFuture<LobObjectResponse> getLobObject(final LobObjectId id);

    public ListenableFuture<LobObjectResponseList> getAllLobObjects();

    public ListenableFuture<LobObjectResponseList> getLobObjects(final int count);

    public ListenableFuture<LobObjectResponseList> getLobObjects(final int count, final int offset);

    // Setting methods
    public ListenableFuture<SettingResponseList> getAllSettings();

    // Service methods
    public ListenableFuture<ServiceResponseList> getAllServices();

    // Postcard methods
    public ListenableFuture<PostcardResponse> createPostcard(final PostcardRequest postcardRequest);

    public ListenableFuture<PostcardResponse> getPostcard(final PostcardId id);

    public ListenableFuture<PostcardResponseList> getAllPostcards();

    public ListenableFuture<PostcardResponseList> getPostcards(final int count);

    public ListenableFuture<PostcardResponseList> getPostcards(final int count, final int offset);

    // Check methods
    public ListenableFuture<CheckResponse> createCheck(final CheckRequest checkRequest);

    public ListenableFuture<CheckResponse> getCheck(final CheckId id);

    public ListenableFuture<CheckResponseList> getAllChecks();

    public ListenableFuture<CheckResponseList> getChecks(final int count);

    public ListenableFuture<CheckResponseList> getChecks(final int count, final int offset);

    // Bank account methods
    public ListenableFuture<BankAccountResponse> createBankAccount(final BankAccountRequest bankAccountRequest);

    public ListenableFuture<BankAccountResponse> getBankAccount(final BankAccountId id);

    public ListenableFuture<BankAccountResponseList> getAllBankAccounts();

    public ListenableFuture<BankAccountResponseList> getBankAccounts(final int count);

    public ListenableFuture<BankAccountResponseList> getBankAccounts(final int count, final int offset);

    // Area mail methods
    public ListenableFuture<AreaMailResponse> createAreaMail(final AreaMailRequest areaMailRequest);

    public ListenableFuture<AreaMailResponse> getAreaMail(final AreaMailId id);

    public ListenableFuture<AreaMailResponseList> getAllAreaMails();

    public ListenableFuture<AreaMailResponseList> getAreaMails(final int count);

    public ListenableFuture<AreaMailResponseList> getAreaMails(final int count, final int offset);
}
