package com.visualnovel.controllers;

import com.visualnovel.novel.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChoiceSceneController extends BaseController {
    private String[] routingTable;
    @FXML
    private Button optionOne;
    @FXML
    private Button optionTwo;
    @FXML
    private Button optionThree;

    @FXML
    protected void clickOptionOne() {
        this.nextScene(this.routingTable[0]);
    }
    @FXML
    protected void clickOptionTwo() {
        this.nextScene(this.routingTable[1]);
    }
    @FXML
    protected void clickOptionThree() {
        this.nextScene(this.routingTable[2]);
    }

    private void nextScene(String sceneID) {
        Game.processNextScene(sceneID);
    }

    public void updateChoices(String[] choices) {
        optionOne.setText(choices[0]);
        optionTwo.setText(choices[1]);
        optionThree.setText(choices[2]);
    }

    public void updateRouting(String[] routes) {
        this.routingTable = routes;
    }

}