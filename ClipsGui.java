package clips2.agent;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class ClipsGui extends JFrame {

	private ClipsAgent myAgent;


	ClipsGui(ClipsAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(new JLabel("AGENTES"));
		getContentPane().add(p, BorderLayout.CENTER);
		

		getContentPane().add(p, BorderLayout.SOUTH);	
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}


	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}
}

