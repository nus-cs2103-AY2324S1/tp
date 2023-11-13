package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.PersonType;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String PATIENT_MESSAGE_SUCCESS = "Listed all patients";

    public static final String SPECIALIST_MESSAGE_SUCCESS = "Listed all specialists";

    private final PersonType personType;

    /**
     * @param personType The type of person being listed i.e. patient or specialist
     */
    public ListCommand(PersonType personType) {
        this.personType = personType;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(personType.getSearchPredicate());
        model.commit();
        return new CommandResult(personType == PersonType.PATIENT
                ? PATIENT_MESSAGE_SUCCESS
                : SPECIALIST_MESSAGE_SUCCESS);

    }
}
