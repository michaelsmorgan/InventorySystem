package C482;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
    private Scene mainScene;
    private FXMLLoader mainLoader;
    private MainController mC;
    private Scene partScene;
    private FXMLLoader partLoader;
    private PartController pC;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage partStage = new Stage();
        partStage.initModality(Modality.WINDOW_MODAL);

        mainLoader = new FXMLLoader(getClass().getResource("mainForm.fxml"));
        Parent mainRoot = mainLoader.load();
        mC = mainLoader.getController();
        mainScene = new Scene(mainRoot);

        partLoader = new FXMLLoader(getClass().getResource("partForm.fxml"));
        Parent partRoot = partLoader.load();
        pC = partLoader.getController();
        partScene = new Scene(partRoot);

        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        primaryStage.setScene(mainScene);
        primaryStage.show();

        mC.addPart.setOnAction(e -> {
            partStage.setScene(partScene);
            partStage.setResizable(false);
            partStage.show();
        });

        pC.cancel.setOnAction(e -> {
            partStage.close();
            pC.clearInput();
        });

        partStage.setOnCloseRequest(e -> {
            pC.clearInput();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
