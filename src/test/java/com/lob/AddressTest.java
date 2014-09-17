package com.lob;

import com.lob.exception.LobException;
import com.lob.exception.APIException;
import com.lob.Lob;
import com.lob.model.Address;
import com.lob.model.AddressCollection;
import com.lob.model.Verify;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddressTest {
    static Map<String, Object> defaultAddressParams = new HashMap<String, Object>();

    @BeforeClass
    public static void setUp() {
      Lob.apiKey = "test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc";

      defaultAddressParams.put("name", "Test Address");
      defaultAddressParams.put("email", "support@lob.com");
      defaultAddressParams.put("phone", "1234567890");
      defaultAddressParams.put("address_line1", "123 Test Street");
      defaultAddressParams.put("address_city", "San Francisco");
      defaultAddressParams.put("address_state", "CA");
      defaultAddressParams.put("address_zip", "94107");
    }

    @Test
    public void testAddressRetrieveAll() throws LobException {
      Map<String, Object> listParams = new HashMap<String, Object>();
      listParams.put("count", 2);
      listParams.put("offset", 3);

      AddressCollection addresses = Address.all(listParams, Lob.apiKey);
      assertEquals(addresses.getData().size(), 2);
    }

    @Test(expected=APIException.class)
    public void testAddressRetrieveAllFail() throws LobException {
      Map<String, Object> listParams = new HashMap<String, Object>();
      listParams.put("count", 100000);

      AddressCollection addresses = Address.all(listParams, Lob.apiKey);
    }

    @Test
    public void testAddressCreate() throws LobException {
      Address address = Address.create(defaultAddressParams, Lob.apiKey);
      assertEquals(address.getName(), "Test Address");
      assertEquals(address.getAddress_line1(), "123 Test Street");
      assertEquals(address.getAddress_line2(), null);
      assertEquals(address.getAddress_city(), "San Francisco");
      assertEquals(address.getAddress_state(), "California");
      assertEquals(address.getAddress_zip(), "94107");
      assertEquals(address.getAddress_country(), "United States");
      assertEquals(address.getPhone(), "1234567890");
      assertEquals(address.getEmail(), "support@lob.com");

      address.setName("Test");
      address.setEmail("Test");
      address.setPhone("Test");
      address.setAddress_line1("Test");
      address.setAddress_line2("Test");
      address.setAddress_city("Test");
      address.setAddress_state("Test");
      address.setAddress_zip("Test");
      address.setAddress_country("Test");
    }

    @Test
    public void testAddressDelete() throws LobException {
      Address address = Address.create(defaultAddressParams, Lob.apiKey);
      Address.delete(address.getId(), Lob.apiKey);
    }

    @Test(expected=APIException.class)
    public void testAddressCreateFail() throws LobException {
      Map<String, Object> badAddressParams = new HashMap<String, Object>();
      badAddressParams.put("name", "Test Address");
      Address badAddress = Address.create(badAddressParams, Lob.apiKey);
    }

    @Test
    public void testAddressRetrieve() throws LobException {
      Address createdAddress = Address.create(defaultAddressParams, Lob.apiKey);
      Address retrievedAddress = Address.retrieve(createdAddress.getId(), Lob.apiKey);
      assertEquals(createdAddress.getId(), retrievedAddress.getId());
    }

    @Test(expected=APIException.class)
    public void testAddressRetrieveFail() throws LobException {
      Address address = Address.retrieve("asdf", Lob.apiKey);
    }

    @Test
    public void testAddressVerify() throws LobException {
      Map<String, Object> verifyParams = new HashMap<String, Object>();
      verifyParams.put("address_line1", "220 William T Morrissey Boulevard");
      verifyParams.put("address_city", "Boston");
      verifyParams.put("address_state", "MA");
      verifyParams.put("address_zip", "02125");

      Verify verify = Address.verify(verifyParams, Lob.apiKey);

      assertEquals(verify.getAddress().getAddress_line1(), "220 WILLIAM T MORRISSEY BLVD");
    }
}
