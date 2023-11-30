package pizzariainteligente.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.HashMap;
import java.util.Map;

public class AgenteEstoque extends Agent {

    private Map<String, ReceitaPizza> receitasPizza;

    @Override
    protected void setup() {
        receitasPizza = new HashMap<>();
        initializeReceitaPizzas();

        addBehaviour(new GerenciadorMensagem());
    }

    private void initializeReceitaPizzas() {
        ReceitaPizza receitaPeperroni = new ReceitaPizza();
        receitaPeperroni.addIngrediente("Massa", 1);
        receitaPeperroni.addIngrediente("Molho", 1);
        receitaPeperroni.addIngrediente("Queijo", 1);
        receitaPeperroni.addIngrediente("Fatias Pepperoni", 8);

        receitasPizza.put("Pepperoni", receitaPeperroni);
    }

    private class GerenciadorMensagem extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                switch (msg.getPerformative()) {
                    case ACLMessage.REQUEST:
                        handleOrder(msg);
                        break;
                    default:
                        break;
                }
            } else {
                block();
            }
        }

        private void handleOrder(ACLMessage order) {
            String flavor = order.getContent();

            if (receitasPizza.containsKey(flavor)) {
                System.out.println("Sabor existe");
            } else {
                System.out.println("Sabor não existe");
            }
        }
    }
}
