import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.*;
import java.awt.event.*;


public class EditPanel extends JPanel implements ActionListener{
    
    private JButton saveButton, cancelButton;
    private JLabel titleLabel, recipeLabel;
    private JLabel tagLabel, ingLabel, dirLabel;
    private JTextField name, tags, ingrediants;
    private JTextArea directions;
    private JPanel titlePanel, namePanel, tagPanel, ingPanel, dirPanel,
            buttonPanel, mainPanel;
    
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
        
        saveButton = new JButton("Save Recipe");
        saveButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        
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
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        
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
    
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(cancelButton))
        {
            this.setVisible(false);
            Driver.rightPanel.setVisible(true);
        }
        if(e.getSource().equals(saveButton))
        {
            //code for adding recipe to database
        }
    }
}
