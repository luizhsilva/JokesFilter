package tags;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import tagger.Joke;

import com.google.gson.Gson;

/**
 * Represent the original jokes
 * it's just an experiment... maybe change later 
 * @author Xizhe 
 * @version 1.0
 */
public class OriginalJokes {

	 private ArrayList<Joke> jokes;
		
		
		
		public OriginalJokes() {
			this.jokes = new ArrayList<Joke>();
		
			
		}
		
		public void addJoke(Joke j) {
			
			this.jokes.add(j);
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
			OriginalJokes newData = creator.fromJson(jsonString, this.getClass());
			jokes = newData.getJokes();
			
			}

		
		public void writeFile() {
			String pathname = "resources/files/UserData/OriginalJokes.json";
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

		
		public ArrayList<Joke> getJokes() {
			return jokes;
		}

}
