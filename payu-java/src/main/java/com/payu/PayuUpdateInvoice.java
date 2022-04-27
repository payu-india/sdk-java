
package com.payu;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.*;

public class PayuUpdateInvoice {

    public String key;
    public String var1;
    public String var2;
    public String var3;
    public File file;

    public File fileName;


    public String hash;
    public String environment;
    private String returnResponse;

    public String updateInvoice() {

        try {

            URL url = new URL("https://secure.payu.in/merchant/postservice.php?form=2");
            if (this.environment.equalsIgnoreCase("Test")) {
                url = new URL("https://test.payu.in/merchant/postservice.php?form=2");
//            System.out.println("This is test environment");

            } else if (this.environment.equalsIgnoreCase("Production")) {
                url = new URL("https://secure.payu.in/merchant/postservice.php?form=2");
//            System.out.println("This is Production environment");

            }

            if (this.key == null) {
                throw new PayuException("key is mandatory param, please pass your merchant key");
            } else if (this.var1 == null) {
                throw new PayuException("var1 is mandatory param, please pass var1 for the API");
            } else if (this.var2 == null) {
                throw new PayuException("var2 is mandatory param, please pass var2 for the API");
            } else if (this.var3 == null) {
                throw new PayuException("var3 is mandatory param, please pass var3 for the API");
            } else if (this.file == null) {
                throw new PayuException("file is mandatory param, please pass file for the API");
            }


            OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("key", this.key)
                .addFormDataPart("command", "opgsp_upload_invoice_awb")
                .addFormDataPart("var1", this.var1)
                .addFormDataPart("var2", this.var2)
                .addFormDataPart("var3", "Invoice")
                .addFormDataPart("File", this.fileName.toString(),
                    RequestBody.create(MediaType.parse("application/octet-stream"),
                        new File(String.valueOf(this.file))))
                .addFormDataPart("hash", this.hash)
                .build();
            Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .build();
            Response response = client.newCall(request).execute();
            returnResponse = response.body().string();
            //System.out.println(response.body().string());
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }
        return returnResponse;

    }
}
