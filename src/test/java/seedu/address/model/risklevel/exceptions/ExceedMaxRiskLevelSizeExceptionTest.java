package seedu.address.model.risklevel.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExceedMaxRiskLevelSizeExceptionTest {

    @Test
    public void constructor_validInput_returnsCorrectMessage() {
        int maxSize = 10;
        ExceedMaxRiskLevelSizeException exception = new ExceedMaxRiskLevelSizeException(maxSize);

        assertEquals("number of tags cannot exceed " + maxSize + "!", exception.getMessage());
    }
}
