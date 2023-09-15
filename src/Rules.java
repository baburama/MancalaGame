//Names: Pragieth Suresh and Rupert Maiti
//Teacher: Mr.Lee
//
//Description:This class shows the rules and the objective of the Manacala game
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Rules implements ActionListener {
	
	//frame
	static JFrame frame = new JFrame("Rules");
	//back button
	static JButton back = new JButton("back");
	
	//stores all rules
	JTextArea textarea = new JTextArea ("Rule 1: You have 6 bins, when you click a bin," 
			+ "\n  all the stones in the bin are distributed counter clockwise \n"
			+ "\n \nRule 2: If the last stone lands into your home bin you will get"
			+ " \n  another turn\n \nRule 3: If the last stone lands into a empty pit, you"
			+ " \n  take the stones in opposite bin and add it to your home bin "
			+ "\n\nRule 4: when one player has no more stones left, all enemy "
			+ "\nstones exect for enemy home bin go in your home bin "
			+ "\n\nGoal: Whichever player has the most stones in their home"
			+ "\n  bin at the end of the game wins");
	

	
	
	
	public Rules() {
		
		
		frame.setLayout(null);
			
		
		frame.setSize(800,600);
		
		frame.add(textarea);
		
		//add text area
		textarea.setBounds(100,0,500,500);
		textarea.setFont(new Font("Display", Font.PLAIN, 18));
		textarea.setEditable(false);
		
		//add back button
		back.addActionListener(this);
		back.setBounds(0,0,100,50);
		frame.add(back);
			
		frame.setVisible(true);	

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//if back button clicked go to title frame
		if(e.getSource()==back) {
			frame.setVisible(false);
			gameApplication.titleFrame.setVisible(true);
		}
	}
	
}