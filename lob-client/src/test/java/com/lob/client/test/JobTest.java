package com.lob.client.test;

import com.google.common.collect.Iterables;
import com.lob.ClientUtil;
import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.id.SettingId;
import com.lob.protocol.request.AddressRequest;
import com.lob.protocol.request.JobRequest;
import com.lob.protocol.request.JobRequest.Builder;
import com.lob.protocol.request.LobObjectRequest;
import com.lob.protocol.response.AddressResponse;
import com.lob.protocol.response.CountryResponse;
import com.lob.protocol.response.CountryResponseList;
import com.lob.protocol.response.JobResponse;
import com.lob.protocol.response.JobResponseList;
import com.lob.protocol.response.LobObjectResponse;
import com.lob.protocol.response.LobObjectResponseList;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class JobTest {
    private final LobClient client = AsyncLobClient.createDefault("test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc");

    @Test
    public void testListJobs() throws Exception {
        final JobResponseList responseList = client.getAllJobs().get();
        final JobResponse response = Iterables.get(responseList, 0);

        assertTrue(response instanceof JobResponse);
        assertThat(responseList.getObject(), is("list"));
    }

    @Test
    public void testListJobsLimit() throws Exception {
        final JobResponseList responseList = client.getJobs(2).get();

        assertThat(responseList.getData().size(), is(2));
        assertThat(responseList.getObject(), is("list"));
    }

    @Test(expected = ExecutionException.class)
    public void testListJobsFail() throws Exception {
        client.getJobs(1000).get();
    }

    @Test
    public void testCreateJob() throws Exception {
        final AddressResponse address = Iterables.get(client.getAddresses(1).get(), 0);
        final LobObjectResponse lobObject = Iterables.get(client.getLobObjects(1).get(), 0);

        final JobRequest request = JobRequest.builder()
            .to(address.getId())
            .from(address.getId())
            .objectId(lobObject.getId())
            .build();

        final JobResponse response = client.createJob(request).get();
        assertTrue(response instanceof JobResponse);
        assertThat(response.getTo().getId(), is(address.getId()));
        assertThat(response.getFrom().getId(), is(address.getId()));
        assertThat(Iterables.get(response.getObjects(), 0).getId(), is(lobObject.getId()));
    }

    @Test
    public void testCreateJobInline() throws Exception {
        final JobRequest request = JobRequest.builder()
            .to(AddressRequest.builder()
                .name("Lob0")
                .line1("185 Berry Street")
                .line2("Suite 1510")
                .city("San Francisco")
                .state("CA")
                .zip("94107")
                .country("US")
                .build())
            .from(AddressRequest.builder()
                .name("Lob1")
                .line1("185 Berry Street")
                .line2("Suite 1510")
                .city("San Francisco")
                .state("CA")
                .zip("94107")
                .country("US")
                .build())
            .object(LobObjectRequest.builder()
                .name("Object0")
                .file("https://s3-us-west-2.amazonaws.com/lob-assets/test.pdf")
                .setting(SettingId.COLOR_CARD_4X6)
                .build())
            .build();

        final JobResponse response = client.createJob(request).get();
        assertTrue(response instanceof JobResponse);
        assertThat(response.getTo().getName(), is("Lob0"));
        assertThat(response.getFrom().getName(), is("Lob1"));
        assertThat(Iterables.get(response.getObjects(), 0).getName(), is("Object0"));
    }

    @Test
    public void testCreateJobMultiObject() throws Exception {
        final AddressResponse address = Iterables.get(client.getAddresses(1).get(), 0);

        final JobRequest request = JobRequest.builder()
            .to(address.getId())
            .from(address.getId())
            .object(LobObjectRequest.builder()
                .name("Test Job")
                .file(ClientUtil.fileFromResource("goblue.pdf"))
                .setting(SettingId.COLOR_DOCUMENT)
                .quantity(2)
                .build())
            .build();

        final JobResponse response = client.createJob(request).get();
        assertTrue(response instanceof JobResponse);
        assertThat(response.getTo().getId(), is(address.getId()));
        assertThat(response.getFrom().getId(), is(address.getId()));
        assertThat(Iterables.get(response.getObjects(), 0).getQuantity(), is(2));
    }

    @Test
    public void testCreateJobLocalFile() throws Exception {
        final AddressResponse address = Iterables.get(client.getAddresses(1).get(), 0);

        final JobRequest request = JobRequest.builder()
            .to(address.getId())
            .from(address.getId())
            .object(LobObjectRequest.builder()
                .name("Test Job")
                .file(ClientUtil.fileFromResource("goblue.pdf"))
                .setting(SettingId.COLOR_DOCUMENT)
                .build())
            .build();

        final JobResponse response = client.createJob(request).get();
        assertTrue(response instanceof JobResponse);
        assertThat(Iterables.get(response.getObjects(), 0).getName(), is("Test Job"));

        final JobResponse retrievedResponse = client.getJob(response.getId()).get();
        assertThat(retrievedResponse.getId(), is(response.getId()));
    }
}
