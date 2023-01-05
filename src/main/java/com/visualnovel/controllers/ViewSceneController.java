package com.visualnovel.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class ViewSceneController extends BaseController {
    @FXML
    private Text characterLabel;
    @FXML
    private Text characterDialogue;

    public void updateNameBox(String line) { characterLabel.setText(line); }

    public void updateDialogueBox(String line) {
        characterDialogue.setText(line);
    }


}