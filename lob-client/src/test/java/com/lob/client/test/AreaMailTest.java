package com.lob.client.test;

import com.google.common.collect.Iterables;
import com.lob.ClientUtil;
import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.id.AreaMailId;
import com.lob.protocol.request.AreaMailRequest;
import com.lob.protocol.request.TargetType;
import com.lob.protocol.request.ZipCodeRouteRequest;
import com.lob.protocol.response.AreaMailResponse;
import com.lob.protocol.response.AreaMailResponseList;
import com.lob.protocol.response.ZipCodeRouteResponseList;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AreaMailTest {
    private final LobClient client = AsyncLobClient.createDefault("test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc");
    private ZipCodeRouteResponseList route;

    @Before
    public void setUp() throws Exception {
        final ZipCodeRouteRequest request = ZipCodeRouteRequest.builder().addStringZip("94158").build();
        this.route = client.getZipCodeRoutes(request).get();
    }

    @Test
    public void testCreateAreaWithZip() throws Exception {
        final AreaMailRequest request = AreaMailRequest.builder()
            .name("area_test_zip")
            .front("https://s3-us-west-2.amazonaws.com/lob-assets/areafront.pdf")
            .back("https://s3-us-west-2.amazonaws.com/lob-assets/areafront.pdf")
            .routesForZips("94158", "60031")
            .targetType(TargetType.ALL)
            .fullBleed(true)
            .build();

        final AreaMailResponse response = client.createAreaMail(request).get();
        assertTrue(response instanceof AreaMailResponse);
    }

    @Test
    public void testCreateAreaWithRoute() throws Exception {
        final AreaMailRequest request = AreaMailRequest.builder()
            .name("area_test_route")
            .front("https://s3-us-west-2.amazonaws.com/lob-assets/areafront.pdf")
            .back("https://s3-us-west-2.amazonaws.com/lob-assets/areafront.pdf")
            .routes(this.route)
            .targetType(TargetType.ALL)
            .fullBleed(true)
            .build();

        final AreaMailResponse response = client.createAreaMail(request).get();
        assertTrue(response instanceof AreaMailResponse);
    }

    @Test
    public void testCreateAreaLocalFile() throws Exception {
        final File front = ClientUtil.fileFromResource("areafront.pdf");
        final File back = ClientUtil.fileFromResource("areaback.pdf");

        final AreaMailRequest request = AreaMailRequest.builder()
            .name("area_local_file")
            .front(front)
            .back(back)
            .routes(this.route)
            .targetType(TargetType.ALL)
            .fullBleed(true)
            .build();

        final AreaMailResponse response = client.createAreaMail(request).get();
        assertTrue(response instanceof AreaMailResponse);
    }

    @Test
    public void testListAreas() throws Exception {
        final AreaMailResponseList responseList = client.getAllAreaMails().get();
        final AreaMailResponse response = Iterables.get(responseList.getData(), 0);
        assertTrue(response instanceof AreaMailResponse);
        assertThat(responseList.getObject(), is("list"));
    }

    @Test
    public void testListAreasLimit() throws Exception {
        final AreaMailResponseList responseList = client.getAreaMails(2).get();
        final AreaMailResponse response = Iterables.get(responseList.getData(), 0);
        assertTrue(response instanceof AreaMailResponse);
        assertThat(responseList.getData().size(), is(2));

    }

    @Test(expected = ExecutionException.class)
    public void testListAreasFail() throws Exception {
        client.getAreaMails(1000).get();
    }
}
