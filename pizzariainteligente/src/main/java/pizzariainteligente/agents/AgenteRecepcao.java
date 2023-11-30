package pizzariainteligente.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class AgenteRecepcao extends Agent {
    private List<String> pizzasProntas = new ArrayList<>();

    public void setup() {

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msgAssador = receive();

				

                if (msgAssador != null || pizzasProntas.size() != 0) {
                    String pizzaAtual = msgAssador.getContent();
                    pizzasProntas.add(pizzaAtual);
                    System.out.println("A pizza " + pizzaAtual + " está sendo preparada para ser entregue");

                    // Simulando o tempo de assar
                    try {
                        TimeUnit.SECONDS.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("A pizza " + pizzaAtual + " foi entregue");
					pizzasProntas.remove(0);

                } else {
                    block();
                }
            }
        });
		
    }
}
