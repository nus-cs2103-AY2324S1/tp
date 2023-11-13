package seedu.lovebook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.lovebook.model.date.Date;

/**
 * An UI component that displays information of a {@code Date}.
 */
public class DateCard extends UiPart<Region> {

    private static final String FXML = "DateListCard.fxml";

    private static final String MALE_AVATAR_FILE_PATH = "images/avatars/male/";
    private static final String FEMALE_AVATAR_FILE_PATH = "images/avatars/female/";
    private static final String INVALID_IMAGE_FILE_PATH = "images/bot.png";
    private static final String STAR_IMAGE_FILE_PATH = "images/star.png";
    private static final String HOROSCOPE_FILE_PATH = "images/horoscopes/";
    private static final String MALE_GENDER_FILE_PATH = "images/genders/male.png";
    private static final String FEMALE_GENDER_FILE_PATH = "images/genders/female.png";
    private static final String AGE_HEIGHT = " years old, with a height of ";
    private static final String HEIGHT_INCOME = "cm, and an income of $";
    private static final String INCOME = " per month.";
    private static final String PNG = ".png";


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/LoveBook-level4/issues/336">The issue on LoveBook level 4</a>
     */

    public final Date date;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label aboutInfo;
    @FXML
    private ImageView starImage;
    @FXML
    private ImageView genderImage;
    @FXML
    private ImageView horoscopeImage;
    @FXML
    private ImageView avatarImage;


    /**
     * Creates a {@code PersonCode} with the given {@code Date} and index to display.
     */
    public DateCard(Date date, int displayedIndex) {
        super(FXML);
        this.date = date;
        id.setText(displayedIndex + ". ");
        name.setText(date.getName().fullName);
        aboutInfo.setText(date.getAge().value + AGE_HEIGHT + date.getHeight().value
                + HEIGHT_INCOME + date.getIncome().value + INCOME);
        displayIcons();
    }
    private void displayIcons() {
        try {
            horoscopeImage.setImage(new Image(HOROSCOPE_FILE_PATH + date.getHoroscope().value.toLowerCase()
                    + PNG));
            if (date.getGender().value.equals("M")) {
                genderImage.setImage(new Image(MALE_GENDER_FILE_PATH));
                avatarImage.setImage(new Image(MALE_AVATAR_FILE_PATH + date.getAvatar().value + PNG));
            } else {
                genderImage.setImage(new Image(FEMALE_GENDER_FILE_PATH));
                avatarImage.setImage(new Image(FEMALE_AVATAR_FILE_PATH + date.getAvatar().value + PNG));
            }
            if (date.getStar().isStarred.equals("true")) {
                starImage.setImage(new Image(STAR_IMAGE_FILE_PATH));
            }
        } catch (IllegalArgumentException e) {
            horoscopeImage.setImage(new Image(INVALID_IMAGE_FILE_PATH));
        }
    }

    /**
     * Returns the name of the date.
     * @return name of the date
     */
    public String getName() {
        return name.getText();
    }

    /**
     * Returns information about the date.
     * @return information about the date
     */
    public String getAboutInfo() {
        return aboutInfo.getText();
    }

    /**
     * Returns the ID of the date.
     * @return ID of the date
     */
    public String getID() {
        return id.getText();
    }

    /**
     * Returns the image url of the gender of the date.
     * @return url of the gender image
     */
    public String getGenderImage() {
        return genderImage.getImage().getUrl();
    }

    /**
     * Returns the image url of the horoscope of the date.
     * @return url of the horoscope image
     */
    public String getHoroscopeImage() {
        return horoscopeImage.getImage().getUrl();
    }

}
