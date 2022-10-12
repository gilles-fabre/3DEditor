package utilities;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class SimpleStringTokenizer extends StreamTokenizer {
	String token = "?";
	
	public SimpleStringTokenizer(String string) {
		super(new StringReader(string));
		eolIsSignificant(true);
		int	[]whiteSpaces = {'.', ' ', ','};
		for (int i = 0; i < whiteSpaces.length; i++)
			whitespaceChars(whiteSpaces[i], whiteSpaces[i]);
	}
	
	public String lastToken() {
		try {
			while (nextToken() != TT_EOF) {
				token = sval;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return token;
	}
}
