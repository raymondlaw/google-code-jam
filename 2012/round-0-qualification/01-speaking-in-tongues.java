import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class mapping {
	public char english;
	public char google;
	
	public mapping(char e){
		english = e;
		google = '?';
	}
	public mapping(char e, char g){
		english = e;
		google = g;
	}
}

public class map {
	public mapping letters[];
	
	public map(){
		letters = new mapping[26];
		letters[0] = new mapping('a','y');
		letters[1] = new mapping('b','n');
		letters[2] = new mapping('c','f');
		letters[3] = new mapping('d','i');
		letters[4] = new mapping('e','c');
		letters[5] = new mapping('f','w');
		letters[6] = new mapping('g','l');
		letters[7] = new mapping('h','b');
		letters[8] = new mapping('i','k');
		letters[9] = new mapping('j','u');
		letters[10] = new mapping('k','o');
		letters[11] = new mapping('l','m');
		letters[12] = new mapping('m','x');
		letters[13] = new mapping('n','s');
		letters[14] = new mapping('o','e');
		letters[15] = new mapping('p','v');
		letters[16] = new mapping('q','z');
		letters[17] = new mapping('r','p');
		letters[18] = new mapping('s','d');
		letters[19] = new mapping('t','r');
		letters[20] = new mapping('u','j');
		letters[21] = new mapping('v','g');
		letters[22] = new mapping('w','t');
		letters[23] = new mapping('x','h');
		letters[24] = new mapping('y','a');
		letters[25] = new mapping('z','q');
	}
	public char toGoogle(char input){
		int index = 0;
		for(int i=0;i<26;i++){
			if(input == letters[i].english){
				index = i;
				break;
			}
		}
		return letters[index].google;
		
	}
	public char toEnglish(char input){
		int index = 0;
		boolean foundSomething = false;
		for(int i=0;i<26;i++){
			if(input==letters[i].google){
				index = i;
				foundSomething = true;
				break;
			}
		}
		if(!foundSomething){
			return '?';
		}
		return letters[index].english;
	}
}

public class googlerese {
	public static void main(String[] args) {
		String input="";
		int line = 0;
		try {
			BufferedReader inputStream = new BufferedReader(new FileReader("input"));
			line = Integer.parseInt(inputStream.readLine());
			for(int i=0; i< line; i++){
				input = inputStream.readLine(); 
				System.out.println("Case #" + (i+1) + ": " + decrypt(input));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String decrypt(String input){
		String output="";
		
		map dictionary = new map();
		int ascii;
		char c;
		for (int i = 0; i < input.length(); i++){
			c = input.charAt(i);
			ascii = (int) c;
			if(ascii < 97 || ascii > 122){ 
				output += c;
				continue;
			};
			output += dictionary.toEnglish(c);
		}
		return output;

	}
}
