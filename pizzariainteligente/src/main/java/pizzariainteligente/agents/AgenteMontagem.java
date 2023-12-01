package pizzariainteligente.agents;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteMontagem extends Agent {
    public void setup() {
        System.out.println("Olá! Sou Luigi, o montador de pizzas!");

        List<String> pizzasMontar = new ArrayList<String>();

		addBehaviour(new TickerBehaviour(this, 10000) {
			@Override
			public void onTick() {
                if (pizzasMontar.size() > 0){
                    String pizzaAtual = pizzasMontar.remove(0);
                    System.out.println("Montando pizza " + pizzaAtual + "...");
                    
                    ACLMessage msgRx = new ACLMessage(ACLMessage.REQUEST);
                    msgRx.addReceiver(new AID("assador", false));
                    msgRx.setContent(pizzaAtual);  
				    send(msgRx);
                    System.out.println("Enviado para assar: " + pizzaAtual);
                    
                }
			}
		});

        addBehaviour(new CyclicBehaviour() {
			public void action() {
				ACLMessage msgRx = receive();
				if (msgRx != null) {
                    System.out.println("Pizza Recebida para montar: " + msgRx.getContent());
                    pizzasMontar.add(msgRx.getContent());
                    System.out.println("Pizzas na fila para montar: " + String.join(", ", pizzasMontar));
				} else {
					block();
				}
			}
		});
	}
}
