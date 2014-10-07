import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Arrays;

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
	private JButton searchButton;
	private JTextField searchBar;
	private JPanel searchPanel,mainPanel,titlePanel;
    final String SEARCHDEFAULT = "search";
	RecipeDatabase data = new RecipeDatabase("recipe.db");
	
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
		this.recipeTitle.setFont(new Font("Serif", Font.PLAIN, 45));
		this.recipeTitle.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0,Color.BLACK));
		
		this.tags = new JLabel("Tags:");
		this.tags.setBorder(BorderFactory.createBevelBorder(1));
		
		this.recipe = new JTextPane();
		this.recipe.setPreferredSize(new Dimension(700,500));
		this.recipe.setEditable(false);
		this.recipe.setContentType("text/html");
		
		this.scrollPane = new JScrollPane(this.recipe);
		this.scrollPane.setPreferredSize(new Dimension(700,900));
		this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.searchBar = new JTextField(SEARCHDEFAULT);
		this.searchBar.addFocusListener(new Prompt());
		this.searchBar.addActionListener(new buttonListener());
		
		this.searchIcon = new ImageIcon("./Icons/search.png");
		this.searchButton = new JButton();
		this.searchButton.setIcon(this.searchIcon);
		this.searchButton.setPreferredSize(new Dimension(30,30));
		this.searchButton.addActionListener(new buttonListener());
		
		this.searchPanel = new JPanel();
		this.searchPanel.setPreferredSize(new Dimension(700,30));
		
		this.searchPanel.add(this.searchButton);
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
		
		
		//default for recipe display
        String[] ingredients = new String[]{"Ingredient1", "Ingredient2"};
        String[] ingredients2 = new String[]{"Amount", "Amount"};
        String[] category = new String[]{"All"};
        Recipe first  = new Recipe("Recipe", "testRecipe", ingredients, ingredients2, category, "Directions");
        displayRecipe(first);
	}
	
	
    public void displayRecipe(Recipe recipe){
    	this.recipeTitle.setText(recipe.name);
    	this.tags.setText("Tags: " + joinArray(recipe.categories, ", "));
    	this.recipe.setText("<h1>INGREDIENTS </h1><hr><br/>" + 
    						formatIngredients(recipe.ingredients, recipe.ingredientAmounts) + 
    						"<br/>" + "<h1>" + "DESCRIPTION" + "</h1><hr><br/>" + recipe.directions.replaceAll("\n", "<br/>"));
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
    
    public class buttonListener implements ActionListener
    {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == searchButton || e.getSource() == searchBar){
				String searchTerm = searchBar.getText();
				DatabaseEntry[] matches = data.getRecipesWithName(searchTerm);
				ArrayList<Recipe> recipes = new ArrayList<Recipe>();
				for(DatabaseEntry entry : matches){
					recipes.add(data.readRecipe(entry.id));
				}
				Driver.leftPanel.listPanel.recipeList = recipes;
				Driver.leftPanel.listPanel.mainPanel.removeAll();
				Driver.leftPanel.listPanel.setUpPanel();
				Driver.leftPanel.listPanel.repaint();
				Driver.leftPanel.listPanel.revalidate();
			}
			
		}
    	
    }
    
	public class Prompt implements FocusListener 
	{
	
	 public void focusGained(FocusEvent e)
	 {
		//removes text from searchBar when it regains focus 
	   if(e.getSource() == searchBar)
	   {
		   
		 if(searchBar.getText().equals(SEARCHDEFAULT)){
			 searchBar.setText("");
		 }
	   }
	 }
	 public void focusLost(FocusEvent e)
	 {
         if(e.getSource() == searchBar)
         {

             if(searchBar.getText().isEmpty()){
                 searchBar.setText(SEARCHDEFAULT);
             }
         }
 }
}
}


