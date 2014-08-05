package com.lob;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lob.exception.LobException;

import com.lob.model.Address;

public class LobTest {
  static Map<String, Object> defaultAddressParams = new HashMap<String, Object>();

  public static void setUp() {
    Lob.apiKey = "test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc";

    //Address Setup
    defaultAddressParams.put("name", "Shrav Mehta");
    defaultAddressParams.put("email", "shrav@lob.com");
    defaultAddressParams.put("phone", "(408) 000-0000");
    defaultAddressParams.put("address_line1", "185 Berry Street");
    defaultAddressParams.put("address_line1", "Suite #1510");
    defaultAddressParams.put("address_city", "San Francisco");
    defaultAddressParams.put("address_state", "CA");
    defaultAddressParams.put("address_zip", "94107");
    defaultAddressParams.put("address_country", "US");
  }

  @Test
	public void testAPIBase() throws LobException {
		assertEquals("https://api.lob.com", Lob.getApiBase());
	}

  @Test
  public void testAddressCreate() throws LobException {
    Address createdAddress = Address.create(defaultAddressParams);
    System.out.println(createdAddress);
    //assertNotNull(createdAddress.getId());
  }
}
