package com.lob.client.test;

import com.google.common.collect.Iterables;
import com.lob.Lob;
import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.protocol.response.CountryResponse;
import com.lob.protocol.response.CountryResponseList;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LobTest {
    private final static String baseUrl = Lob.getBaseUrl();
    private final static String apiVersion = Lob.getApiVersion();

    @AfterClass
    public static void tearDown() throws Exception {
        Lob.setBaseUrl(baseUrl);
        Lob.setApiVersion(apiVersion);
    }

    @Test
    public void testSetApiVersion() throws Exception {
        Lob.setApiVersion("lol");
        assertThat(Lob.getApiVersion(), is("lol"));
    }

    @Test
    public void testSetBaseUrl() throws Exception {
        Lob.setBaseUrl("lol");
        assertThat(Lob.getBaseUrl(), is("lol"));
    }

    @Test(expected = ExecutionException.class)
    public void testInvalidAuth() throws Exception {
        Lob.setBaseUrl(baseUrl);
        Lob.setApiVersion(apiVersion);

        AsyncLobClient.createDefault("lol").getAddresses().get();
    }
}
