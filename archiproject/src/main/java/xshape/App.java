package xshape;

import xshape.Xshape;
import xshape.data.test.SceneTest;
import xshape.graphic.AwtApp;

public class App {
    public static void main(String[] args) {
        Xshape appAwt = new AwtApp();
        appAwt.run();
        SceneTest test = new SceneTest();
        test.testRedo();
    }
}

