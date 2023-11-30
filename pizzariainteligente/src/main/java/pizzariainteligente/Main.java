package pizzariainteligente;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Main {
    public static void main(String[] args) {
        try {
            // Criar instância do Jade Runtime
            Runtime runtime = Runtime.instance();

            // Criar um perfil
            Profile profile = new ProfileImpl();
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            profile.setParameter(Profile.GUI, "true");

            // Criar o container principal
            AgentContainer mainContainer = runtime.createMainContainer(profile);

            // Iniciar o AgenteRecepcao
            AgentController agRecepcao = mainContainer.createNewAgent("recepcao", "pizzariainteligente.agents.AgenteRecepcao", null);
            agRecepcao.start();

            // Iniciar o AgenteMontagem
            AgentController agMontagem = mainContainer.createNewAgent("montagem", "pizzariainteligente.agents.AgenteMontagem", null);
            agMontagem.start();

            // Iniciar o AgenteAssador
            AgentController agAssador = mainContainer.createNewAgent("assador", "pizzariainteligente.agents.AgenteAssador", null);
            agAssador.start();

            AgentController agMain = mainContainer.createNewAgent("main", "pizzariainteligente.agents.MainAgent", null);
            agMain.start();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
