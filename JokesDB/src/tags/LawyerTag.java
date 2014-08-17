package tags;

import java.util.ArrayList;

public class LawyerTag implements Tag {

	ArrayList<String> jokes;
	
	public void addJoke(String content) {
		jokes.add(content);
	}
	
}
