package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.CS2100;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages2;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Deck;
import seedu.address.model.Model2;
import seedu.address.model.ReadOnlyDeck;
import seedu.address.model.ReadOnlyUserPrefs2;
import seedu.address.model.person.Card;
import seedu.address.testutil.CardBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand2(null));
    }

    @Test
    public void execute_cardAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCardAdded modelStub = new ModelStubAcceptingCardAdded();
        Card validCard = new CardBuilder().build();

        CommandResult commandResult = new AddCommand2(validCard).execute(modelStub);

        assertEquals(String.format(AddCommand2.MESSAGE_SUCCESS, Messages2.format(validCard)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCard), modelStub.cardsAdded);
    }

    @Test
    public void execute_duplicateCard_throwsCommandException() {
        Card validCard = new CardBuilder().build();
        AddCommand2 addCommand = new AddCommand2(validCard);
        ModelStub modelStub = new ModelStubWithCard(validCard);
        assertThrows(CommandException.class, AddCommand2.MESSAGE_DUPLICATE_CARD, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Card cs2100 = new CardBuilder().withQuestion("What colour is the sky").build();
        Card cs1101s = new CardBuilder().withQuestion("what colour is the grass").build();
        AddCommand2 addcs2100Command = new AddCommand2(cs2100);
        AddCommand2 addcs1101sCommand = new AddCommand2(cs1101s);

        // same object -> returns true
        assertTrue(addcs2100Command.equals(addcs2100Command));

        // same values -> returns true
        AddCommand2 addcs2100CommandCopy = new AddCommand2(cs2100);
        assertTrue(addcs2100Command.equals(addcs2100CommandCopy));

        // different types -> returns false
        assertFalse(addcs2100Command.equals(1));

        // null -> returns false
        assertFalse(addcs2100Command.equals(null));

        // different Card -> returns false
        assertFalse(addcs2100Command.equals(addcs1101sCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand2 addCommand = new AddCommand2(CS2100);
        String expected = AddCommand2.class.getCanonicalName() + "{toAdd=" + CS2100 + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model2 {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs2 userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs2 getUserPrefs() {
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
