package tags;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class SportsTag implements Tag {

	ArrayList<String> jokes;
	
	public SportsTag() {
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
	
	@Override
	public void writeFile() {
		String pathname = "resources/files/UserData/SportsTag.json";
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
	
}
