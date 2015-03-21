package com.lob.client;

import com.google.common.util.concurrent.ListenableFuture;
import com.lob.protocol.request.JobRequest;
import com.lob.protocol.response.JobResponse;

public interface LobClient {
    public ListenableFuture<JobResponse> createJob(final JobRequest jobRequest);
}
