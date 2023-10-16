package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages2;
import seedu.address.model.Model2;
import seedu.address.model.ModelManager2;
import seedu.address.model.UserPrefs2;
import seedu.address.model.person.Card;
import seedu.address.testutil.CardBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model2 model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager2(getTypicalDeck(), new UserPrefs2());
    }

    @Test
    public void execute_newCard_success() {
        Card validCard = new CardBuilder().build();

        Model2 expectedModel = new ModelManager2(model.getDeck(), new UserPrefs2());
        expectedModel.addCard(validCard);

        assertCommandSuccess(new AddCommand2(validCard), model,
                String.format(AddCommand2.MESSAGE_SUCCESS, Messages2.format(validCard)),
                expectedModel);
    }

    @Test
    public void execute_duplicateCard_throwsCommandException() {
        Card cardInList = model.getDeck().getCardList().get(0);
        assertCommandFailure(new AddCommand2(cardInList), model,
                AddCommand2.MESSAGE_DUPLICATE_CARD);
    }

}
