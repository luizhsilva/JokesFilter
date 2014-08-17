package tags;

import java.util.ArrayList;

public class TooLongTag implements Tag{

	ArrayList<String> jokes;
	
	public void addJoke(String content) {
		jokes.add(content);
	}
	
}
