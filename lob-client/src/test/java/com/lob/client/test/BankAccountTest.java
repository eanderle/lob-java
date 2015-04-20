package com.lob.client.test;

import com.google.common.collect.Iterables;
import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.protocol.request.AddressRequest;
import com.lob.protocol.request.BankAccountRequest;
import com.lob.protocol.response.AddressResponse;
import com.lob.protocol.response.BankAccountDeleteResponse;
import com.lob.protocol.response.BankAccountResponse;
import com.lob.protocol.response.BankAccountResponseList;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BankAccountTest {
    private final LobClient client = AsyncLobClient.createDefault("test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc");

    @Test
    public void testListBankAccounts() throws Exception {
        final BankAccountResponseList responseList = client.getAllBankAccounts().get();
        final BankAccountResponse response = Iterables.get(responseList.getData(), 0);

        assertTrue(response instanceof BankAccountResponse);
        assertThat(responseList.getObject(), is("list"));
    }

    @Test
    public void testListBankAccountsLimit() throws Exception {
        final BankAccountResponseList responseList = client.getBankAccounts(2).get();
        final BankAccountResponse response = Iterables.get(responseList.getData(), 0);

        assertTrue(response instanceof BankAccountResponse);
        assertThat(responseList.getData().size(), is(2));

    }

    @Test(expected = ExecutionException.class)
    public void testListBankAccountsFail() throws Exception {
        client.getBankAccounts(1000).get();
    }

    @Test
    public void testCreateBankAccount() throws Exception {
        final AddressResponse address = Iterables.get(client.getAddresses(1).get().getData(), 0);
        final BankAccountRequest request = BankAccountRequest.builder()
            .routingNumber("122100024")
            .accountNumber("123456789")
            .bankAddress(address.getId())
            .accountAddress(address.getId())
            .signatory("John Doe")
            .build();

        final BankAccountResponse response = client.createBankAccount(request).get();
        assertTrue(response instanceof BankAccountResponse);
        assertThat(response.getAccountAddress().getId(), is(address.getId()));
    }

    @Test
    public void testCreateBankAccountInlineAddresses() throws Exception {
        final BankAccountRequest request = BankAccountRequest.builder()
            .routingNumber("122100024")
            .accountNumber("123456789")
            .bankAddress(AddressRequest.builder()
                    .name("Lob0")
                    .line1("185 Berry Street")
                    .line2("Suite 1510")
                    .city("San Francisco")
                    .state("CA")
                    .zip("94107")
                    .country("US")
                    .build())
            .accountAddress(AddressRequest.builder()
                    .name("Lob1")
                    .line1("185 Berry Street")
                    .line2("Suite 1510")
                    .city("San Francisco")
                    .state("CA")
                    .zip("94107")
                    .country("US")
                    .build())
            .signatory("John Doe")
            .build();

        final BankAccountResponse response = client.createBankAccount(request).get();
        assertTrue(response instanceof BankAccountResponse);
        assertThat(response.getBankAddress().getName(), is("Lob0"));
        assertThat(response.getAccountAddress().getName(), is("Lob1"));

        final BankAccountResponse retrievedResponse = client.getBankAccount(response.getId()).get();
        assertThat(retrievedResponse.getId(), is(response.getId()));
    }

    @Test
    public void testDeleteBankAccount() throws Exception {
        final BankAccountResponse response = Iterables.get(client.getAllBankAccounts().get().getData(), 0);
        final BankAccountDeleteResponse deleteResponse = client.deleteBankAccount(response.getId()).get();
        assertThat(deleteResponse.getId(), is(response.getId()));
    }
}
