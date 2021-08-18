package C482;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;

import java.util.function.UnaryOperator;

public class PartController {
    @FXML
    private Label partLabel = new Label();
    @FXML
    private RadioButton inHouse = new RadioButton();
    @FXML
    private RadioButton outsourced = new RadioButton();
    @FXML
    private TextField id = new TextField();
    @FXML
    private TextField name = new TextField();
    @FXML
    private TextField inv = new TextField();
    @FXML
    private TextField cost = new TextField();
    @FXML
    private TextField max = new TextField();
    @FXML
    private TextField min = new TextField();
    @FXML
    private Label machCompId = new Label();
    @FXML
    private TextField machComp = new TextField();
    @FXML
    public Button save = new Button();
    @FXML
    public Button cancel = new Button();
    @FXML
    public Label errorLabel = new Label();

    @FXML
    public void initialize() {
        save.setDisable(true);

        inHouse.setOnAction(e -> {
            machCompId.setText("Machine ID");
            machCompId.setPadding(new Insets(0, 50, 0, 0));
        });
        outsourced.setOnAction(e -> {
            machCompId.setText("Company Name");
            machCompId.setPadding(new Insets(0, 25, 0, 0));
        });

        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(name.textProperty(),
                        inv.textProperty(),
                        cost.textProperty(),
                        max.textProperty(),
                        min.textProperty(),
                        machComp.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (name.getText().isEmpty()
                        || inv.getText().isEmpty()
                        || cost.getText().isEmpty()
                        || max.getText().isEmpty()
                        || min.getText().isEmpty()
                        || machComp.getText().isEmpty());
            }
        };

        save.disableProperty().bind(bb);

        UnaryOperator<TextFormatter.Change> numericFilter = change -> {
            String text = change.getText();
            if (!text.matches("[0-9]")) {
                change.setText("");

            }
            return change;
        };

        inv.setTextFormatter(new TextFormatter<String>(numericFilter));
        min.setTextFormatter(new TextFormatter<String>(numericFilter));
        max.setTextFormatter(new TextFormatter<String>(numericFilter));

        inv.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (!newVal) {
                boolean bounded = true;
                if (inv.getText().length() > 0) {
                    if (max.getText().length() > 0) {
                        if (Integer.parseInt(inv.getText()) > Integer.parseInt(max.getText())) {
                            inv.setText("");
                            errorLabel.setText("Inv must be set equal to or less than Max");
                            bounded = false;
                        }
                    }
                    else if (min.getText().length() > 0) {
                        if (Integer.parseInt(inv.getText()) < Integer.parseInt(min.getText())) {
                            inv.setText("");
                            errorLabel.setText("Inv must be set equal to or greater than Min");
                            bounded = false;
                        }
                    }
                }
                if (bounded) errorLabel.setText("");
            }
        });

        max.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (!newVal) {
                boolean bounded = true;
                if (max.getText().length() > 0) {
                    if (inv.getText().length() > 0) {
                        if (Integer.parseInt(inv.getText()) > Integer.parseInt(max.getText())) {
                            max.setText("");
                            errorLabel.setText("Max must be set equal to or greater than Inv");
                            bounded = false;
                        }
                    }
                    else if (min.getText().length() > 0) {
                        if (Integer.parseInt(max.getText()) < Integer.parseInt(min.getText())) {
                            max.setText("");
                            errorLabel.setText("Max must be set equal to or greater than Min");
                            bounded = false;
                        }
                    }
                }
                if (bounded) errorLabel.setText("");
            }
        });

        min.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (!newVal) {
                boolean bounded = true;
                if (min.getText().length() > 0) {
                    if (inv.getText().length() > 0) {
                        if (Integer.parseInt(min.getText()) > Integer.parseInt(inv.getText())) {
                            min.setText("");
                            errorLabel.setText("Min must be set equal to or less than Inv");
                            bounded = false;
                        }
                    }
                    else if (max.getText().length() > 0) {
                        if (Integer.parseInt(max.getText()) < Integer.parseInt(min.getText())) {
                            min.setText("");
                            errorLabel.setText("Min must be set equal to or less than Max");
                            bounded = false;
                        }
                    }
                }
                if (bounded) errorLabel.setText("");
            }
        });
    }

    @FXML
    public void clearInput() {
        id.clear();
        machComp.clear();
        name.clear();
        inv.clear();
        cost.clear();
        max.clear();
        min.clear();
    }
}
