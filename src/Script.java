package agents.src;

import net.sf.clipsrules.jni.*;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


 //Con interfaz
class Script extends JFrame {

	private AgentScript myAgent;
	private JTextField titleField;

	Script(AgentScript a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(new JLabel("AGENTES"));
		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("CARGAR");
		addButton.addActionListener( new ActionListener() {

			String texto="";


			public void actionPerformed(ActionEvent ev) {
				try {
					Environment clips;

					try{
						clips = new Environment();

						System.out.println("\n--------------------------------------------------------\nPersonas:\n");
						clips.eval("(clear)");
						//clips.load("/CLIPS 6.31/clips/clips_jni_051/test/src/test.clp");
						clips.load("/Jade 4.5.0/jade/src/agents/persons/load-persons.clp");
						clips.eval("(reset)");
						clips.eval("(facts)");

						clips.load("/Jade 4.5.0/jade/src/agents/persons/load-persons-rules.clp");
						clips.eval("(rules)");
						clips.run();


						System.out.println("\n-----------------------------------------------------\nProductos:\n");
						clips.eval("(clear)");
						clips.load("/Jade 4.5.0/jade/src/agents/products/load-prod-cust.clp");
						clips.eval("(reset)");
						clips.eval("(facts)");

						clips.load("/Jade 4.5.0/jade/src/agents/products/load-prodcust-rules.clp");
						clips.eval("(rules)");
						clips.run();


						System.out.println("\n------------------------------------------------------\nMarket:\n");
						clips.eval("(clear)");
						clips.load("/Jade 4.5.0/jade/src/agents/market/templates.clp");
						clips.load("/Jade 4.5.0/jade/src/agents/market/facts.clp");
						clips.eval("(reset)");
						clips.eval("(facts)");

						clips.load("/Jade 4.5.0/jade/src/agents/market/rules.clp");
						clips.load("/Jade 4.5.0/jade/src/agents/market/persons.clp");
						clips.eval("(rules)");
						clips.run();

					}catch(Exception e){
						System.out.println("ERROR");
					}
					
					//myAgent.insertarTexto(title);
					titleField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(Script.this, "VALORES INVALIDOS. "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
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


/*


public class Script extends Agent{
	protected void setup(){

		Environment clips;

		addBehaviour(new MyGenericBehaviour());
	}


	private class MyGenericBehaviour extends Behaviour{

		int count = 0;

		public void action(){

			clips = new Environment();

			clips.eval("(clear)");
			//clips.load("/CLIPS 6.31/clips/clips_jni_051/test/src/test.clp");
			clips.load("/Jade 4.5.0/jade/src/agents/persons/load-persons.clp");
			clips.eval("(reset)");
			clips.eval("(facts)");

			clips.load("/Jade 4.5.0/jade/src/agents/persons/load-persons-rules.clp");
			clips.eval("(rules)");
			//cargar, clips.load("direccion.clp")
			//opcional, clips.eval("(rules)")
			clips.run();

			count+=1;

		}


		public boolean done(){
			if(count == 1)
				return true;
			else
				return false;
		}

		public int onEnd(){
			myAgent.doDelete();
			return super.onEnd();
		}

	}
}*/



/*
class Script {

	public static void main(String args[]){
		Environment clips;
		clips = new Environment();

		System.out.println("\n--------------------------------------------------------\nPersonas:\n");
		clips.eval("(clear)");
		//clips.load("/CLIPS 6.31/clips/clips_jni_051/test/src/test.clp");
		clips.load("/Jade 4.5.0/jade/src/agents/persons/load-persons.clp");
		clips.eval("(reset)");
		clips.eval("(facts)");

		clips.load("/Jade 4.5.0/jade/src/agents/persons/load-persons-rules.clp");
		clips.eval("(rules)");
		clips.run();


		System.out.println("\n-----------------------------------------------------\nProductos:\n");
		clips.eval("(clear)");
		clips.load("/Jade 4.5.0/jade/src/agents/products/load-prod-cust.clp");
		clips.eval("(reset)");
		clips.eval("(facts)");

		clips.load("/Jade 4.5.0/jade/src/agents/products/load-prodcust-rules.clp");
		clips.eval("(rules)");
		clips.run();


		System.out.println("\n------------------------------------------------------\nMarket:\n");
		clips.eval("(clear)");
		clips.load("/Jade 4.5.0/jade/src/agents/market/templates.clp");
		clips.load("/Jade 4.5.0/jade/src/agents/market/facts.clp");
		clips.eval("(reset)");
		clips.eval("(facts)");

		clips.load("/Jade 4.5.0/jade/src/agents/market/rules.clp");
		clips.load("/Jade 4.5.0/jade/src/agents/market/persons.clp");
		clips.eval("(rules)");
		clips.run();

	}
}*/




