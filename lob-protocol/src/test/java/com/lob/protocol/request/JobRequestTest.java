package com.lob.protocol.request;

import com.lob.id.AddressId;
import com.lob.id.LobObjectId;
import org.junit.Test;

import java.util.Arrays;

public class JobRequestTest {

    @Test
    public void paramMapTest() throws Exception {
        final JobRequest request = JobRequest.builder()
            .to(AddressId.parse("adr_aaaaaaaaaaaaaaaa"))
            .from(AddressId.parse("adr_aaaaaaaaaaaaaaaa"))
            .objectIds(Arrays.asList(LobObjectId.parse("obj_aaaaaaaaaaaaaaaa")))
            .build();
        System.out.println(request.toParamMap());
    }
}
