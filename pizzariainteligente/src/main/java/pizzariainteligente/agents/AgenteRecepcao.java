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
		System.out.println("Olá! Eu sou Bowser, o recepcionista!");

		addBehaviour(new CyclicBehaviour() {
			public void action() {
				ACLMessage msgRx = receive();
				if (msgRx != null) {
					switch (msgRx.getPerformative()) {
						case ACLMessage.CONFIRM:
							System.out.println("Pizza Recebida para entregar: " + msgRx.getContent());
							pizzasEntregar.add(msgRx.getContent());
							System.out.println("Pizzas na fila para entregar: " + String.join(", ", pizzasEntregar));
							
							break;
						case ACLMessage.CANCEL:
							String[] info = msgRx.getContent().split("-");
							String pedido = info[0];
							String motivo = info[1];
							System.out.println("Nao foi possivel concluir o pedido da pizza de " + pedido+" por conta de "+motivo);

							break;
						default:
							break;
					}
				} else {
					block();
				}
			}
		});

	}
}
