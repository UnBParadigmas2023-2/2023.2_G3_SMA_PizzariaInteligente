package pizzariainteligente;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);

        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        ContainerController container = rt.createMainContainer(p);
        AgentController seller;

        try {
            seller = container.createNewAgent("recepcao", "agents.AgenteRecepcao", null);
            seller.start();
            seller = container.createNewAgent("montador", "agents.AgenteMontagem", null);
            seller.start();
            seller = container.createNewAgent("assador", "agents.AgenteAssador", null);
            seller.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1600, 900));
        primaryStage.show();
    }

}