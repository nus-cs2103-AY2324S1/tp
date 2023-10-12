package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.flashcard.FlashCard;
import seedu.address.model.flashcard.UniqueFlashCardList;

/**
 * Wraps all data at the flashlingo level
 * Duplicates are not allowed (by .isSameFlash comparison)
 */
public class Flashlingo implements ReadOnlyFlashlingo {

    private final UniqueFlashCardList flashCards;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        flashCards = new UniqueFlashCardList();
    }

    public Flashlingo() {}

    /**
     * Creates an Flashlingo using the FlashCards in the {@code toBeCopied}
     */
    public Flashlingo(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<FlashCard> flashCards) {
        this.flashCards.setFlashCards(flashCards);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasFlashCard(FlashCard flashCard) {
        requireNonNull(flashCard);
        return flashCards.contains(flashCard);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addFlashCard(FlashCard flashCard) {
        flashCards.add(flashCard);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setFlashCard(FlashCard target, FlashCard editedFlashCard) {
        requireNonNull(editedFlashCard);

        flashCards.setFlashCard(target, editedFlashCard);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeFlashCard(FlashCard key) {
        flashCards.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
          .add("FlashCards", flashCards)
          .toString();
    }

    @Override
    public ObservableList<FlashCard> getFlashCardList() {
        return flashCards.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        Flashlingo otherFlashlingo = (Flashlingo) other;
        return flashCards.equals(otherFlashlingo.flashCards);
    }

    @Override
    public int hashCode() {
        return flashCards.hashCode();
    }
}
