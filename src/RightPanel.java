import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


public class RightPanel extends JPanel{
	
	private ImageIcon searchIcon;
	private JTextArea recipe;
	private JLabel recipeTitle, tags;
	private JButton search;
	private JTextField searchBar;
	private JPanel searchPanel,mainPanel,titlePanel;
	
	public RightPanel(){
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		this.recipeTitle = new JLabel("Title",SwingConstants.CENTER);
		this.recipeTitle.setPreferredSize(new Dimension(500,50));
		this.recipeTitle.setFont(new Font("Serif", Font.PLAIN, 50));
		
		this.tags = new JLabel("Tags:");
		this.tags.setBorder(BorderFactory.createBevelBorder(1));
		
		this.recipe = new JTextArea();
		this.recipe.setPreferredSize(new Dimension(700,500));
		
		this.searchBar = new JTextField("search");
		this.searchBar.addFocusListener(new Prompt());
		
		this.searchIcon = new ImageIcon("./Icons/search.png");
		this.search = new JButton();
		this.search.setIcon(this.searchIcon);
		this.search.setPreferredSize(new Dimension(30,30));
		
		this.searchPanel = new JPanel();
		this.searchPanel.setPreferredSize(new Dimension(700,30));
		
		this.searchPanel.add(this.search);
		this.searchPanel.add(this.searchBar);
		this.searchPanel.setLayout(new BoxLayout(this.searchPanel,BoxLayout.X_AXIS));
		
		this.titlePanel = new JPanel();
		this.titlePanel.setLayout(new BorderLayout());
		this.titlePanel.add(this.recipeTitle,BorderLayout.NORTH);
		
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BorderLayout());
		this.mainPanel.add(this.tags,BorderLayout.NORTH);
		this.mainPanel.add(this.recipe,BorderLayout.CENTER);
		this.mainPanel.add(this.searchPanel,BorderLayout.SOUTH);
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(this.titlePanel);
		add(this.mainPanel);
		setPreferredSize(new Dimension(700,600));
	}
	
	public class Prompt implements FocusListener 
	{
	
	 public void focusGained(FocusEvent e)
	 {
		//removes text from searchBar when it regains focus 
	   if(e.getSource() == searchBar)
	   {
	     searchBar.setText("");
	   }
	 }
	 public void focusLost(FocusEvent e)
	 {
	 
 }
}
}


