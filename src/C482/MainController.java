package C482;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.function.UnaryOperator;

public class MainController {
    @FXML
    final Button exitButton = new Button();

    @FXML
    public Button addPart = new Button();
    @FXML
    public Button modPart = new Button();
    @FXML
    public Button delPart = new Button();

    @FXML
    public Button addProd = new Button();
    @FXML
    public Button modProd = new Button();
    @FXML
    public Button delProd = new Button();

    @FXML
    public TableView<Part> partTable = new TableView();
    @FXML
    public TableColumn<Part, String> partId = new TableColumn();
    @FXML
    public TableColumn<Part, String> partName = new TableColumn();
    @FXML
    public TableColumn<Part, String> partLvl = new TableColumn();
    @FXML
    public TableColumn<Part, String> partPrice = new TableColumn();
    @FXML
    public TextField partSearch = new TextField();

    @FXML
    public TableView<Product> prodTable = new TableView();
    @FXML
    public TableColumn<Product, String> prodId = new TableColumn();
    @FXML
    public TableColumn<Product, String> prodName = new TableColumn();
    @FXML
    public TableColumn<Product, String> prodLvl = new TableColumn();
    @FXML
    public TableColumn<Product, String> prodPrice = new TableColumn();
    @FXML
    public TextField prodSearch = new TextField();

    @FXML
    private void end() {
        Platform.exit();
    }

    @FXML
    public void initialize() {
        UnaryOperator<TextFormatter.Change> numericFilter = change -> {
            String text = change.getText();
            if (!text.matches("[0-9]*")) {
                change.setText("");

            }
            return change;
        };
        TextFormatter<String> formatter = new TextFormatter<String>(numericFilter);
        prodSearch.setTextFormatter(formatter);

        prodSearch.focusedProperty().addListener(((observableValue, old, n) -> {
            if (!n) {
                int result = Integer.parseInt(prodSearch.getText());
                if (result > 50 || result < 10) {
                    prodSearch.setText("");
                }
            }
        }));
    }
}
