package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.CS2100;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Deck;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyDeck;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.card.Card;
import seedu.address.model.goal.Goal;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.CardBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_cardAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCardAdded modelStub = new ModelStubAcceptingCardAdded();
        Card validCard = new CardBuilder().build();

        CommandResult commandResult = new AddCommand(validCard).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validCard)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCard), modelStub.cardsAdded);
    }

    @Test
    public void execute_invalidModel_throwsNullPointerException() {
        Card validCard = new CardBuilder().build();

        assertThrows(NullPointerException.class, () -> new AddCommand(validCard).execute(null));
    }

    @Test
    public void execute_duplicateCard_throwsCommandException() {
        Card validCard = new CardBuilder().build();
        AddCommand addCommand = new AddCommand(validCard);
        ModelStub modelStub = new ModelStubWithCard(validCard);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_CARD, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Card cs2100 = new CardBuilder().withQuestion("What colour is the sky").build();
        Card cs1101s = new CardBuilder().withQuestion("what colour is the grass").build();
        AddCommand addcs2100Command = new AddCommand(cs2100);
        AddCommand addcs1101sCommand = new AddCommand(cs1101s);
        assertNotEquals(addcs2100Command, addcs1101sCommand);

        // same object -> returns true
        assertEquals(addcs2100Command, addcs2100Command);

        // different types -> returns false
        assertNotEquals(1, addcs2100Command);

        // null -> returns false
        assertNotEquals(null, addcs2100Command);

        // same values -> returns true
        AddCommand addcs2100CommandCopy = new AddCommand(cs2100);
        assertEquals(addcs2100Command, addcs2100CommandCopy);

        // different Card -> returns false
        assertNotEquals(addcs2100Command, addcs1101sCommand);

        // different Tags -> return false
        Card cs2100WithTag = new CardBuilder().withQuestion("What colour is the sky")
                .withTags(new ArrayList<>(Collections.singleton(new Tag("Tag1"))))
                .build();
        AddCommand addcs2100CommandWithTag = new AddCommand(cs2100WithTag);
        assertNotEquals(addcs2100Command, addcs2100CommandWithTag);
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(CS2100);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + CS2100 + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getDeckFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeckFilePath(Path deckFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCard(Card card) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeck(ReadOnlyDeck newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGoal(int target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Goal getGoal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyDeck getDeck() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCard(Card card) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCard(Card target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCard(Card target, Card editedCard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Card> getFilteredCardList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCardList(Predicate<Card> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getDeckSize() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRandomIndex(Index index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Index getRandomIndex() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetRandomIndex() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single Card.
     */
    private class ModelStubWithCard extends ModelStub {
        private final Card card;

        ModelStubWithCard(Card card) {
            requireNonNull(card);
            this.card = card;
        }

        @Override
        public boolean hasCard(Card card) {
            requireNonNull(card);
            return this.card.isSameCard(card);
        }
    }

    /**
     * A Model stub that always accept the Card being added.
     */
    private class ModelStubAcceptingCardAdded extends ModelStub {
        final ArrayList<Card> cardsAdded = new ArrayList<>();

        @Override
        public boolean hasCard(Card card) {
            requireNonNull(card);
            return cardsAdded.stream().anyMatch(card::isSameCard);
        }

        @Override
        public void addCard(Card card) {
            requireNonNull(card);
            cardsAdded.add(card);
        }

        @Override
        public ReadOnlyDeck getDeck() {
            return new Deck();
        }
    }
}
