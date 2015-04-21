package com.lob.client.test;

import com.google.common.collect.Iterables;
import com.lob.ClientUtil;
import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.id.AreaMailId;
import com.lob.id.ZipCode;
import com.lob.protocol.request.AreaMailRequest;
import com.lob.protocol.request.TargetType;
import com.lob.protocol.request.ZipCodeRouteRequest;
import com.lob.protocol.response.AreaMailResponse;
import com.lob.protocol.response.AreaMailResponseList;
import com.lob.protocol.response.ZipCodeRouteResponseList;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static com.lob.ClientUtil.print;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AreaMailTest {
    private final LobClient client = AsyncLobClient.createDefault("test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc");

    @Test
    public void testCreateArea() throws Exception {
        final AreaMailRequest.Builder builder = AreaMailRequest.builder()
            .name("area_test_zip")
            .front("https://s3-us-west-2.amazonaws.com/lob-assets/areafront.pdf")
            .back("https://s3-us-west-2.amazonaws.com/lob-assets/areafront.pdf")
            .routesForZips("94158", "60031")
            .targetType(TargetType.ALL)
            .fullBleed(true);

        final AreaMailResponse response = client.createAreaMail(builder.build()).get();
        assertTrue(response instanceof AreaMailResponse);

        final ZipCodeRouteRequest routeRequest = ZipCodeRouteRequest.builder().addStringZips("94158").build();
        final ZipCodeRouteResponseList route = client.getZipCodeRoutes(routeRequest).get();

        assertTrue(response.getId() instanceof AreaMailId);
        assertFalse(response.getName().isEmpty());
        assertFalse(response.getStatus().isEmpty());
        assertFalse(response.getUrl().isEmpty());
        assertThat(response.getObject(), is("area"));
        assertTrue(response.getExpectedDeliveryDate() instanceof DateTime);
        assertTrue(response.getNumAddresses() > 0);
        assertTrue(response.getPrice() instanceof Money);
        assertTrue(response.getTargetType() instanceof TargetType);
        assertFalse(response.getThumbnails().isEmpty());
        assertFalse(response.getZipCodeRouteResponses().isEmpty());
        assertTrue(response.getDateCreated() instanceof DateTime);
        assertTrue(response.getDateModified() instanceof DateTime);

        client.createAreaMail(builder.butWith().routes(route).build()).get();
        client.createAreaMail(builder.butWith().routesForZips(ZipCode.parse("94158")).build()).get();
        client.createAreaMail(builder.butWith().routesForZips(Arrays.asList(ZipCode.parse("94158"))).build()).get();
        client.createAreaMail(builder.butWith().routesForStringZips(Arrays.asList("94158")).build()).get();
    }

    @Test
    public void testCreateAreaLocalFile() throws Exception {
        final ZipCodeRouteRequest routeRequest = ZipCodeRouteRequest.builder().addStringZips("94158").build();
        final ZipCodeRouteResponseList route = client.getZipCodeRoutes(routeRequest).get();
        final File front = ClientUtil.fileFromResource("areafront.pdf");
        final File back = ClientUtil.fileFromResource("areaback.pdf");

        final AreaMailRequest request = AreaMailRequest.builder()
            .name("area_local_file")
            .front(front)
            .back(back)
            .routes(route)
            .targetType(TargetType.ALL)
            .fullBleed(true)
            .build();

        final AreaMailResponse response = print(client.createAreaMail(request).get());
        assertTrue(response instanceof AreaMailResponse);
    }

    @Test
    public void testListAreas() throws Exception {
        final AreaMailResponseList responseList = print(client.getAllAreaMails().get());
        final AreaMailResponse response = responseList.get(0);
        assertTrue(response instanceof AreaMailResponse);
        assertThat(responseList.getObject(), is("list"));
    }

    @Test
    public void testListAreasLimit() throws Exception {
        final AreaMailResponseList responseList = client.getAreaMails(2).get();
        final AreaMailResponse response = responseList.get(0);
        assertTrue(response instanceof AreaMailResponse);
        assertThat(responseList.getCount(), is(2));

    }

    @Test(expected = ExecutionException.class)
    public void testListAreasFail() throws Exception {
        client.getAreaMails(1000).get();
    }
}
