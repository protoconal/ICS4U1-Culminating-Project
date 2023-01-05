package com.visualnovel.novel;

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
}