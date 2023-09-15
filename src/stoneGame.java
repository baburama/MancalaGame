/*//Name: Rupert and Pragieth
 * Teacher:Mr.Lee
 * Date: june 2 2022
 * description: GUI frame for the mancala game,Allows you to play agains player of computer 
 * based on what you selected in title frame
 * 
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class stoneGame implements ActionListener {
	
	 //random number for ai
	static Random rand = new Random();
	static int randomNum;
	
	//stores ai recomended move
	static int currentRecomendation;
	
	JFrame frame = new JFrame();
	
	//background
	JLabel bg = new JLabel(new ImageIcon("images/board2.jpg"));
	JLabel title = new JLabel("Mancala game");

	static String textOutput = titleFrame.p1Name+"'s turn";
	static JLabel turn = new JLabel(textOutput);
	static JButton[] buttons = new JButton[14];

	//variables from textGame
	static int[] binsArray = {0,3,3,3,3,3,3,0,3,3,3,3,3,3};
	static int choice;
	static int player2Choice;
	static Scanner input = new Scanner(System.in);
	boolean isGameOver = false;
	static int startingStones;
	//position of bin oposite to chosen bin
	static int oppositePosition;
	
	//verify turn
	static int whoseTurn = 1;
	


	
	public stoneGame() {
		frame.setSize(800, 600);
		
		// Clears the window background
		frame.setLayout(null);
		//change background colour
		frame.getContentPane().setBackground(new Color(175,238,238));

		
		//add background
		bg.setBounds(0,200,800,217);
		frame.add(bg);
		bg.setVisible(true);
		
		//add title
		frame.add(title);
		title.setBounds(0,0,100,50);
		
		//add label which says whoose turn it is
		frame.add(turn);
		turn.setBounds(400,120,200,40);
		
		//set buttons text
		buttons[0] = new JButton("0");
		buttons[1] = new JButton(binsArray[1]+"");
		buttons[2] = new JButton(binsArray[2]+"");
		buttons[3] = new JButton(binsArray[3]+"");
		buttons[4] = new JButton(binsArray[4]+"");
		buttons[5] = new JButton(binsArray[5]+"");
		buttons[6] = new JButton(binsArray[6]+"");
		buttons[7] = new JButton("0");
		buttons[8] = new JButton(binsArray[8]+"");
		buttons[9] = new JButton(binsArray[9]+"");
		buttons[10] = new JButton(binsArray[10]+"");
		buttons[11] = new JButton(binsArray[11]+"");
		buttons[12] = new JButton(binsArray[12]+"");
		buttons[13] = new JButton(binsArray[13]+"");
		
		//place buttons
		buttons[0].setBounds(40,300,50,50);
		buttons[1].setBounds(135,335,50,50);
		buttons[2].setBounds(235,335,50,50);
		buttons[3].setBounds(335,335,50,50);
		buttons[4].setBounds(425,335,50,50);
		buttons[5].setBounds(515,335,50,50);
		buttons[6].setBounds(615,335,50,50);
		buttons[7].setBounds(720,300,50,50);
		
		buttons[8].setBounds(615,235,50,50);
		buttons[9].setBounds(515,235,50,50);
		buttons[10].setBounds(425,235,50,50);
		buttons[11].setBounds(335,235,50,50);
		buttons[12].setBounds(235,235,50,50);
		buttons[13].setBounds(135,235,50,50);
		//add action listener to buttons
		for(int i =1;i<14;i++) {
			if(i==7)continue;
			buttons[i].addActionListener(this);
		}
		
		//add buttons
		frame.add(buttons[0]);
		frame.add(buttons[1]);
		frame.add(buttons[2]);
		frame.add(buttons[3]);
		frame.add(buttons[4]);
		frame.add(buttons[5]);
		frame.add(buttons[6]);
		frame.add(buttons[7]);
		frame.add(buttons[8]);
		frame.add(buttons[9]);
		frame.add(buttons[10]);
		frame.add(buttons[11]);
		frame.add(buttons[12]);
		frame.add(buttons[13]);
		frame.setVisible(true);
		
		//set amount of starting stones
		startingStones = titleFrame.stoneChoice;
		setBoard(startingStones);
		update();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//checks which button is clicked and makes move accordingly, only if it is player ones turn
		for(int i = 1;i<7;i++) {
			if(e.getSource()==buttons[i]&&whoseTurn == 1) {
				makeMove(i);
			}
		}
		//checks if player 2 button is clickes and moves acoringly if it is player 2's turn 
		//and user didnt want to player with AI
		for(int i = 8;i<14;i++) {
			if(e.getSource()==buttons[i]&&whoseTurn == 2&& titleFrame.vsAI==false) {
				player2Move(i);
			}
		}
		
		//if vsAi is true makes move according to ai Recomendations
		if(whoseTurn == 2&&titleFrame.vsAI==true) {
			currentRecomendation = aiRecomend();
			//if computer picked empty bin choose another bin
			while(binsArray[currentRecomendation]==0) {
				currentRecomendation = aiRecomend();
				//if games over break
				if(gameOver()) {
					break;
				}
			}
			
			System.out.println("AI chooses to move "+currentRecomendation);
			player2Move(currentRecomendation);
		}
		//checks if game is over
		if(gameOver()) {
			findWinner();
		}
	}
	
	//update text in buttons to reflect what's on array
	public static void update() {
		for(int i = 0;i<14;i++) {
			buttons[i].setText(binsArray[i]+"");
		}
	}
	
	public static void makeMove(int position) {
		turn.setText(titleFrame.p2Name+"'s turn");
		//check if bin they chose is 0 ask them to pick another bin
		while(binsArray[position]==0) {
			System.out.println("error: bin has no stones in it pick another bin");
			return;
		}
		
		for(int i =1;i<=binsArray[position];i++) {
			
			//if i+position is bigger then 11 that means you have to loop around so subtract position by 12
			if((position+i)>13) {
				//go back one full loop add one to avoid putting it in opponent home bin
				binsArray[position+i-14+1]++;
			}
			//if last stone lands on empty pin, take stones from both bins
			else if(i==binsArray[position]&&binsArray[position+i]==0 &&position+i!=7) {
				oppositePosition = (6-(position+i))*2+(position+i)+2;
				
				//if oppoite pit doesnt jave stone this condition shouldnt appply
				if(binsArray[oppositePosition]==0){
					
					binsArray[position+i]++;
					binsArray[position]=0;

					update();
					turn.setText(titleFrame.p2Name+"'s turn");
					whoseTurn = 2;
					return;
				}
				
				//add bin and opposite bin to home bin
				binsArray[7]= binsArray[7]+1+binsArray[oppositePosition];
				binsArray[position]=0;
				binsArray[position+i]=0;
				
				//set opposite bin to 0
				binsArray[oppositePosition]=0;
				update();
				turn.setText(titleFrame.p2Name+"'s turn");
				whoseTurn = 2;
				return;
				
			}
			
			else {
				binsArray[position+i]++;
				update();

			}
			
			//if last stone lands in your home bin you get another turn
			//print game and set original bin to 0 and re run this method to avoid code at bottom
			if(i==binsArray[position]&&position+i==7) {
				binsArray[position]=0;
				update();
				turn.setText(titleFrame.p1Name+" gets another turn");
				
				//used to break out of method
				return;
			}
		
		}
		binsArray[position]=0;
		update();
		turn.setText(titleFrame.p2Name+"'s turn");
		whoseTurn = 2;


	}
	public static void player2Move(int position)  {
		//check if bin they chose is 0 ask them to pick another bin
		while(binsArray[position]==0) {
			System.out.println("error: bin has no stones in it pick another bin");
			break;
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
					update();
					turn.setText(titleFrame.p1Name+"'s turn");
					whoseTurn = 1;
					return;
				}
				
				//add bin and opposite bin to home bin
				binsArray[0]= binsArray[0]+1+binsArray[oppositePosition];
				binsArray[position]=0;
				binsArray[position+i]=0;
				
				//set opposite bin to 0
				binsArray[oppositePosition]=0;
				update();
				turn.setText(titleFrame.p1Name+"'s turn");
				whoseTurn = 1;
				return;
			}
			else {
				binsArray[position+i]++;

			}
			//if computer puts his last stone in his home bin he gets another turn
			if(i==binsArray[position]&&position+i-14==0) {
				binsArray[position]=0;
				update();
				turn.setText(titleFrame.p2Name+" gets another turn");
				
				//if computer gets another turn make computer play again
				if(titleFrame.vsAI){
					currentRecomendation = aiRecomend();
					//if computer picked empty bin choose another bin
					while(binsArray[currentRecomendation]==0) {
						currentRecomendation = aiRecomend();
						//if games over break
						if(gameOver()) {
							break;
						}
						
					}
					System.out.println("AI chooses to move "+currentRecomendation);
					player2Move(currentRecomendation);
					turn.setText(titleFrame.p1Name+"'s turn");

				}
				
				//used to break out of method
				return;
			}
		}
		binsArray[position]=0;
		update();
		turn.setText(titleFrame.p1Name+"'s turn");
		whoseTurn = 1;

	}
	//method returns move ai should make
	public static int aiRecomend() {
		//time delay 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int recomendation = 8;
		int largestBin = 8;
		
		for(int i = 8;i<14;i++){
			oppositePosition = (binsArray[i]+i) - (binsArray[i]+i-8)*2-2;

			//if ai can steal from opponent move ai reccomends that move
			if(binsArray[i]+i<14 &&binsArray[i]!=0) {
				if(binsArray[i+binsArray[i]]==0 ) {
					//if opposite bin has no stones you cant steal so dont make this move
					if(binsArray[oppositePosition]==0) {
						continue;
					}
					System.out.println("computer steals from your bin");
					
					return i;
			}
			}
			//if last stone lands in home bin, ai recomends this move
			else if(binsArray[i]+i==14&&binsArray[i]!=0){
				System.out.println("computer gets another turn");
				return i;
			}
			
			//if number is larger, it is better to make that move since it fills your home bin and enemys empty bin
			else if(binsArray[i]>=binsArray[largestBin]){
				largestBin = i;
			}
		}
		//if no move that allows computer to steal or play again can be found, computer plays bin with most stones
		recomendation =  largestBin;
		
		//make sure recomendation is not an empty bin
		while(binsArray[recomendation]==0) {
			System.out.println("recomending empty bin!!");
			randomNum = rand.nextInt((13 - 8) + 1) + 8;
			if(gameOver()) {
				break;
			}
		}
		return recomendation;
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
		
		//if no stones in your side take opponent stones and end game
		if(sum ==0){
		

			binsArray[7] = binsArray[7] + oppositeSum;
			setBoard(0);
			System.out.println("\n\ngame over\n\n");
			System.out.println("bin 0 :"+binsArray[0]+" bin 7:"+binsArray[7]);
			

			update();
			return true;
		}
		//if no stones on opponents side take your stones and end game
		if(oppositeSum ==0){
			binsArray[0]+=sum;
			setBoard(0);
			System.out.println("\n\ngame over\n\n");
			System.out.println("bin 0 :"+binsArray[0]+" bin 7:"+binsArray[7]);
			
			
			
			update();

			return true;
		}
		
		return false;
	}
	//finds and displays who won game
	public static void findWinner(){
		if(gameOver()){
			if(binsArray[0]>binsArray[7]){
				turn.setText(titleFrame.p2Name+" is the winner!!");

			}
			else if(binsArray[7]>binsArray[0]){
				turn.setText(titleFrame.p1Name+" is the winner!!");

			}
			else if (binsArray[0]==binsArray[7]){
				turn.setText("Tie!!");
			}
		}
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