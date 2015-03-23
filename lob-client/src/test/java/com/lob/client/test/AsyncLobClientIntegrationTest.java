package com.lob.client.test;

import com.google.common.util.concurrent.ListenableFuture;
import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.protocol.request.AddressRequest;
import com.lob.protocol.request.AddressRequest.Builder;
import com.lob.protocol.request.JobRequest;
import com.lob.protocol.request.PostcardRequest;
import com.lob.protocol.response.JobResponse;
import com.lob.protocol.response.PostcardResponse;
import com.neovisionaries.i18n.CountryCode;

public class AsyncLobClientIntegrationTest {
    public static void main(final String[] args) throws Exception {
        final LobClient client = AsyncLobClient.createDefault("test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc");

        final JobRequest jobRequest = JobRequest.builder()
            .name("Michigan fan letter")
            .to("adr_43769b47aed248c2")
            .from("adr_7f9ece71fbca3796")
            .objectId("obj_7ca5f80b42b6dfca")
            .build();

        final ListenableFuture<JobResponse> jobResponse = client.createJob(jobRequest);
        System.out.println(jobResponse.get());

        final Builder toAddrBuilder = AddressRequest.builder()
            .name("eric")
            .line1("123 main st")
            .city("san francisco")
            .state("ca")
            .zip("94107")
            .country(CountryCode.US);

        final PostcardRequest postcardRequest = PostcardRequest.builder()
            .name("demo postcard")
            .to(toAddrBuilder.build())
            .from(toAddrBuilder.butWith().name("peter").line1("850 Berry").build())
            .fullBleed(true)
            .front("https://lob.com/postcardfront.pdf")
            .back("https://lob.com/postcardback.pdf")
            .build();

        final ListenableFuture<PostcardResponse> postcardResponse = client.createPostcard(postcardRequest);
        System.out.println(postcardResponse.get());
    }
}
