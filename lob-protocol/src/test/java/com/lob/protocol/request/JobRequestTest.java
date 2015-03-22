package com.lob.protocol.request;

import com.lob.id.AddressId;
import com.lob.id.LobObjectId;
import com.lob.id.SettingId;
import com.neovisionaries.i18n.CountryCode;
import org.junit.Test;

import java.util.Arrays;

public class JobRequestTest {

    @Test
    public void paramMapTest() throws Exception {
        final AddressRequest to = AddressRequest.builder()
            .name("eric")
            .email("email@example.com")
            .line1("123 main street")
            .city("Grand Rapids")
            .state("MI")
            .zip("49404")
            .country(CountryCode.US)
            .build();

        final LobObjectRequest.Builder objectABuilder = LobObjectRequest.builder()
            .name("myObject")
            .file("http://example.com/objectA")
            .setting(SettingId.BLACK_AND_WHITE_DOCUMENT)
            .quantity(2);

        final LobObjectRequest.Builder objectBBuilder = objectABuilder.butWith()
            .file("http://example.com/objectB");

        final JobRequest request = JobRequest.builder()
            .to(to)
            .from(AddressId.parse("adr_aaaaaaaaaaaaaaaa"))
            .objects(Arrays.asList(
                objectABuilder.build(),
                objectBBuilder.build()))
            .build();
        System.out.println(request.toParamMap());
    }
}
