import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class problem {
	public static boolean alldigitsseen(boolean[] digits){
		for(int i=0 ;i<10; i++){
			if(digits[i] == false){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("A-large.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        boolean digits[] = new boolean[10];
        
        int T=0;
        int N=0;
        T = Integer.parseInt(in.readLine());
        String line = null;
        int casen=0;
        while ((line = in.readLine()) != null) {
        	casen++;
    		N = Integer.parseInt(line);
    		if(N==0){
        		out.write("Case #"+ casen + ": INSOMNIA");
        		out.newLine();
        		continue;
    		}
    		for(int i=0; i<10; i++){
    			digits[i] = false;
    		}
    		int mult=0;
    		int currentNumber = -1;
    		while(!alldigitsseen(digits)){
    			mult++;
    			currentNumber = N*mult;
    			int originalnumber = currentNumber;
    			
    			while(currentNumber/10 > 0){
    				int lastdigit = currentNumber%10;
    				currentNumber = currentNumber/10;
    				digits[lastdigit] = true;
    			}
    			digits[currentNumber] = true;
    			currentNumber = originalnumber;
    		}
    		out.write("Case #"+ casen + ": " + currentNumber+"");
    		out.newLine();
        }
        
		out.close();
        in.close();

	}

}
