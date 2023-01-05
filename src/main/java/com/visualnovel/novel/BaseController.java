package com.visualnovel.novel;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

abstract public class BaseController {
    @FXML
    protected void showMainMenu(ActionEvent event) {
        VisualNovel.changeSceneFromController(event, "main-menu.fxml");
    }

    @FXML
    protected void showSaveMenu(ActionEvent event) {
        VisualNovel.changeSceneFromController(event, "save-menu.fxml");
    }

    @FXML
    protected void quitWithoutSaving() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void saveAndQuit() {
        // TODO: implement quicksave
        // quicksave

        quitWithoutSaving();
    }
}