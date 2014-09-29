import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class EditPanel extends JPanel implements ActionListener{
    
    RecipeDatabase data = new RecipeDatabase("recipe.db");
    
    private JButton saveButton, cancelButton, addIngButton, delIngButton;
    private JLabel titleLabel, recipeLabel;
    private JLabel tagLabel, amtLabel, ingLabel, dirLabel, desLabel;
    private JTextField name, tags, description;
    private JTextArea directions;
    private JPanel titlePanel, namePanel, tagPanel, ingredientsPanel, ingPanel, 
            amtPanel, ingButPanel, dirPanel, desPanel, buttonPanel, mainPanel;
    private ArrayList<JTextField> ingredients, amounts;
    
    public EditPanel(){
    	
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
        
        JScrollPane scrollPane = new JScrollPane();
        
        
        amounts = new ArrayList<JTextField>();
        ingredients = new ArrayList<JTextField>();
        
        saveButton = new JButton("Save Recipe");
        saveButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        addIngButton = new JButton("Add Ingredient");
        addIngButton.addActionListener(this);
        delIngButton = new JButton("Remove Ingredient");
        delIngButton.addActionListener(this);
        
        titleLabel = new JLabel("Edit Recipe",SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(500,50));
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        
        recipeLabel = new JLabel("Recipe Name");
        
        recipeLabel = new JLabel("Recipe:");
        tagLabel = new JLabel("Tags:");
        ingLabel = new JLabel("Ingredients:");
        amtLabel = new JLabel("Amounts:");
        dirLabel = new JLabel("Directions:");
        desLabel = new JLabel("Description:");
        
        name = new JTextField("Chicken Noodles");
        tags = new JTextField("Chicken, Noodles, Dinner");
        ingredients.add(new JTextField("Chicken"));
        ingredients.add(new JTextField("Noodles"));
        amounts.add(new JTextField("1 Cup"));
        amounts.add(new JTextField("2 Cups"));
        directions = new JTextArea("Put the chicken in the noodles");
        directions.setPreferredSize(new Dimension(400,200));
        description = new JTextField("A tasty meal for those lacking taste");
        
        mainPanel = new JPanel();
        
        titlePanel = new JPanel();
        namePanel = new JPanel();
        tagPanel = new JPanel();
        ingPanel = new JPanel();
        amtPanel = new JPanel();
        dirPanel = new JPanel();
        desPanel = new JPanel();
        buttonPanel = new JPanel();
        ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.LINE_AXIS));
        ingButPanel = new JPanel();
        ingButPanel.setLayout(new BoxLayout(ingButPanel, BoxLayout.LINE_AXIS));
        
        titlePanel.add(titleLabel);
        
        namePanel.setLayout(new BorderLayout());
        namePanel.add(recipeLabel, BorderLayout.CENTER);
        namePanel.add(name, BorderLayout.SOUTH);
        
        tagPanel.setLayout(new BorderLayout());
        tagPanel.add(tagLabel, BorderLayout.CENTER);
        tagPanel.add(tags, BorderLayout.SOUTH);
        
        ingPanel.setLayout(new BoxLayout(ingPanel, BoxLayout.PAGE_AXIS));
        ingPanel.add(ingLabel, BorderLayout.CENTER);
        ingPanel.add(ingredients.get(0), BorderLayout.SOUTH);
        ingPanel.add(ingredients.get(1), BorderLayout.SOUTH);
        
        amtPanel.setLayout(new BoxLayout(amtPanel, BoxLayout.PAGE_AXIS));
        amtPanel.add(amtLabel, BorderLayout.CENTER);
        amtPanel.add(amounts.get(0), BorderLayout.SOUTH);
        amtPanel.add(amounts.get(1), BorderLayout.SOUTH);
        
        dirPanel.setLayout(new BorderLayout());
        dirPanel.add(dirLabel,BorderLayout.CENTER);
        dirPanel.add(directions, BorderLayout.SOUTH);
        
        desPanel.setLayout(new BorderLayout());
        desPanel.add(desLabel,BorderLayout.CENTER);
        desPanel.add(description, BorderLayout.SOUTH);
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        ingredientsPanel.add(amtPanel);
        ingredientsPanel.add(ingPanel);
        
        ingButPanel.add(addIngButton);
        ingButPanel.add(delIngButton);
        
        mainPanel.add(namePanel);
        mainPanel.add(desPanel);
        mainPanel.add(tagPanel);
        mainPanel.add(ingredientsPanel);
        mainPanel.add(ingButPanel);
        mainPanel.add(dirPanel);
        
        scrollPane.setViewportView(mainPanel);
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        
        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setPreferredSize(new Dimension(700,600));
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(cancelButton))
        {
            this.setVisible(false);
            Driver.rightPanel.setVisible(true);
        }
        if(e.getSource().equals(saveButton))
        {
            String updName = name.getText();
            String updDesc = description.getText();
            String tagsToAdd = tags.getText();
            String[] updTagsArray = tagsToAdd.split(".[ ]*");
            String[] updIngs = new String[ingredients.size()];
            updIngs = ingredients.toArray(updIngs);
            String[] updAmts = new String[amounts.size()];
            updAmts = amounts.toArray(updAmts);
            String updDir = directions.getText();
            
            //query to update Recipe
            //Recipe(String _name, String _description, String[] _ingredients, String[] _ingredientAmounts, String[] _categories, String _directions)
            
            //Recipe updatedRecipe = new Recipe(updName, updDesc, updIngs, updAmts, updTagsArray, updDir);
            //data.updateRecipe(updatedRecipe);
            
        }
        if(e.getSource().equals(addIngButton))
        {
            amounts.add(new JTextField());
            ingredients.add(new JTextField());
            
            for(int i = 1; i < 2; i++)
            {
                amtPanel.add(amounts.get(amounts.size()-1), BorderLayout.AFTER_LAST_LINE);
                ingPanel.add(ingredients.get(ingredients.size()-1), BorderLayout.AFTER_LAST_LINE);
            }
            amtPanel.revalidate();
            ingPanel.revalidate();
            
            repaint();
            
        }
        if(e.getSource().equals(delIngButton))
        {
            amtPanel.remove(amounts.get(amounts.size()-1));
            amounts.remove(amounts.size()-1);
            ingPanel.remove(ingredients.get(ingredients.size()-1));
            ingredients.remove(ingredients.size()-1);
            amtPanel.revalidate();
            ingPanel.revalidate();
            
            repaint();
        }
    }
    
}
