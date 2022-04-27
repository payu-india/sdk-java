package com.payu;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for Hash class.
 */
class HashTest {
  private Hasher hash = new Hasher("yourMerchantKey", "yourMerchantSalt");
  private HasherParams.Builder builder;


  @BeforeEach
  void init() {
    builder = new HasherParams.Builder()
      .setTxnId("5512244")
      .setAmount("1000")
      .setProductInfo("mobile")
      .setFirstName("Jacob")
      .setEmail("test@payu.in");
  }

  @Test
  void testGenerateHash() {
    String hashStr =  hash.generateHash(builder.build());
    assertEquals(hashStr, "put hash to test");
  }

  @Test
  void testGenerateHashWithUdf() {
    builder.setUdf1("user defined param");
    String hashStr =  hash.generateHash(builder.build());
    assertEquals(hashStr, "put hash to test");
  }


  @Test
  void testVerifyHash() {
    boolean isVerifiedFalse =  hash.validateHash(
      "<reverse_hash>",
      "success",
      builder.build()
    );
    assertEquals(isVerifiedFalse, false);
    boolean isVerifiedTrue =  hash.validateHash(
      "put hash to test",
      "success",
      builder.build()
    );
    assertEquals(isVerifiedTrue, true);
  }

  @Test
   void testHashParamsValidation() {
    Exception exception = assertThrows(PayuException.class, () -> {
      builder.setAmount("100.00");
      builder.build();
    });
    assertTrue(exception.getMessage()
      .equals("amount should contain digits with upto 2 decimal places")
    );

    exception = assertThrows(PayuException.class, () -> {
      builder.setEmail("test@invalid");
      builder.build();
    });
    assertTrue(exception.getMessage()
      .equals("invalid email id")
    );
  }
}
