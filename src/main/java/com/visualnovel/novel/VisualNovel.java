package com.visualnovel.novel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class VisualNovel extends Application {
    final String gameName = "TESTING GAME";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VisualNovel.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(gameName);
        stage.setScene(scene);
        stage.show();
    }

    public static void changeSceneFromController(ActionEvent event, String sceneFile) {
        // https://stackoverflow.com/a/37202221
        Parent pane = null;
        try {
            pane = FXMLLoader.load(
                    Objects.requireNonNull(
                            VisualNovel.class.getResource(sceneFile)
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // get scene from any controller
        // https://stackoverflow.com/a/44191727
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        mainStage.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}