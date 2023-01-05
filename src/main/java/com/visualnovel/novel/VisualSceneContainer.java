package com.visualnovel.novel;

import java.util.HashMap;

public class VisualSceneContainer {
    public HashMap<String, VisualScene> scenes;
    public VisualSceneContainer() {
        scenes = new HashMap<>();
    }
    public VisualScene getScene(String scene) {
        return scenes.getOrDefault(scene, null);
    }

    public void addScene(String sceneID, VisualScene scene) {
        scenes.put(sceneID, scene);
    }

}
