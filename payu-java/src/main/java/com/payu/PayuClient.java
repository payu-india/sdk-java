package com.payu;

public class PayuClient extends BasePayu {
  public Hasher hasher;

  private static PayuClient instance = null;

  private PayuClient(String key, String salt) {
    super(key, salt);
    hasher = new Hasher(key, salt);
  }

  public static PayuClient init(String key, String salt) {
      // To ensure only one instance is created
      if (instance == null) {
        instance = new PayuClient(key, salt);
      }
      return instance;
  }
}
