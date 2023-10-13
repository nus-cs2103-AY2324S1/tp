package networkbook.ui;

/**
 * Prepares test environment to be headless, when the test is run on Command Interface
 * which does not support UI pop-up.
 */
public class HeadlessHelper {
    //@@author Singa-Pirate reused
    //Reused from https://stackoverflow.com/a/70440691/19206704

    public static void setupForHeadlessTesting() {
        String isRunningOnCi = System.getProperty("CI");
        if ("true".equalsIgnoreCase(isRunningOnCi)) {
            System.setProperty("monocle.platform", "Headless");
            System.setProperty("testfx.robot", "glass");
            System.setProperty("glass.platform", "Monocle");
            System.setProperty("embedded", "monocle");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");

            // System.setProperty("prism.text", "t2k");
            System.setProperty("prism.text", "native");

            System.setProperty("java.awt.headless", "true");
        }
    }
}
