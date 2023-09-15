

import java.util.*;
public class textGame {
	static int[] binsArray = {0,3,3,3,3,3,3,0,3,3,3,3,3,3};
	static int choice;
	static int player2Choice;
	static Scanner input = new Scanner(System.in);
	boolean isGameOver = false;
	static int startingStones;
	
	//position of bin oposite to chosen bin
	static int oppositePosition;
	
	public static void main(String[] args) {
		System.out.println("Welcome to stones(mancala) game");
		System.out.println("These are the numbers of the bins, bins 1-6 are for player 1, bins 8-13 are for player 2");
		System.out.println("    |13| |12| |11| |10|  |9| |8|");
		System.out.println("|0|                              |7|");
		System.out.println("    |1|  |2|  |5|  |4|   |5| |6|");
		
		System.out.println("how many stones would you like in each bin, select number from 2-5");
		startingStones = input.nextInt();
		setBoard(startingStones);
		
		System.out.println("this is current game board");
		printGame();
		
		while(true) {
			System.out.println(" player 1 make a move, choose one of the pits, enter "
					+ "number from 1-6");
			choice = input.nextInt();
			//error check
			while(choice<0||choice>6) {
				System.out.println("error:invalid input enter another number");
				choice = input.nextInt();
			}
			makeMove(choice);
			
			if(gameOver()){
				
				break;
			}
			
			//alternate between ai and human choices
			System.out.println("player 2 making move, choose number 8-13");
			player2Choice = input.nextInt();
			//error check
			while(player2Choice<8||player2Choice>13) {
				System.out.println("error:invalid input enter another number");
				player2Choice = input.nextInt();
			}
			player2Move(player2Choice);

			if(gameOver()){	
				break;
			}
		}
	}
	
	public static void printGame() {
		/*
		 * bins in relation to array
		 *    |13| |12| |11| |10|  |9| |8|
		 * |0|                            |7|
		 *    |1|  |2|  |5|  |4|   |5| |6|
		 */
		System.out.println("  "+binsArray[13]+"  "+binsArray[12]+"  "+binsArray[11]+"  "+binsArray[10]+"  "+binsArray[9]+"  "+binsArray[8]);
		System.out.println(binsArray[0]+"\t\t   "+binsArray[7]);
		System.out.println("  "+binsArray[1]+"  "+binsArray[2]+"  "+binsArray[3]+"  "+binsArray[4]+"  "+binsArray[5]+"  "+binsArray[6]);
	}
	
	public static void makeMove(int position) {
		
		//check if bin they chose is 0 ask them to pick another bin
		while(binsArray[position]==0) {
			System.out.println("error: bin has no stones in it pick another bin");
			position = input.nextInt();
		}
		
		for(int i =1;i<=binsArray[position];i++) {
			//if i+position is bigger then 11 that means you have to loop around so subract position by 12
			
			if((position+i)>13) {
				//go back one full lopp add one to avoid putting it in opponponents home bin
				binsArray[position+i-14+1]++;
			}
			//if last stone lands on empty pin, take stones from both bins
			else if(i==binsArray[position]&&binsArray[position+i]==0 &&position+i!=7) {
				oppositePosition = (6-(position+i))*2+(position+i)+2;
				
				//if oppoite pit doesnt jave stone this condition shouldnt appply
				if(binsArray[oppositePosition]==0){
					
					binsArray[position+i]++;
					binsArray[position]=0;

					printGame();
					return;
				}
				
				//add bin and opposite bin to home bin
				binsArray[7]= binsArray[7]+1+binsArray[oppositePosition];
				binsArray[position]=0;
				binsArray[position+i]=0;
				
				//set opposite bin to 0
				binsArray[oppositePosition]=0;
				printGame();
				return;
				
			}
			
			else {
				binsArray[position+i]++;

			}
			
			//if last stone lands in your home bin you get another turn
			//print game and set original bin to 0 and re run this method to avoid code at bottom
			if(i==binsArray[position]&&position+i==7) {
				binsArray[position]=0;
				printGame();
				System.out.println("player 1 gets another turn");
				System.out.println("Please seclect another number 1-6 for your extra turn");
				choice = input.nextInt();
				makeMove(choice);
				
				//used to break out of method
				return;
			}
		
		}
		binsArray[position]=0;
		printGame();

	}
	public static void player2Move(int position) {
		//check if bin they chose is 0 ask them to pick another bin
		while(binsArray[position]==0) {
			System.out.println("error: bin has no stones in it pick another bin");
			position = input.nextInt();
		}
		
		for(int i =1;i<=binsArray[position];i++) {
			//if i+position is bigger then 11 that means you have to loop around so subract position by 12
			
			if((position+i)>13) {
				
				//skip opponents home bin
				if((position+i-14)>=7) {
					binsArray[position+i-14+1]++;
					continue;
				}
				
				//go back one full loop
				binsArray[position+i-14]++;

			}
			//if lands on empty pit
			else if(i==binsArray[position]&&binsArray[position+i]==0 &&position+i!=0) {
				//find position of adjacent pit
				oppositePosition = (position+i) - (position+i-8)*2-2;
				
				//if oppoite pit doesnt jave stone this condition shouldnt appply
				if(binsArray[oppositePosition]==0){
					
					binsArray[position]=0;
					binsArray[position+i]++;
					printGame();
					return;
				}
				
				//add bin and opposite bin to home bin
				binsArray[0]= binsArray[0]+1+binsArray[oppositePosition];
				binsArray[position]=0;
				binsArray[position+i]=0;
				
				//set opposite bin to 0
				binsArray[oppositePosition]=0;
				printGame();
				return;
			}
			else {
				binsArray[position+i]++;

			}
			//if computer puts his last stone in his home bin he gets another turn
			if(i==binsArray[position]&&position+i-14==0) {
				binsArray[position]=0;
				printGame();
				System.out.println("player 2 gets another turn");
				System.out.println("Please seclect another number 8-13 for player 2's extra turn");
				player2Choice = input.nextInt();
				player2Move(player2Choice);
				
				//used to break out of method
				return;
			}
		}
		binsArray[position]=0;
		printGame();
	}
	//checks if game is over
	public static boolean gameOver(){
		//sum of all stones on player 1 side
		int sum = 0;
		
		//sum of all stones in opposite players side
		int oppositeSum = 0;
		
		//takes sum of stones on your side and opponents side 
		for(int i = 1;i<7;i++){
			sum += binsArray[i];
		}
		for(int i = 8;i<=13;i++){
			oppositeSum =oppositeSum+ binsArray[i];
		}
		
		//if no stones in your side takeopponent stones and end game
		if(sum ==0){
		

			binsArray[7] = binsArray[7] + oppositeSum;
			setBoard(0);
			System.out.println("\n\ngame over\n\n");

			printGame();
			return true;
		}
		//if no stones on opponents side take your stones and end game
		if(oppositeSum ==0){
			binsArray[0]+=sum;
			setBoard(0);
			System.out.println("\n\ngame over\n\n");

			printGame();

			return true;
		}
		
		return false;
	}
	//sets all bins except for home bins to the number passed through parameter
	public static void setBoard( int number){
		for(int i = 1;i<7;i++){
			binsArray[i] = number;
		}
		for(int i = 8;i<14;i++){
			binsArray[i] = number ;
		}
	}
}