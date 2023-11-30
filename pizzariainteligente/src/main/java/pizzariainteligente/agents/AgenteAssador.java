package pizzariainteligente.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AgenteAssador extends Agent {
    private List<String> pizzasAssar = new ArrayList<>();

    public void setup() {
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msgMontador = receive();

                if (msgMontador != null || pizzasAssar.size() != 0) {
                    String pizzaAtual = msgMontador.getContent();
                    pizzasAssar.add(pizzaAtual);
                    System.out.println("A pizza " + pizzaAtual + " está sendo assada");

                    // Simulando o tempo de assar
                    try {
                        TimeUnit.SECONDS.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("A pizza " + pizzaAtual + " terminou de assar e foi enviada para a recepção");

                    // Enviar sinal para AgenteRecepcao
                    ACLMessage msgRx = new ACLMessage(ACLMessage.INFORM);
                    msgRx.addReceiver(new AID("recepcao", false));
                    msgRx.setContent(pizzasAssar.remove(0));
                    send(msgRx);
                } else {
                    block();
                }
            }
        });
    }
}
