package vkapi;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VKApiTest {
	public static void main(String[] args) throws IOException {
		HttpClient client = HttpClientBuilder.create().build();
		String path = "http://api.vk.com/method/wall.get?owner_id=-57536014&count=100&filter=owner&offset=";
		int offset = 0;
		HttpGet req = new HttpGet(path + offset);
		HttpResponse response = client.execute(req);
		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result);
	}

	class SomeException extends Throwable {

	}

}