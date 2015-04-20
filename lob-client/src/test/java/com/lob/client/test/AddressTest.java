package com.lob.client.test;

import com.google.common.collect.Iterables;
import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.id.AddressId;
import com.lob.protocol.request.AddressRequest;
import com.lob.protocol.request.VerifyAddressRequest;
import com.lob.protocol.response.AddressDeleteResponse;
import com.lob.protocol.response.AddressResponse;
import com.lob.protocol.response.AddressResponseList;
import com.lob.protocol.response.VerifyAddressResponse;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AddressTest {
    private final LobClient client = AsyncLobClient.createDefault("test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc");

    @Test
    public void testListAddresses() throws Exception {
        final AddressResponseList addresses = client.getAllAddresses().get();
        final AddressResponse response = Iterables.get(addresses.getData(), 0);
        assertTrue(response instanceof AddressResponse);
        assertThat(addresses.getObject(), is("list"));

    }

    @Test
    public void testListAddressesLimit() throws Exception {
        final AddressResponseList addresses = client.getAddresses(2).get();
        final AddressResponse response = Iterables.get(addresses.getData(), 0);
        assertTrue(response instanceof AddressResponse);
        assertThat(addresses.getData().size(), is(2));
    }

    @Test(expected = ExecutionException.class)
    public void testListAddressesFail() throws Exception {
        client.getAddresses(1000).get();
    }

    @Test
    public void testCreateAddress() throws Exception {
        final AddressRequest request = AddressRequest.builder()
            .name("Lob")
            .line1("185 Berry Street")
            .line2("Suite 1510")
            .city("San Francisco")
            .state("CA")
            .zip("94107")
            .country("US")
            .build();

        final AddressResponse response = client.createAddress(request).get();
        assertTrue(response instanceof AddressResponse);
        assertThat(response.getName(), is("Lob"));
    }

    @Test
    public void testRetrieveAddress() throws Exception {
        final AddressResponse response = Iterables.get(client.getAllAddresses().get().getData(), 0);
        assertTrue(response instanceof AddressResponse);
    }

    @Test
    public void testDeleteAddress() throws Exception {
        final AddressId id = Iterables.get(client.getAllAddresses().get().getData(), 0).getId();
        final AddressDeleteResponse response = client.deleteAddress(id).get();
        assertThat(response.getId(), is(id));
    }

    @Test
    public void testAddressVerification() throws Exception {
        final VerifyAddressRequest request = VerifyAddressRequest.builder()
            .line1("220 William T Morrissey")
            .city("Boston")
            .state("MA")
            .zip("02125")
            .country("US")
            .build();

        final VerifyAddressResponse response = client.verifyAddress(request).get();
        assertThat(response.getLine1(), is("220 WILLIAM T MORRISSEY BLVD"));
    }
}
