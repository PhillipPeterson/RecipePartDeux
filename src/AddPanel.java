import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class AddPanel extends JPanel implements ActionListener{
    
    RecipeDatabase data = new RecipeDatabase("recipe.db");
    
    private JButton addButton, cancelButton, addIngButton, delIngButton;
    private JLabel titleLabel, recipeLabel;
    private JLabel tagLabel, amtLabel, ingLabel, dirLabel, desLabel;
    private JTextField name, tags, description;
    private JTextArea directions;
    private JPanel titlePanel, namePanel, tagPanel, ingredientsPanel, ingPanel, 
            amtPanel, ingButPanel, dirPanel, desPanel, buttonPanel, mainPanel;
    private ArrayList<JTextField> ingredients, amounts;
    
    public AddPanel(){
        
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

        addButton = new JButton("Add Recipe");
        addButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        addIngButton = new JButton("Add Ingredient");
        addIngButton.addActionListener(this);
        delIngButton = new JButton("Remove Ingredient");
        delIngButton.addActionListener(this);
        
        titleLabel = new JLabel("Add Recipe",SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(500,50));
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        
        recipeLabel = new JLabel("Recipe Name");
        
        recipeLabel = new JLabel("Recipe:");
        tagLabel = new JLabel("Tags:");
        ingLabel = new JLabel("Ingredients:");
        amtLabel = new JLabel("Amounts:");
        dirLabel = new JLabel("Directions:");
        desLabel = new JLabel("Description:");
        
        name = new JTextField("Enter Recipe Name here");
        tags = new JTextField("Enter Tags here");
        ingredients.add(new JTextField("Enter Ingredients here"));
        amounts.add(new JTextField("Enter the amount for ingredient"));
        directions = new JTextArea("Enter Directions here");
        directions.setPreferredSize(new Dimension(400,200));
        description = new JTextField("Enter Description here");
        
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
        
        amtPanel.setLayout(new BoxLayout(amtPanel, BoxLayout.PAGE_AXIS));
        amtPanel.add(amtLabel, BorderLayout.CENTER);
        amtPanel.add(amounts.get(0), BorderLayout.SOUTH);
        
        dirPanel.setLayout(new BorderLayout());
        dirPanel.add(dirLabel,BorderLayout.CENTER);
        dirPanel.add(directions, BorderLayout.SOUTH);
        
        desPanel.setLayout(new BorderLayout());
        desPanel.add(desLabel,BorderLayout.CENTER);
        desPanel.add(description, BorderLayout.SOUTH);
        
        buttonPanel.add(addButton);
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
        if(e.getSource().equals(addButton))
        {
            String addName = name.getText();
            String addDesc = description.getText();
            String tagsToAdd = tags.getText();
            String[] addTagsArray = tagsToAdd.split(",[ ]*");
            String[] addIngs = getIngredients(ingredients).clone();
            String[] addAmts = getAmounts(amounts).clone();
            String addDir = directions.getText();
            
            //query to insert Recipe
            try 
            {
                data.init();
                data.insertRecipe(addName, addDesc, addIngs, addAmts, addTagsArray, addDir);
                LeftPanel.listPanel.updateRecipeList(null);
                Driver.leftPanel.updateCategoriesMenu(data.getCategories());
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
        if(e.getSource().equals(addIngButton))
        {
            amounts.add(new JTextField());
            ingredients.add(new JTextField());
            
            amtPanel.add(amounts.get(amounts.size()-1), BorderLayout.AFTER_LAST_LINE);
            ingPanel.add(ingredients.get(ingredients.size()-1), BorderLayout.AFTER_LAST_LINE);
            
            amtPanel.revalidate();
            ingPanel.revalidate();
            
        }
        if(e.getSource().equals(delIngButton))
        {
            amtPanel.remove(amounts.get(amounts.size()-1));
            amounts.remove(amounts.size()-1);
            ingPanel.remove(ingredients.get(ingredients.size()-1));
            ingredients.remove(ingredients.size()-1);
            amtPanel.revalidate();
            ingPanel.revalidate();

        }
    }
    
    private String[] getIngredients(ArrayList<JTextField> ingList)
    {
        String[] ingArray = new String[ingList.size()];
        
        for(int i = 0; i < ingList.size(); i++)
            ingArray[i] = ingList.get(i).getText();
        
        return ingArray;
    }
    
    private String[] getAmounts(ArrayList<JTextField> amtList)
    {
        String[] amtArray = new String[amtList.size()];
        
        for(int i = 0; i < amtList.size(); i++)
            amtArray[i] = amtList.get(i).getText();
        
        return amtArray;
    }
    
}
