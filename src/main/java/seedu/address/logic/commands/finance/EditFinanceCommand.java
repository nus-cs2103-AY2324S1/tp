package seedu.address.logic.commands.finance;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_DUE;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.finance.Amount;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Description;
import seedu.address.model.finance.Expense;
import seedu.address.model.finance.Finance;
import seedu.address.model.finance.TimeDue;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing finance in the finance book.
 */
public class EditFinanceCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the finance entry identified "
            + "by the index number used in the displayed finance list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_AMOUNT + "AMOUNT] "
            + "[" + PREFIX_CLIENT + "CLIENT] "
            + "[" + PREFIX_TIME_DUE + "TIME] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Photoshop subscription "
            + PREFIX_AMOUNT + "300";

    public static final String MESSAGE_EDIT_FINANCE_SUCCESS = "Edited Finance: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_FINANCE = "This finance entry already exists in the finance book";

    private final Index index;
    private final EditFinanceDescriptor editFinanceDescriptor;

    /**
     * @param index of the event in the filtered finance list to edit
     * @param editFinanceDescriptor details to edit the finance with
     */
    public EditFinanceCommand(Index index, EditFinanceDescriptor editFinanceDescriptor) {
        requireNonNull(index);
        requireNonNull(editFinanceDescriptor);

        this.index = index;
        this.editFinanceDescriptor = editFinanceDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Finance> lastShownList = model.getFilteredFinanceList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FINANCE_DISPLAYED_INDEX);
        }

        Finance financeToEdit = lastShownList.get(index.getZeroBased());
        Finance editedFinance = createEditedFinance(financeToEdit, editFinanceDescriptor, model);

        if (!financeToEdit.isSameFinance(editedFinance) && model.hasFinance(editedFinance)) {
            throw new CommandException(MESSAGE_DUPLICATE_FINANCE);
        }

        if (financeToEdit instanceof Expense && editedFinance instanceof Expense) {
            model.setExpense((Expense) financeToEdit, (Expense) editedFinance);
        }
        if (financeToEdit instanceof Commission && editedFinance instanceof Commission) {
            model.setCommission((Commission) financeToEdit, (Commission) editedFinance);
        }
        model.updateFilteredFinanceList(Model.PREDICATE_SHOW_ALL_FINANCES);
        return new CommandResult(String.format(MESSAGE_EDIT_FINANCE_SUCCESS, Messages.formatFinance(editedFinance)));
    }


    /**
     * Creates and returns a {@code Finance} with the details of {@code financeToEdit}
     * edited with {@code editFinanceDescriptor}.
     */
    private static Finance createEditedFinance(Finance financeToEdit, EditFinanceDescriptor editFinanceDescriptor,
                                               Model model) throws CommandException {
        assert financeToEdit != null;

        Amount updatedAmount = editFinanceDescriptor.getAmount().orElse(financeToEdit.getAmount());


        Person updatedClient;

        if (editFinanceDescriptor.isClientChanged) {
            Person client = editFinanceDescriptor.client;

            // Unable to edit commission with a null client, since commission should always contain a client
            if (client == null && financeToEdit instanceof Commission) {
                throw new CommandException(Messages.MESSAGE_UNABLE_TO_EDIT_CLIENT);
            }

            updatedClient = getValidClient(client, model);

        } else {
            updatedClient = financeToEdit.getClient();
        }

        Description updatedDescription = editFinanceDescriptor.getDescription().orElse(financeToEdit.getDescription());
        TimeDue updatedTimeDue = editFinanceDescriptor.getTimeDue().orElse(financeToEdit.getTimeDue());

        if (financeToEdit instanceof Expense) {
            return new Expense(updatedAmount, updatedClient, updatedDescription, updatedTimeDue);
        } else {
            return new Commission(updatedAmount, updatedClient, updatedDescription, updatedTimeDue);
        }
    }

    private static Person getValidClient(Person client, Model model) throws CommandException {
        if (client == null) {
            return null;
        }
        if (!model.isValidClient(client)) {
            throw new CommandException(Messages.MESSAGE_CLIENT_DOES_NOT_EXIST);
        }
        return model.getMatchedClient(client);
    }



    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditFinanceCommand)) {
            return false;
        }

        EditFinanceCommand otherEditFinanceCommand = (EditFinanceCommand) other;
        return index.equals(otherEditFinanceCommand.index)
                && editFinanceDescriptor.equals(otherEditFinanceCommand.editFinanceDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editFinanceDescriptor", editFinanceDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the finance with. Each non-empty field value will replace the
     * corresponding field value of the finance.
     * Note for this particular descriptor, unlike the other descriptors, does NOT have a copy constructor as there is
     * no clear way to properly implementing the isClientChecked for the copy instructor.
     */
    public static class EditFinanceDescriptor {
        private Amount amount;
        private Person client;
        private Description description;
        private TimeDue timeDue;
        private boolean isClientChanged = false;

        public EditFinanceDescriptor() {}

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(amount, description, timeDue) || isClientChanged;
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        public void setClient(Person client) {
            isClientChanged = true;
            this.client = client;
        }
        public Optional<Person> getClient() {
            return Optional.ofNullable(client);
        }

        public void setDescription(Description description) {
            this.description = description;
        }
        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }
        public void setTimeDue(TimeDue timeDue) {
            this.timeDue = timeDue;
        }
        public Optional<TimeDue> getTimeDue() {
            return Optional.ofNullable(timeDue);
        }




        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFinanceDescriptor)) {
                return false;
            }

            EditFinanceDescriptor otherEditFinanceDescriptor = (EditFinanceDescriptor) other;
            return Objects.equals(amount, otherEditFinanceDescriptor.amount)
                    && Objects.equals(client, otherEditFinanceDescriptor.client)
                    && Objects.equals(description, otherEditFinanceDescriptor.description)
                    && Objects.equals(timeDue, otherEditFinanceDescriptor.timeDue);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("amount", amount)
                    .add("client", client)
                    .add("description", description)
                    .add("timeDue", timeDue)
                    .toString();
        }
    }
}
