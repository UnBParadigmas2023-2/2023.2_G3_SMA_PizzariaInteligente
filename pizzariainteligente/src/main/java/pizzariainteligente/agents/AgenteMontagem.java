package pizzariainteligente.agents;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AgenteMontagem extends Agent {
    private List<String> pizzasMontar = new ArrayList<>();

    public void setup() {

       addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msgMain = receive();

                if (msgMain != null || pizzasMontar.size() != 0) {
                    String pizzaAtual = msgMain.getContent();
                    pizzasMontar.add(pizzaAtual);
                    System.out.println("A pizza " + pizzaAtual + " está sendo montada");

                    // Simulando o tempo de assar
                    try {
                        TimeUnit.SECONDS.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("A pizza " + pizzaAtual + " terminou de ser montada e será assada");

                    // Enviar sinal para AgenteRecepcao
                    ACLMessage msgRx = new ACLMessage(ACLMessage.INFORM);
                    msgRx.addReceiver(new AID("assador", false));
                    msgRx.setContent(pizzasMontar.remove(0));
                    send(msgRx);
                } else {
                    block();
                }
            }
        });
    }
}