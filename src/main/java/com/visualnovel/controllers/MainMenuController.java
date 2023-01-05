package com.visualnovel.controllers;

import com.visualnovel.novel.Game;
import javafx.fxml.FXML;

import static com.visualnovel.novel.VisualNovelLoader.viewManagement;

public class MainMenuController extends BaseController {
    @FXML
    public void start() {
        new Game("001");
        ManagementHub.changeView(viewManagement.getSceneView());
    }
}