package com.lob.client.test;

import com.google.common.util.concurrent.ListenableFuture;
import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.protocol.request.JobRequest;
import com.lob.protocol.response.JobResponse;

public class AsyncLobClientIntegrationTest {
    public static void main(final String[] args) throws Exception {
        final LobClient client = AsyncLobClient.createDefault("test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc");

        final JobRequest jobRequest = JobRequest.builder()
            .name("Michigan fan letter")
            .to("adr_43769b47aed248c2")
            .from("adr_7f9ece71fbca3796")
            .objectId("obj_7ca5f80b42b6dfca")
            .build();

        final ListenableFuture<JobResponse> response = client.createJob(jobRequest);
        System.out.println(response.get());
    }
}
