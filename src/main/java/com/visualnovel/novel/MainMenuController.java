package com.visualnovel.novel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
public class MainMenuController {
    @FXML
    protected void onSaveMenuClick(ActionEvent event) {
        VisualNovel.changeSceneFromController(event, "save-menu.fxml");
    }
}