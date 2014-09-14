import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.*;
/*
 * 
 *	Pardon my java, haven't used it all summer. 
 *  Feel free to completely ignore this, I just wanted something as a 
 *  starting point but its completely open to suggestions.
 *  
 */


public class Driver {
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		
		

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
		JPanel mainPanel = new JPanel();
		mainPanel.add(new LeftPanel());
		mainPanel.add(new RightPanel());
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
		
		JFrame frame = new JFrame();
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(1000,600));
		frame.pack();
		

			
	}
	

}

