package com.payu;

import java.util.regex.Pattern;

class Validator {
  private static final String EMAIL_REGEX = "^(?=.{6,254}$)[A-Za-z0-9_\\-\\.]{1,64}\\@[A-Za-z0-9_\\-\\.]+\\.[A-Za-z]{2,}$";
  private static final String AMOUNT_REGEX = "^\\d+(\\.\\d{1,2})?$";

  public static void validateTnxId(String txnId) {
    if (txnId != null && txnId.length() > 25) {
      throw new PayuException("txnId length should be less than equal to 25");
    }
  }

  public static void validateAmount(String amount) {
    if (amount == null) {
      throw new PayuException("amount cannot be null");
    }
    Pattern pattern = Pattern.compile(AMOUNT_REGEX);
    if (!pattern.matcher(amount).matches()) {
      throw new PayuException("amount should contain digits with upto 2 decimal places");
    }
  }

  public static void validateProductInfo(String productInfo) {
    if (productInfo != null && productInfo.length() > 100) {
      throw new PayuException("productInfo length should be less than equal to 100");
    }
  }

  public static void validateFirstName(String firstName) {
    if (firstName != null && firstName.length() > 60) {
      throw new PayuException("firstName length should be less than equal to 60");
    }
  }

  public static void validateEmail(String email) {
    if (email != null && email.length() > 50) {
      throw new PayuException("email length should be less than equal to 50");
    }
    Pattern pattern = Pattern.compile(EMAIL_REGEX);
    if (!pattern.matcher(email).matches()) {
      throw new PayuException("invalid email id");
    }
  }

  public static void validateUdf(String udf) {
    if (udf != null && udf.length() > 255) {
      throw new PayuException("udf length should be less than equal to 255");
    }
  }
}
