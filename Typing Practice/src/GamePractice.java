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
	
	String gameOption;
	int crtLevel = 0;
	
	
	JLabel pageTitle;
	JPanel mainPanel;
	JPanel gameMenuPanel;
	JPanel gameLevelPanel;
	JPanel areaWarPanel;
	
	JTextField userText;
	JTextField computerText;
	
	JLabel[] areaLabels;
	
	JButton returnMainMenu;
	JButton returnGameMenu;
	JButton areaWar, flightFight;
	
	JLabel lvDisplay;
	
	String[] words;
	
	private TypingPractice typingPractice;
	private int computerCharIndex = 0;
	private int randomIndex;
	private Timer typingTimer;
	
	public GamePractice(TypingPractice typingPractice) {
		this.typingPractice = typingPractice;

		
		
		// Initialize Game Panel	
		collectWords();
		
		initMainPanel();
		initGameSelctMenu();
		initSelectLevel();
		
		initAreaWarPanel();
	}
	
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
	
	private void initGameSelctMenu() {
		gameMenuPanel = new JPanel();
		gameMenuPanel.setLayout(null);
		gameMenuPanel.setBounds(0, 0, 1096, 720);
		gameMenuPanel.setBackground(Color.black);
		
		JPanel gameMenu = new JPanel();
		gameMenu.setLayout(new GridLayout(2, 1, 5, 5));
		gameMenu.setBounds(300, 295, 480, 127);
		gameMenu.setBackground(Color.black);
		
		areaWar = new JButton("Area War");
		flightFight = new JButton("Flight Fight");
		
		JButton[] gameSelection = {areaWar, flightFight};
		for (JButton button : gameSelection) {
			button.setFont(new Font("Lucida Console", Font.PLAIN, 20));
            button.setForeground(Color.white);
            button.setBackground(Color.black);
            button.setFocusable(false);
            gameMenu.add(button);
            button.addActionListener(new ActionListener() {
            	//@Override
            	public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == areaWar) {
                    	gameOption = "areaWar";
                    	pageTitle.setText("Area War");
                    } else if (e.getSource() == flightFight) {
                    	gameOption = "flightFight";
                    	pageTitle.setText("Flight Fight");
                    }
                    
                    gameLevelPanel.setVisible(true);
                	gameMenuPanel.setVisible(false);
            	}
            });
		}
		
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
        
		gameMenuPanel.add(gameMenu);
		gameMenuPanel.add(returnMainMenu);
		mainPanel.add(gameMenuPanel);
				
		
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
                    
                    if (gameOption == "areaWar") {
                    	areaWarPanel.setVisible(true);
                    	gameLevelPanel.setVisible(false);
                    } else if (gameOption == "flightFight") {
                    	System.out.println(gameOption);
                    }
                    
                    runGame(gameOption);

                    
                    
            	}
            });
		}
		
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
            	gameMenuPanel.setVisible(true);
            	pageTitle.setText("Gaems");
            	eraseBoard();
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
		gameZone.setLayout(new GridLayout(4,4,0,0));
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
            	areaWarPanel.setVisible(false);
            	gameLevelPanel.setVisible(true);
            	eraseBoard();
            }
        });
        
        areaLabels = new JLabel[16];
        for (int i = 0; i < 16; i++) {
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

        areaWarPanel.add(exitBtn);
        areaWarPanel.add(gameZone);
        areaWarPanel.add(typingZone);
        areaWarPanel.add(lvDisplay);
        
        typingZone.add(userName);
        typingZone.add(userText);
        typingZone.add(computerName);
        typingZone.add(computerText);
        
        mainPanel.add(areaWarPanel);
        areaWarPanel.setVisible(false);
	}
	
	private String giveWord() {
		int numOfWords = words.length;
		Random rand = new Random();
		int randomIndex = rand.nextInt(numOfWords);
		
		String word = words[randomIndex];
		
		return word;
	}
	
	
	public void collectWords() {
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
	

	public void playAreaWar() {
		userText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
					compareWords(userText.getText());
					userText.setText("");
				}
			}
		});
	}
	
	public void runGame(String gameOption) {
    	playAreaWar();
    	startComputerTyping(crtLevel);
        userText.requestFocus();
	}
	
	public void compareWords(String text) {
		for (int i = 0; i < areaLabels.length; i++) {
            if (areaLabels[i].getText().equals(text)) {
                Color currentColor = areaLabels[i].getBackground();
                if (currentColor.equals(Color.black)) {
                    areaLabels[i].setBackground(Color.blue);
                } else if (currentColor.equals(Color.blue) || currentColor.equals(Color.red)) {
                    areaLabels[i].setBackground(Color.black);
                }
                areaLabels[i].setText(giveWord());
                break;
            }
        }
	}
	
	public void eraseBoard() {
		
    	typingTimer.stop();
		
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
		
		System.out.println(crtLevel);
		System.out.println(speed);
		
		Random rand = new Random();
		randomIndex = rand.nextInt(16);
		
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
                    compareWords(computerText.getText());
                    computerText.setText("");
                    computerCharIndex = 0;
                    randomIndex = rand.nextInt(16);
                }
            }
        });
		typingTimer.start();
	}
	
	public JPanel getPanel() {
		return mainPanel;
	}
}
