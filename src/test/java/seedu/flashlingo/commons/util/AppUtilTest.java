package seedu.flashlingo.commons.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.flashlingo.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppUtilTest {

    @Test
    public void getImage_existingImage() {
        assertNotNull(AppUtil.getImage("/images/flash_lingo.png"));
    }

    @Test
    public void getImage_nullGiven_throwsNullPointerException() {
        //Tests for NullPointerException being thrown if launching a null image
        assertThrows(NullPointerException.class, () -> AppUtil.getImage(null));
    }

    @Test
    public void checkArgument_true_nothingHappens() {
        AppUtil.checkArgument(true);
        AppUtil.checkArgument(true, "");
    }

    @Test
    public void checkArgument_falseWithoutErrorMessage_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> AppUtil.checkArgument(false));
    }

    @Test
    public void checkArgument_falseWithErrorMessage_throwsIllegalArgumentException() {
        //Tests of error message is correctly thrown
        String errorMessage = "error message";
        assertThrows(
                IllegalArgumentException.class, errorMessage, () -> AppUtil.checkArgument(false, errorMessage)
        );
    }
}
