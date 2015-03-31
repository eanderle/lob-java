package com.lob.client;

import com.google.common.util.concurrent.ListenableFuture;
import com.lob.id.JobId;
import com.lob.protocol.request.AreaMailRequest;
import com.lob.protocol.request.BankAccountRequest;
import com.lob.protocol.request.CheckRequest;
import com.lob.protocol.request.JobRequest;
import com.lob.protocol.request.PostcardRequest;
import com.lob.protocol.response.AreaMailResponse;
import com.lob.protocol.response.BankAccountResponse;
import com.lob.protocol.response.CheckResponse;
import com.lob.protocol.response.JobResponse;
import com.lob.protocol.response.JobResponseList;
import com.lob.protocol.response.PostcardResponse;

import java.util.Collection;

public interface LobClient {
    public ListenableFuture<JobResponse> createJob(final JobRequest jobRequest);

    public ListenableFuture<JobResponse> getJob(final JobId id);

    public ListenableFuture<JobResponseList> getAllJobs();

    public ListenableFuture<JobResponseList> getJobs(final int count);

    public ListenableFuture<JobResponseList> getJobs(final int count, final int offset);

    public ListenableFuture<PostcardResponse> createPostcard(final PostcardRequest postcardRequest);

    public ListenableFuture<CheckResponse> createCheck(final CheckRequest checkRequest);

    public ListenableFuture<BankAccountResponse> createBankAccount(final BankAccountRequest bankAccountRequest);

    public ListenableFuture<AreaMailResponse> createAreaMail(final AreaMailRequest areaMailRequest);
}
