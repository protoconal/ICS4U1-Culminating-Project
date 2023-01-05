package com.visualnovel.novel;

import com.visualnovel.controllers.BaseController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    public static String gameState = "MAINMENU";
    public static final DialogueOrchestrator dialogueOrchestrator = new DialogueOrchestrator();
    private static VisualSceneContainer currentSceneContainer;
    private static VisualScene currentScene;
    final static ArrayList<String> validCharacterTags = new ArrayList<>(List.of(new String[]{"PHB", "JSH", "DAN"}));
    final static Character[] characters = {
            new Phoebe(new String[]{""}),
            new Josh(new String[]{""}),
            new Daniel(new String[]{""}),
    };

    public Game(String currentSceneID) {
        processNextScene(currentSceneID);
    }

    public static void processNextScene(String nextSceneID) {
        // if only is numbers, then it must be a scene id
        if (Utils.isNumeric(nextSceneID)) {
            processParentScene(nextSceneID);
        }
        else {
            processScene(nextSceneID);
        }
    }

    public static void processParentScene(String sceneID) {
        currentSceneContainer = SceneRawParser.parseRaw(sceneID);
        processScene(sceneID);
    }

    public static void processScene(String sceneID) {
        currentScene = currentSceneContainer.getScene(sceneID);
        // process emotions
        ArrayList<String[]> emotions = (ArrayList<String[]>) currentScene.getEmotionConsequences().clone();
        System.out.println(emotions);
        for (int x = 0; x < emotions.size(); x++) {
            String[] emotion = emotions.remove(0);
            System.out.println(Arrays.toString(emotion));

            int characterIndex = validCharacterTags.indexOf(emotion[0]);
            Character character = characters[characterIndex];

            character.updateEmotionIndex(emotion[1], Integer.parseInt(emotion[2]));
            System.out.println("Updated Emotion indexi: " + Arrays.toString(character.getEmotionIndex()));
        }

        // update dialogue orchestrator with raw data
        dialogueOrchestrator.updateOrchestrator(currentScene.getDialogue());


        BaseController.showScene();
    }

    public static boolean isChoicesAvailable() {
        return (currentScene.getChoices()[0] != null);
    }

    public static String getNextSceneID() {
        return currentScene.getGoTo();
    }

    public static void processChoices() {
        new ChoiceManager(currentScene);
    }


}
