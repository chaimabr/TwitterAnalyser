package tweetanalyse ;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
 
public class tweetanalyser {
	public static void main(String[] args) {
		try {
			String url = "D:\\projeteurecom\\dataset\\facup_tweets\\facup.json";
			JsonReader reader = new JsonReader(new InputStreamReader(
					new FileInputStream(url)));
			JsonParser jsonParser = new JsonParser();
			JsonObject userArray = jsonParser.parse(reader).getAsJsonObject();
			JsonArray objets = userArray.getAsJsonArray();
			String job = objets.toString();
			int ind=0;
			for (int i=0;i<job.length();i++) {
				if (job.indexOf("favorited",ind)>0 ){
					ind=job.indexOf("favorited");
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
