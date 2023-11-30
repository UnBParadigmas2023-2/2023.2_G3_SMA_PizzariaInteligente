package pizzariainteligente.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.StaleProxyException;

import java.util.Scanner;

public class MainAgent extends Agent {

    public void setup() {
        // Adicionar um comportamento que exibe o menu
        addBehaviour(new MenuBehaviour());
    }

    private class MenuBehaviour extends OneShotBehaviour {
        public void action() {
            Scanner scanner = new Scanner(System.in);
            int escolha;

            do {
                System.out.println("\nMenu:");
                System.out.println("1. Pedido de pizza");
                System.out.println("2. Encerrar programa");
                escolha = scanner.nextInt();

                switch (escolha) {
                    case 1:
                        try {
                            ACLMessage msgRx = new ACLMessage(ACLMessage.INFORM);
                            msgRx.addReceiver(new AID("montagem", false));
                            msgRx.setContent("frango");
                            send(msgRx);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;

                    case 2:
                        System.out.println("Encerrando o programa.");
                        break;
                }
            } while (escolha != 2);

            // Feche o scanner
            scanner.close();
        }
    }
}
