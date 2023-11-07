package seedu.lovebook.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.testutil.TypicalDates.BENSON;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.Scene;
import javafx.stage.Stage;
import seedu.lovebook.model.date.Date;

public class DateCardTest extends ApplicationTest {

    private final Date testDate = BENSON;
    private DateCard dateCard;

    @Override
    public void start(Stage stage) throws Exception {
        dateCard = new DateCard(testDate, 1);
        stage.setScene(new Scene(dateCard.getRoot()));
        stage.show();
    }

    @Test
    public void viewDateCard_hasName() {
        assertTrue(dateCard.getName().equals(testDate.getName().fullName));
    }

    @Test
    public void viewDateCard_hasAboutInfo() {
        String aboutInfo = BENSON.getAge() + " years old, with a height of " + BENSON.getHeight() + "cm, and "
                + "an income of $" + BENSON.getIncome() + " per month.";
        assertTrue(aboutInfo.equals(dateCard.getAboutInfo()));
    }

    @Test
    public void viewDateCard_hasID() {
        assertEquals("1. ", dateCard.getID());
    }

    @Test
    public void viewDateCard_hasHoroscopeImage() {
        assertTrue(dateCard.getHoroscopeImage().contains("images/horoscopes/libra.png"));
    }

    @Test
    public void viewDateCard_hasGenderImage() {
        assertTrue(dateCard.getGenderImage().contains("images/genders/male.png"));
    }
}
