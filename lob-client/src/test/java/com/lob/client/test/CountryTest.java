package com.lob.client.test;

import com.google.common.collect.Iterables;
import com.lob.ClientUtil;
import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.protocol.request.AreaMailRequest;
import com.lob.protocol.request.TargetType;
import com.lob.protocol.request.ZipCodeRouteRequest;
import com.lob.protocol.response.AreaMailResponse;
import com.lob.protocol.response.AreaMailResponseList;
import com.lob.protocol.response.CountryResponse;
import com.lob.protocol.response.CountryResponseList;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CountryTest {
    private final LobClient client = AsyncLobClient.createDefault("test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc");

    @Test
    public void testCountries() throws Exception {
        final CountryResponseList responseList = client.getAllCountries().get();
        final CountryResponse response = Iterables.get(responseList.getData(), 0);

        assertTrue(response instanceof CountryResponse);
        assertThat(responseList.getObject(), is("list"));
    }
}
