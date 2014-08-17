package tags;

import java.util.ArrayList;

public class NonEnglishTag implements Tag {

	ArrayList<String> jokes;
	
	public void addJoke(String content) {
		jokes.add(content);
	}
	
}
