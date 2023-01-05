package com.visualnovel.controllers;

import com.visualnovel.novel.Game;
import javafx.fxml.FXML;
public class MainMenuController extends BaseController {
    @FXML
    public void start() {
        new Game("001");
    }
}