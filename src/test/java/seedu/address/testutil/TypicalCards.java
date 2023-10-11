package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Card;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Card} objects to be used in tests.
 */
public class TypicalCards {

    public static final Card CS2100 = new CardBuilder().withQuestion("R-Format instruction opcode")
            .withAnswer("0").build();
    public static final Card CS1231S = new CardBuilder().withQuestion("Name the 3 relations")
            .withAnswer("Reflexive, Symmetric, Transitive").build();
    public static final Card CS1101S = new CardBuilder().withQuestion("What is the language used for this mod?")
            .withAnswer("Source").build();
    private TypicalCards() {} // prevents instantiation

    public static List<Card> getTypicalCard() {
        return new ArrayList<>(Arrays.asList(CS2100, CS1101S, CS1231S));
    }
}
