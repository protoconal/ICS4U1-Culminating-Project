package com.visualnovel.novel;

import com.visualnovel.controllers.ViewSceneController;

import static com.visualnovel.novel.VisualNovelLoader.viewManagement;

public class Novel {
    Character[] characters = {
            new Phoebe(new String[]{""}),
            new Josh(new String[]{""}),
            new Daniel(new String[]{""}),
    };
    DialogueOrchestrator dialogueOrchestrator = new DialogueOrchestrator(characters);

    public static void run() {
        ViewSceneController sceneController = viewManagement.getViewSceneController();
        sceneController.updateNameBox("Jared");
        sceneController.updateDialogueBox("Jared");
    }

    public void processScene(String sceneID) {
        //
    }

}
