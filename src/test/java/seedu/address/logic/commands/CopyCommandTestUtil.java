package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Contains helper methods for testing CopyCommands that copy text to the clipboard.
 */
public class CopyCommandTestUtil {

    /**
     * Gets the contents of the clipboard.
     *
     * @return the contents of the clipboard as a {@code Transferable} object.
     */
    public static Transferable getClipboardContents() {
        return Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
    }

    /**
     * Checks that the contents of the clipboard is equal to the correct text.
     *
     * @param correctText the correct text to check against.
     */
    public static void checkClipboardContents(String correctText) {
        try {
            assertEquals(correctText,
                    Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
        } catch (UnsupportedFlavorException e) {
            throw new AssertionError("DataFlavor.stringFlavor is not supported", e);
        } catch (IOException e) {
            throw new AssertionError("IOException when getting clipboard contents", e);
        }
    }

    /**
     * Replaces the contents of the clipboard with the specified {@code Transferable} object.
     *
     * @param clipboardContent the {@code Transferable} object to replace the clipboard contents with.
     */
    public static void replaceClipboardContents(Transferable clipboardContent) {
        if (clipboardContent != null) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(clipboardContent, null);
        }
    }
}
