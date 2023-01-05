package com.visualnovel.novel;

import com.visualnovel.controllers.ViewSceneController;

import java.util.ArrayList;

import static com.visualnovel.novel.Game.validCharacterTags;
import static com.visualnovel.novel.Game.characters;
import static com.visualnovel.novel.VisualNovelLoader.viewManagement;

public class DialogueOrchestrator {
    private final ViewSceneController sceneController = viewManagement.getViewSceneController();
    private ArrayList<ArrayList<String>> sceneDialogue;
    // beginning of each arraylist will hold the character index

    private Character currentCharacter;
    public DialogueOrchestrator() {}

    public void updateOrchestrator(ArrayList<String> sceneDialogue) {
        this.sceneDialogue = processRawDialogue(sceneDialogue);
        nextCharacter();
    }

    public ArrayList<ArrayList<String>> processRawDialogue(ArrayList<String> rawDialogue) {
        ArrayList<ArrayList<String>> dialogueQueue = new ArrayList<>();
        ArrayList<String> characterDialogue = null;
        String characterTag = "";

        // seperate by character
        while (!rawDialogue.isEmpty()) {
            // example line : DAN: <>This is dialogue 3
            String currentLine = rawDialogue.remove(0);
            String currentCharacterTag = currentLine.substring(0,3);
            String dialogue = currentLine.substring(currentLine.indexOf("<>") + 2);

            if (!currentCharacterTag.equals(characterTag)) {
                // must have ended that character's dialogue
                if (characterDialogue != null) {
                    dialogueQueue.add(characterDialogue);
                }
                // (CHARID, DIALOGUE, DIALOGUE, DIALOGUE)
                characterDialogue = new ArrayList<>();
                characterDialogue.add(
                        Integer.toString(validCharacterTags.indexOf(currentCharacterTag))
                );
                characterTag = currentCharacterTag;
            }
            characterDialogue.add(dialogue);
        }
        dialogueQueue.add(characterDialogue);
        return dialogueQueue;
    }

    public void nextCharacter() {
        if (!sceneDialogue.isEmpty()) {
            System.out.println(sceneDialogue);
            ArrayList<String> currentQueue = sceneDialogue.remove(0);
            currentCharacter = characters[Integer.parseInt(currentQueue.remove(0))];
            currentCharacter.dialogueQueue = currentQueue;
            this.sceneController.updateNameBox(currentCharacter.getName());
            this.nextLine();
        }
        else {
            Game.gameState = "CHOICE";
            // empty sceneQueue, somehow signal home
            // change to choice scene
            sceneController.showScene();
        }
    }
    public void nextLine() {
        if (!currentCharacter.dialogueQueue.isEmpty()) {
            Game.gameState = "DIALOGUE";
            this.sceneController.updateDialogueBox(
                    currentCharacter.dialogueQueue.remove(0)
            );
        }
        else {
            // empty dialogueQueue, jump to next character
            nextCharacter();
        }
    }



}
