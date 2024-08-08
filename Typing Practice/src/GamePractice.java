import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GamePractice {
	
	private TypingPractice typingPractice;
	
	JLabel pageTitle;
	JPanel mainPanel;
	JPanel gameStartPanel; 
	JPanel gameLevelPanel;
	JPanel areaWarPanel;
	
	JTextField userText;
	JTextField computerText;
	
	JLabel[] areaLabels;	
	
	JLabel lvDisplay;
	
	String[] words;
	
	int seconds = 100;
	JLabel seconds_left;
	
	int crtLevel = 0;
	private int computerCharIndex = 0;
	private int randomIndex;

	private Timer typingTimer;
	
	Timer timeRemain = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			seconds--;
			seconds_left.setText(String.valueOf(seconds) + "s");
			
			if (seconds <= 0) {
				timeOut();
			}
		}
	});
	
	
	public GamePractice(TypingPractice typingPractice) {
		this.typingPractice = typingPractice;
		
		collectWords();
		
		// Initialize each Panel
		initMainPanel();
		initGameStartPanel();
		initSelectLevel();		
		initAreaWarPanel();
	}
	
	//  initialize main game panel
	private void initMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.black);
		mainPanel.setBounds(0,0,1096,720);
		
        pageTitle = new JLabel("Games");        
        pageTitle.setFont(new Font("Lucida Console", Font.BOLD, 50));
        pageTitle.setBounds(0, 20, 1096, 100);
        pageTitle.setBackground(Color.black);
        pageTitle.setForeground(Color.white);
        pageTitle.setHorizontalAlignment(JLabel.CENTER); 
        pageTitle.setFocusable(false);
        
        mainPanel.add(pageTitle);
	}
	
	// initialize game start panel
	private void initGameStartPanel() {
		gameStartPanel = new JPanel();
		gameStartPanel.setLayout(null);
		gameStartPanel.setBounds(0, 0, 1096, 720);
		gameStartPanel.setBackground(Color.black);
		
		JButton areaWar;
		areaWar = new JButton("Start Game");
		areaWar.setBounds(300,295,480,60);
		areaWar.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		areaWar.setForeground(Color.white);
		areaWar.setBackground(Color.black);
		areaWar.setFocusable(false);
        areaWar.addActionListener(new ActionListener() {
            	//@Override
            	public void actionPerformed(ActionEvent e) {
                    pageTitle.setText("Area War");
                    gameLevelPanel.setVisible(true);
                	gameStartPanel.setVisible(false);
            	}
            });
		
        // return to main menu
		JButton returnMainMenu;
		returnMainMenu = new JButton();
        returnMainMenu.setBounds(740,250,40,40);
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
            }
        });
        
        JPanel viewRules = new JPanel();
        viewRules.setBounds(100,400,896,220);
        viewRules.setLayout(null);
        viewRules.setBackground(Color.black);
        viewRules.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        viewRules.setFocusable(false);
        
        JLabel gameTitle = new JLabel("Area War");
        gameTitle.setBounds(10,5,150,30);
        gameTitle.setFont(new Font("Lucida Console", Font.PLAIN, 30));
        gameTitle.setForeground(Color.white);
        gameTitle.setBackground(Color.black);
        
        JTextArea rules = new JTextArea();
        rules.setBounds(10,40,880,170);
        rules.setText("Area War is an typing game where you face off against the computer on a 5x5 grid. Type words quickly and accurately to turn squares blue and claim them before the computer turns them red.\r\n"
        		+ "\n"
        		+ "You can revert red squares to black by typing the words on them,\nbut beware—the computer might reclaim your blue squares in the process!\r\n"
        		+ "\n"
        		+ "Good luck in this high-speed typing challenge!");
        rules.setFont(new Font("Lucida Console", Font.PLAIN, 20));
        rules.setForeground(Color.white);
        rules.setBackground(Color.black);
        rules.setEditable(false);
        rules.setFocusable(false);
        rules.setLineWrap(true);
        rules.setWrapStyleWord(true);
        
        viewRules.add(gameTitle);
        viewRules.add(rules);
        
		gameStartPanel.add(areaWar);
		gameStartPanel.add(returnMainMenu);
		gameStartPanel.add(viewRules);
		mainPanel.add(gameStartPanel);
	}
	
	private void initSelectLevel() {
		gameLevelPanel = new JPanel();
		gameLevelPanel.setLayout(null);
		gameLevelPanel.setBounds(0, 0, 1096, 720);
		gameLevelPanel.setBackground(Color.black);
		
		JPanel gameLevel = new JPanel();
		gameLevel.setLayout(new GridLayout(7, 1, 5, 5));
		gameLevel.setBounds(300, 220, 480, 320);
		gameLevel.setBackground(Color.black);
		
		JButton level1 = new JButton("LEVEL 1");
		JButton level2 = new JButton("LEVEL 2");
		JButton level3 = new JButton("LEVEL 3");
		JButton level4 = new JButton("LEVEL 4");
		JButton level5 = new JButton("LEVEL 5");
		JButton level6 = new JButton("LEVEL 6");
		JButton level7 = new JButton("LEVEL 7");
		
		JButton[] levelSelection = {level1, level2, level3, level4, level5, level6, level7};
		for (JButton button : levelSelection) {
			button.setFont(new Font("Lucida Console", Font.PLAIN, 20));
            button.setForeground(Color.white);
            button.setBackground(Color.black);
            button.setFocusable(false);
            gameLevel.add(button);
            button.addActionListener(new ActionListener() {
            	//@Override
            	public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == level1) {
                    	crtLevel = 1;
                    } else if (e.getSource() == level2) {
                    	crtLevel = 2;
                    } else if (e.getSource() == level3) {
                    	crtLevel = 3;
                    } else if (e.getSource() == level4) {
                    	crtLevel = 4;
                    } else if (e.getSource() == level5) {
                    	crtLevel = 5;
                    } else if (e.getSource() == level6) {
                    	crtLevel = 6;
                    } else if (e.getSource() == level7) {
                    	crtLevel = 7;
                    }
                    
                    lvDisplay.setText("Lv." + String.valueOf(crtLevel));

                	areaWarPanel.setVisible(true);
                	gameLevelPanel.setVisible(false);
                    
                    runGame();
            	}
            });
		}
		
		JButton returnGameMenu;
		returnGameMenu = new JButton();
		returnGameMenu.setBounds(740,175,40,40);
		returnGameMenu.setText("x");
		returnGameMenu.setFont(new Font("Lucida Console", Font.BOLD, 25));
		returnGameMenu.setBackground(Color.black);
		returnGameMenu.setForeground(Color.white);
		returnGameMenu.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		returnGameMenu.setFocusable(false);
		returnGameMenu.addActionListener(new ActionListener() {        	
            @Override
            public void actionPerformed(ActionEvent e) {
            	gameLevelPanel.setVisible(false);
            	gameStartPanel.setVisible(true);
            	pageTitle.setText("Games");
            	clearBoard();
            }
        });
        
        gameLevelPanel.add(gameLevel);
        gameLevelPanel.add(returnGameMenu);
        mainPanel.add(gameLevelPanel);
        gameLevelPanel.setVisible(false);
	}
	
	public void initAreaWarPanel() {
		areaWarPanel = new JPanel();
		areaWarPanel.setLayout(null);
		areaWarPanel.setBounds(0, 0, 1096, 720);
		areaWarPanel.setBackground(Color.black);
		
		JPanel gameZone = new JPanel();
		gameZone.setBounds(100, 200, 896, 370);
		gameZone.setLayout(new GridLayout(5,5,0,0));
		gameZone.setBackground(Color.black);
        gameZone.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        
        JButton exitBtn = new JButton();
        exitBtn.setBounds(956,160,40,40);
        exitBtn.setText("x");
        exitBtn.setFont(new Font("Lucida Console", Font.BOLD, 25));
        exitBtn.setBackground(Color.black);
        exitBtn.setForeground(Color.white);
        exitBtn.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        exitBtn.setFocusable(false);
        exitBtn.addActionListener(new ActionListener() {        	
            @Override
            public void actionPerformed(ActionEvent e) {  	
            	SwingUtilities.invokeLater(new Runnable() {
            		public void run() {
            			clearBoard();
            			exitGame();
            		}
            	});
            	
            }
        });
        
        areaLabels = new JLabel[25];
        for (int i = 0; i < areaLabels.length; i++) {
        	areaLabels[i] = new JLabel();
        	areaLabels[i].setText(giveWord());
        	areaLabels[i].setForeground(Color.white);
        	areaLabels[i].setOpaque(true);
        	areaLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
        	areaLabels[i].setBackground(Color.black);
        	areaLabels[i].setFont(new Font("Lucida Console", Font.PLAIN, 20));
        	areaLabels[i].setBorder(BorderFactory.createLineBorder(Color.white, 4));
        	gameZone.add(areaLabels[i]);
        }
        
        JPanel typingZone = new JPanel();
        typingZone.setBounds(100, 570, 896, 50);
        typingZone.setLayout(null);
        typingZone.setBackground(Color.black);
        typingZone.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        
        JLabel userName = new JLabel();
        userName.setBounds(10,0,50,50);
        userName.setBackground(Color.black);
        userName.setForeground(Color.white);
        userName.setFont(new Font("Lucida Console", Font.PLAIN, 15));
        userName.setText("You: ");
        
        userText = new JTextField();
        userText.setBounds(60,10,200,30);
        userText.setBackground(Color.black);
        userText.setForeground(Color.white);
        userText.setFont(new Font("Lucida Console", Font.PLAIN, 15));
        userText.setBorder(BorderFactory.createEmptyBorder());
        userText.setCaretColor(Color.red);
        userText.setText("");
        
        JLabel computerName = new JLabel();
        computerName.setBounds(445,0,110,50);
        computerName.setBackground(Color.black);
        computerName.setForeground(Color.white);
        computerName.setFont(new Font("Lucida Console", Font.PLAIN, 15));
        computerName.setBorder(new MatteBorder(0,7,0,0, Color.white));
        computerName.setText(" Computer: ");
        
        computerText = new JTextField();
        computerText.setBounds(555,10,200,30);
        computerText.setBackground(Color.black);
        computerText.setForeground(Color.white);
        computerText.setFont(new Font("Lucida Console", Font.PLAIN, 15));
        computerText.setBorder(BorderFactory.createEmptyBorder());
        computerText.setCaretColor(Color.red);
        computerText.setText("");
        computerText.setEditable(false);
        computerText.setFocusable(false);
        
        lvDisplay = new JLabel("Lv.");
        lvDisplay.setBounds(100,160,150,40);
        lvDisplay.setFont(new Font("Lucida Console", Font.PLAIN, 15));
        lvDisplay.setForeground(Color.white);
        
        typingZone.add(userName);
        typingZone.add(userText);
        typingZone.add(computerName);
        typingZone.add(computerText);
        
        JLabel timeDisplay = new JLabel("Time left: ");
        timeDisplay.setBounds(550,160,150,40);
        timeDisplay.setBackground(Color.black);
        timeDisplay.setForeground(Color.white);
        timeDisplay.setFont(new Font("Lucida Console", Font.BOLD, 15));
        
        seconds_left = new JLabel("100s");
        seconds_left.setBounds(680,160,80,40);
        seconds_left.setBackground(Color.black);
        seconds_left.setForeground(Color.white);
        seconds_left.setFont(new Font("Lucida Console", Font.BOLD, 15));

        areaWarPanel.add(exitBtn);
        areaWarPanel.add(gameZone);
        areaWarPanel.add(typingZone);
        areaWarPanel.add(lvDisplay);
        areaWarPanel.add(timeDisplay);
        areaWarPanel.add(seconds_left);
        
        mainPanel.add(areaWarPanel);
        areaWarPanel.setVisible(false);
	}	
	
	private void collectWords() {
		List<String> lines = new ArrayList<>();
        
        try {
            Scanner scanner = new Scanner(new File("src/GameWords.txt"));
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        words = lines.toArray(new String[0]);
	}
	
	private String giveWord() {
		int numOfWords = words.length;
		
		Random rand = new Random();
		int randomIndex = rand.nextInt(numOfWords);
		
		String word = words[randomIndex];
		
		return word;
	}
	

	private void playAreaWar() {
		userText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
					compareWords(userText.getText(), 0);
					userText.setText("");
				}
			}
		});
	}
	
	private void runGame() {
    	playAreaWar();
    	startComputerTyping(crtLevel);
    	timeRemain.start();
        userText.requestFocus();
	}
	
	public void compareWords(String text, int player) { 	// player = 0: user player = 1: computer
		for (int i = 0; i < areaLabels.length; i++) {
            if (areaLabels[i].getText().equals(text)) {
                Color currentColor = areaLabels[i].getBackground();
                if (currentColor.equals(Color.black)) {
                	if (player == 0) {
                		areaLabels[i].setBackground(Color.blue);
                	} else if (player == 1) {
                		areaLabels[i].setBackground(Color.red);
                	}
                } else if (currentColor.equals(Color.red)) {
                	if (player == 0) {
                		areaLabels[i].setBackground(Color.black);
                	}  
                } else if (currentColor.equals(Color.blue)) {
                	if (player == 1) {
                		areaLabels[i].setBackground(Color.black);
                	}
                }
                areaLabels[i].setText(giveWord());
                break;
            }
        }
		
		boolean isAllBlue = true;
        for (int i = 0; i < areaLabels.length ; i++) {
        	Color currentColor = areaLabels[i].getBackground();
        	if (!currentColor.equals(Color.blue)) {
        		isAllBlue = false;
                break;
            }
        }
        
        if (isAllBlue == true) {
        	endAreaWar(true);
        }
	}
	
	public void clearBoard() {
		timeRemain.stop();
		seconds = 100;
		seconds_left.setText("100s");
		
    	typingTimer.stop();
    	computerText.setText("");
    	userText.setText("");

    	// set every word label's background color black
		for (int i = 0; i < areaLabels.length; i++) {
            Color currentColor = areaLabels[i].getBackground();
            if (currentColor.equals(Color.blue) || currentColor.equals(Color.red)) {
                areaLabels[i].setBackground(Color.black);
            }
        }
        
	}
	
	public void startComputerTyping(int crtLevel) {
		int speed = 0;		
		
		switch(crtLevel) {
		case 1 :
			speed = 800;
			break;
		case 2 :
			speed = 480;
			break;
		case 3 :
			speed = 343;
			break;
		case 4 :
			speed = 267;
			break;
		case 5 :
			speed = 218;
			break;
		case 6 :
			speed = 185;
			break;
		case 7 :
			speed = 160;
			break;
		}
		
		// to randomly select words to be entered by a computer
		Random rand = new Random();
		randomIndex = rand.nextInt(areaLabels.length);
		
		// if formal typingTimer wasn't stopped, it will stop here
		if (typingTimer != null) {
			typingTimer.stop();
		}
		
		
		typingTimer = new Timer(speed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                String word = areaLabels[randomIndex].getText();
                
                if (computerCharIndex < word.length()) {
                    computerText.setText(computerText.getText() + word.charAt(computerCharIndex));
                    computerCharIndex++;
                } else {
                    compareWords(computerText.getText(), 1);
                    computerText.setText("");
                    computerCharIndex = 0;
                    
                    boolean found = false;
                    for (int i = 0; i < areaLabels.length ; i++) {
                    	Color currentColor = areaLabels[i].getBackground();
                    	if (!currentColor.equals(Color.red)) {
                            found = true;
                            break;
                        }
                    }
                    
                    if (found) {
                    	Color currentColor;
                        do {
                        	randomIndex = rand.nextInt(areaLabels.length);
                        	currentColor = areaLabels[randomIndex].getBackground();
                        } while (currentColor.equals(Color.red));
                    } else {
                    	endAreaWar(false);
                    }
                }
            }
        });
		typingTimer.start();
	}
	
	private void timeOut() {
		int playerLabel = 0;
		int computerLabel = 0;
		
        for (int i = 0; i < areaLabels.length ; i++) {
        	Color currentColor = areaLabels[i].getBackground();
        	if (currentColor.equals(Color.blue)) {
        		playerLabel++;
            } else if (currentColor.equals(Color.red)) {
            	computerLabel++;
            }
        }
        
        if (playerLabel > computerLabel) {
        	endAreaWar(true);
        } else {
        	endAreaWar(false);
        }
	}
	
	private void endAreaWar(boolean win) {
		int keepPractice;
		String computerWon;
		String userWon;
		String userCompeleteTheGame;
		
		typingTimer.stop();
		timeRemain.stop();
		seconds = 100;
		
		computerWon = "The computer has won! Keep practicing to improve your skills.\nWould you like to try again?";
		userWon = "Congratulations, you won! You've mastered this level.\nWould you like to challenge the next level?";
		userCompeleteTheGame = "Congratulations, you won! You've mastered every level.\nWould you like to play this level again?";
		
		if (win == false) {
	    	keepPractice = JOptionPane.showConfirmDialog(null, computerWon, "Game Over", JOptionPane.YES_NO_OPTION);
		} else if (win == true && crtLevel == 7)  {
			keepPractice = JOptionPane.showConfirmDialog(null, userCompeleteTheGame, "Complete the game!", JOptionPane.YES_NO_OPTION);
		} else {
			keepPractice = JOptionPane.showConfirmDialog(null, userWon, "Victory!", JOptionPane.YES_NO_OPTION);
			if (keepPractice == JOptionPane.YES_OPTION) {
				crtLevel++;
				lvDisplay.setText("Lv." + String.valueOf(crtLevel));
			}
		}
		
		if (keepPractice == JOptionPane.NO_OPTION) {
			clearBoard();
    		exitGame();
    	} else if (keepPractice == JOptionPane.YES_OPTION) {
    		clearBoard();
    		runGame();
    	}
		
		
	}
	
	private void exitGame() {			
		gameLevelPanel.setVisible(true);
    	areaWarPanel.setVisible(false);
    	pageTitle.setText("Area War");
	}
	
	public JPanel getPanel() {
		return mainPanel;
	}
}
