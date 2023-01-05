package com.visualnovel.novel;

public class Novel {
    Character[] characters = {
            new Phoebe(new String[]{""}),
            new Josh(new String[]{""}),
            new Daniel(new String[]{""}),
    };
    DialogueOrchestrator dialogueOrchestrator = new DialogueOrchestrator(characters);

    public void processScene(String sceneID) {
        //
    }

}
