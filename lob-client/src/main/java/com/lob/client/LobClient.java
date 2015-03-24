package com.lob.client;

import com.google.common.util.concurrent.ListenableFuture;
import com.lob.protocol.request.BankAccountRequest;
import com.lob.protocol.request.CheckRequest;
import com.lob.protocol.request.JobRequest;
import com.lob.protocol.request.PostcardRequest;
import com.lob.protocol.response.BankAccountResponse;
import com.lob.protocol.response.CheckResponse;
import com.lob.protocol.response.JobResponse;
import com.lob.protocol.response.PostcardResponse;

public interface LobClient {
    public ListenableFuture<JobResponse> createJob(final JobRequest jobRequest);

    public ListenableFuture<PostcardResponse> createPostcard(final PostcardRequest postcardRequest);

    public ListenableFuture<CheckResponse> createCheck(final CheckRequest checkRequest);

    public ListenableFuture<BankAccountResponse> createBankAccount(final BankAccountRequest bankAccountRequest);
}
