package agents;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteRecepcao extends Agent {

	List<String> pizzasEntregar = new ArrayList<String>();

    public void setup() {

		addBehaviour(new CyclicBehaviour() {
			public void action() {
				ACLMessage msgRx = receive();
				if (msgRx != null) {
                    System.out.println("Pizza Recebida para entregar: " + msgRx.getContent());
                    pizzasEntregar.add(msgRx.getContent());
                    System.out.println("Pizzas na fila para entregar: " + String.join(", ", pizzasEntregar));
				} else {
					block();
				}
			}
		});

		addBehaviour(new OneShotBehaviour(){
			public void action() {
				menu();
			}
		});

	}

	public void menu(){

		int opcao;
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("\tPizzaria inteligente");
		System.out.println("1. Adicionar pizza");
		System.out.println("2. Entregar pizza");
		System.out.println("3. verificar filas");
		System.out.println("4. sair");
		System.out.println("Opcao:");

		opcao = entrada.nextInt();
		
		switch(opcao){
		case 1:
			adicionarPizza();
			break;
			
		case 2:
			entregarPizza();
			break;
			
		case 3:
			verificarFilas();
			break;

		default:
			System.out.println("Opção inválida.");
		}

		return;
    }

	public void adicionarPizza(){
		System.out.println("Qual pizza você quer?");
		Scanner in = new Scanner(System.in); 
		String pizzaEscolhida = in.nextLine();

		ACLMessage msgRx = new ACLMessage(ACLMessage.REQUEST);
			msgRx.addReceiver(new AID("montador", false));
			msgRx.setContent(pizzaEscolhida);  
			send(msgRx);
			System.out.println("Enviado para montagem: " + pizzaEscolhida);
		
		menu();
		
		return;

    }
    
	public void entregarPizza() {
		System.out.println("Qual pizza você quer entregar?\n");
		for (int i = 0; i < pizzasEntregar.size(); i++) {
			System.out.println(i + 1 + ". " + pizzasEntregar.get(i));
		}
	
		Scanner in = new Scanner(System.in);
		int pizzaIndex = in.nextInt() - 1;
		
		// Verifica se o índice fornecido é válido
		if (pizzaIndex >= 0 && pizzaIndex < pizzasEntregar.size()) {
			String pizzaEntregar = pizzasEntregar.get(pizzaIndex);
	
			// Envia a pizza para o agente de montagem
			ACLMessage msgRx = new ACLMessage(ACLMessage.REQUEST);
			msgRx.addReceiver(new AID("montador", false));
			msgRx.setContent(pizzaEntregar);
			send(msgRx);
			System.out.println("Enviado para montagem: " + pizzaEntregar);
	
			// Remove a pizza da lista de pizzas a entregar
			pizzasEntregar.remove(pizzaIndex);
		} else {
			System.out.println("Índice inválido. Tente novamente.");
		}
	
		menu();
	}
	
	
    
    public void verificarFilas(){
        System.out.println("Você entrou no método Exclui.");
		menu();
    }
}
