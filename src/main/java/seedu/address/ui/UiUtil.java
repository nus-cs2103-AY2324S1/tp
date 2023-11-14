package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

/**
 * Utility class for UI components.
 */
public class UiUtil {
    private static final Logger logger = Logger.getLogger(UiUtil.class.getName());
    /**
     * Opens a link using the default web browser on the user's system.
     * @param url The URL to be opened in the web browser.
     */
    public static void open(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        assert !os.equals("");
        logger.info("Opening link: " + url);

        try {
            if (os.contains("win") || os.contains("mac")) {
                Desktop.getDesktop().browse(new URI(url));
            } else if (os.contains("nix") || os.contains("nux") || os.contains("linux")) {
                Runtime.getRuntime().exec("xdg-open " + url);
            } else {
                // We do not support other operating systems.
                logger.severe("Unsupported operating system: " + os);
                throw new UnsupportedOperationException("Unsupported operating system: " + os);
            }
        } catch (IOException | URISyntaxException | UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a link to the telegram profile using the default web browser
     * @param telegramHandle The telegram handle
     */
    public static void openTelegram(String telegramHandle) {
        if (telegramHandle != null && !telegramHandle.isEmpty()) {
            UiUtil.open("https://t.me/" + telegramHandle);
        }
    }

    /**
     * Opens a link to the user guide using the default web browser
     */
    public static void openUserGuide() {
        final String userGuideUrl = "https://ay2324s1-cs2103t-t12-1.github.io/tp/UserGuide.html";
        UiUtil.open(userGuideUrl);
    }

}
