package tags;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import tagger.Joke;

//import org.json.JSONException;
//import org.json.JSONObject;
import com.google.gson.*;

/**
 * 
 * @author Luiz Silva, Xizhe
 * @version 1.1
 *
 */

public class InappropriateTag implements Tag {

	ArrayList<String> jokes;
	
	
	
	public InappropriateTag() {
		this.jokes = new ArrayList<String>();
		
	}
	
	public void addJoke(String content) {
		
		jokes.add(content);
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
		InappropriateTag newData = creator.fromJson(jsonString, this.getClass());
		jokes = newData.getJokes();
		
		}

	
	public void writeFile() {
		String pathname = "resources/files/UserData/InappropriateTag.json";
		PrintWriter writer;
		try {
			writer = new PrintWriter(pathname, "UTF-8");
			writer.write(this.generateJSON());
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
