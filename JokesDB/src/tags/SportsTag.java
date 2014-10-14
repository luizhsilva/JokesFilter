package tags;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.JSONException;

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
	
	/**
	 * Convert json to CSV format
	 * @throws JSONException
	 * @throws IOException
	 */
	public void toCSV() throws JSONException, IOException {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resources/files/Outputs/SportsJokes.csv"), "UTF-8"));
		
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
