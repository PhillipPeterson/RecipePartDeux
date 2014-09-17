import javax.swing.*;
import java.awt.event.*;

import java.awt.*;

public class LeftPanel extends JPanel implements ActionListener{

	private ImageIcon editIcon,deleteIcon,addIcon;
    private JButton edit,delete,add;
    private JLabel title,programTitle;
    private JPanel recipes;
    private JComboBox categories;
    private JPanel buttonPanel,mainPanel;

    public LeftPanel(){
    	
    	this.editIcon = new ImageIcon("./Icons/edit.png");
    	this.deleteIcon = new ImageIcon("./Icons/delete.png");
    	this.addIcon = new ImageIcon("./Icons/add.png");
    	
    	this.programTitle = new JLabel("Recipes",SwingConstants.CENTER);
    	this.programTitle.setFont(new Font("Serif", Font.PLAIN, 45));
    	this.programTitle.setPreferredSize(new Dimension(300,50));
    	
    	this.recipes = new JPanel();
    	this.recipes.setBorder(BorderFactory.createBevelBorder(1));
    	this.recipes.setPreferredSize(new Dimension(300,900));
    	
    	this.mainPanel = new JPanel();
    	this.mainPanel.setPreferredSize(new Dimension(300,100));
    	
    	this.categories = new JComboBox<>();
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
        add(this.recipes);
        add(this.buttonPanel);
        setPreferredSize(new Dimension(300,600));
        

    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(edit))
        {
            Driver.rightPanel.setVisible(false);
            Driver.addPanel.setVisible(false);
            Driver.editPanel.setVisible(true);
            
            
        }
        if(e.getSource().equals(add))
        {
            Driver.rightPanel.setVisible(false);
            Driver.editPanel.setVisible(false);
            Driver.addPanel.setVisible(true);
        }
        if(e.getSource().equals(delete))
        {
            JOptionPane.showConfirmDialog(null, "Are you sure you want to delete"
                    + " this recipe?", "Delete Recipe", JOptionPane.YES_NO_OPTION);
        }
    }

}