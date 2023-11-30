
package pizzariainteligente.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import java.util.List;

public class AgenteAssador extends Agent {
    private List<String> pizzasAssar = new ArrayList<>();

    protected void setup() {
        addBehaviour(new AssarPizza());
    }

    private class AssarPizza extends CyclicBehaviour {
        public void action() {
            if (!pizzasAssar.isEmpty()) {
                String nomePizza = pizzasAssar.get(0);

                // Simular tempo de assagem
                doWait(20000);
                System.out.println("Pizza assada: " + nomePizza);

                // Enviar pizza para AgenteRecepcao
                ACLMessage mensagem = new ACLMessage(ACLMessage.INFORM);
                mensagem.setContent(nomePizza);
                mensagem.addReceiver(getAID("AgenteRecepcao"));
                send(mensagem);

                // Remover pizza da lista
                pizzasAssar.remove(0);
            }
        }
    }
}
