package com.visualnovel.novel;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static com.visualnovel.novel.Game.validCharacterTags;

public class SceneRawParser {
    static String[] validTags = {
            "SCENE ID",
            "CHOICE",
            "CHECK",
            "GOTO",
            "SUBSCENE",
            "ENDSCENE",
            "EMOTION"
    };
    public static ArrayList<String> getRawScene(String sceneID) {
        ArrayList<String> rawSceneData = new ArrayList<>();

        // https://stackoverflow.com/a/21591230
        URL resource = SceneRawParser.class.getResource("scenes/" + sceneID +".txt");
        String storyPath = "";
        try {
            storyPath = String.valueOf(Paths.get(resource.toURI()).toFile());
        }
        catch (URISyntaxException e) {
            System.out.println("MISSING SCENE FILE...");
        }

        Scanner scan = null;
        try {
            scan = new Scanner(new BufferedReader(new FileReader(storyPath)));

            while (scan.hasNext()) {
                String currentLine = scan.nextLine();
                // ignore comments
                if (currentLine.startsWith("//")) {
                    continue;
                }
                if (currentLine.equals("")) {
                    continue;
                }
                // check for valid tags
                for (int x = 0; x < validCharacterTags.size(); x++) {
                    if (currentLine.startsWith(validCharacterTags.get(x))) {
                        // SAVE THIS
                        rawSceneData.add(currentLine);
                    }
                }
                for (int x = 0; x < validTags.length; x++) {
                    if (currentLine.startsWith(validTags[x])) {
                        // SAVE THIS
                        rawSceneData.add(currentLine);
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
        finally {
            if (scan != null) {
                scan.close();
            }
        }
        return rawSceneData;
    }

    // return VisualScene
    public static VisualSceneContainer parseRaw(String sceneID) {

        ArrayList<String> rawInput = getRawScene(sceneID);
        VisualSceneContainer vsContainer = new VisualSceneContainer();

        // harvestScene information
        String currentSceneID = sceneID;
        int choiceCounter = 0;
        ArrayList<String> dialogue = new ArrayList<>();
        ArrayList<String> emotionConsequences = new ArrayList<>();
        String[] choices = new String[3];
        String[] checks = new String[3];
        String goTo = "";

        for (int x = 0; x < rawInput.size(); x++) {
            String currentLine = rawInput.get(x);
            String tag = currentLine.substring(0, currentLine.indexOf(":"));
            String data = currentLine.substring(currentLine.indexOf(": ") + 2);
            if (validCharacterTags.contains(tag)) {
                // must be dialogue
                dialogue.add(currentLine);
            }
            else if (tag.equals("EMOTION")) {
                emotionConsequences.add(data.substring(data.indexOf("<>") + 2));
            }
            else if (tag.equals("CHOICE")) {
                choices[choiceCounter] = data.substring(data.indexOf("<>") + 2);
                choiceCounter++;
            }
            else if (tag.equals("CHECK")) {
                checks[choiceCounter - 1] = data;
            }
            else if (tag.equals("GOTO")) {
                goTo = data;
            }
            else if (tag.equals("SCENE") || tag.equals("SUBSCENE") || tag.equals("ENDSCENE")) {
                vsContainer.addScene(currentSceneID, new VisualScene(choices, checks, dialogue, emotionConsequences, goTo));
                // reset for next scene

                currentSceneID = data.substring(data.indexOf("<>") + 2);
                choiceCounter = 0;
                dialogue = new ArrayList<>();
                emotionConsequences = new ArrayList<>();
                choices = new String[3];
                checks = new String[3];
                goTo = "";
            }
        }
        vsContainer.addScene(currentSceneID, new VisualScene(choices, checks, dialogue, emotionConsequences, goTo));
        return vsContainer;
    }





}
