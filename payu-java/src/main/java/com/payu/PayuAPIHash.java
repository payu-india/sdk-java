
package com.payu;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;



public class PayuAPIHash  {

    //public PayuAPIHash(String key, String salt) {super(key, salt);}


    public String key;
    public String command ;
    public String var1;
    public String salt;

    public String generateHashForUdfUpdteApi() {
        this.command = "udf_update";
        String template = "%s|%s|%s|%s";
        String str = String.format(template, this.key, this.command, this.var1, this.salt);
        return digestHashForAPIsAPI(str);
        // return str;
    }




    private String digestHashForAPIsAPI(String str) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(Constants.HASH_ALGO);
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] mdbytes = messageDigest.digest();
            for (byte hashByte : mdbytes) {
                hash.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }




    public String generateHashForInvoiceUploadApi() {
        this.command = "opgsp_upload_invoice_awb";
        String template = "%s|%s|%s|%s";
        String str = String.format(template, this.key, this.command, this.var1, this.salt);
        return digestHashForAPIsAPI(str);
        // return str;
    }

}



