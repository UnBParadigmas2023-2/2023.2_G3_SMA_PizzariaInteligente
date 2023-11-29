package agents;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteAssador extends Agent {
    public void setup() {
		List<String> pizzasAssar = new ArrayList<String>();

		addBehaviour(new TickerBehaviour(this, 10000) {
			public void onTick() {
				// Lógica para assar pizzas
				if (pizzasAssar.size() > 0) {
					String pizzaAtual = pizzasAssar.remove(0);
					System.out.println("Assado pizza: " + pizzaAtual);
		
					ACLMessage msgRx = new ACLMessage(ACLMessage.REQUEST);
					msgRx.addReceiver(new AID("recepcao", false));
					msgRx.setContent(pizzaAtual);
					send(msgRx);
					System.out.println("Enviado para recepcao: " + pizzaAtual);
				}
		
				// Lógica para receber pizzas para assar
				ACLMessage msgRx = receive();
				if (msgRx != null) {
					System.out.println("Pizza Recebida para assar: " + msgRx.getContent());
					pizzasAssar.add(msgRx.getContent());
					System.out.println("Pizzas na fila para assar: " + String.join(", ", pizzasAssar));
				}
			}
		});
		



        addBehaviour(new CyclicBehaviour() {
			public void action() {
				ACLMessage msgRx = receive();
				if (msgRx != null) {
                    System.out.println("Pizza Recebida para assar: " + msgRx.getContent());
                    pizzasAssar.add(msgRx.getContent());
                    System.out.println("Pizzas na fila para assar: " + String.join(", ", pizzasAssar));
				} else {
					block();
				}
			}
		});
	}
}
