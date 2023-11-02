package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESTAMP;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertTransactionCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_ELEMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ELEMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ELEMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_ELEMENT;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.FIONA;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Timestamp;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PortionBuilder;
import seedu.address.testutil.TransactionBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code SettlePersonCommand}.
 */
public class SettlePersonCommandIntegrationTest {

    private static final Timestamp TIME = new Timestamp(new Timestamp(VALID_TIMESTAMP).value.plusDays(1));

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }
    @Test
    public void execute_settlePositiveBalance_success() {
        Person personToSettle = new PersonBuilder(ALICE).build();
        Name personToSettleName = personToSettle.getName();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Transaction transaction = new TransactionBuilder().withPayeeName(personToSettleName.fullName).withDescription(
                String.format(SettlePersonCommand.SETTLE_TRANSACTION_DESCRIPTION, personToSettleName.fullName))
                .withAmount(expectedModel.getBalance(personToSettleName).toString())
                .withPortions(Set.of(new PortionBuilder().withName(Name.SELF.fullName)
                .withWeight("1").build())).withTimestamp(TIME.toString()).build();
        expectedModel.addTransaction(transaction);

        assertTransactionCommandSuccess(new SettlePersonCommand(INDEX_FIRST_ELEMENT, TIME), model,
                String.format(SettlePersonCommand.MESSAGE_SETTLE_PERSON_SUCCESS, personToSettle.getName()),
                expectedModel);
    }

    @Test
    public void execute_settleNegativeBalance_success() {
        Person personToSettle = new PersonBuilder(BENSON).build();
        Name personToSettleName = personToSettle.getName();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Transaction transaction = new TransactionBuilder().withPayeeName(Name.SELF.fullName).withDescription(
                String.format(SettlePersonCommand.SETTLE_TRANSACTION_DESCRIPTION, personToSettleName.fullName))
                .withAmount(expectedModel.getBalance(personToSettleName).abs().toString())
                .withPortions(Set.of(new PortionBuilder().withName(personToSettleName.fullName)
                .withWeight("1").build())).withTimestamp(TIME.toString()).build();
        expectedModel.addTransaction(transaction);

        assertTransactionCommandSuccess(new SettlePersonCommand(INDEX_SEVENTH_ELEMENT, TIME), model,
                String.format(SettlePersonCommand.MESSAGE_SETTLE_PERSON_SUCCESS, personToSettle.getName()),
                expectedModel);
    }
    @Test
    public void execute_duplicate_failure() {
        Person personToSettle = new PersonBuilder(BENSON).build();
        Name personToSettleName = personToSettle.getName();

        Transaction transaction = new TransactionBuilder().withPayeeName(Name.SELF.fullName).withDescription(
                String.format(SettlePersonCommand.SETTLE_TRANSACTION_DESCRIPTION, personToSettleName.fullName))
                .withAmount(model.getBalance(personToSettleName).abs().toString())
                .withPortions(Set.of(new PortionBuilder().withName(personToSettleName.fullName)
                .withWeight("1").build())).withTimestamp(TIME.toString()).build();
        model.addTransaction(transaction);

        assertCommandFailure(new SettlePersonCommand(INDEX_SECOND_ELEMENT, TIME), model,
                SettlePersonCommand.MESSAGE_DUPLICATE_TRANSACTION);
    }

    @Test
    public void execute_noOutstandingBalance_throwsCommandException() {
        Person personToSettle = new PersonBuilder(FIONA).build();
        Name personToSettleName = personToSettle.getName();

        assertCommandFailure(new SettlePersonCommand(INDEX_FIFTH_ELEMENT, TIME),
                model, String.format(SettlePersonCommand.MESSAGE_NO_OUTSTANDING_BALANCE, personToSettleName, TIME));
    }
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        SettlePersonCommand settlePersonCommand = new SettlePersonCommand(outOfBoundIndex, TIME);

        assertCommandFailure(settlePersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
