import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class LeftPanel extends JPanel implements ActionListener{

    private ImageIcon editIcon,deleteIcon,addIcon;
    private JButton edit,delete,add;
    private JLabel title,programTitle;
    private JComboBox categories;
    private JPanel buttonPanel,mainPanel;
    public static RecipeListPanel listPanel;
    private ArrayList<String> categoriesSet;
    DefaultComboBoxModel model;
    
    private RecipeDatabase data = new RecipeDatabase("recipe.db");

    public LeftPanel(){
        
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

    	this.editIcon = new ImageIcon("./Icons/edit.png");
    	this.deleteIcon = new ImageIcon("./Icons/delete.png");
    	this.addIcon = new ImageIcon("./Icons/add.png");
    	
    	this.programTitle = new JLabel("Recipes",SwingConstants.CENTER);
    	this.programTitle.setFont(new Font("Serif", Font.PLAIN, 45));
    	this.programTitle.setPreferredSize(new Dimension(300,50));
    	
    	listPanel = new RecipeListPanel(recipeList);
    	listPanel.setBorder(BorderFactory.createBevelBorder(1));
    	listPanel.setPreferredSize(new Dimension(300,900));
    	
    	this.mainPanel = new JPanel();
    	this.mainPanel.setPreferredSize(new Dimension(300,100));
    	this.categoriesSet = new ArrayList<String>();
    	this.categoriesSet.add("All");
    	for (String category : data.getCategories()){
    		this.categoriesSet.add(category);
    	}
   
    	Collections.sort(this.categoriesSet);
    	model = new DefaultComboBoxModel(categoriesSet.toArray());
    	this.categories = new JComboBox<>();
    	this.categories.setModel(model);
    	this.categories.addItemListener(new ComboBoxListener());
    	this.categories.setPreferredSize(new Dimension(300,50));
    	
        this.edit = new JButton();
        this.delete = new JButton();
        this.add = new JButton();
        edit.addActionListener(this);
        delete.addActionListener(this);
        add.addActionListener(this);
        this.edit.setIcon(this.editIcon);
        this.delete.setIcon(this.deleteIcon);
        this.add.setIcon(this.addIcon);
        
        
        this.buttonPanel = new JPanel();
        this.buttonPanel.add(edit);
        this.buttonPanel.add(delete);
        this.buttonPanel.add(add);
        this.buttonPanel.setLayout(new GridLayout(1,0));
        this.buttonPanel.setPreferredSize(new Dimension(300,60));
        this.mainPanel.setLayout(new BorderLayout());
        
        this.mainPanel.add(this.programTitle,BorderLayout.NORTH);
        this.mainPanel.add(this.categories,BorderLayout.CENTER);
    
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(this.mainPanel);
        add(listPanel);
        add(this.buttonPanel);
        setPreferredSize(new Dimension(300,600));
        

    }
    
    //updates the categories dropdown menu with new categories
    public void updateCategoriesMenu(String[] categoryArray){
        Arrays.sort(categoryArray);
        //removes item listener on jcombobox
    	this.categories.removeItemListener(this.categories.getItemListeners()[0]);
    	this.model.removeAllElements();
    	this.categories.addItemListener(new ComboBoxListener());
    	//makes sure All is at beginning of categories list
    	this.model.addElement("All");
    	for(String category : categoryArray){
    		this.model.addElement(category);

    	}
    }
    
   
    
    public void recipesWithCategorySelected()
    {
    	ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
    	
    	String categorySelected = (String) categories.getSelectedItem();
    	
    	if(!categorySelected.equals("All"))
    	{
	    	try {
                                data.init();
				DatabaseEntry[] entries = data.getRecipesInCategory(categorySelected);
				
				for(DatabaseEntry entry : entries)
				{
			
					recipeList.add(data.readRecipe(entry.id));
				}
				
				listPanel.updateRecipeList(recipeList);
				
				
			} catch (Exception e) {
				System.out.println("Error getting recipes in selected category: LeftPanel Line 86");
			}	
                data.close();
    	}
    	else
    	{
    		listPanel.updateRecipeList(null);
    	}
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(edit))
        {
            if(RecipeListPanel.recipeSelected == null)
                JOptionPane.showMessageDialog(null, "You currently have no recipe "
                    + "selected. Please select a recipe and try again");
            else
            {
                Driver.rightPanel.setVisible(false);
                Driver.addPanel.setVisible(false);
                Driver.editPanel.setVisible(true);
                Driver.editPanel.setRecipe(RecipeListPanel.recipeSelected);
            }

            
        }
        if(e.getSource().equals(add))
        {
            Driver.rightPanel.setVisible(false);
            Driver.editPanel.setVisible(false);
            Driver.addPanel.setVisible(true);
        }
        if(e.getSource().equals(delete))
        {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete"
                    + " this recipe?", "Delete Recipe", JOptionPane.YES_NO_OPTION);
            if(reply == JOptionPane.YES_OPTION)
            {
                try {
                    if(RecipeListPanel.recipeSelected == null)
                        JOptionPane.showMessageDialog(null, "You need to select a recipe first");
                    else
                    {
                        data.init();
                        data.deleteRecipe(RecipeListPanel.recipeSelected.recipeId);
                        listPanel.updateRecipeList(null);
                        this.updateCategoriesMenu(data.getCategories());
                    }
                }
                catch(Exception error)
                {
                    error.printStackTrace();
                }
                finally
                {
                    data.close();
                }
                
            }
            
        }
    }

    private class ComboBoxListener implements ItemListener
    {

		public void itemStateChanged(ItemEvent event) {
			
			recipesWithCategorySelected();
			
		}
    	
    }
}