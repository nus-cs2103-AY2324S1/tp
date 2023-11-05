package seedu.address.model.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class PracticeDateTest {
    @Test
    public void getDisplayName() {
        PracticeDate practiceDate1 = new PracticeDate(LocalDateTime.of(2023, 12, 8, 7, 4, 0));
        PracticeDate practiceDate2 = new PracticeDate(LocalDateTime.of(2024, 6, 30, 12, 1, 0));
        PracticeDate practiceDate3 = new PracticeDate(LocalDateTime.of(2022, 3, 17, 0, 0, 23));

        assertEquals(practiceDate1.getDisplayName(), "8 December 2023");
        assertEquals(practiceDate2.getDisplayName(), "30 June 2024");
        assertEquals(practiceDate3.getDisplayName(), "17 March 2022");
    }
}
