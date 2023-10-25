package seedu.lovebook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.lovebook.model.person.Date;

/**
 * An UI component that displays information of a {@code Date}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "DateListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/LoveBook-level4/issues/336">The issue on LoveBook level 4</a>
     */

    public final Date date;

//    @FXML
//    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
//    @FXML
//    private Label age;
//    @FXML
//    private Label height;
//    @FXML
//    private Label income;
//    @FXML
//    private Label horoscope;
    @FXML
    private Label aboutInfo;
    @FXML
    private ImageView genderImage;
    @FXML
    private ImageView horoscopeImage;
    @FXML
    private ImageView avatarImage;

    /**
     * Creates a {@code PersonCode} with the given {@code Date} and index to display.
     */
    public PersonCard(Date date, int displayedIndex) {
        super(FXML);
        this.date = date;
        id.setText(displayedIndex + ". ");
        name.setText(date.getName().fullName);
//        age.setText(date.getAge().value);
//        height.setText(date.getHeight().value);
//        income.setText(date.getIncome().value);
//        horoscope.setText(date.getHoroscope().value);
        aboutInfo.setText(date.getAge().value + " years old, with a height of " + date.getHeight().value + "cm, and "
                + "a income of $" + date.getIncome().value + " per month.");
        if (date.getGender().value.equals("M")) {
            genderImage.setImage(new Image("images/genders/male.png"));
        } else {
            genderImage.setImage(new Image("images/genders/female.png"));
        }
        displayHoroscope(date.getHoroscope().value);
        avatarImage.setImage(new Image("images/avatars/female-avatar.png"));
    }

    public void displayHoroscope(String horoscope) {
        try {
            horoscopeImage.setImage(new Image("images/horoscopes/" + horoscope.toLowerCase() + ".png"));
        } catch (IllegalArgumentException e) {
            horoscopeImage.setImage(new Image("images/bot.png"));
        }
    }
}
