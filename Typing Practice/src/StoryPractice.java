import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class StoryPractice {
	
	JLabel pageTitle;
	
    JPanel mainPanel;
    JPanel storyPanel;
    JPanel practiceZone;
    JPanel resultPanel;
    JButton returnMainMenu;
    JButton returnStoryMenu;
    
    //story select panel
    JPanel storySelect;
    JButton storyA, storyB, storyC, storyD, storyE;
    
    private TypingPractice typingPractice;
	
	public StoryPractice(TypingPractice typingPractice) {
		this.typingPractice = typingPractice;
		initmainPanel();
		initStorySelectPanel();
		initPracticeZone();
		
	}
	
	public JPanel getPanel() {
		return mainPanel;
	}
	
	private void initmainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.black);
		mainPanel.setBounds(0,0,1096,720);
		
        pageTitle = new JLabel("Story Practice");        
        pageTitle.setFont(new Font("Lucida Console", Font.BOLD, 50));
        pageTitle.setBounds(0, 20, 1096, 100);
        pageTitle.setBackground(Color.black);
        pageTitle.setForeground(Color.white);
        pageTitle.setHorizontalAlignment(JLabel.CENTER); 
        pageTitle.setFocusable(false);
        
        mainPanel.add(pageTitle);
	}
	
	private void initStorySelectPanel() {
		storySelect = new JPanel();
		storySelect.setLayout(null);
		storySelect.setBounds(0, 0, 1096, 720);
		storySelect.setBackground(Color.black);
		
		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(5, 1, 5, 5));
		menu.setBounds(300, 220, 480, 320);
		menu.setBackground(Color.black);
		
		storyA = new JButton("Sleeping Beauty ......... 7799 ch");
		storyB = new JButton("Little Red Riding Hood .. 7327 ch");
		storyC = new JButton("Rapunzel ................ 7256 ch");
		storyD = new JButton("The Frog Prince ......... 6024 ch");
		storyE = new JButton("The Golden Goose ........ 8079 ch");
		
		JButton[] storyOption = {storyA, storyB, storyC, storyD, storyE};
		
		for (JButton button : storyOption) {
			button.setFont(new Font("Lucida Console", Font.PLAIN, 20));
            button.setForeground(Color.white);
            button.setBackground(Color.black);
            button.setFocusable(false);
            menu.add(button);
            button.addActionListener(new ActionListener() {
            	//@Override
            	public void actionPerformed(ActionEvent e) {
            		storySelect.setVisible(false);
                    storyPanel.setVisible(true);
            	}
            });
		}
		
        returnMainMenu = new JButton();
        returnMainMenu.setBounds(740,175,40,40);
        returnMainMenu.setText("x");
        returnMainMenu.setFont(new Font("Lucida Console", Font.BOLD, 25));
        returnMainMenu.setBackground(Color.black);
        returnMainMenu.setForeground(Color.white);
        returnMainMenu.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        returnMainMenu.setFocusable(false);
        returnMainMenu.addActionListener(new ActionListener() {        	
            @Override
            public void actionPerformed(ActionEvent e) {
                typingPractice.showMainMenu();
                storySelect.setVisible(true);
                storyPanel.setVisible(false);
                
            }
        });
		
        storySelect.add(menu);
        storySelect.add(returnMainMenu);
		
		mainPanel.add(storySelect);
	}
	
	private void initPracticeZone() {
				
		storyPanel = new JPanel();
		storyPanel.setLayout(null);
		storyPanel.setBackground(Color.black);
		storyPanel.setBounds(0,0,1096,720);
		
		practiceZone = new JPanel();
		practiceZone.setBounds(100, 170, 896, 420);
        practiceZone.setLayout(null);
        practiceZone.setBackground(Color.black);
        practiceZone.setForeground(Color.white);
        practiceZone.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        
        resultPanel = new JPanel();
        resultPanel.setLayout(null);
        resultPanel.setBackground(Color.black);
        resultPanel.setBounds(100,590,896,50);        
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        resultPanel.setFocusable(false);
        
        returnStoryMenu = new JButton();
        returnStoryMenu.setBounds(956,130,40,40);
        returnStoryMenu.setText("x");
        returnStoryMenu.setFont(new Font("Lucida Console", Font.BOLD, 25));
        returnStoryMenu.setBackground(Color.black);
        returnStoryMenu.setForeground(Color.white);
        returnStoryMenu.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        returnStoryMenu.setFocusable(false);
        returnStoryMenu.addActionListener(new ActionListener() {        	
            @Override
            public void actionPerformed(ActionEvent e) {
            	storySelect.setVisible(true);
            	storyPanel.setVisible(false);
                
            }
        });
        
        storyPanel.add(practiceZone);
        storyPanel.add(resultPanel);
        storyPanel.add(returnStoryMenu);

        storyPanel.setVisible(false);
        
        mainPanel.add(storyPanel);
        
	}

}
