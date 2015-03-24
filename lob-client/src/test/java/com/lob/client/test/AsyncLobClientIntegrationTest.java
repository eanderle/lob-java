package com.lob.client.test;

import com.google.common.util.concurrent.ListenableFuture;
import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.protocol.request.AddressRequest;
import com.lob.protocol.request.AddressRequest.Builder;
import com.lob.protocol.request.BankAccountRequest;
import com.lob.protocol.request.CheckRequest;
import com.lob.protocol.request.JobRequest;
import com.lob.protocol.request.PostcardRequest;
import com.lob.protocol.response.BankAccountResponse;
import com.lob.protocol.response.JobResponse;
import com.lob.protocol.response.PostcardResponse;
import com.neovisionaries.i18n.CountryCode;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

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

        final Builder addrA = AddressRequest.builder()
            .name("eric")
            .line1("123 main st")
            .city("san francisco")
            .state("ca")
            .zip("94107")
            .country(CountryCode.US);
        final Builder addrB = addrA.butWith().name("peter").line1("850 Berry");

        final PostcardRequest postcardRequest = PostcardRequest.builder()
            .name("demo postcard")
            .to(addrA.build())
            .from(addrB.build())
            .fullBleed(true)
            .front("https://lob.com/postcardfront.pdf")
            .back("https://lob.com/postcardback.pdf")
            .build();

        final ListenableFuture<PostcardResponse> postcardResponse = client.createPostcard(postcardRequest);
        System.out.println(postcardResponse.get());

        final BankAccountRequest bankAccountRequest = BankAccountRequest.builder()
            .name("my bank account")
            .bankAddress(addrA.build())
            .accountAddress(addrB.build())
            .routingNumber("322271627")
            .accountNumber("123456789")
            .signatory("John Doe")
            .build();

        final BankAccountResponse bankAccountResponse = client.createBankAccount(bankAccountRequest).get();
        System.out.println(bankAccountResponse);

        final CheckRequest checkRequest = CheckRequest.builder()
            .name("test check")
            .to(addrA.build())
            .amount(Money.of(CurrencyUnit.USD, 20.00))
            .bankAccount(bankAccountResponse.getId())
            .memo("rent")
            .build();

        System.out.println(client.createCheck(checkRequest).get());

    }
}
