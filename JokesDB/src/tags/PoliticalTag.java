package tags;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PoliticalTag implements Tag {

	private ArrayList<String> jokes;
	
	//JsonArray taggedJokes = null;
	
	public PoliticalTag() {
		this.jokes = new ArrayList<String>();
	
		//taggedJokes = new JsonArray();
		
	}
	
	public void addJoke(String content) {
		//JsonObject joke = new JsonObject();
		//joke.addProperty(content, true);
		//taggedJokes.add(joke);
		this.jokes.add(content);
	}
	
	public String generateJSON() {
		Gson creator; 
		creator = new Gson();
		return creator.toJson(this);
	}
	
	
	/**
	 * maybe we need it later 
	 * @param jsonString
	 */
	public void createFromJSONText(String jsonString) {
		Gson creator;
		creator = new Gson();
		PoliticalTag newData = creator.fromJson(jsonString, this.getClass());
		jokes = newData.getJokes();
		
		}

	
	public void writeFile() {
		String pathname = "resources/files/UserData/PoliticalTag.json";
		PrintWriter writer;
		try {
			writer = new PrintWriter(pathname, "UTF-8");
			writer.write(this.generateJSON());
		//	writer.println(this.generateJSON());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	
	public ArrayList<String> getJokes() {
		return jokes;
	}

		
}
