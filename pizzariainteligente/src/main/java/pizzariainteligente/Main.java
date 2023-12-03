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

    String AgenteRecepcao = "pizzariainteligente.agents.AgenteRecepcao";
    String AgenteEstoque = "pizzariainteligente.agents.AgenteRecepcao";
    String AgenteMontagem = "pizzariainteligente.agents.AgenteRecepcao";
    String AgenteAssador = "pizzariainteligente.agents.AgenteRecepcao";
    String AgenteController = "pizzariainteligente.agents.AgenteRecepcao";

    try {
      seller = container.createNewAgent("recepcao", AgenteRecepcao, null);
      seller.start();
      seller = container.createNewAgent("estoque", AgenteEstoque, null);
      seller.start();
      seller = container.createNewAgent("montador", AgenteMontagem, null);
      seller.start();
      seller = container.createNewAgent("assador", AgenteAssador, null);
      seller.start();
      seller = container.createNewAgent("controller", AgenteController, null);
      seller.start();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}