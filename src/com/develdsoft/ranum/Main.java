package com.develdsoft.ranum;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;
import javax.swing.JTextField;

public class Main {
	
	public boolean Restarted = true;
	public int minval;
	public int maxval;
	public int arrayMax;
	public int arrayCountHolder = 0;
	public Random rand =  new Random();
	public List<String> genNumList = new ArrayList<String>();
	public String resultRand;
	
	private JFrame frmRanum;
	private JTextField textFieldStart;
	private JTextField textFieldEnd;
	private JTextField textFieldResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmRanum.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}
	
	public int randomInteger(int min, int max) {
	    int randomNum = min + (int)(Math.random() * ((max - min) + 1));
	    return randomNum;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRanum = new JFrame();
		frmRanum.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frmRanum.getContentPane().setBackground(Color.WHITE);
		frmRanum.setBackground(Color.WHITE);
		frmRanum.setResizable(false);
		frmRanum.setTitle("Ranum");
		frmRanum.setBounds(100, 100, 225, 176);
		frmRanum.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRanum.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Random Number Generator");
		lblTitle.setBackground(Color.WHITE);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 202, 17);
		frmRanum.getContentPane().add(lblTitle);
		
		JLabel lblStart = new JLabel("Start Number:");
		lblStart.setFont(new Font("Arial", Font.PLAIN, 11));
		lblStart.setBounds(20, 39, 78, 14);
		frmRanum.getContentPane().add(lblStart);
		
		JLabel lblEnd = new JLabel("End Number:");
		lblEnd.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEnd.setBounds(20, 64, 66, 14);
		frmRanum.getContentPane().add(lblEnd);
		
		textFieldStart = new JTextField();
		textFieldStart.setBounds(98, 36, 100, 20);
		frmRanum.getContentPane().add(textFieldStart);
		textFieldStart.setColumns(10);
		
		textFieldEnd = new JTextField();
		textFieldEnd.setBounds(98, 61, 100, 20);
		frmRanum.getContentPane().add(textFieldEnd);
		textFieldEnd.setColumns(10);
		
		textFieldResult = new JTextField();
		textFieldResult.setBounds(98, 92, 100, 20);
		frmRanum.getContentPane().add(textFieldResult);
		textFieldResult.setColumns(10);
		textFieldResult.setEditable(false);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBackground(Color.WHITE);
		btnGenerate.setFont(new Font("Arial", Font.PLAIN, 11));
		btnGenerate.setBounds(10, 91, 80, 23);
		frmRanum.getContentPane().add(btnGenerate);
		
		JLabel lblCredits = new JLabel("by develdsoft.com");
		lblCredits.setFont(new Font("Arial", Font.PLAIN, 10));
		lblCredits.setBounds(96, 123, 133, 14);
		frmRanum.getContentPane().add(lblCredits);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.setBackground(Color.WHITE);
		btnRestart.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRestart.setBounds(10, 118, 80, 23);
		frmRanum.getContentPane().add(btnRestart);
		
		//Buttons Events
		
		btnGenerate.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent event) {
				if( Restarted )  {
					int eminval = Integer.parseInt(textFieldStart.getText());
					int emaxval = Integer.parseInt(textFieldEnd.getText());
					if(eminval > emaxval) {
						minval = emaxval;
						maxval = eminval;
						textFieldStart.setText(Integer.toString(minval));
						textFieldEnd.setText(Integer.toString(maxval));
					}else{
						minval = eminval;
						maxval = emaxval;
					}
					textFieldStart.setEditable(false);
				    System.out.println("Minimal Value: "+minval+" | Locked Min Value!");
				    textFieldEnd.setEditable(false);
				    System.out.println("Maximal Value: "+maxval+" | Locked Max Value!");
				    Restarted = false;
				    arrayMax = maxval - minval + 1;
				    System.out.println("Maximum Array Number set to: "+arrayMax);
				    arrayCountHolder += 1;
				    resultRand = Integer.toString(randomInteger(minval, maxval));
				    System.out.println("Random Value: "+resultRand+" | Array Usage: "+arrayCountHolder);
				    arrayCountHolder -= 1;
				}
				arrayCountHolder += 1;
				while(genNumList.contains(resultRand)) {
					resultRand = Integer.toString(randomInteger(minval, maxval));
					System.out.println("Random Value: "+resultRand+" | Array Usage: "+arrayCountHolder);
				}
				genNumList.add(resultRand);
			    textFieldResult.setText(resultRand);
			    if(arrayCountHolder == arrayMax) {
			    	System.out.println("All numbers in range Generated!");
			    	btnGenerate.setEnabled(false);
			    }
			  }
		});
		
		btnRestart.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent event) {
			    Restarted = true;
			    arrayCountHolder = 0;
			    btnGenerate.setEnabled(true);
			    textFieldStart.setText(null);
			    textFieldStart.setEditable(true);
			    textFieldEnd.setText(null);
			    textFieldEnd.setEditable(true);
			    textFieldResult.setText(null);
			    genNumList.clear();
			    System.out.println("Restart... | All values returned to null!");
			  }
		});
		
	}
}
