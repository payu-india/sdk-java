package com.payu;

public class HasherParams {
  private String txnId;
  private String amount;
  private String productInfo;
  private String firstName;
  private String email;
  private String udf1;
  private String udf2;
  private String udf3;
  private String udf4;
  private String udf5;
  private String additionalCharges;

  private HasherParams(Builder builder) {
    this.txnId = builder.txnId;
    this.amount = builder.amount;
    this.productInfo = builder.productInfo;
    this.firstName = builder.firstName;
    this.email = builder.email;
    this.udf1 = builder.udf1;
    this.udf2 = builder.udf2;
    this.udf3 = builder.udf3;
    this.udf4 = builder.udf4;
    this.udf5 = builder.udf5;
    this.additionalCharges = builder.additionalCharges;
  }

  public String getTxnId() {
    return this.txnId;
  }

  public String getAmount() {
    return this.amount;
  }

  public String getProductInfo() {
    return this.productInfo;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getEmail() {
    return this.email;
  }

  public String getUdf1() {
    return this.udf1;
  }
  
  public String getUdf2() {
    return this.udf2;
  }

  public String getUdf3() {
    return this.udf3;
  }

  public String getUdf4() {
    return this.udf4;
  }

  public String getUdf5() {
    return this.udf5;
  }

  public String getAdditionalCharges() {
    return this.additionalCharges;
  }
  public static class Builder {
    private String txnId;
    private String amount;
    private String productInfo;
    private String firstName;
    private String email;
    private String udf1 = "";
    private String udf2 = "";
    private String udf3 = "";
    private String udf4 = "";
    private String udf5 = "";
    private String additionalCharges = null;

    /**
     * set txn id
     *
     * @param txnId           transaction id
     * @throws PayuException  if txnid is invalid
     * @return                Builder
    */
    public Builder setTxnId(String txnId) {
      Validator.validateTnxId(txnId);
      this.txnId = txnId;
      return this;
    }

    /**
     * set amount
     *
     * @param amount          amount
     * @throws PayuException  if amount is invalid
     * @return                Builder
    */
    public Builder setAmount(String amount) {
      Validator.validateAmount(amount);
      this.amount = amount;
      return this;
    }

    /**
     * set productInfo
     *
     * @param productInfo    productInfo
     * @throws PayuException if productInfo is invalid
     * @return               Builder
    */
    public Builder setProductInfo(String productInfo) {
      Validator.validateProductInfo(productInfo);
      this.productInfo = productInfo;
      return this;
    }

    /**
     * set firstName
     *
     * @param firstName      firstName
     * @throws PayuException if firstName is invalid
     * @return               Builder
    */
    public Builder setFirstName(String firstName) {
      Validator.validateFirstName(firstName);
      this.firstName = firstName;
      return this;
    }

    /**
     * set email
     *
     * @param email      email
     * @throws PayuException if email is invalid
     * @return               Builder
    */
    public Builder setEmail(String email) {
      Validator.validateEmail(email);
      this.email = email;
      return this;
    }

    /**
     * set udf1
     *
     * @param udf1            udf1
     * @throws PayuException if udf1 is invalid
     * @return               Builder
    */
    public Builder setUdf1(String udf1) {
      Validator.validateUdf(udf1);
      this.udf1 = udf1;
      return this;
    }
  
    /**
     * set udf2
     *
     * @param udf2            udf2
     * @throws PayuException if udf2 is invalid
     * @return               Builder
    */
    public Builder setUdf2(String udf2) {
      Validator.validateUdf(udf2);
      this.udf2 = udf2;
      return this;
    }
  
    /**
     * set udf3
     *
     * @param udf3            udf3
     * @throws PayuException if udf3 is invalid
     * @return               Builder
    */
    public Builder setUdf3(String udf3) {
      Validator.validateUdf(udf3);
      this.udf3 = udf3;
      return this;
    }

    /**
     * set udf4
     *
     * @param udf4            udf4
     * @throws PayuException if udf4 is invalid
     * @return               Builder
    */
    public Builder setUdf4(String udf4) {
      Validator.validateUdf(udf4);
      this.udf4 = udf4;
      return this;
    }
  
    /**
     * set udf5
     *
     * @param udf5            udf5
     * @throws PayuException if udf5 is invalid
     * @return               Builder
    */
    public Builder setUdf5(String udf5) {
      Validator.validateUdf(udf5);
      this.udf5 = udf5;
      return this;
    }

    public Builder setAdditionalCharges(String additionalCharges) {
      this.additionalCharges = additionalCharges;
      return this;
    }

    public HasherParams build() {
      if (this.txnId == null) {
        throw new PayuException("txnId is mandatory param. set is using .setTxnId");
      }
      if (this.amount == null) {
        throw new PayuException("amount is mandatory param. set is using .setAmount");
      }
      if (this.productInfo == null) {
        throw new PayuException("productInfo is mandatory param. set is using .setProductInfo");
      }
      if (this.firstName == null) {
        throw new PayuException("firstName is mandatory param. set is using .setFirstName");
      }
      if (this.email == null) {
        throw new PayuException("email is mandatory param. set is using .setEmail");
      }
      return new HasherParams(this);
    }
  }
}
