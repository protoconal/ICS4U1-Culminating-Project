package com.visualnovel.novel;

import java.util.ArrayList;
import java.util.Arrays;

// data structure
public class VisualScene {
    private final String[] choices;
    private final String[][] choiceChecks;
    private final ArrayList<String> dialogue;
    private final ArrayList<String> emotionConsequences;
    private final String goTo;

    public VisualScene(String[] choices, String[] checks, ArrayList<String> dialogue, ArrayList<String> emotionConsequences, String goTo) {
        this.choices = choices;
        this.dialogue = dialogue;
        this.emotionConsequences = emotionConsequences;
        this.goTo = goTo;

        String[][] parsedCheck = new String[][]{{},{},{}};
        for (int x = 0; x < checks.length; x++) {
            String currentCondition = checks[x];
            if (currentCondition == null) {
                continue;
            }
            String[] currentCheck = new String[5];
            for (int y = 0; y < 5; y++) {
                currentCheck[y] = currentCondition.substring(currentCondition.indexOf("(") + 1, currentCondition.indexOf(")"));
                currentCondition = currentCondition.substring(currentCondition.indexOf(")") + 1);
            }
            parsedCheck[x] = currentCheck;
        }
        this.choiceChecks = parsedCheck;

        System.out.println(Arrays.toString(this.choices));
        System.out.println(Arrays.toString(this.choiceChecks[0]) + Arrays.toString(this.choiceChecks[1]) + Arrays.toString(this.choiceChecks[2]));
        System.out.println(this.dialogue);
        System.out.println(this.emotionConsequences);
        System.out.println(this.goTo);
        System.out.println("____________________________________");
    }

    public VisualScene(ArrayList<String> dialogue, String goTo) {
        this.goTo = goTo;
        this.choices = new String[]{};
        this.choiceChecks = new String[][]{{},{},{}};
        this.emotionConsequences = new ArrayList<String>();
        this.dialogue = dialogue;
    }

    public String[] getChoices() {
        return choices;
    }
    public ArrayList<String> getDialogue() {
        return dialogue;
    }

    public ArrayList<String> getEmotionConsequences() {
        return emotionConsequences;
    }

    public String[] checkChoiceRequirements(int choice) {
        return choiceChecks[choice];
    }

    public String getGoTo() {
        return goTo;
    }
}