import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TypingPractice {
    JFrame mainFrame;    
    JButton sentences, stories, game, about;
    JPanel mainPanel;
	JPanel cardPanel;
	JPanel menuPanel;
    JLabel pageTitle;
    CardLayout cardLayout;

    public TypingPractice() {
        // Initialize main frame
        mainFrame = new JFrame("Typing Practice");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1096, 720);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        
        // Initialize card layout and panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(0, 0, 1096, 720);
        mainPanel = new JPanel();
        
        // Initialize main menu panel
        initMainMenu();
        
        // Add panels to card panel
        cardPanel.add(mainPanel, "MainMenu");
        cardPanel.add(new SentencePractice(this).getPanel(), "SentencePractice");
        cardPanel.add(new StoryPractice(this).getPanel(), "StoryPractice");
        cardPanel.add(new GamePractice(this).getPanel(), "GamePractice");
        cardPanel.add(new About(this).getPanel(), "AboutPanel");

        // Add card panel to frame
        mainFrame.add(cardPanel);
        mainFrame.setVisible(true);        
    }

    private void initMainMenu() {
        sentences = new JButton("SENTENCES");
        stories = new JButton("STORIES");
        game = new JButton("GAMES");
        about = new JButton("ABOUT");

        JButton[] functionButtons = { sentences, stories, game, about };
        
        for (JButton button : functionButtons) {
            button.setFont(new Font("Lucida Console", Font.PLAIN, 20));
            button.setForeground(Color.white);
            button.setBackground(Color.black);
            button.setFocusable(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == sentences) {
                        cardLayout.show(cardPanel, "SentencePractice");
                        SentencePractice.myStc.requestFocusInWindow();
                    } else if (e.getSource() == stories) {
                        cardLayout.show(cardPanel, "StoryPractice");
                    } else if (e.getSource() == game) {
                        cardLayout.show(cardPanel, "GamePractice");
                    } else if (e.getSource() == about) {
                    	cardLayout.show(cardPanel, "AboutPanel");
                    }
                }
            });
        }
        
        mainPanel.setBackground(Color.black);
        mainPanel.setLayout(null);

        pageTitle = new JLabel("Typing Practice");
        
        pageTitle.setFont(new Font("Lucida Console", Font.BOLD, 50));
        pageTitle.setBounds(0, 20, 1080, 100);
        pageTitle.setBackground(Color.black);
        pageTitle.setForeground(Color.white);
        pageTitle.setHorizontalAlignment(JLabel.CENTER);
        
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1, 5, 5));
        menuPanel.setBounds(300, 220, 480, 320);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        menuPanel.setBackground(Color.black);

        menuPanel.add(sentences);
        menuPanel.add(stories);
        menuPanel.add(game);
        menuPanel.add(about);
        
        mainPanel.add(pageTitle);
        mainPanel.add(menuPanel);
    }
    

    public void showMainMenu() {
        cardLayout.show(cardPanel, "MainMenu");
    }
    

}
