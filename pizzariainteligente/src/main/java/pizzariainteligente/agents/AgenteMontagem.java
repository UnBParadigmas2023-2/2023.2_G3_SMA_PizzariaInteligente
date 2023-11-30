
package pizzariainteligente.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import java.util.List;


public class AgenteMontagem extends Agent {
    private List<String> pizzasMontar = new ArrayList<>();

    protected void setup() {
        addBehaviour(new MontarPizza());
    }

    private class MontarPizza extends CyclicBehaviour {
        public void action() {
            if (!pizzasMontar.isEmpty()) {
                String nomePizza = pizzasMontar.get(0);

                // Simular tempo de montagem
                doWait(10000);
                System.out.println("Pizza montada: " + nomePizza);

                // Enviar pizza para AgenteAssador
                ACLMessage mensagem = new ACLMessage(ACLMessage.INFORM);
                mensagem.setContent(nomePizza);
                mensagem.addReceiver(getAID("AgenteAssador"));
                send(mensagem);

                // Remover pizza da lista
                pizzasMontar.remove(0);
            }
        }
    }
}
