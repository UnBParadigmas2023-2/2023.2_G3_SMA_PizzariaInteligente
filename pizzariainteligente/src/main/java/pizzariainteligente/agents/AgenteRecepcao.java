
package pizzariainteligente.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgenteRecepcao extends Agent {
    private List<String> pizzasProntas = new ArrayList<>();

    protected void setup() {
        addBehaviour(new ReceberPedidos());
        addBehaviour(new RealizarEntrega());
    }

    private class ReceberPedidos extends CyclicBehaviour {
        public void action() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Menu:");
            System.out.println("1. Adicionar pizza");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();

            if (escolha == 1) {
                System.out.print("Digite o nome da pizza: ");
                String nomePizza = scanner.next();

                // Enviar pizza para AgenteMontagem
                ACLMessage mensagem = new ACLMessage(ACLMessage.REQUEST);
                mensagem.setContent(nomePizza);
                mensagem.addReceiver(getAID("AgenteMontagem"));
                send(mensagem);

                System.out.println("Pizza adicionada com sucesso!");
            } else if (escolha == 2) {
                doDelete(); // Encerra o agente
            }
        }
    }

    private class RealizarEntrega extends CyclicBehaviour {
        public void action() {
            if (!pizzasProntas.isEmpty()) {
                String nomePizza = pizzasProntas.get(0);

                // Simular tempo de entrega
                System.out.println("A pizza está sendo entregue...");
                doWait(15000);
                System.out.println("A pizza foi entregue ao cliente: " + nomePizza);

                // Remover pizza da lista
                pizzasProntas.remove(0);
            }
        }
    }
}
