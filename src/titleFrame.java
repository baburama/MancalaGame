/*//Name: Rupert and Pragieth
 * Teacher:Mr.Lee
 * Date: june 2 2022
 * description: GUI frame for the introduction, Allows user to input name, asks how many stones
 * they would like in each bin and asks if they would like to play against player or 
 * computer, runs the stone game when next button is clicked, runs rules frame when help button is
 * clicked
 * 
 * Note: you need to hover mouse around mancala board to make buttons show up
 */


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class titleFrame extends JFrame implements ActionListener {
	
	JLabel title = new JLabel("Welcome to stones game");
	
	//asking user for the amount of starting stones 
	JLabel stonesQuestion = new JLabel("How many stones do you want in each bin?");
	static JRadioButton stones2 = new JRadioButton("2 Stones");
	static JRadioButton stones3 = new JRadioButton("3 Stones");
	static JRadioButton stones4 = new JRadioButton("4 Stones");
	static JRadioButton stones5 = new JRadioButton("5 Stones");
	static ButtonGroup stoneButtons = new ButtonGroup();
	
	//Asking user for names
	JLabel p1Question = new JLabel("What is player 1's name");
	JLabel p2Question = new JLabel("What is player 2's name");
	static JTextField p1NameBox = new JTextField();
	static JTextField p2NameBox = new JTextField();
	
	//asking for vs player of vs ai
	JLabel opponentQuestion = new JLabel("Do you want to play against human or computer");
	JRadioButton human = new JRadioButton("Human");
	static JRadioButton computer = new JRadioButton("Computer");
	ButtonGroup opponentChoice = new ButtonGroup();
	
	//next button
	JButton next = new JButton("next");
	
	//help button
	JButton help = new JButton("help");

	//variables to store choices
	static int stoneChoice;
	static String p1Name;
	static String p2Name;
	static boolean vsAI;
    // Resizes the Frame
	public titleFrame(){
		setSize(800,600);
		setLayout(null);
		// background colour and the positioning 
		getContentPane().setBackground(new Color(252,233,212));
		title.setBounds(300,5,200,40);
		add(title);
		
		stonesQuestion.setBounds(100,150,300,25);
		stones2.setBounds(100,200,100,40);
		stones3.setBounds(100,250,100,40);
		stones4.setBounds(100,300,100,40);
		stones5.setBounds(100,350,100,40);
		// buttons for choosing the amount of stones
		stoneButtons.add(stones2);
		stoneButtons.add(stones3);
		stoneButtons.add(stones4);
		stoneButtons.add(stones5);

		add(stones2);
		add(stones3);
		add(stones4);
		add(stones5);
		add(stonesQuestion);
		
		// Displays the names and the question
		p1Question.setBounds(400,150,300,25);
		p2Question.setBounds(400,250,300,25);
		p1NameBox.setBounds(400,200,300,25);
		p2NameBox.setBounds(400,300,300,25);

		add(p1Question);
		add(p2Question);
		add(p1NameBox);
		add(p2NameBox);
		
		//vs human or computer
		opponentQuestion.setBounds(100,400,300,40);
		human.setBounds(100,450,100,40);
		computer.setBounds(200,450,100,40);
		opponentChoice.add(computer);
		opponentChoice.add(human);
		
		add(opponentQuestion);
		add(human);
		add(computer);
		
		next.setBounds(600,500,100,50);
		next.addActionListener(this);
		add(next);
		
		help.setBounds(600,430,100,50);
		help.addActionListener(this);
		add(help);
		
		
		setVisible(true);
	}
	//Allows user to choose the amount of stones they want to play with from 2-5 stones 
	public static void getChoices() {
		if(stones2.isSelected()) {
			stoneChoice = 2;
		}
		else if(stones3.isSelected()) {
			stoneChoice = 3;
		}
		else if(stones4.isSelected()) {
			stoneChoice = 4;
		}
		else  {
			stoneChoice = 5;
		}
		// returns users names
		p1Name = p1NameBox.getText();
		p2Name = p2NameBox.getText();
		
		// returns the the name computer if user selected computer 
		if(computer.isSelected()) {
			vsAI = true;
			p2Name = "computer";
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//if next button is clicked go to stone game frame
		if(e.getSource()==next) {
			getChoices();
			setVisible(false);
			stoneGame stoneGame = new stoneGame();
			
		}
		// if help button clicked go to rules page
		if(e.getSource()==help) {
			setVisible(false);
			Rules rules = new Rules();
		}
	}
}