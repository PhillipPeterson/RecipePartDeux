import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;


public class RecipeListPanel extends JPanel{

	public ArrayList<JButton> recipeButtons;
	public ArrayList<Recipe> recipeList;
	public JButton lastButtonHit;
	public String recipeToShow;
	
	RecipeListPanel(ArrayList<Recipe> recipeList)
	{
		this.recipeList = recipeList;
		
		setLayout(new GridLayout(1,recipeList.size()));
		setUpPanel();
		
	}
	
	private void setUpPanel()
	{
		for (Recipe recipe : recipeList)
		{
			JButton button = new JButton(recipe.name);
			button.setBorder(null);
			add(button);
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
			}
			
			else if(lastButtonHit != null)
			{
				lastButtonHit.setForeground(Color.black);
				lastButtonHit = (JButton)event.getSource();
				lastButtonHit.setForeground(Color.blue);
				recipeToShow = lastButtonHit.getText();
			}
			
			
		}
	}
}
