package email.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class MyFrame extends JFrame{
	private static final long serialVersionUID = 1L;


	public MyFrame() {
		init();
	}

	private void init(){

        add(new MyPanel());

        setResizable(false);
        pack();
        setTitle("                                                                            Facade");    
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public static void main(String[] args) {
		
		 EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				JFrame ex = new MyFrame();
				ex.setVisible(true);
				
			}
		});
		
	}

}