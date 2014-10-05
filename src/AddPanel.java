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
    final String NAMEDEFAULT = "Enter Recipe Name here";
    final String TAGSDEFAULT = "Enter Tags here";
    final String INGREDIENTSDEFAULT = "Enter Ingredients here";
    final String AMOUNTDEFAULT = "Enter the amount for ingredient";
    final String DIRECTIONSDEFAULT = "Enter Directions here";
    final String DESCRIPTIONDEFAULT = "Enter Description here";
    
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
        
        name = new JTextField(NAMEDEFAULT);
        name.addFocusListener(new Prompt());
        tags = new JTextField(TAGSDEFAULT);
        tags.addFocusListener(new Prompt());
        ingredients.add(createFieldWithFocusListener(INGREDIENTSDEFAULT));
        amounts.add(createFieldWithFocusListener(AMOUNTDEFAULT));
        directions = new JTextArea(DIRECTIONSDEFAULT);
        directions.addFocusListener(new Prompt());
        directions.setPreferredSize(new Dimension(400,200));
        description = new JTextField(DESCRIPTIONDEFAULT);
        description.addFocusListener(new Prompt());
        
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
            amounts.add(createFieldWithFocusListener(AMOUNTDEFAULT));
            ingredients.add(createFieldWithFocusListener(INGREDIENTSDEFAULT));
            
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

    private JTextField createFieldWithFocusListener(String defaultText){
        JTextField field = new JTextField(defaultText);
        field.addFocusListener(new Prompt());
        return field;
    }

    public class Prompt implements FocusListener{
        public void focusGained(FocusEvent e)
        {
            //removes text from addPanel textfields when focus is gained and default text is present
            if(e.getSource() == name)
            {

                if(name.getText().equals(NAMEDEFAULT)){
                    name.setText("");
                }
            }
            else if(e.getSource() == tags){
                if(tags.getText().equals(TAGSDEFAULT)){
                    tags.setText("");
                }
            }
            else if(e.getSource() == description){
                if(description.getText().equals(DESCRIPTIONDEFAULT)){
                    description.setText("");
                }
            }
            else if(e.getSource() == directions){
                if(directions.getText().equals(DIRECTIONSDEFAULT)){
                    directions.setText("");
                }
            }
            else{
                JTextField src = (JTextField) e.getSource();

                if(src.getText().equals(INGREDIENTSDEFAULT) || src.getText().equals(AMOUNTDEFAULT)){
                    src.setText("");
                    System.out.println(src.getText());
                }
            }

        }
        public void focusLost(FocusEvent e)
        {
            if(e.getSource() == name)
            {

                if(name.getText().isEmpty()){
                    name.setText(NAMEDEFAULT);
                }
            }
            else if(e.getSource() == tags){
                if(tags.getText().isEmpty()){
                    tags.setText(TAGSDEFAULT);
                }
            }
            else if(e.getSource() == description){
                if(description.getText().isEmpty()){
                    description.setText(DESCRIPTIONDEFAULT);
                }
            }
            else if(e.getSource() == directions){
                if(directions.getText().isEmpty()){
                    directions.setText(DIRECTIONSDEFAULT);
                }
            }
            else{
                JTextField src = (JTextField) e.getSource();

                if(src.getText().isEmpty()){
                    //conditional that evaluates whether source field is for ingredients or amounts
                    if(ingredients.contains(src)){
                        src.setText(INGREDIENTSDEFAULT);
                    }
                    else{
                        src.setText(AMOUNTDEFAULT);
                    }

                }
            }

        }
    }
    
}
