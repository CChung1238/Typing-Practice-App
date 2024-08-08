import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class About {
	
	JPanel aboutPanel;
	JLabel pageTitle;
	JButton exitBtn;
	JPanel explanation;
	
	private TypingPractice typingPractice;
	
	
	public About(TypingPractice typingPractice) {
		this.typingPractice = typingPractice; 
		initAboutPage();
	}
	
	public JPanel getPanel() {
		return aboutPanel;
	}
	
	public void initAboutPage() {
		aboutPanel = new JPanel();
		aboutPanel.setLayout(null);
		aboutPanel.setBackground(Color.black);
		aboutPanel.setBounds(0, 0, 1096, 720);
		
		pageTitle = new JLabel("About");        		
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
        exitBtn.addActionListener(new ActionListener() {        	
            @Override
            public void actionPerformed(ActionEvent e) {
                typingPractice.showMainMenu();
            }
        });
        
        explanation = new JPanel();
        explanation.setBounds(100, 200, 896, 420);
        explanation.setBackground(Color.black);
        explanation.setForeground(Color.white);
        explanation.setFocusable(false);
        explanation.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        explanation.setLayout(new BoxLayout(explanation, BoxLayout.Y_AXIS));
        
        JPanel section1 = createSection(" # How fast people type generally", 
        		 						"  Elementary school (Grades 3–5) : 8–15 WPM\n"
        							  + "  Middle school (Grades 6–8)     : 12–25 WPM\n"
        							  + "  High school (Grades 9–12)      : 20–35 WPM\n"
        							  + "  College/Adult                  : 50 WPM\n");
        
        JPanel section2 = createSection(" # How to measure typing speed?", "  Typing speed is measured by the number of words you can type correctly in a set amount of time.\n"
                + "  A “word” is equivalent to five keystrokes. In this program, use the formula:\n"
                + "  (number of characters / 5) / (time taken in minute)\n");
        
        JPanel section3 = createSection(" # Sources", "  Proverbs: https://en.wikipedia.org/wiki/List_of_proverbial_phrases.\n"
                + "  Stories: https://www.gutenberg.org/cache/epub/2591/pg2591-images.html\n");
        
        JPanel section4 = createSection(" # Maker", "  riplaz884@gmail.com\n"
                + "  https://github.com/CChung1238");
        
        explanation.add(section1);
        explanation.add(section2);
        explanation.add(section3);
        explanation.add(section4);
        
        aboutPanel.add(pageTitle);
        aboutPanel.add(exitBtn);
        aboutPanel.add(explanation);
	}
	
	public JPanel createSection(String titleText, String contentText) {
		JPanel section = new JPanel();
        section.setLayout(new BorderLayout());
        section.setBackground(Color.black);
        
        JTextArea title = new JTextArea();
        title.setFont(new Font("Lucida Console", Font.PLAIN, 30));
        title.setBackground(Color.black);
        title.setForeground(Color.white);
        title.setFocusable(false);
        title.setEditable(false);
        title.setText(titleText);
        
        JTextArea content = new JTextArea();
        content.setFont(new Font("Lucida Console", Font.PLAIN, 15));
        content.setBackground(Color.black);
        content.setForeground(Color.white);
        content.setFocusable(false);
        content.setEditable(false);
        content.setText(contentText);
        
        section.add(title, BorderLayout.NORTH);
        section.add(content, BorderLayout.CENTER);
		
		
		return section;
	}

}
