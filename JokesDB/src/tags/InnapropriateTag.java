package tags;

import java.util.ArrayList;

/**
 * 
 * @author Luiz Silva
 *
 */

public class InnapropriateTag implements Tag {

	ArrayList<String> jokes;
	
	public void addJoke(String content) {
		jokes.add(content);
	}
	
}
