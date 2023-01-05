package com.visualnovel.novel;

import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        SceneRawParser.getRawScene("001");
        SceneRawParser.parseRaw("001");
    }
}