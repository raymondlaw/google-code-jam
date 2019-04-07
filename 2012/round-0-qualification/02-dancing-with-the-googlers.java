import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class triplet {
	int judgea;
	int judgeb;
	int judgec;
	String status;
	
	public triplet(int a, int b, int c){
		int minab, maxab;
		minab = Math.min(a, b);
		maxab = Math.max(a, b);
				
		judgea = Math.min(minab, c);
		judgeb = Math.min(maxab, c);
		judgec = Math.max(maxab, c);
		
		if(judgea < 0 || judgec > 10){
			status = "invalid";
		}
		else{
			if(judgec - judgea==2){
				status = "surprising";
			}
			else if((judgec - judgea)==1 || (judgec - judgea)==0){
				status = "not surprising";
			}
			else{
				status = "invalid";
			}
		}
	}
	public int score(){
		return Math.max(Math.max(judgea,judgeb),judgec);
	}
	public String toString(){
		if(status.matches("invalid")){
			return "invalid";
		}
		return "(" + judgea+ "," + judgeb + "," + judgec +")";
	}
}

public class dancing {
	public static void main(String[] args) {
		String input="";
		StringTokenizer strtok;
		
		int googlers, surprises, p, count=0;
		
		int line = 0;
		int totalscore, base, residual;
		try {
			BufferedReader inputStream = new BufferedReader(new FileReader("B-large.in"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
			
			line = Integer.parseInt(inputStream.readLine());
			for(int i=0; i< line; i++){
				input = inputStream.readLine(); 
				strtok = new StringTokenizer(input," ");
				
				googlers = Integer.parseInt(strtok.nextToken());
				surprises = Integer.parseInt(strtok.nextToken());
				p = Integer.parseInt(strtok.nextToken());
				for(int j=0 ; j< googlers; j++){
					totalscore = Integer.parseInt(strtok.nextToken());
					base = totalscore/3;
					residual = totalscore % 3;
					
					triplet normal,surprise;
					
					if(residual==0){
						//normal
						normal = new triplet(base,base,base);
						//surprising
						surprise = new triplet(base-1,base,base+1);
						
						if(normal.score()>=p)
							count++;
						else if(surprise.status!="invalid" && surprise.score()>=p && surprises > 0){
							count++;
							surprises--;
						}
					}
					else if(residual==1){
						//normal
						normal = new triplet(base,base,base+1);
						if(normal.score()>=p)
							count++;
					}
					else if(residual==2){
						//normal
						normal = new triplet(base,base+1,base+1);
						//surprising
						surprise = new triplet(base,base,base+2);
						if(normal.score()>=p)
							count++;
						else if(surprise.status!="invalid" && surprise.score()>=p && surprises > 0){
							count++;
							surprises--;
						}
					}
				}
				if(i!=line-1)
					writer.write("Case #"+(i+1)+": "+count+"\r\n");
				else
					writer.write("Case #"+(i+1)+": "+count);
				count = 0;
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
