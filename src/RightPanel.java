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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.sun.xml.internal.ws.util.StringUtils;


public class RightPanel extends JPanel{
	
	private ImageIcon searchIcon;
	private JTextPane recipe;
	private JScrollPane scrollPane;
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
		
		this.recipe = new JTextPane();
		this.recipe.setPreferredSize(new Dimension(700,500));
		this.recipe.setEditable(false);
		this.recipe.setContentType("text/html");
		
		this.scrollPane = new JScrollPane(this.recipe);
		this.scrollPane.setPreferredSize(new Dimension(700,500));
		
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
		this.mainPanel.add(this.scrollPane,BorderLayout.CENTER);
		this.mainPanel.add(this.searchPanel,BorderLayout.SOUTH);
		
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		add(this.titlePanel);
		add(this.mainPanel);
		setPreferredSize(new Dimension(700,600));
		
        String[] ingredients = new String[]{"ingredient1", "ingredient2"};
        String[] ingredients2 = new String[]{"ingredient2", "ingredient2"};
        String[] category = new String[]{"Drinks"};
        String[] direction = new String[]{"Stir"};
        Recipe first  = new Recipe("testRecipe", "testRecipe", ingredients, ingredients2, category, "test");
        displayRecipe(first);
	}
	
	
    public void displayRecipe(Recipe recipe){
    	this.recipeTitle.setText(recipe.name);
    	this.tags.setText("tags: " + joinArray(recipe.categories, ","));
    	this.recipe.setText("<h1>INGREDIENTS </h1><hr><br/>" + 
    						formatIngredients(recipe.ingredients, recipe.ingredientAmounts) + 
    						"<br/>" + "<h1>" + "DESCRIPTION" + "</h1><hr><br/>" + recipe.description);
    }
    
    //joins a string array together by delimiter
    public String joinArray(String[] stringArray, String delimiter){
    	String joinedString = "";
    	for(int i = 0; i < stringArray.length; i++){
    		if(i == stringArray.length - 1){
    			joinedString += stringArray[i];
    		}
    		else{
    			joinedString += stringArray[i] + delimiter;
    		}
    	}
    	return joinedString;
    }
	
    public String formatIngredients(String[] ingredients, String[] ingredientAmounts){
    	String formattedString = "<ul>";
    	for(int i = 0; i < ingredients.length; i++){
    		formattedString += "<li>" +  ingredients[i] + ": " + ingredientAmounts[i] + "</li><br/>";
    	}
    	return formattedString + "<ul>";
    	
    }
    
	public class Prompt implements FocusListener 
	{
	
	 public void focusGained(FocusEvent e)
	 {
		//removes text from searchBar when it regains focus 
	   if(e.getSource() == searchBar)
	   {
		   
		 if(searchBar.getText().equals("search")){
			 searchBar.setText("");
		 }
	   }
	 }
	 public void focusLost(FocusEvent e)
	 {
	 
 }
}
}


