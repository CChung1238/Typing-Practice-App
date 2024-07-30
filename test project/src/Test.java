import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Test {

    JFrame mainFrame;
    JPanel stcPanel;
    JPanel resultPanel;
	JPanel practiceZone = new JPanel();
    
	JLabel pageTitle = new JLabel();
	JTextPane givenStc = new JTextPane();
	JTextField myStc = new JTextField();

	
	String[] sampleStc;
		
    JLabel crtSpd = new JLabel("Current Speed:");
    JLabel crtPrc = new JLabel("Current Precisie:");
    JLabel prevSpd = new JLabel("Previous Speed:");
    JLabel fastSpd = new JLabel("Fastest Speed:");

    JLabel crtSpd_disp = new JLabel("0 wpm");
    JLabel crtPrc_disp = new JLabel("0 wpm");
    JLabel prevSpd_disp = new JLabel("0 wpm");
    JLabel fastSpd_disp = new JLabel("0 wpm");
	

    Font myFont = new Font("Courier", Font.PLAIN, 20);

    Test() {
        // main Frame
        mainFrame = new JFrame("Typing Practice");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1096, 720);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);

        // sentences practice page
        stcPanel = new JPanel();
        stcPanel.setLayout(null);
        stcPanel.setBackground(Color.black);
        stcPanel.setBounds(0,0,1080,720);

        pageTitle.setText("Sentence Practice");
        pageTitle.setFont(new Font("Courier", Font.BOLD, 50));
        pageTitle.setBounds(0, 0, 1080, 100);
        pageTitle.setBackground(Color.black);
        pageTitle.setForeground(Color.white);
        pageTitle.setHorizontalAlignment(JLabel.CENTER);
        pageTitle.setBorder(BorderFactory.createBevelBorder(0));
        
        
        // Practice zone set up
		practiceZone.setBounds(100, 200, 896, 370);
		practiceZone.setLayout(new GridLayout(2,1,0,0));
		
        practiceZone.setBackground(Color.gray);
        practiceZone.setForeground(Color.white);
        practiceZone.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        
        givenStc.setFont(new Font("Courier", Font.PLAIN, 30));
        givenStc.setEditable(false);
        givenStc.setBackground(Color.GRAY);
        givenStc.setForeground(Color.white);
        
        
        myStc.setFont(new Font("Courier", Font.PLAIN, 30));
        myStc.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		compareText();
        	}
        });

        
        practiceZone.add(new JScrollPane(givenStc));
        practiceZone.add(myStc);

        
        
        //resultPanel set up
        
        resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(1,4,0,0));
        resultPanel.setBackground(Color.gray);
        resultPanel.setBounds(100,570,896,50);
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        
	    crtSpd.setHorizontalAlignment(JLabel.CENTER);
	    crtSpd.setFont(new Font("Courier", Font.PLAIN, 15));
	    crtPrc.setFont(new Font("Courier", Font.PLAIN, 15));
	    fastSpd.setFont(new Font("Courier", Font.PLAIN, 15));
	    prevSpd.setFont(new Font("Courier", Font.PLAIN, 15));
	    crtSpd_disp.setFont(new Font("Courier", Font.PLAIN, 15));
	    crtPrc_disp.setFont(new Font("Courier", Font.PLAIN, 15));
	    fastSpd_disp.setFont(new Font("Courier", Font.PLAIN, 15));
	    prevSpd_disp.setFont(new Font("Courier", Font.PLAIN, 15));
        
        resultPanel.add(crtSpd);
        resultPanel.add(crtSpd_disp);
        resultPanel.add(crtPrc);
        resultPanel.add(crtPrc_disp);
        resultPanel.add(fastSpd);
        resultPanel.add(fastSpd_disp);
        resultPanel.add(prevSpd);
        resultPanel.add(prevSpd_disp);
              
        
        collectStc();

    	int numOfSentence = sampleStc.length;
    	
    	Random rand = new Random();
    	int randomIndex = rand.nextInt(numOfSentence);
    	givenStc.setText(sampleStc[randomIndex]);
        
        stcPanel.add(pageTitle);
        stcPanel.add(practiceZone);
        stcPanel.add(resultPanel);

        mainFrame.add(stcPanel);

        stcPanel.setVisible(true);
        mainFrame.setVisible(true);
        
    }
    
    public void collectStc() {
    	
    	List<String> lines = new ArrayList<>();

    	
    	try {
			Scanner scanner = new Scanner(new File("src/proverbs.txt"));
			while(scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	sampleStc = lines.toArray(new String[0]);
    	
    }
   
    
    public void compareText() {
    	String givenText = givenStc.getText();
    	String typedText = myStc.getText();
    	
    	givenStc.getHighlighter().removeAllHighlights();
    	
    	Highlighter.HighlightPainter correctPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.green);
    	Highlighter.HighlightPainter wrongPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
    	
    	for (int i = 0; i < typedText.length(); i++) {
    		try {
    			if (i < givenText.length() && typedText.charAt(i) == givenText.charAt(i)) {
    				givenStc.getHighlighter().addHighlight(i, i+1,  correctPainter);
    			}
    			else {
    				givenStc.getHighlighter().addHighlight(i, i+1,  wrongPainter);
    			}
    		} catch (BadLocationException e) {
    			e.printStackTrace();
    		}
    	}
    	
    }
     

    public static void main(String[] args) {
        new Test();
    }
}
