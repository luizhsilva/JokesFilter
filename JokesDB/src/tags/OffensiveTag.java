
	
package tags;	
	
import java.util.ArrayList;

public class OffensiveTag  implements Tag{
	
	private ArrayList<String> jokes;
	
	public OffensiveTag() {
		this.jokes = new ArrayList<String>();
	}
	
	public void addJoke(String content) {
		
		jokes.add(content);
	}

	@Override
	public void writeFile() {
		// we dont need the json file from it 
	}

}
