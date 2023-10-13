package networkbook.ui;

/**
 * Prepares test environment to be headless, when the test is run on Command Interface
 * which does not support UI pop-up.
 */
public class HeadlessHelper {
    //@@author Singa-Pirate reused
    //Reused from https://stackoverflow.com/a/35309920/19206704

    public static void setupForHeadlessTesting() {
        if (Boolean.getBoolean("headless")) {
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        }
    }
}
