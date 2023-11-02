package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class ExportWindowTest {
    @Test
    public void checkValidFilePath() {
        assertTrue(ExportWindow.verifyFilePath("data/deck.json"));
    }
    @Test
    public void checkInvalidFilePath() {
        assertFalse(ExportWindow.verifyFilePath("data/.json"));
    }
}
