package com.visualnovel.controllers;

import com.visualnovel.novel.VisualNovelLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

import static com.visualnovel.novel.VisualNovelLoader.mainStage;

public class ManagementHub {
    private final Parent mainMenu;
    private final Parent saveMenu;
    private final Parent viewScene;
    private final Parent choiceScene;
    private final MainMenuController mainMenuController;
    private final SaveMenuController saveMenuController;
    private final ViewSceneController viewSceneController;
    private final ChoiceSceneController choiceSceneController;

    public ManagementHub() throws IOException {
        FXMLLoader fxmlloader;

        // loading
        fxmlloader = new FXMLLoader(VisualNovelLoader.class.getResource("main-menu.fxml"));
        mainMenu = fxmlloader.load();
        this.mainMenuController = fxmlloader.getController();

        fxmlloader = new FXMLLoader(VisualNovelLoader.class.getResource("save-menu.fxml"));
        saveMenu = fxmlloader.load();
        this.saveMenuController = fxmlloader.getController();

        fxmlloader = new FXMLLoader(VisualNovelLoader.class.getResource("view-scene.fxml"));
        viewScene = fxmlloader.load();
        this.viewSceneController = fxmlloader.getController();

        fxmlloader = new FXMLLoader(VisualNovelLoader.class.getResource("choice-scene.fxml"));
        choiceScene = fxmlloader.load();
        this.choiceSceneController = fxmlloader.getController();
    }

    public Parent getMainMenuView() {
        return this.mainMenu;
    }

    public Parent getSaveMenuView() {
        return this.saveMenu;
    }

    public Parent getSceneView() {
        return this.viewScene;
    }

    public Parent getChoiceView() { return choiceScene; }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    public SaveMenuController getSaveMenuController() {
        return saveMenuController;
    }

    public ViewSceneController getViewSceneController() {
        return viewSceneController;
    }

    public ChoiceSceneController getChoiceSceneController() { return choiceSceneController; }

    public static void changeView(Parent view) {
        // update scene from stage
        // https://stackoverflow.com/a/44191727
        try {
            mainStage.getScene().setRoot(view);
        }
        catch (NullPointerException e) {
            System.out.println("WARNING: Error in changing views. Stage and or View is not found.");
        }
    }




}
