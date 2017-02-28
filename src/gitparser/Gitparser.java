package gitparser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Gitparser {

	private static final MongoDatabase db = new MongoClient().getDatabase("git-parser");

	public static void main(String[] args) throws Exception {
		String path = "https://api.github.com/repos/NikichXP/hm2/commits?page=" + 3;
		String result = query(path);
//		JsonParser jp = new JsonParser();
		JsonElement js = new JsonParser().parse(result);
		js.getAsJsonArray().forEach(x -> {
			JsonObject jso = new JsonParser().parse(x.toString()).getAsJsonObject();
			GitCommit gc = new GitCommit(jso);
//			System.out.println(gc.toString());
		});
	}

	public static String query (String path) throws IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet req = new HttpGet(path);// + offset);
		HttpResponse response = client.execute(req);
		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		return result.toString();
	}

}
/*
	JsonElement jelement = new JsonParser().parse(jsonLine);
    JsonObject  jobject = jelement.getAsJsonObject();
    jobject = jobject.getAsJsonObject("data");
    JsonArray jarray = jobject.getAsJsonArray("translations");
    jobject = jarray.get(0).getAsJsonObject();
    String result = jobject.get("translatedText").toString();
    return result;
 */
