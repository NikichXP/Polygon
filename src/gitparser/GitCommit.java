package gitparser;

import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class GitCommit {

	String sha;
	LocalDateTime date;
	String author;
	String message;
	String url;
	int diffSize;

	public GitCommit(JsonObject jso) {
		System.out.println(jso.toString());
		this.sha = jso.get("sha").getAsString();
		this.date = LocalDateTime.parse(jso.get("commit").getAsJsonObject().get("author").getAsJsonObject().get("date").getAsString().replace('Z', ' ').trim());
		this.author = jso.get("commit").getAsJsonObject().get("author").getAsJsonObject().get("name").getAsString();
		this.message = jso.get("commit").getAsJsonObject().get("message").getAsString();
		this.url = jso.get("url").getAsString();
//		this.diffSize = jso.get("stats").getAsJsonObject();//.get("total").getAsInt();
//		System.out.println();
//		jso.entrySet().forEach(x -> System.out.println(x.getKey()));
	}
}
