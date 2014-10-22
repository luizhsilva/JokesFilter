package tags;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.io.FileUtils;


/**
 * 
 * @author Luiz Silva, Xizhe
 * @version 1.1
 *
 */

public class InappropriateTag implements Tag {

	private ArrayList<String> jokes;
	
	
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

	/**
	 * convert json array to csv format
	 * @throws JSONException
	 * @throws IOException
	 */
	public void toCSV() throws JSONException, IOException {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resources/files/Outputs/InappropriateJokes.csv"), "UTF-8"));
		
		for(String str : this.jokes) {
			StringBuffer oneline = new StringBuffer();
			oneline.append(str);
			oneline.append("\r");
			bw.write(oneline.toString());
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}
	
	
}
