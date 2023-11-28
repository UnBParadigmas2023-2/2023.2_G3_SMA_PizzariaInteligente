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
        addBehaviour(new TickerBehaviour(this, 20000) {
			public void onTick() {
				ACLMessage msgPizzaAssar = receive();
				if (msgPizzaAssar != null) {
                    System.out.println("Pizza assando: " + msgPizzaAssar.getContent());

					ACLMessage msgPizzaPronta = new ACLMessage(ACLMessage.REQUEST);
                    msgPizzaPronta.addReceiver(new AID("recepcao", false));
                    msgPizzaPronta.setContent(msgPizzaAssar.getContent());  
				    send(msgPizzaPronta);
                    System.out.println("Enviado para recepcao: " + msgPizzaAssar.getContent());
				} else {
					block();
				}
			}
		});

		List<String> pizzasAssar = new ArrayList<String>();

		addBehaviour(new TickerBehaviour(this, 10000) {
			@Override
			public void onTick() {
                if (pizzasAssar.size() > 0){
                    String pizzaAtual = pizzasAssar.remove(0);
                    System.out.println("Assado pizza: " + pizzaAtual);
                    
                    ACLMessage msgRx = new ACLMessage(ACLMessage.REQUEST);
                    msgRx.addReceiver(new AID("recepcao", false));
                    msgRx.setContent(pizzaAtual);  
				    send(msgRx);
                    System.out.println("Enviado para recepcao: " + pizzaAtual);
                    
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
