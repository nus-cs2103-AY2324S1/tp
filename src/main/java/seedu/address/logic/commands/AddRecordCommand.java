package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.record.Record;
import seedu.address.model.record.UniqueRecordList;

/**
 * Adds a record to a patient in the MedBook
 */
public class AddRecordCommand extends Command {

    public static final String COMMAND_WORD = "addrecord";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient record to the address book.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DATE + "DATETIME "
            + PREFIX_CONDITION + "CONDITION "
            + PREFIX_MEDICATION + "MEDICATION " + "\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "18-09-2023 1800 "
            + PREFIX_CONDITION + "Fever "
            + PREFIX_MEDICATION + "Tylenol";

    public static final String MESSAGE_SUCCESS = "New record added: %1$s";

    public static final String MESSAGE_DUPLICATE_RECORDS = "This record already exists in the record of the patient.";

    private final Record record;

    private final Index index;

    /**
     * Creates a record under a patient of specified index
     */
    public AddRecordCommand(Index index, Record record) {
        requireAllNonNull(index, record);
        this.index = index;
        this.record = record;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (model.hasRecord(record, index)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECORDS);
        }

        Person personToAddRecord = lastShownList.get(index.getZeroBased());
        UniqueRecordList newRecords = new UniqueRecordList();
        newRecords.setRecords(personToAddRecord.getRecords());
        newRecords.add(record);
        Person personWithAddedRecord = new Person(personToAddRecord.getName(), personToAddRecord.getNric(),
                personToAddRecord.getEmail(), personToAddRecord.getPhone(), personToAddRecord.getGender(),
                personToAddRecord.getAge(), personToAddRecord.getBloodType(), personToAddRecord.getAllergies(),
                newRecords, personToAddRecord.getAppointments(), personToAddRecord.isPinned());

        model.setPerson(personToAddRecord, personWithAddedRecord);
        model.updateRecordList(personWithAddedRecord);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(record, personWithAddedRecord)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddRecordCommand)) {
            return false;
        }

        AddRecordCommand otherAddRecordCommand = (AddRecordCommand) other;
        return record.equals(otherAddRecordCommand.record)
                && index.equals(otherAddRecordCommand.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("record", record)
                .toString();
    }
}
