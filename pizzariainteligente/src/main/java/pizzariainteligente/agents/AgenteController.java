package pizzariainteligente.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.StaleProxyException;

import java.util.Scanner;

public class AgenteController extends Agent {

    public void setup() {
        addBehaviour(new MenuBehaviour());
    }

    private class MenuBehaviour extends OneShotBehaviour {
        public void action() {
            Scanner scanner = new Scanner(System.in);
            int escolha;

            do {
                System.out.println("-----------------MARIO'S PIZZARIA---------------");
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
                            System.out.println("Pizza " + msgRx.getContent() + " enviada para verificação no estoque");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                    case 2:
                        System.out.println("Obrigado por ter vindo!");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } while (escolha != 2);

            scanner.close();
            System.exit(0);
        }
    }

    public String adicionarPizza(){
		System.out.println("Qual pizza você quer?");
        System.out.println("-----------------SABORES---------------");
        System.out.println("Pepperoni");
        System.out.println("Marguerita");
        System.out.println("Frango");
        System.out.println("Quatro Queijos");
        System.out.println("-------------------------------------");
		Scanner in = new Scanner(System.in); 
		String pizzaEscolhida = in.nextLine();

				
		return pizzaEscolhida;
    }
}
