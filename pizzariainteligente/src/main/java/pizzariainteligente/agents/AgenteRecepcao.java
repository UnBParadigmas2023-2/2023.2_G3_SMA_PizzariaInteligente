package pizzariainteligente.agents;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteRecepcao extends Agent {

	List<String> pizzasEntregar = new ArrayList<String>();

    public void setup() {

		addBehaviour(new CyclicBehaviour() {
			public void action() {
				ACLMessage msgRx = receive();
				if (msgRx != null) {
                    System.out.println("Pizza Recebida para entregar: " + msgRx.getContent());
                    pizzasEntregar.add(msgRx.getContent());
                    System.out.println("Pizzas na fila para entregar: " + String.join(", ", pizzasEntregar));
				} else {
					block();
				}
			}
		});

	}
}
