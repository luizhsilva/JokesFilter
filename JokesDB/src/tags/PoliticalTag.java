package tags;

import java.util.ArrayList;

public class PoliticalTag implements Tag {

	ArrayList<String> jokes;
	
	public void addJoke(String content) {
		jokes.add(content);
	}
	
}
