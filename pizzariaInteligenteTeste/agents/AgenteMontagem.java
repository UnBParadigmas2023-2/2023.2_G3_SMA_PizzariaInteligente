package agents;

import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteMontagem extends Agent {
    public void setup() {
        System.out.println("Olá, sou o agente montador");

        List<String> pizzas = List.of("Calabresa", "Portuguesa", "Marguerita");

		addBehaviour(new OneShotBehaviour() {
			@Override
			public void action() {
                pizzas.forEach(pizza -> {
                    ACLMessage msgRx = new ACLMessage(ACLMessage.REQUEST);
                    msgRx.addReceiver(new AID("assador", false));
                    msgRx.setContent(pizza);  
				    send(msgRx);
                    System.out.println("Enviado para assar: " + pizza);
                });

			}
		});
	}
}
