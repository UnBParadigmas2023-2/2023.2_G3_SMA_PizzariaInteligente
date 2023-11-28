package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteAssador extends Agent {
    public void setup() {
        System.out.println("Olá, sou o agente Assador");
        addBehaviour(new CyclicBehaviour() {
			public void action() {
				ACLMessage msgRx = receive();
				if (msgRx != null) {
                    System.out.println("Pizza assando: " + msgRx.getContent());
					// System.out.println("Assando : " + msgRx.getContent().split(":")[1]);
				} else {
					block();
				}
			}
		});
	}
}
