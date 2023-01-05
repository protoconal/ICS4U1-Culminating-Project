package com.visualnovel.novel;

import java.util.ArrayList;

public abstract class Character {
    private final String[] emotionStrings = {
            "happy",
            "sad",
            "angry",
    };
    private final double[] emotionIndex = {0, 0, 0};
    private final String name;
    private final String[] spriteLocations;
    private final double decayEmotionFactor;

    private ArrayList<String> dialogueQueue;


    public Character(String name, String[] spriteLocations, double decayEmotionFactor) {
        this.name = name;
        this.spriteLocations = spriteLocations;
        this.decayEmotionFactor = decayEmotionFactor;
    }

    public boolean hasDialogue() {
        return !dialogueQueue.isEmpty();
    }

    public String nextDialogue() {
        return dialogueQueue.remove(0);
    }

    public void setDialogue(ArrayList<String> dialogueQueue) {
        this.dialogueQueue = dialogueQueue;
    }

    public String[] getSpriteLocations() {
        return this.spriteLocations;
    }

    public String getName() {
        return this.name;
    }

    public double[] getEmotionIndex() {
        return this.emotionIndex;
    }
    public String[] getEmotionStrings() {
        return this.emotionStrings;
    }


    public void decayEmotion() {
        for (int x = 0; x <= this.emotionIndex.length; x++) {
            this.emotionIndex[x] *= decayEmotionFactor;
        }
    }

    public boolean updateEmotionIndex(String emotion, double intensity) {
        int emotionIndex = Utils.findSequentialIndex(emotion, this.emotionStrings);
        // check emotion found
        if (emotionIndex == -1) {
            return false;
        }

        double futureEmotiveIndex = this.emotionIndex[emotionIndex] + intensity;
        // maximum allowed values are 0 to 10, check then update
        if (futureEmotiveIndex >= 10) {
            this.emotionIndex[emotionIndex] = 10;
        }
        else if (futureEmotiveIndex <= 0) {
            this.emotionIndex[emotionIndex] = 0;
        }
        else {
            this.emotionIndex[emotionIndex] = futureEmotiveIndex;
        }

        return true;
    }
}
