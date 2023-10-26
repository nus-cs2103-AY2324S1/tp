package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class CopyCommandTestUtil {

    public static Transferable saveClipboardContents() {
        return Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
    }

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

    public static void replaceClipboardContents(Transferable clipboardContent) {
        if (clipboardContent != null) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(clipboardContent, null);
        }
    }
}
