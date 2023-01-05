package com.visualnovel.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import static com.visualnovel.novel.Game.dialogueOrchestrator;

public class ViewSceneController extends BaseController {
    @FXML
    private Text characterLabel;
    @FXML
    private Text characterDialogue;

    @FXML
    public void updateNameBox(String line) { characterLabel.setText(line); }

    @FXML
    public void updateDialogueBox(String line) {
        characterDialogue.setText(line);
    }

    @FXML
    public void onClick() {
        if (isNotTimedOut()) {
            setTimeout(0.5);
            dialogueOrchestrator.nextLine();
        }
    }
}