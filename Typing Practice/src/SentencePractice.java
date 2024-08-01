import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SentencePractice {
    JPanel stcPanel;
    JPanel practiceZone;
    JPanel resultPanel;
    JLabel givenStc;    
    JTextField myStc;
    JLabel showTypo;
    JLabel pageTitle;
    JButton exitBtn;
    String[] sampleStc;
    
    JLabel crtSpd, precisie, prevSpd, fastSpd;
    JLabel crtSpd_disp, precisie_disp, prevSpd_disp, fastSpd_disp;

    public SentencePractice() {
        // Initialize components
        stcPanel = new JPanel();
        stcPanel.setLayout(null);
        stcPanel.setBackground(Color.black);
        stcPanel.setBounds(0, 0, 1096, 720);
        
        pageTitle = new JLabel("Sentence Practice");        
        pageTitle.setFont(new Font("Lucida Console", Font.BOLD, 50));
        pageTitle.setBounds(0, 20, 1096, 100);
        pageTitle.setBackground(Color.black);
        pageTitle.setForeground(Color.white);
        pageTitle.setHorizontalAlignment(JLabel.CENTER); 
        pageTitle.setFocusable(false);
        
        exitBtn = new JButton();
        exitBtn.setBounds(956,160,40,40);
        exitBtn.setText("x");
        exitBtn.setFont(new Font("Lucida Console", Font.BOLD, 25));
        exitBtn.setBackground(Color.black);
        exitBtn.setForeground(Color.white);
        exitBtn.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        exitBtn.setFocusable(false);
        exitBtn.addActionListener(null);
        
        practiceZone = new JPanel();
        practiceZone.setBounds(100, 200, 896, 320);
        practiceZone.setLayout(null);
        practiceZone.setBackground(Color.black);
        practiceZone.setForeground(Color.white);
        practiceZone.setBorder(BorderFactory.createLineBorder(Color.white, 2));

        givenStc = new JLabel();
        givenStc.setBounds(10,80,886,80);
        givenStc.setFont(new Font("Lucida Console", Font.PLAIN, 30));
        givenStc.setBackground(Color.black);
        givenStc.setForeground(Color.white);
        givenStc.setBorder(BorderFactory.createEmptyBorder());
        givenStc.setFocusable(false);

        myStc = new JTextField();
        myStc.setBounds(10,160,886,80);
        myStc.setBackground(Color.black);
        myStc.setForeground(Color.gray);
        myStc.setFont(new Font("Lucida Console", Font.PLAIN, 30));
        myStc.setBorder(new MatteBorder(0,0,0,2, Color.white));
        myStc.setFocusable(true);
        
        showTypo = new JLabel();
        showTypo.setBounds(10,70,886,40);
        showTypo.setFont(new Font("Lucida Console", Font.PLAIN, 30));
        showTypo.setForeground(Color.red);
        showTypo.setBorder(BorderFactory.createEmptyBorder());
        showTypo.setFocusable(false);
        
        practiceZone.add(showTypo);
        practiceZone.add(givenStc);
        practiceZone.add(myStc);
        
        // result panel
        resultPanel = new JPanel();
        resultPanel.setLayout(null);
        resultPanel.setBackground(Color.black);
        resultPanel.setBounds(100,520,896,50);        
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        resultPanel.setFocusable(false);
        
        crtSpd = new JLabel("Current Speed");
        precisie = new JLabel("Current Precisie");
        prevSpd = new JLabel("Previous Speed");        
        fastSpd = new JLabel("Fastest Speed");
        crtSpd_disp = new JLabel("0 wpm");
        precisie_disp = new JLabel("0 wpm");        
        prevSpd_disp = new JLabel("0 wpm");
        fastSpd_disp = new JLabel("0 wpm");
        
        prevSpd.setBounds(0,0,200,50);
        prevSpd_disp.setBounds(200,0,99,50);
        crtSpd.setBounds(299,0,200,50);
        crtSpd_disp.setBounds(499,0,99,50);
        precisie.setBounds(598,0,200,50);
        precisie_disp.setBounds(798,0,98,50);
        
        fastSpd.setBounds(100,160,150,40);
        fastSpd_disp.setBounds(250,160, 80,40);
                
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
                
        resultPanel.add(prevSpd);       
        resultPanel.add(prevSpd_disp);        
        resultPanel.add(crtSpd);
        resultPanel.add(crtSpd_disp);
        resultPanel.add(precisie);
        resultPanel.add(precisie_disp);
        
        stcPanel.add(fastSpd);
        stcPanel.add(fastSpd_disp);              
        stcPanel.add(pageTitle);
        stcPanel.add(resultPanel);
        stcPanel.add(exitBtn);
        stcPanel.add(practiceZone);
        
        
        
        //stcPanel.setVisible(true);
        myStc.setFocusable(true);
        
        collectStc();
        giveStc();
        startPractice();
        	
    }

    public JPanel getPanel() {
        return stcPanel;
    }

    public void collectStc() {
        List<String> lines = new ArrayList<>();
        
        try {
            Scanner scanner = new Scanner(new File("src/proverbs.txt"));
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        sampleStc = lines.toArray(new String[0]);
    }

    public void giveStc() {
        int numOfSentence = sampleStc.length;
        Random rand = new Random();
        int randomIndex = rand.nextInt(numOfSentence);
        givenStc.setText(sampleStc[randomIndex]);
    }

    public void compareText() {
    	
        String givenText = givenStc.getText();
        String typedText = myStc.getText();
        
        StringBuilder typoBuilder = new StringBuilder();     
                    
        for (int i = 0; i < typedText.length(); i++) {
            if (i < givenText.length()) {
                if (typedText.charAt(i) != givenText.charAt(i)) {
                    typoBuilder.append("▼");
                } else {
                    typoBuilder.append(" ");
                }
            }

        }
        showTypo.setText(typoBuilder.toString());
    }
    
    public void startPractice() {
    	myStc.addKeyListener(new KeyAdapter() {
           	@Override
            public void keyReleased(KeyEvent e) {
            compareText();
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (myStc.getText().equals(givenStc.getText())) {
                    myStc.setText("");
                    giveStc();
                }
            }
        }
    		
    	});
    }
}
