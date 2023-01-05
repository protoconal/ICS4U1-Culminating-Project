package com.visualnovel.novel;

import java.util.Arrays;

import static com.visualnovel.novel.Game.validCharacterTags;
import static com.visualnovel.novel.VisualNovelLoader.viewManagement;
import static com.visualnovel.novel.Game.characters;
public class ChoiceManager {

    String[] choiceDialogs = new String[3];
    String[] choiceScenes = new String[3];
    private final VisualScene currentScene;

    public ChoiceManager(VisualScene currentScene) {
        this.currentScene = currentScene;
        processChoices();
    }

    public void processChoices() {
        // CHOICE: <>JSH<>Talk with Daniel.
        String[] rawChoices = this.currentScene.getChoices();

        // [DAN, happy, more than, 3, a, b][][]
        for (int x = 0; x < rawChoices.length; x++) {
            String currentChoice = rawChoices[x];
            // CHOICE: <>JSH<>Talk with Daniel.
            this.choiceDialogs[x] = currentChoice.substring(currentChoice.indexOf("<>") + 2);

            String[] choiceCheck = this.currentScene.checkChoiceRequirements(x);
            if (choiceCheck.length == 0) {
                this.choiceScenes[x] = currentChoice.substring(0, currentChoice.indexOf("<>"));
            }
            else {
                if (processCheck(choiceCheck)) {
                    this.choiceScenes[x] = choiceCheck[4];
                }
                else {
                    this.choiceScenes[x] = choiceCheck[5];
                }
            }
        }
        System.out.println(Arrays.toString(this.choiceDialogs));
        System.out.println(Arrays.toString(this.choiceScenes));
        viewManagement.getChoiceSceneController().updateChoices(this.choiceDialogs);
        viewManagement.getChoiceSceneController().updateRouting(this.choiceScenes);
    }

    public boolean processCheck(String[] check) {
        System.out.println(Arrays.toString(check));

        int characterIndex = validCharacterTags.indexOf(check[0]);
        Character character = characters[characterIndex];
        double[] emotiveIndex = character.getEmotionIndex();
        String[] emotiveStrings = character.getEmotionStrings();

        // [DAN, happy, more than, 3, a, b][][]
        int index = Utils.findSequentialIndex(check[1], emotiveStrings);
        double currentEmotionLevel = emotiveIndex[index];

        System.out.println(check[2]);
        System.out.println(Arrays.toString(emotiveIndex) + "  " + check[3]);
        if (check[2].equals("more than")) {
            return currentEmotionLevel > Integer.parseInt(check[3]);
        }
        else if (check[2].equals("less than")) {
            return currentEmotionLevel < Integer.parseInt(check[3]);
        }
        else {
            System.out.println("Invalid Condition: " + check[2]);
            return false;
        }
    }




}
