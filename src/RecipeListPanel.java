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
	RecipeDatabase data = new RecipeDatabase("recipe.db");
	final int PANELMINITEMS = 10;
	
	public static Recipe recipeSelected;
	
	public
	
	RecipeListPanel(ArrayList<Recipe> recipeList)
	{
		super(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.recipeList = updateRecipeList();
		
		mainPanel.setLayout(new GridLayout(0,1));
		
		setUpPanel();
		
		
		
	}
	
	public ArrayList<Recipe> updateRecipeList()
	{
            ArrayList<Recipe> recipes = new ArrayList<Recipe>();
             
            
            DatabaseEntry[] DBEntriesOfRecipes = data.getRecipes();

            for(DatabaseEntry entry: DBEntriesOfRecipes) 
            {
                recipes.add(data.readRecipe(entry.id));
            }
            

		return recipes;
		
	}
	
	public void setUpPanel()
	{
		//ensures proper spacing between recipes in the list
		//also makes sure panel still appears when no recipes are displayed-
		while(this.recipeList.size() < PANELMINITEMS){
			this.recipeList.add(new Recipe(null,null,null,null,null,null));
		}
		
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
	
	public Recipe lastRecipeSelected()
	{
		DatabaseEntry[] recipeNamesList = data.getRecipesWithName(lastButtonHit.getText());
		int recipeID = recipeNamesList[0].id;
		recipeSelected =  data.readRecipe(recipeID);
		
		return recipeSelected;
	}
	
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
                        data.init();
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
                        
                        
			
			Driver.rightPanel.displayRecipe(lastRecipeSelected());
			data.close();
		}
	}
        
        public void updatePanel()
        {
            mainPanel.removeAll();
            data.init();
            
            this.recipeList = updateRecipeList();
            setUpPanel();
            data.close();
            
            revalidate();
        }
}