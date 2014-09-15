import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class DeletePanel extends JPanel{
    
    private JButton deleteButton;
    private JLabel deleteTitle;
    private JTextField searchBar;
    private JPanel buttonPanel,searchPanel,titlePanel, mainPanel;
    
    public DeletePanel(){
        
        deleteTitle = new JLabel("Delete",SwingConstants.CENTER);
        deleteTitle.setPreferredSize(new Dimension(500,50));
        deleteTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        
        searchBar = new JTextField("Enter the Recipe Name you would like to delete");
        searchBar.setPreferredSize(new Dimension(400,30));
        searchBar.addFocusListener(new Prompt());
        
        deleteButton = new JButton("Delete");
        
        titlePanel = new JPanel();
        searchPanel = new JPanel();
        buttonPanel = new JPanel();
        mainPanel = new JPanel();
        
        deleteButton.setPreferredSize(new Dimension(100,50));
        mainPanel.setPreferredSize(new Dimension(700,400));
        
        titlePanel.add(deleteTitle, BorderLayout.NORTH);
        searchPanel.add(searchBar, BorderLayout.CENTER);
        buttonPanel.add(deleteButton, BorderLayout.SOUTH);
        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        
        add(this.titlePanel);
        add(this.mainPanel);
        add(this.buttonPanel);
        setPreferredSize(new Dimension(700,600));
        
        
    }
    public class Prompt implements FocusListener 
    {
	
        public void focusGained(FocusEvent e)
        {
               //removes text from searchBar when it regains focus 
          if(e.getSource() == searchBar)
          {
            searchBar.setText("");
          }
        }
        
        public void focusLost(FocusEvent e){}
    }
}
