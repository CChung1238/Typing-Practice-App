import javax.swing.*;
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
    JTextPane givenStc;    
    JTextField myStc;
    JLabel showTypo;
    JLabel pageTitle;
    JButton exitBtn;
    String[] sampleStc;
    
    JLabel crtSpd = new JLabel("Current Speed");
    JLabel crtPrc = new JLabel("Current Precisie");
    JLabel prevSpd = new JLabel("Previous Speed");
    JLabel fastSpd = new JLabel("Fastest Speed");

    JLabel crtSpd_disp = new JLabel("0 wpm");
    JLabel crtPrc_disp = new JLabel("0 wpm");
    JLabel prevSpd_disp = new JLabel("0 wpm");
    JLabel fastSpd_disp = new JLabel("0 wpm");

    public SentencePractice() {
        // Initialize components
        stcPanel = new JPanel();
        stcPanel.setLayout(null);
        stcPanel.setBackground(Color.black);
        stcPanel.setBounds(0, 0, 1096, 720);
        
        pageTitle = new JLabel("Sentence Practice");
        
        pageTitle.setFont(new Font("Lucida Console", Font.BOLD, 50));
        pageTitle.setBounds(0, 20, 1080, 100);
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
        
        practiceZone = new JPanel();
        practiceZone.setBounds(100, 200, 896, 320);
        practiceZone.setLayout(new GridLayout(4, 1, 0, 0));
        practiceZone.setBackground(Color.black);
        practiceZone.setForeground(Color.white);
        practiceZone.setBorder(BorderFactory.createLineBorder(Color.white, 2));

        givenStc = new JTextPane();
        givenStc.setEditable(false);
        givenStc.setFont(new Font("Lucida Console", Font.PLAIN, 30));
        givenStc.setFocusable(false);
        givenStc.setBackground(Color.black);
        givenStc.setForeground(Color.white);
        givenStc.setBorder(BorderFactory.createEmptyBorder());

        myStc = new JTextField();
        
        myStc.setFont(new Font("Lucida Console", Font.PLAIN, 30));
        myStc.setBorder(BorderFactory.createEmptyBorder());
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
        
        showTypo = new JLabel();
        showTypo.setFont(new Font("Lucida Console", Font.PLAIN, 30));
        showTypo.setForeground(Color.red);
        showTypo.setFocusable(false);
        showTypo.setBorder(BorderFactory.createEmptyBorder());
        
        // result panel
        resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(2,4,0,0));
        resultPanel.setBackground(Color.black);
        resultPanel.setBounds(100,520,896,100);
        resultPanel.setFocusable(false);
        
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        
	    crtSpd.setHorizontalAlignment(JLabel.CENTER);
	    crtSpd.setHorizontalAlignment(JLabel.CENTER);
	    crtPrc.setHorizontalAlignment(JLabel.CENTER);
	    fastSpd.setHorizontalAlignment(JLabel.CENTER);
	    prevSpd.setHorizontalAlignment(JLabel.CENTER);
	    
	    crtSpd_disp.setHorizontalAlignment(JLabel.CENTER);
	    crtPrc_disp.setHorizontalAlignment(JLabel.CENTER);
	    fastSpd_disp.setHorizontalAlignment(JLabel.CENTER);
	    prevSpd_disp.setHorizontalAlignment(JLabel.CENTER);
	    
	    
	    crtSpd.setFont(new Font("Lucida Console", Font.PLAIN, 15));
	    crtPrc.setFont(new Font("Lucida Console", Font.PLAIN, 15));
	    fastSpd.setFont(new Font("Lucida Console", Font.PLAIN, 15));
	    prevSpd.setFont(new Font("Lucida Console", Font.PLAIN, 15));
	    crtSpd_disp.setFont(new Font("Lucida Console", Font.PLAIN, 15));
	    crtPrc_disp.setFont(new Font("Lucida Console", Font.PLAIN, 15));
	    fastSpd_disp.setFont(new Font("Lucida Console", Font.PLAIN, 15));
	    prevSpd_disp.setFont(new Font("Lucida Console", Font.PLAIN, 15));
	    
	    crtSpd.setForeground(Color.white);	    
	    crtPrc.setForeground(Color.white);
	    fastSpd.setForeground(Color.white);
	    prevSpd.setForeground(Color.white);

	    crtSpd_disp.setForeground(Color.white);
	    crtPrc_disp.setForeground(Color.white);
	    fastSpd_disp.setForeground(Color.white);
	    prevSpd_disp.setForeground(Color.white);
        
        resultPanel.add(crtSpd);
        resultPanel.add(crtPrc);
        resultPanel.add(fastSpd);
        resultPanel.add(prevSpd);
        
        resultPanel.add(crtSpd_disp);
        resultPanel.add(crtPrc_disp);
        resultPanel.add(fastSpd_disp);
        resultPanel.add(prevSpd_disp);
                

        practiceZone.add(showTypo);
        practiceZone.add(givenStc);
        practiceZone.add(myStc);

        stcPanel.add(pageTitle);
        stcPanel.add(resultPanel);
        stcPanel.add(exitBtn);
        stcPanel.add(practiceZone);
        
        collectStc();
        giveStc();
        
        stcPanel.setVisible(true);
        myStc.setFocusable(true);
        	
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
                
        typoBuilder.append("\n");
    

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
}
