package com.visualnovel.novel;

import com.visualnovel.controllers.ViewSceneController;

import java.util.ArrayList;
import java.util.List;

import static com.visualnovel.novel.VisualNovelLoader.viewManagement;

public class Game {
    public static String gameState = "";

    private final ViewSceneController sceneController = viewManagement.getViewSceneController();
    public static final DialogueOrchestrator dialogueOrchestrator = new DialogueOrchestrator();
    private VisualSceneContainer currentSceneContainer;
    private VisualScene currentScene;
    final static ArrayList<String> validCharacterTags = new ArrayList<>(List.of(new String[]{"PHB", "JSH", "DAN"}));
    final static Character[] characters = {
            new Phoebe(new String[]{""}),
            new Josh(new String[]{""}),
            new Daniel(new String[]{""}),
    };

    String currentSceneID;

    public Game(String currentSceneID) {
        this.currentSceneID = currentSceneID;
        processParentScene(currentSceneID);
    }

    public void processParentScene(String sceneID) {
        this.currentSceneContainer = SceneRawParser.parseRaw(sceneID);
        processScene(sceneID);
    }

    public void processScene(String sceneID) {
        this.currentScene = currentSceneContainer.getScene(sceneID);
        // update dialogue orchestrator with raw data
        dialogueOrchestrator.updateOrchestrator(this.currentScene.getDialogue());
        gameState = "DIALOGUE";
    }
}
