package com.visualnovel.controllers;

import com.visualnovel.novel.Game;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.util.Duration;

import static com.visualnovel.novel.VisualNovelLoader.viewManagement;

abstract public class BaseController {
    PauseTransition delay;
    private boolean timeout = false;

    public boolean isNotTimedOut() {
        return !timeout;
    }

    public void setTimeout(double time) {
        timeout = true;
        // https://stackoverflow.com/a/27334614
        delay = new PauseTransition(Duration.seconds(time));
        delay.setOnFinished( actionEvent -> this.timeout = false);
        delay.play();
    }

    @FXML
    public void showMainMenu() {
        ManagementHub.changeView(viewManagement.getMainMenuView());
    }

    @FXML
    public void showSaveMenu() {
        ManagementHub.changeView(viewManagement.getSaveMenuView());
    }

    @FXML
    public static void showScene() {
        // check if in scene or choice
        System.out.println("processed");
        if (Game.gameState.equals("DIALOGUE")) {
            System.out.println("dd");
            ManagementHub.changeView(viewManagement.getSceneView());
        }
        else if (Game.gameState.equals("CHOICE")) {
            System.out.println("cc");
            ManagementHub.changeView(viewManagement.getChoiceView());
        }
    }

    @FXML
    public void goBack() {
        System.out.println(Game.gameState);
        // check if in scene or choice
        if (Game.gameState.equals("MAINMENU")) {
            ManagementHub.changeView(viewManagement.getMainMenuView());
        }
        else if (Game.gameState.equals("DIALOGUE")) {
            ManagementHub.changeView(viewManagement.getSceneView());
        }
        else if (Game.gameState.equals("CHOICE")) {
            ManagementHub.changeView(viewManagement.getChoiceView());
        }
    }

    @FXML
    public void quitWithoutSaving() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void saveAndQuit() {
        // TODO: implement quicksave

        quitWithoutSaving();
    }
}