package seedu.address.testutil;

import seedu.address.model.user.Password;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;

/**
 * A utility class to help with building User objects.
 */
public class UserBuilder {
    public static final String DEFAULT_USERNAME = "validUsername";
    public static final String DEFAULT_PASSWORD = "Pa55word";
    public static final String SECRET_QUESTION = "What is your favourite food?";
    public static final String ANSWER = "Pizza";
    private Username username;
    private Password password;
    private String secretQuestion;
    private String answer;

    /**
     * Creates a {@code UserBuilder} with the default details.
     */
    public UserBuilder() {
        username = new Username(DEFAULT_USERNAME);
        password = new Password(DEFAULT_PASSWORD);
        secretQuestion = SECRET_QUESTION;
        answer = ANSWER;
    }

    /**
     * Initializes the UserBuilder with the data of {@code User}.
     */
    public UserBuilder(User userToCopy) {
        username = userToCopy.getUsername();
        password = userToCopy.getPassword();
        secretQuestion = userToCopy.getSecretQuestion();
        answer = userToCopy.getAnswer();
    }

    /**
     * Sets the {@code Username} of the {@code User} that we are building.
     */
    public UserBuilder withUsername(String username) {
        this.username = new Username(username);
        return this;
    }

    /**
     * Sets the {@code Password} of the {@code User} that we are building.
     */
    public UserBuilder withPassword(String password) {
        this.password = new Password(password, true);
        return this;
    }

    /**
     * Sets the {@code secretQuestion} of the {@code User} that we are building.
     */
    public UserBuilder withSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
        return this;
    }

    /**
     * Sets the {@code answer} of the {@code User} that we are building.
     */
    public UserBuilder withAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    /**
     * Builds the {@code User} with the given parameters.
     *
     * @return The User with the given parameters.
     */
    public User build() {
        return new User(username, password, true, secretQuestion, answer);
    }
}
