package flashlingo.commons.util;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.commons.util.AppUtil;
import seedu.flashlingo.commons.util.StringUtil;

import static org.junit.jupiter.api.Assertions.*;


public class AppUtilTest {

    @Test
    public void getImage_exitingImage() {
        assertNotNull(AppUtil.getImage("/images/address_book_32.png"));
    }

    @Test
    public void getImage_nullGiven_throwsNullPointerException() {
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
        String errorMessage = "error message";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> AppUtil.checkArgument(false, errorMessage)
        );
        assertEquals(errorMessage, exception.getMessage());
    }
}
