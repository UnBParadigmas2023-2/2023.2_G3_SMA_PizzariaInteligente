package pizzariainteligente;

import javafx.event.ActionEvent;

import pizzariainteligente.agents.AgenteController;

public class FrontController {
    public void pepperoni(ActionEvent e) {
        pizzariainteligente.agents.AgenteController.setEscolha("Pepperoni");
        pizzariainteligente.agents.AgenteController.MenuBehaviour.action;
        System.out.println("SDAFJONDFJSIUSDFHNSDKJFHKUSDHFSDKJBCNJYGFVJHYGDSJCHBDSYUFGCBSDHBC");
    }

    public void marguerita(ActionEvent e) {
        pizzariainteligente.agents.AgenteController.setEscolha("Marguerita");
    }

    public void frango(ActionEvent e) {
        pizzariainteligente.agents.AgenteController.setEscolha("Frango");
    }

    public void quatro(ActionEvent e) {
        pizzariainteligente.agents.AgenteController.setEscolha("Quatro Queijos");
    }
}
