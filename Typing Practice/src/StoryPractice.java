import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class StoryPractice {
	
	double second = 0.0;
	double crtWPM = 0.0;
	double fastestWPM = 0.0;
	int percentPcs = 100;
	
	int option = 0;
	
	Timer timer = new Timer(100, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			second += 0.1;
		}
	});
	
	JLabel pageTitle;
	
    JPanel mainPanel;
    JPanel storyPanel;
    JPanel practiceZone;
    JPanel resultZone;
    JPanel displayZone;
    JButton returnMainMenu;
    JButton returnStoryMenu;
    
    JTextPane given1, given2, given3, given4, given5;
    JTextField user1, user2, user3, user4, user5;
    
    JTextPane[] givenSentences = {given1, given2, given3, given4, given5};
    JTextField[] userSentences = {user1, user2, user3, user4, user5};
    
    //story select panel
    JPanel storySelect;
    JButton storyA, storyB, storyC, storyD, storyE;
    
    //result panel
    JLabel crtSpd, precisie, prevSpd, fastSpd;
    JLabel crtSpd_disp, precisie_disp, prevSpd_disp, fastSpd_disp;
    
    String[] storyLocation = {"src/StoryCollection/Little_Red_Riding_Hood.txt", 
    						"src/StoryCollection/Rapunzel.txt",
    						"src/StoryCollection/Sleeping_Beauty.txt",
    						"src/StoryCollection/The_Frog_Prince.txt",
    						"src/StoryCollection/The_Golden_Goose.txt"};
   
    String[] displayStory;
    
    
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
		
		storyA = new JButton("Little Red Riding Hood .. 7327 ch");
		storyB = new JButton("Rapunzel ................ 7256 ch");
		storyC = new JButton("Sleeping Beauty ......... 7799 ch");
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
            		user1.requestFocus();
                    if (e.getSource() == storyA) {
                    	option = 0;
                    	pageTitle.setText("Little Red Riding Hood");
                    } else if (e.getSource() == storyB) {
                    	option = 1;
                    	pageTitle.setText("Rapunzel");
                    } else if (e.getSource() == storyC) {
                    	option = 2;
                    	pageTitle.setText("Sleeping Beauty");
                    } else if (e.getSource() == storyD) {
                    	option = 3;
                    	pageTitle.setText("The Frog Prince");
                    } else if (e.getSource() == storyE) {
                    	option = 4;
                    	pageTitle.setText("The Golden Goose");
                    }
                    startPracticing(option);
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
        
        displayZone = new JPanel();
        displayZone.setBounds(10, 30, 876, 370);
        displayZone.setLayout(new GridLayout(10,1,0,10));
        displayZone.setBackground(Color.black);
        displayZone.setForeground(Color.white);
        displayZone.setBorder(BorderFactory.createEmptyBorder());
        
        given1 = new JTextPane();
        given2 = new JTextPane();
        given3 = new JTextPane();
        given4 = new JTextPane();
        given5 = new JTextPane();
        
        givenSentences = new JTextPane[]{given1, given2, given3, given4, given5};
        

        for (JTextPane given : givenSentences) {
        	given.setFont(new Font("Lucida Console", Font.PLAIN, 20));
        	given.setBackground(Color.black);
        	given.setForeground(Color.white);
        	given.setBorder(BorderFactory.createEmptyBorder());
        	given.setFocusable(false);
        }

        
        user1 = new JTextField();
        user2 = new JTextField();
        user3 = new JTextField();
        user4 = new JTextField();
        user5 = new JTextField();
        
        userSentences = new JTextField[]{user1, user2, user3, user4, user5};
        for (JTextField user : userSentences) {
        	user.setBackground(Color.black);
        	user.setForeground(Color.gray);
        	user.setFont(new Font("Lucida Console", Font.PLAIN, 20));
        	user.setBorder(BorderFactory.createEmptyBorder());
        	user.setFocusable(true);
        }
        


        displayZone.add(given1);
        displayZone.add(user1);
        displayZone.add(given2);
        displayZone.add(user2);
        displayZone.add(given3);
        displayZone.add(user3);
        displayZone.add(given4);
        displayZone.add(user4);
        displayZone.add(given5);
        displayZone.add(user5);    
        
        practiceZone.add(displayZone);
        
        // exit button
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
        
        //result panel
        resultZone = new JPanel();
        resultZone.setLayout(null);
        resultZone.setBackground(Color.black);
        resultZone.setBounds(100,590,896,50);        
        resultZone.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        resultZone.setFocusable(false);
        
        crtSpd = new JLabel("Current Speed");
        precisie = new JLabel("Current Precisie");        
        prevSpd = new JLabel("Previous Speed");        
        fastSpd = new JLabel("Fastest Speed");
        crtSpd_disp = new JLabel("0 wpm");
        precisie_disp = new JLabel("100 %");        
        prevSpd_disp = new JLabel("0 wpm");
        fastSpd_disp = new JLabel("0 wpm");
        
        prevSpd.setBounds(0,0,200,50);
        prevSpd_disp.setBounds(200,0,99,50);
        crtSpd.setBounds(299,0,200,50);
        crtSpd_disp.setBounds(499,0,99,50);
        precisie.setBounds(598,0,200,50);
        precisie_disp.setBounds(798,0,98,50);
        
        fastSpd.setBounds(100,135,150,40);
        fastSpd_disp.setBounds(250,135, 80,40);
        
        JLabel[] resultL = { crtSpd, precisie, prevSpd};
        for (JLabel label : resultL) {
        	label.setHorizontalAlignment(JLabel.CENTER);
        	label.setFont(new Font("Lucida Console", Font.PLAIN, 15));
        	label.setForeground(Color.white);	    
        	label.setBorder(new MatteBorder(0,2,0,0, Color.white));
        }
        
        JLabel[] resultR = { crtSpd_disp, precisie_disp, prevSpd_disp, fastSpd, fastSpd_disp };
        for (JLabel label : resultR) {
        	label.setFont(new Font("Lucida Console", Font.PLAIN, 15));
        	label.setForeground(Color.white);	
        }
        
        resultZone.add(prevSpd);       
        resultZone.add(prevSpd_disp);        
        resultZone.add(crtSpd);
        resultZone.add(crtSpd_disp);
        resultZone.add(precisie);
        resultZone.add(precisie_disp);
        
        // add element to story Panel
        
        storyPanel.add(fastSpd);
        storyPanel.add(fastSpd_disp);
        
        storyPanel.add(practiceZone);
        storyPanel.add(resultZone);
        storyPanel.add(returnStoryMenu);

        storyPanel.setVisible(false);
        
        mainPanel.add(storyPanel);
        
	}
	
	private void startPracticing(int option) {
		displayStory = collectStory(storyLocation[option]);
	    given1.setText(displayStory[0]);
	    given2.setText(displayStory[1]);
	    given3.setText(displayStory[2]);
	    given4.setText(displayStory[3]);
	    given5.setText(displayStory[4]);
	    
	    practicing();
	}
	
	public String[] collectStory(String filename) {
		List<String> lines = new ArrayList<>();
		
		try {
			Scanner scanner = new Scanner(new File(filename));
			StringBuilder currentLine = new StringBuilder();
			while (scanner.hasNext()) {
				String word = scanner.next();
				if (currentLine.length() + word.length() + 1 > 72) {
					lines.add(currentLine.toString());
					currentLine = new StringBuilder();
				}
				if (currentLine.length() > 0 ) {
					currentLine.append(" ");
				}
				currentLine.append(word);
			}
			if (currentLine.length() > 0) {
				lines.add(currentLine.toString());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return lines.toArray(new String[0]);
	}

	
	private void practicing() {
		
		user1.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		compareText(1);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	if (user1.getText().equals(given1.getText())) {
                		user1.setEditable(false);
                		user2.setEditable(true);
                		user2.requestFocus();         		
                	}
                }

        	}
        }); 
		
	    user2.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		compareText(2);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	if (user2.getText().equals(given2.getText())) {
                		user2.setEditable(false);
                		user3.setEditable(true);
                		user3.requestFocus();         		
                	}
                }

        	}
        }); 
	    
	    user3.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		compareText(3);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	if (user3.getText().equals(given3.getText())) {
                		user3.setEditable(false);
                		user4.setEditable(true);
                		user4.requestFocus();         		
                	}
                }

        	}
        }); 
	    
	    user4.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		compareText(4);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	if (user4.getText().equals(given4.getText())) {
                		user4.setEditable(false);
                		user5.setEditable(true);
                		user5.requestFocus();         		
                	}
                }

        	}
        }); 
	    
	    user5.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		compareText(5);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	if (user5.getText().equals(given5.getText())) {
                		user5.setEditable(false);
                		user1.setEditable(true);
                		user1.requestFocus();    
                		displayResult();
                	}
                }

        	}
        }); 
	}
	
	private void compareText(int num) {
		num = num -1;
    	String givenText = givenSentences[num].getText();
    	String typedText = userSentences[num].getText();
   	
    	givenSentences[num].getHighlighter().removeAllHighlights();

        Highlighter.HighlightPainter wrongPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);

    	for (int i = 0; i < typedText.length(); i++) {
    		try {
    			
    			if (i < givenText.length()) {
    				if (typedText.charAt(i) != givenText.charAt(i)) {
    					givenSentences[num].getHighlighter().addHighlight(i, i+1, wrongPainter);
    				}
    			}    			
    		} catch (BadLocationException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    private void displayResult() {
    	JOptionPane.showMessageDialog(null, "Keep working!");
    	loadNextSet();
    }
	
	
	private void loadNextSet() {
		int currentIndex = Arrays.asList(displayStory).indexOf(given1.getText());
		
		if (currentIndex + 5 < displayStory.length) {
	        given1.setText(displayStory[currentIndex + 5]);
	        given2.setText(displayStory[currentIndex + 6]);
	        given3.setText(displayStory[currentIndex + 7]);
	        given4.setText(displayStory[currentIndex + 8]);
	        given5.setText(displayStory[currentIndex + 9]);
	    } else {
	        // if story ends, the practice ends
	        JOptionPane.showMessageDialog(null, "You have completed the practice!");
	        typingPractice.showMainMenu();
	    }

	    user1.setText("");
	    user2.setText("");
	    user3.setText("");
	    user4.setText("");
	    user5.setText("");
	    
	    second = 0.0;
	    
	    practicing();
	}

}
