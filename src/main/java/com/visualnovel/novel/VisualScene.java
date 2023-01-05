package com.visualnovel.novel;

import java.util.ArrayList;
import java.util.Arrays;

// data structure
public class VisualScene {
    private final String[] choices;
    private final String[][] choiceChecks;
    private final ArrayList<String> dialogue;
    private final ArrayList<String[]> emotionalConsequences;
    private final String goTo;

    public VisualScene(String[] choices, String[] checks, ArrayList<String> dialogue, ArrayList<String> emotionConsequences, String goTo) {
        this.choices = choices;
        this.dialogue = dialogue;
        this.goTo = goTo;

        this.emotionalConsequences = new ArrayList<>();
        for (int x = 0; x < emotionConsequences.size(); x++) {
            String currentConsequence = emotionConsequences.get(x);
            if (currentConsequence == null) {
                continue;
            }
            String[] currentCheck = new String[3];
            for (int y = 0; y < currentCheck.length; y++) {
                currentCheck[y] = currentConsequence.substring(currentConsequence.indexOf("(") + 1, currentConsequence.indexOf(")"));
                currentConsequence = currentConsequence.substring(currentConsequence.indexOf(")") + 1);
            }
            this.emotionalConsequences.add(currentCheck);
        }


        String[][] parsedCheck = new String[][]{{},{},{}};
        for (int x = 0; x < checks.length; x++) {
            String currentCondition = checks[x];
            if (currentCondition == null) {
                continue;
            }
            String[] currentCheck = new String[6];
            for (int y = 0; y < currentCheck.length; y++) {
                currentCheck[y] = currentCondition.substring(currentCondition.indexOf("(") + 1, currentCondition.indexOf(")"));
                currentCondition = currentCondition.substring(currentCondition.indexOf(")") + 1);
            }
            parsedCheck[x] = currentCheck;
        }
        this.choiceChecks = parsedCheck;

        System.out.println();
        System.out.println("CHO" + Arrays.toString(this.choices));
        System.out.println("CHE" + Arrays.toString(this.choiceChecks[0]) + Arrays.toString(this.choiceChecks[1]) + Arrays.toString(this.choiceChecks[2]));
        System.out.println("DIA" + this.dialogue);
        for (String[] emotionalConsequence : this.emotionalConsequences) {
            System.out.println("ECO" + Arrays.toString(emotionalConsequence));
        }
        System.out.println("GTO" + this.goTo);
        System.out.println("____________________________________");
    }

    public VisualScene(ArrayList<String> dialogue, String goTo) {
        this.goTo = goTo;
        this.choices = new String[]{};
        this.choiceChecks = new String[][]{{},{},{}};
        this.emotionalConsequences = new ArrayList<String[]>();
        this.dialogue = dialogue;
    }

    public String[] getChoices() {
        return choices;
    }
    public ArrayList<String> getDialogue() {
        return dialogue;
    }

    public ArrayList<String[]> getEmotionConsequences() {
        return emotionalConsequences;
    }

    public String[] checkChoiceRequirements(int choice) {
        return choiceChecks[choice];
    }

    public String getGoTo() {
        return goTo;
    }
}