package pizzariainteligente.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.StaleProxyException;

import java.util.Scanner;

public class AgenteController extends Agent {

    public void setup() {
        // Adicionar um comportamento que exibe o menu
        addBehaviour(new MenuBehaviour());
    }

    private class MenuBehaviour extends OneShotBehaviour {
        public void action() {
            Scanner scanner = new Scanner(System.in);
            int escolha;

            do {
                System.out.println("-----------------Menu---------------");
                System.out.println("1. Pedir pizza");
                System.out.println("2. Encerrar programa");
                System.out.println("-------------------------------------");
                escolha = scanner.nextInt();

                switch (escolha) {
                    case 1:
                        try {
                            ACLMessage msgRx = new ACLMessage(ACLMessage.REQUEST);
                            msgRx.addReceiver(new AID("estoque", false));
                            msgRx.setContent(adicionarPizza());
                            send(msgRx);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } while (escolha != 2);

            scanner.close();
        }
    }

    public String adicionarPizza(){
		System.out.println("Qual pizza você quer?");
		Scanner in = new Scanner(System.in); 
		String pizzaEscolhida = in.nextLine();

				
		return pizzaEscolhida;
    }
}
