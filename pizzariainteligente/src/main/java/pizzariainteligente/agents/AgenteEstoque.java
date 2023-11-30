package pizzariainteligente.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.HashMap;
import java.util.Map;

public class AgenteEstoque extends Agent {

    private Map<String, Integer> estoqueIngredientes;
    private Map<String, ReceitaPizza> receitasPizza;

    @Override
    protected void setup() {
        estoqueIngredientes = new HashMap<>();
        estoqueIngredientes.put("Massa", 50);
        estoqueIngredientes.put("Molho", 30);
        estoqueIngredientes.put("Queijo", 40);
        estoqueIngredientes.put("Fatias Pepperoni", 10);

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
            String saborEscolhido = order.getContent();

            if (receitasPizza.containsKey(saborEscolhido)) {
                if(possuiIngredientes(saborEscolhido)){
                    removerDoEstoque(saborEscolhido);
                    System.out.println("Estoque atualizado: " + estoqueIngredientes);

                    ACLMessage msgRx = new ACLMessage(ACLMessage.REQUEST);
                    msgRx.addReceiver(new AID("montador", false));
                    msgRx.setContent(saborEscolhido);
                    send(msgRx);
                    System.out.println("Enviado para montagem: " + saborEscolhido);
                }
                else {
                    ACLMessage msgRx = new ACLMessage(ACLMessage.REQUEST);
                    msgRx.addReceiver(new AID("recepcao", false));
                    msgRx.setContent(saborEscolhido+"-ingredientes insuficientes");
                    send(msgRx);

                    System.out.println("enviado pra recepcao");
                }
            } else {
                ACLMessage msgRx = new ACLMessage(ACLMessage.REQUEST);
                msgRx.addReceiver(new AID("recepcao", false));
                msgRx.setContent(saborEscolhido+"-sabor não existe");
                send(msgRx);

                System.out.println("enviado pra recepcao");
            }
        }

        private boolean possuiIngredientes(String sabor) {
            Map<String, Integer> ingredientes = receitasPizza.get(sabor).getIngredients();
            for (Map.Entry<String, Integer> item : ingredientes.entrySet()) {
                String ingrediente = item.getKey();
                int qtdNecessaria = item.getValue();

                if (!estoqueIngredientes.containsKey(ingrediente) || estoqueIngredientes.get(ingrediente) < qtdNecessaria) {
                    return false;
                }
            }
            return true;
        }

        private void removerDoEstoque(String sabor){
            Map<String, Integer> ingredientes = receitasPizza.get(sabor).getIngredients();
            for (Map.Entry<String, Integer> item : ingredientes.entrySet()) {
                String ingrediente = item.getKey();
                int qtdNecessaria = item.getValue();
                int qtdAtual = estoqueIngredientes.get(ingrediente);

                estoqueIngredientes.replace(ingrediente, qtdAtual-qtdNecessaria);
            }
        }
    }
}
