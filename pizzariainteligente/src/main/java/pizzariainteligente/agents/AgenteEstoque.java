package pizzariainteligente.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.HashMap;
import java.util.Map;
import pizzariainteligente.ReceitaPizza;

public class AgenteEstoque extends Agent {

    private Map<String, Integer> estoqueIngredientes;
    private Map<String, ReceitaPizza> receitasPizza;

    @Override
    protected void setup() {
        populaEstoqueIngredientes();
        populaReceitaPizzas();
        addBehaviour(new GerenciadorMensagem());
    }

    private void populaEstoqueIngredientes() {
        estoqueIngredientes = new HashMap<>();
        estoqueIngredientes.put("Massa", 50);
        estoqueIngredientes.put("Molho", 30);
        estoqueIngredientes.put("Queijo", 12);
        estoqueIngredientes.put("Fatias Pepperoni", 10);
        estoqueIngredientes.put("Folhas manjericao", 18);
        estoqueIngredientes.put("Frango", 40);
        estoqueIngredientes.put("Catupiry", 20);
    }

    private void populaReceitaPizzas() {
        receitasPizza = new HashMap<>();

        ReceitaPizza receitaPepperoni = new ReceitaPizza();
        receitaPepperoni.addIngrediente("Massa", 1);
        receitaPepperoni.addIngrediente("Molho", 1);
        receitaPepperoni.addIngrediente("Queijo", 1);
        receitaPepperoni.addIngrediente("Fatias Pepperoni", 8);

        ReceitaPizza receitaMarguerita = new ReceitaPizza();
        receitaMarguerita.addIngrediente("Massa", 1);
        receitaMarguerita.addIngrediente("Molho", 1);
        receitaMarguerita.addIngrediente("Queijo", 1);
        receitaMarguerita.addIngrediente("Folhas manjericao", 5);

        ReceitaPizza receitaFrango = new ReceitaPizza();
        receitaFrango.addIngrediente("Massa", 1);
        receitaFrango.addIngrediente("Molho", 1);
        receitaFrango.addIngrediente("Queijo", 1);
        receitaFrango.addIngrediente("Frango", 20);
        receitaFrango.addIngrediente("Catupiry", 6);

        ReceitaPizza receitaQuatroQueijos = new ReceitaPizza();
        receitaQuatroQueijos.addIngrediente("Massa", 1);
        receitaQuatroQueijos.addIngrediente("Molho", 1);
        receitaQuatroQueijos.addIngrediente("Queijo", 4);

        receitasPizza.put("Pepperoni", receitaPepperoni);
        receitasPizza.put("Marguerita", receitaMarguerita);
        receitasPizza.put("Frango", receitaFrango);
        receitasPizza.put("Quatro Queijos", receitaQuatroQueijos);
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
