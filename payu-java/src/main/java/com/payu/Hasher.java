package com.payu;

import java.security.MessageDigest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

/**
 * Hasher class
 */
public class Hasher extends BasePayu {

    public Hasher(String key, String salt) {
        super(key, salt);
    }

    private String digestHash(String str) {
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

    /**
     * Generate payment hash
     *
     * @param params   HasherParams obj containing transaction params
     * @return         hashstring
     */
    public String generateHash(HasherParams params) {
        if (params == null) {
            throw new PayuException("generateHash parameter should not be null");
        }
        String udf1 = params.getUdf1();
        String udf2 = params.getUdf2();
        String udf3 = params.getUdf3();
        String udf4 = params.getUdf4();
        String udf5 = params.getUdf5();
        String template = "%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s||||||%s";
        String str = String.format(template, key, params.getTxnId(), params.getAmount(), params.getProductInfo(), params.getFirstName(), params.getEmail(), udf1, udf2, udf3, udf4, udf5, salt);
        return digestHash(str);
    }

    public String yourKey()
    {
        return this.key;
    }
    public String yourSalt()
    {
        return this.salt;
    }

    /**
     * Validate payment hash
     *
     * @param reveseHash   reverse hash string received from payu on checkout
     * @param params       transaction status recieved from payu on checkout
     * @param params       HasherParams obj containing transaction params
     * @return             true if valid hash else false
     */
    public boolean validateHash(String reveseHash, String status, HasherParams params) {
        if (reveseHash ==null || status == null || params == null) {
            throw new PayuException("validateHash parameters should not be null");
        }
        String udf1 = params.getUdf1();
        String udf2 = params.getUdf2();
        String udf3 = params.getUdf3();
        String udf4 = params.getUdf4();
        String udf5 = params.getUdf5();
        String additionalCharges = params.getAdditionalCharges();
        String template = "%s|%s||||||%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s";
        String str = String.format(template, salt, status, udf1, udf2, udf3, udf4, udf5, params.getEmail(), params.getFirstName(), params.getProductInfo(), params.getAmount(), params.getTxnId(), key);
        if (additionalCharges != null) {
            StringBuilder string = new StringBuilder(additionalCharges);
            string.append("|");
            string.append(str);
            str = string.toString();
        }
        return digestHash(str).equals(reveseHash);
    }
}
