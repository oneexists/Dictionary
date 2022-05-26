package com.trackers.dictionaryGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.trackers.dictionary.business.facade.Dictionary;
import static com.trackers.dictionaryGUI.MenuEnum.*;

public class MenuGUI implements ActionListener {
	private JFrame frame;
	private JPanel panel;
	private JPanel buttonPanel;
	private JLabel titleLabel;
	private JLabel messageLabel;
	private JButton[] menuButtons = new JButton[10];
	
	public MenuGUI() {
		frame = new JFrame("Tracker Dictionary");
		frame.setSize(600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGUI window = new MenuGUI();
					window.initialize();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		buildHeaderPanel();
		buildButtonPanel();
		frame.setVisible(true);
	}

	private void buildButtonPanel() {
		buttonPanel = new JPanel();
		menuButtons[0] = new JButton(ADD_FLASHCARD.getName());
		menuButtons[1] = new JButton(ADD_VOCABULARY.getName());
		menuButtons[2] = new JButton(SEARCH_FLASHCARD.getName());
		menuButtons[3] = new JButton(SEARCH_VOCABULARY.getName());
		menuButtons[4] = new JButton(REMOVE_FLASHCARD.getName());
		menuButtons[5] = new JButton(REMOVE_VOCABULARY.getName());
		menuButtons[6] = new JButton(RANDOM_FLASHCARD.getName());
		menuButtons[7] = new JButton(RANDOM_VOCABULARY.getName());
		menuButtons[8] = new JButton(SAVE.getName());
		menuButtons[9] = new JButton(EXIT.getName());
		buttonPanel.setLayout(new GridLayout(5,2));
		Arrays.asList(menuButtons).forEach(button -> buttonPanel.add(button));
		frame.add(buttonPanel);		
	}
	
	private void buildHeaderPanel() {
		panel = new JPanel();
		titleLabel = new JLabel("Menu");
		messageLabel = new JLabel();
		panel.setLayout(new FlowLayout());
		panel.add(titleLabel);
		panel.add(messageLabel);
		frame.add(panel, BorderLayout.NORTH);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (ADD_FLASHCARD.getName().equals(command)) {
			
		}
		if (ADD_VOCABULARY.getName().equals(command)) {
			
		}
		if (SEARCH_FLASHCARD.getName().equals(command)) {
			
		}
		if (SEARCH_VOCABULARY.getName().equals(command)) {
			
		}
		if (REMOVE_FLASHCARD.getName().equals(command)) {
			
		}
		if (REMOVE_VOCABULARY.getName().equals(command)) {
			
		}
		if (RANDOM_FLASHCARD.getName().equals(command)) {
			
		}		
		if (RANDOM_VOCABULARY.getName().equals(command)) {
			
		}
		if (SAVE.getName().equals(command)) {
			save();
		}
		if (EXIT.getName().equals(command)) {
			
		}
	}
	
	private void save() {
		if (Dictionary.save()) {
			
		}
	}

}
