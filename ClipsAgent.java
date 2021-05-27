package clips2.agent; 

import jade.core.Agent;
import jade.core.behaviours.*;

import net.sf.clipsrules.jni.*;

import java.util.*;

import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class ClipsAgent extends Agent{
	private ClipsGui myGui;
	Environment clips;

	protected void setup(){
		DFAgentDescription dfd = new DFAgentDescription();
	    dfd.setName(getAID());
	    ServiceDescription sd = new ServiceDescription();
	    sd.setType("Clips-agent");
	    sd.setName("JADE-CLIPS-tarea");
	    dfd.addServices(sd);
	    try {
	      DFService.register(this, dfd);
	    }
	    catch (FIPAException fe) {
	      fe.printStackTrace();
	    }

	     
		myGui = new ClipsGui(this);
		myGui.showGui();

		addBehaviour(new MyGenericBehaviour());

	}


	protected void takeDown() {
	    try {
	      DFService.deregister(this);
	    }
	    catch (FIPAException fe) {
	      fe.printStackTrace();
	    }

	    myGui.dispose();
	    System.out.println("AGENTE GUI TERMINANDO.");
	 }



	private class MyGenericBehaviour extends Behaviour{

		int count = 0;

		public void action(){

			System.out.println("AGENTE EXISTIENDO");


					try{
						cargarPrimeraVez();
						
					}catch(Exception e){
						System.out.println("ERROR");
					}
					
			count+=1;
		}


		public boolean done(){
			if(count == 1)
				return true;
			else
				return false;
		}

	}



	public void cargarPrimeraVez(){
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

}