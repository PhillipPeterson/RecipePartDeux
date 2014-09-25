import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


public class RecipeListPanel extends JScrollPane{

	public ArrayList<JButton> recipeButtons;
	public ArrayList<Recipe> recipeList;
	public JButton lastButtonHit;
	public String recipeToShow;
	public JScrollPane scrollBar;
	public static JPanel mainPanel = new JPanel();
	//RecipeDatabase data = new RecipeDatabase("recipe.db");
	
	RecipeListPanel(ArrayList<Recipe> recipeList)
	{
		super(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.recipeList = getInitialRecipeList();
		
		mainPanel.setLayout(new GridLayout(0,1));
		
		setUpPanel();
		
		
		
	}
	
	private ArrayList<Recipe> getInitialRecipeList()
	{
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		for(int i = 0; i < 100 ; i++)
		{
			recipes.add(new Recipe("TestRecipe",null,null,null,null,null));
		}
		
		/*
		DatabaseEntry[] databaseEntries = data.getRecipes();
		
		for(DatabaseEntry entry : databaseEntries)
		{
			recipes.add(data.readRecipe(entry.id));
		}
		
		
		for(int i = 0; i < 100 ; i++)
		{
			recipes.add(new Recipe("Test",null,null,null,null,null));
		}
		*/
		return recipes;
		
	}
	
	private void setUpPanel()
	{
		for (Recipe recipe : recipeList)
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			JButton button = new JButton(recipe.name);
			button.addActionListener(new ButtonListener());
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.setOpaque(false);
			button.setHorizontalAlignment(SwingConstants.LEFT);
			panel.setSize(button.getSize());
			panel.add(button);
			mainPanel.add(panel);
		}
	}
	
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(lastButtonHit == null)
			{
				lastButtonHit = (JButton)event.getSource();
				lastButtonHit.setForeground(Color.blue);
				recipeToShow = lastButtonHit.getText();
				lastButtonHit.getParent();
			}
			
			else if(lastButtonHit != null)
			{
				lastButtonHit.setForeground(Color.black);
				lastButtonHit.setBorder(null);
				lastButtonHit = (JButton)event.getSource();
				lastButtonHit.setForeground(Color.blue);
				recipeToShow = lastButtonHit.getText();
				
			}
			
			//DatabaseEntry[] recipeNamesList = data.getRecipesWithName(lastButtonHit.getText());
			//int recipeID = recipeNamesList[0].id;
		//	Recipe recipeSelected =  data.readRecipe(recipeID);
			//updateScreen(recipeSelected);
			
			
		}
	}
}