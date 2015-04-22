package com.lob.client.test;

import com.google.common.collect.Iterables;
import com.lob.ClientUtil;
import com.lob.Or;
import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.protocol.request.AddressRequest;
import com.lob.protocol.request.LobParam;
import com.lob.protocol.request.PostcardRequest;
import com.lob.protocol.response.AddressResponse;
import com.lob.protocol.response.PostcardResponse;
import com.lob.protocol.response.PostcardResponseList;
import org.joda.money.Money;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.ExecutionException;

import static com.lob.Util.print;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PostcardTest {
    private final LobClient client = AsyncLobClient.createDefault("test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc");

    @Test
    public void testListPostcards() throws Exception {
        final PostcardResponseList responseList = print(client.getAllPostcards().get());
        final PostcardResponse response = print(responseList.get(0));

        assertTrue(response instanceof PostcardResponse);
        assertThat(responseList.getObject(), is("list"));
    }

    @Test
    public void testListPostcardsLimit() throws Exception {
        final PostcardResponseList responseList = client.getPostcards(2).get();

        assertThat(responseList.getCount(), is(2));
        assertThat(responseList.getObject(), is("list"));

        assertThat(client.getPostcards(1, 2).get().getCount(), is(1));
    }

    @Test(expected = ExecutionException.class)
    public void testListPostcardsFail() throws Exception {
        client.getPostcards(1000).get();
    }

    @Test
    public void testCreatePostcard() throws Exception {
        final AddressResponse address = Iterables.get(client.getAddresses(1).get(), 0);

        final PostcardRequest.Builder builder = PostcardRequest.builder()
            .name("test postcard")
            .to(address.getId())
            .from(address.getId())
            .front("https://s3-us-west-2.amazonaws.com/lob-assets/test.pdf")
            .back("https://s3-us-west-2.amazonaws.com/lob-assets/test.pdf");

        final PostcardResponse response = client.createPostcard(builder.build()).get();
        assertTrue(response instanceof PostcardResponse);
        assertThat(response.getTo().getId(), is(address.getId()));
        assertThat(response.getFrom().getId(), is(address.getId()));

        print(builder.butWith().name("other postcard").build());
        print(builder.butWith().message("message"));

        print(response.getMessage());
        assertTrue(response.getPrice() instanceof Money);
        assertFalse(response.getStatus().isEmpty());
        print(response.getSetting());

        final PostcardRequest request = print(builder.build());
        assertNull(request.getMessage());
        assertFalse(request.getName().isEmpty());
        assertTrue(request.getBack() instanceof LobParam);
        assertTrue(request.getFrom() instanceof Or);
        assertTrue(request.getFront() instanceof LobParam);
        assertNull(request.getSetting());
        assertTrue(request.getTo() instanceof Or);
    }

    @Test
    public void testCreatePostcardInline() throws Exception {
        final PostcardRequest request = PostcardRequest.builder()
            .to(
                AddressRequest.builder()
                    .name("Lob0")
                    .line1("185 Berry Street")
                    .line2("Suite 1510")
                    .city("San Francisco")
                    .state("CA")
                    .zip("94107")
                    .country("US")
                    .build())
            .from(
                AddressRequest.builder()
                    .name("Lob1")
                    .line1("185 Berry Street")
                    .line2("Suite 1510")
                    .city("San Francisco")
                    .state("CA")
                    .zip("94107")
                    .country("US")
                    .build())
            .front("https://s3-us-west-2.amazonaws.com/lob-assets/test.pdf")
            .back("https://s3-us-west-2.amazonaws.com/lob-assets/test.pdf")
            .build();

        final PostcardResponse response = client.createPostcard(request).get();
        assertTrue(response instanceof PostcardResponse);
        assertThat(response.getTo().getName(), is("Lob0"));
        assertThat(response.getFrom().getName(), is("Lob1"));
    }

    @Test
    public void testCreatePostcardLocalFile() throws Exception {
        final AddressResponse address = Iterables.get(client.getAddresses(1).get(), 0);
        final File file = ClientUtil.fileFromResource("postcardfront.pdf");

        final PostcardRequest request = PostcardRequest.builder()
            .to(address.getId())
            .from(address.getId())
            .front(file)
            .back(file)
            .fullBleed(true)
            .build();

        final PostcardResponse response = client.createPostcard(request).get();
        assertTrue(response instanceof PostcardResponse);

        final PostcardResponse retrievedResponse = client.getPostcard(response.getId()).get();
        assertThat(retrievedResponse.getId(), is(response.getId()));
    }
}
