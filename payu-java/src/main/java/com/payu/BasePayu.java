package com.payu;

/**
 * Base class to be extended by all classes.
 */
public class BasePayu {
  String key;
  String salt;
  public BasePayu(String key, String salt) {
    this.key = key;
    this.salt = salt;
  }
}
