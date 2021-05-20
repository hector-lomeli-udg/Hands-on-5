package agents.src;

import jade.core.Agent;
import jade.core.behaviours.*;

public class AgentScript extends Agent{

	private Script myGui;

	String texto1 = "";
	String texto2 = "";

	protected void setup(){
		myGui = new Script(this);
		myGui.showGui();

		addBehaviour(new MyGenericBehaviour());

	}



	public void mostrarRutaPersona(final String ruta1, final String ruta2) {
		addBehaviour(new OneShotBehaviour() {
			public void action() {
				texto1 = ruta1;
				texto2 = ruta2;

				System.out.println(ruta1 +" insertado.\n");
				System.out.println(ruta2 +" insertado.\n");
			}
		} );
	}


	private class MyGenericBehaviour extends Behaviour{

		int count = 0;

		public void action(){

			System.out.println("AGENTE EXISTIENDO");
			//algo
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

			//System.out.println("EL AGENTE FINALIZO");
		}

	}
}