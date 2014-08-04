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
  public static void setUp() {
    Lob.apiKey = "test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc";
  }

  @Test
	public void testAPIBase() throws LobException {
		assertEquals("https://api.lob.com", Lob.getApiBase());
	}
}
