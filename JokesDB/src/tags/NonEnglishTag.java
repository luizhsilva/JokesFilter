package tags;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class NonEnglishTag implements Tag {

	private ArrayList<String> jokes;
	
	public NonEnglishTag() {
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

	public void createFromJSONText(String jsonString) {
		Gson creator;
		creator = new Gson();
		NonEnglishTag newData = creator.fromJson(jsonString, this.getClass());
		jokes = newData.getJokes();
		
	}
	
	@Override
	public void writeFile() {
		String pathname = "resources/files/UserData/NonEnglishTag.json";
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
