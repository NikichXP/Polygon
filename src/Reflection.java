import java.io.*;
import org.apache.http.*;
import org.apache.http.client.*;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;

public class Reflection {

    public static void main(String[] args) throws IOException {

        String baseURL = "localhost:8888/_ah/api/user/v1/";
        String[] reqs = {
                "auth?login=somelogin&pass=1234",
                "auth?login=somelogin2&pass=12342",
                "auth?login=test&pass=test"
        };

        HttpClient client = new DefaultHttpClient();
//        HttpGet request = new HttpGet("http://localhost:8888/RestBench/api/test/ping");
        HttpGet request;
        System.out.println("bench start");
        long t1 = System.currentTimeMillis();
        char c1 = 'a';
        char c2 = 'a';
        for (int i = 0; i < 10_000; i++) {
//            request = new HttpGet("http://127.0.0.1:8888/_ah/api/user/v1/register?login="+c1+c2+"1&name=Nick&pass="+c1+"11"+c2+"&surname=Cave");
            request = new HttpGet("http://127.0.0.1:8888/_ah/api/user/v1/auth?login=" + c1+c2 + "1&pass="+c1+"11"+c2);
            HttpResponse response = client.execute(request);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";

            while ((line = rd.readLine()) != null) {
                if (c1 == 'a') {
                    System.out.println(line);
                }
            }
            c1++;
            if (c1 > 'z') {
                c2++;
                c1 = 'a';
            }
            if (c2 > 'z') {
                c2 = 'a';
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        System.out.println("bench end");
    }

}
