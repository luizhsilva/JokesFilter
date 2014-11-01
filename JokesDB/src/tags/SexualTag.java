package tags;

import java.util.ArrayList;

public class SexualTag implements Tag{
	private ArrayList<String> jokes;
	
	public SexualTag() {
		this.jokes = new ArrayList<String>();
	}
	
	public void addJoke(String content) {
		
		jokes.add(content);
	}

	@Override
	public void writeFile() {
		// do nothing
		
	}
}
