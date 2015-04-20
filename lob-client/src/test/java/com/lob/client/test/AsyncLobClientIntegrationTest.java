package com.lob.client.test;

import ch.qos.logback.classic.Level;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.common.util.concurrent.ListenableFuture;
import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.id.LobObjectId;
import com.lob.id.SettingId;
import com.lob.id.ZipCode;
import com.lob.id.ZipCodeRouteId;
import com.lob.protocol.request.AddressRequest;
import com.lob.protocol.request.AddressRequest.Builder;
import com.lob.protocol.request.AreaMailRequest;
import com.lob.protocol.request.BankAccountRequest;
import com.lob.protocol.request.JobRequest;
import com.lob.protocol.request.LobObjectRequest;
import com.lob.protocol.request.PostcardRequest;
import com.lob.protocol.request.TargetType;
import com.lob.protocol.request.VerifyAddressRequest;
import com.lob.protocol.request.ZipCodeRouteRequest;
import com.lob.protocol.response.AddressResponse;
import com.lob.protocol.response.AreaMailResponse;
import com.lob.protocol.response.BankAccountResponse;
import com.lob.protocol.response.JobResponse;
import com.lob.protocol.response.JobResponseList;
import com.lob.protocol.response.LobObjectResponse;
import com.lob.protocol.response.PostcardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class AsyncLobClientIntegrationTest {
    public static void main(final String[] args) throws Exception {
        ((ch.qos.logback.classic.Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).setLevel(Level.INFO);

        final LobClient client = AsyncLobClient.createDefault("test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc");

        System.out.println(client.verifyAddress(VerifyAddressRequest.builder()
            .line1("185 Berry Street")
            .line2("Suite 1510")
            .city("San Francisco")
            .state("CA")
            .zip("94107")
            .country("US")
            .build()).get());

        final VerifyAddressRequest verifyRequest = VerifyAddressRequest.builder()
            .line1("220 William T Morrissey Boulevard")
            .city("boston")
            .state("MA")
            .zip(ZipCode.parse("02125"))
            .build();
        System.out.println("verify address " + client.verifyAddress(verifyRequest).get());


        System.out.println("get setting " + client.getSetting(SettingId.parse(100)).get());
        final LobObjectRequest.Builder objectRequest = LobObjectRequest.builder()
            .file("https://lob.com/goblue.pdf")
            .name("myObject")
            .setting(SettingId.BLACK_AND_WHITE_DOCUMENT)
            .template(true);
        final LobObjectRequest otherObject = objectRequest.butWith().name("other name").build();

        final JobRequest.Builder jobRequestBuilder = JobRequest.builder()
            .name("Michigan fan letter")
            .to("adr_43769b47aed248c2")
            .from("adr_7f9ece71fbca3796")
            .objectId("obj_7ca5f80b42b6dfca");

        System.out.println("object request: " + objectRequest.build().getLobParams());
        final LobObjectResponse objectResponse = client.createLobObject(objectRequest.build()).get();
        System.out.println("create object " + objectResponse);

        final JobRequest.Builder multiIdJobBuilder = jobRequestBuilder.butWith().objectIds(objectResponse.getId(), LobObjectId.parse("obj_7ca5f80b42b6dfca"));
        System.out.println("multi object id job " + client.createJob(multiIdJobBuilder.build()).get());

        final JobRequest.Builder multiUrlJobBuilder = jobRequestBuilder.butWith().objects(objectRequest.build(), otherObject);
        System.out.println("multi object url job " + client.createJob(multiUrlJobBuilder.build()).get());

        final File file = fileFromResource("goblue.pdf");
        final LobObjectRequest fileRequest = objectRequest.butWith().name("file object").file(file).setting(SettingId.BLACK_AND_WHITE_DOCUMENT).build();
        System.out.println(fileRequest);
        System.out.println("file object: " + client.createLobObject(fileRequest).get());

        final JobRequest multiFileJob = multiIdJobBuilder.butWith().objects(objectRequest.build(), fileRequest).build();
        System.out.println("multi object file job request: " + multiFileJob.getLobParams());
        System.out.println("multi object file job " + client.createJob(multiFileJob).get());

        final ListenableFuture<JobResponse> jobResponse = client.createJob(jobRequestBuilder.build());
        System.out.println(jobResponse.get());

        final Builder addrA = AddressRequest.builder()
            .name("eric")
            .line1("123 main st")
            .city("san francisco")
            .state("ca")
            .zip(ZipCode.parse("94107"))
            .country("us");
        final Builder addrB = addrA.butWith().name("peter").line1("850 Berry");

        final PostcardRequest.Builder postcardRequestBuilder = PostcardRequest.builder()
            .name("demo postcard")
            .to(addrA.build())
            .from(addrB.build())
            .fullBleed(true)
            .front("https://lob.com/postcardfront.pdf")
            .back("https://lob.com/postcardback.pdf");

        final PostcardRequest postcardFile = postcardRequestBuilder.butWith().front(fileFromResource("postcardfront.pdf")).build();
        System.out.println("postcard with file front: " + client.createPostcard(postcardFile).get());

        final PostcardRequest postcardRequest = postcardRequestBuilder.build();
        final PostcardResponse postcardResponse = client.createPostcard(postcardRequest).get();
        System.out.println(postcardResponse);

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

        /*
        final CheckRequest.Builder checkRequestBuilder = CheckRequest.builder()
            .name("test check")
            .to(addrA.build())
            .amount(Money.of(CurrencyUnit.USD, 20.00))
            .bankAccount(BankAccountId.parse("bank_6250512bd678b6a"))
            .memo("rent");

        final CheckRequest fileLogoCheckRequest = checkRequestBuilder.butWith().logo(fileFromResource("lobCheckLogo.png")).build();
        System.out.println("check response with file logo: " + client.createCheck(fileLogoCheckRequest).get());
        final CheckRequest checkRequest = checkRequestBuilder.build();

        final CheckResponse checkResponse = client.createCheck(checkRequest).get();
        System.out.println(checkResponse);
        */

        final AreaMailRequest.Builder areaMailRequestBuilder = AreaMailRequest.builder()
            .name("sample sam")
            .front("https://lob.com/areafront.pdf")
            .back("https://lob.com/areaback.pdf")
            .routesForIds(Arrays.asList(ZipCodeRouteId.parse("94158-C001"), ZipCodeRouteId.parse("94107-C031")))
            .targetType(TargetType.ALL)
            .fullBleed(true);

        final AreaMailRequest fileAreaMailRequest = areaMailRequestBuilder.butWith().front(fileFromResource("areafront.pdf")).build();
        System.out.println("area mail with file front: " + client.createAreaMail(fileAreaMailRequest).get());

        final AreaMailRequest areaMailRequest = areaMailRequestBuilder.build();
        System.out.println(areaMailRequest.getLobParams());
        final AreaMailResponse areaMailResponse = client.createAreaMail(areaMailRequest).get();
        System.out.println(areaMailResponse);

        System.out.println("Result of getting job: " + client.getJob(jobResponse.get().getId()).get());
        final JobResponseList jobResponses = client.getAllJobs().get();
        System.out.println("Result of getting all jobs: " + jobResponses);

        System.out.println("Result of getting jobs, count 1: " + client.getJobs(1).get());
        System.out.println("Result of getting jobs, count 1, offset 1: " + client.getJobs(1, 1).get());

        final AddressResponse addressResponse = client.createAddress(addrA.build()).get();
        System.out.println("create address " + addressResponse);
        System.out.println("get address " + client.getAddress(addressResponse.getId()).get());
        System.out.println("get all addresses " + client.getAllAddresses().get());
        System.out.println("get all addresses, count 1 " + client.getAddresses(1).get());
        System.out.println("get all addresses, count 1, offset 1 " + client.getAddresses(1, 1).get());
        System.out.println("delete address " + client.deleteAddress(addressResponse.getId()).get());

        System.out.println("get object " + client.getLobObject(objectResponse.getId()).get());
        System.out.println("get all objects " + client.getAllLobObjects().get());
        System.out.println("get all objects, count 1 " + client.getLobObjects(1).get());
        System.out.println("get all objects, count 1, offset 1 " + client.getLobObjects(1, 1).get());
        System.out.println("delete object " + client.deleteLobObject(objectResponse.getId()).get());

        System.out.println("get all settings " + client.getAllSettings().get());

        System.out.println("get all services " + client.getAllServices().get());

        System.out.println("get postcard " + client.getPostcard(postcardResponse.getId()).get());
        System.out.println("get all postcards " + client.getAllPostcards().get());
        System.out.println("get all postcards, count 1 " + client.getPostcards(1).get());
        System.out.println("get all postcards, count 1, offset 1 " + client.getPostcards(1, 1).get());

        //System.out.println("get check " + client.getCheck(checkResponse.getId()).get());
        System.out.println("get all checks " + client.getAllChecks().get());
        System.out.println("get all checks, count 1 " + client.getChecks(1).get());
        System.out.println("get all checks, count 1, offset 1 " + client.getChecks(1, 1).get());

        System.out.println("get bank " + client.getBankAccount(bankAccountResponse.getId()).get());
        System.out.println("get all banks " + client.getAllBankAccounts().get());
        System.out.println("get all banks, count 1 " + client.getBankAccounts(1).get());
        System.out.println("get all banks, count 1, offset 1 " + client.getBankAccounts(1, 1).get());
        System.out.println("delete bank account" + client.deleteBankAccount(bankAccountResponse.getId()).get());

        System.out.println("get area mail " + client.getAreaMail(areaMailResponse.getId()).get());
        System.out.println("get all area mails " + client.getAllAreaMails().get());
        System.out.println("get all area mails, count 1 " + client.getAreaMails(1).get());
        System.out.println("get all area mails, count 1, offset 1 " + client.getAreaMails(1, 1).get());

        final ZipCodeRouteRequest zipCodeRouteRequest = ZipCodeRouteRequest.builder().addStringZips("48168", "94158").build();
        System.out.println("get zip code routes " + client.getZipCodeRoutes(zipCodeRouteRequest).get());

        System.out.println("countries " + client.getAllCountries().get());
        System.out.println("states " + client.getAllStates().get());
        System.out.println("packagings " + client.getAllPackagings().get());
    }

    private static File fileFromResource(final String resource) throws IOException {
        final File file = File.createTempFile("/tmp", ".tmp");
        file.deleteOnExit();
        Resources.asByteSource(Resources.getResource(resource)).copyTo(Files.asByteSink(file));
        return file;
    }
}
