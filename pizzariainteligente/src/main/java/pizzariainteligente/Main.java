package pizzariainteligente;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class Main {
  public static void main(String[] args) {

    Runtime rt = Runtime.instance();
    Profile p = new ProfileImpl();
    ContainerController container = rt.createMainContainer(p);
    AgentController seller;

    try {
      seller = container.createNewAgent("recepcao", "pizzariainteligente.agents.AgenteRecepcao", null);
      seller.start();
      seller = container.createNewAgent("estoque", "pizzariainteligente.agents.AgenteEstoque", null);
      seller.start();
      seller = container.createNewAgent("montador", "pizzariainteligente.agents.AgenteMontagem", null);
      seller.start();
      seller = container.createNewAgent("assador", "pizzariainteligente.agents.AgenteAssador", null);
      seller.start();
      seller = container.createNewAgent("controller", "pizzariainteligente.agents.AgenteController", null);
      seller.start();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}