package com.visualnovel.novel;

import com.visualnovel.controllers.ManagementHub;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VisualNovelLoader extends Application {
    public static ManagementHub viewManagement;
    public static Stage mainStage;
    final String gameName = "TESTING GAME";

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        viewManagement = new ManagementHub();

        // set initial view to mainmenu
        Scene scene = new Scene(viewManagement.getMainMenuView());
        stage.setTitle(gameName);
        stage.setScene(scene);
        stage.show();

        new Game("001");
        // changes only work if after displayed.
        // ManagementHub.changeView(viewManagement.getSceneView());
    }

    public static void main(String[] args) {
        launch();
    }
}