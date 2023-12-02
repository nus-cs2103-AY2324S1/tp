package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_AARON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_FOODBEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HASHED_PASSWORD_AARON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HASHED_PASSWORD_FOODBEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SECRET_QUESTION_AARON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SECRET_QUESTION_FOODBEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_AARON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_FOODBEAR;

import seedu.address.model.user.User;

/**
 * A utility class containing a list of {@code User} objects to be used in tests.
 */
public class TypicalUsers {

    public static final User ANDY = new UserBuilder().withUsername("andy").withPassword("abcd1234")
            .withSecretQuestion("What is your favourite food?").withAnswer("Pizza").build();
    public static final User CARL = new UserBuilder().withUsername("car1").withPassword("1234ABcd")
            .withSecretQuestion("What is your favourite module?").withAnswer("CS2103T").build();

    // Manually added
    public static final User AARON = new UserBuilder().withUsername(VALID_USERNAME_AARON)
            .withPassword(VALID_HASHED_PASSWORD_AARON)
            .withSecretQuestion(VALID_SECRET_QUESTION_AARON)
            .withAnswer(VALID_ANSWER_AARON).build();

    public static final User FOODBEAR = new UserBuilder().withUsername(VALID_USERNAME_FOODBEAR)
            .withPassword(VALID_HASHED_PASSWORD_FOODBEAR)
            .withSecretQuestion(VALID_SECRET_QUESTION_FOODBEAR)
            .withAnswer(VALID_ANSWER_FOODBEAR).build();

    private TypicalUsers() {
    } // prevents instantiation
}
