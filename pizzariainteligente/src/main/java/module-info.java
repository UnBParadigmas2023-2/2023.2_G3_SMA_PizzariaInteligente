module pizzariainteligente {
    requires javafx.controls;
    requires javafx.fxml;
    requires jade;

    opens pizzariainteligente to javafx.fxml;
    exports pizzariainteligente;
    exports pizzariainteligente.agents;
}
