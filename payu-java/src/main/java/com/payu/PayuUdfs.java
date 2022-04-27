package com.payu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class PayuUdfs {

    public String key;
    public String var1;
    public String var2;
    public String var3;
    public String var4;
    public String var5;
    public String var6;
    public String hash;
    public String environment;

    public String PayuUpdateUdfs(){

        try {

            URL url = new URL("https://secure.payu.in/merchant/postservice.php?form=2");
            if(this.environment.equalsIgnoreCase("Test"))
            {
                url = new URL("https://test.payu.in/merchant/postservice.php?form=2");
//            System.out.println("This is test environment");

            }
            else if(this.environment.equalsIgnoreCase("Production"))
            {
                url = new URL("https://secure.payu.in/merchant/postservice.php?form=2");
//            System.out.println("This is Production environment");

            }
            Map<String, String> params = new LinkedHashMap<String, String>();
            params.put("key", this.key);
            params.put("command", "udf_update");
            params.put("var1", this.var1);
            params.put("var2", this.var2);
            params.put("var3", this.var3);
            params.put("var4", this.var4);
            params.put("var5", this.var5);
            params.put("var6", this.var6);

            params.put("hash", this.hash);
            StringBuilder postData = new StringBuilder();
            if (this.key == null) {
                throw new PayuException("key is mandatory param, please pass your merchant key");
            }
            else if (this.var1 == null) {
                throw new PayuException("var1 is mandatory param, please pass var1 for the API");
            }
            else if (this.var2 == null) {
                throw new PayuException("var2 is mandatory param, please pass var2 for the API");
            }
            else if (this.var3 == null) {
                throw new PayuException("var3 is mandatory param, please pass var3 for the API");
            }
            else if (this.var4 == null) {
                throw new PayuException("var4 is mandatory param, please pass var4 for the API");
            }
            else if (this.var5 == null) {
                throw new PayuException("var5 is mandatory param, please pass var5 for the API");
            }
            else if (this.var6 == null) {
                throw new PayuException("var6 is mandatory param, please pass var6 for the API");
            }

            // map entries of params
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                // append equal sign to set params
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // set request type & headers
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            // write to data bytes
            conn.getOutputStream().write(postDataBytes);
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0; )
                sb.append((char) c);
            String response = sb.toString();
//        System.out.println(response);
            return response;
        } catch (Exception e) {
            return e.toString();
        }
        //  return null;
    }

}
