import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class RightPanel extends JPanel{
	
	private ImageIcon searchIcon;
	private JTextArea recipe;
	private JLabel recipeTitle, search;
	private JTextField searchBar;
	private JPanel searchPanel;
	
	public RightPanel(){
		this.recipeTitle = new JLabel("Title",SwingConstants.CENTER);
		this.recipeTitle.setPreferredSize(new Dimension(500,50));
		this.recipeTitle.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		this.recipeTitle.setFont(new Font("Serif", Font.PLAIN, 50));
		
		this.recipe = new JTextArea();
		this.recipe.setPreferredSize(new Dimension(700,500));
		
		this.searchBar = new JTextField("search");
		this.searchBar.addFocusListener(new Prompt());
		
		this.searchIcon = new ImageIcon("./Icons/search.png");
		this.search = new JLabel();
		this.search.setIcon(this.searchIcon);
		this.search.setPreferredSize(new Dimension(30,30));
		
		this.searchPanel = new JPanel();
		this.searchPanel.setPreferredSize(new Dimension(700,30));
		
		this.searchPanel.add(this.search);
		this.searchPanel.add(this.searchBar);
		this.searchPanel.setLayout(new BoxLayout(this.searchPanel,BoxLayout.X_AXIS));
		this.searchPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		
		setLayout(new BorderLayout());
		add(this.recipeTitle,BorderLayout.NORTH);
		add(this.recipe,BorderLayout.CENTER);
		add(this.searchPanel,BorderLayout.SOUTH);
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


