package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;


public class ExportWindowTest {
    @Test
    public void checkInvalidFilePath() {
        assertFalse(ExportWindow.verifyFilePath("data/.json"));
    }
}
