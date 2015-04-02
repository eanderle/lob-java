package com.lob.client;

import com.google.common.util.concurrent.ListenableFuture;
import com.lob.id.AddressId;
import com.lob.id.JobId;
import com.lob.protocol.request.AddressRequest;
import com.lob.protocol.request.AreaMailRequest;
import com.lob.protocol.request.BankAccountRequest;
import com.lob.protocol.request.CheckRequest;
import com.lob.protocol.request.JobRequest;
import com.lob.protocol.request.PostcardRequest;
import com.lob.protocol.response.AddressResponse;
import com.lob.protocol.response.AddressResponseList;
import com.lob.protocol.response.AreaMailResponse;
import com.lob.protocol.response.BankAccountResponse;
import com.lob.protocol.response.CheckResponse;
import com.lob.protocol.response.JobResponse;
import com.lob.protocol.response.JobResponseList;
import com.lob.protocol.response.PostcardResponse;

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

    // Postcard methods
    public ListenableFuture<PostcardResponse> createPostcard(final PostcardRequest postcardRequest);

    // Check methods
    public ListenableFuture<CheckResponse> createCheck(final CheckRequest checkRequest);

    // Bank account methods
    public ListenableFuture<BankAccountResponse> createBankAccount(final BankAccountRequest bankAccountRequest);

    // Area mail methods
    public ListenableFuture<AreaMailResponse> createAreaMail(final AreaMailRequest areaMailRequest);
}
