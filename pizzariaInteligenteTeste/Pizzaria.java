import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
public class Pizzaria {	
	public static void main(String[] args) {
		
		Runtime rt = Runtime.instance();
		Profile p = new ProfileImpl();
		ContainerController container = rt.createMainContainer(p);
		AgentController seller;

		try {
        	seller = container.createNewAgent("montagem", "agents.AgenteMontagem", null);
			seller.start();
			seller = container.createNewAgent("assador", "agents.AgenteAssador", null);
			seller.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

 }