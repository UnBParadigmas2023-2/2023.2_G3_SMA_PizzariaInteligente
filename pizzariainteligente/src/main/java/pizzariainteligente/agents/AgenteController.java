package pizzariainteligente.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import javafx.event.ActionEvent;

import java.util.Scanner;

public class AgenteController extends Agent {
    private String escolha = "";

    public void setEscolha(String s) {
        escolha = s;
        escolherOpcao();
    }

    public void setup() {
        addBehaviour(new MenuBehaviour());
    }

    public class MenuBehaviour extends OneShotBehaviour {
        public void action() {
            while (!escolha.equals("-1"))
                escolherOpcao();
        }
    }

    public void escolherOpcao(){
        if ("sair".equals(escolha)){
            System.exit(0);
        }

        try {
            ACLMessage msgRx = new ACLMessage(ACLMessage.REQUEST);
            msgRx.addReceiver(new AID("estoque", false));
            msgRx.setContent(escolha);
            send(msgRx);
            System.out.println("Pizza " + msgRx.getContent() + " enviada para verificação no estoque");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pepperoni(ActionEvent e) {
        setEscolha("Pepperoni");
    }

}
