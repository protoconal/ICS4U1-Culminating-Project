package com.visualnovel.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;

import static com.visualnovel.novel.VisualNovelLoader.mainStage;
import static com.visualnovel.novel.VisualNovelLoader.viewManagement;

abstract public class BaseController {
    public static void changeView(Parent view) {
        // update scene from stage
        // https://stackoverflow.com/a/44191727
        mainStage.getScene().setRoot(view);
    }
    @FXML
    protected void showMainMenu() {
        changeView(viewManagement.getMainMenuView());
    }

    @FXML
    protected void showSaveMenu() {
        changeView(viewManagement.getSaveMenuView());
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