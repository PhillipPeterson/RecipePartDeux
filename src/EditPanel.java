import javax.swing.*;
import java.awt.*;


public class EditPanel extends JPanel{
    
    private JButton saveButton;
    private JLabel titleLabel, recipeLabel;
    private JLabel tagLabel, ingLabel, dirLabel;
    private JTextField name, tags, ingrediants;
    private JTextArea directions;
    private JPanel titlePanel, namePanel, tagPanel, ingPanel, dirPanel,
            buttonPanel, mainPanel;
    
    public EditPanel(){
        
        saveButton = new JButton("Save Recipe");
        
        titleLabel = new JLabel("Edit Recipe",SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(500,50));
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        
        recipeLabel = new JLabel("Recipe Name");
        
        recipeLabel = new JLabel("Recipe:");
        tagLabel = new JLabel("Tags:");
        ingLabel = new JLabel("Ingrediants:");
        dirLabel = new JLabel("Directions:");
        
        name = new JTextField("Recipe Name");
        name.setPreferredSize(new Dimension(400,30));
        tags = new JTextField("tags, tags, tags");
        tags.setPreferredSize(new Dimension(400,30));
        ingrediants = new JTextField("Chicken, Noodles");
        ingrediants.setPreferredSize(new Dimension(400,30));
        directions = new JTextArea("1. Put chicken in noodles");
        directions.setPreferredSize(new Dimension(400,200));
        
        mainPanel = new JPanel();
        
        titlePanel = new JPanel();
        namePanel = new JPanel();
        tagPanel = new JPanel();
        ingPanel = new JPanel();
        dirPanel = new JPanel();
        buttonPanel = new JPanel();
        
        titlePanel.add(titleLabel);
        
        namePanel.setLayout(new BorderLayout());
        namePanel.add(recipeLabel, BorderLayout.NORTH);
        namePanel.add(name, BorderLayout.SOUTH);
        
        tagPanel.setLayout(new BorderLayout());
        tagPanel.add(tagLabel, BorderLayout.NORTH);
        tagPanel.add(tags, BorderLayout.SOUTH);
        
        ingPanel.setLayout(new BorderLayout());
        ingPanel.add(ingLabel, BorderLayout.NORTH);
        ingPanel.add(ingrediants, BorderLayout.SOUTH);
        
        dirPanel.setLayout(new BorderLayout());
        dirPanel.add(dirLabel,BorderLayout.NORTH);
        dirPanel.add(directions, BorderLayout.SOUTH);
        
        buttonPanel.add(saveButton, BorderLayout.SOUTH);
        
        
        mainPanel.add(namePanel);
        mainPanel.add(tagPanel);
        mainPanel.add(ingPanel);
        mainPanel.add(dirPanel);
        
        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setPreferredSize(new Dimension(700,600));
        
    }
}
