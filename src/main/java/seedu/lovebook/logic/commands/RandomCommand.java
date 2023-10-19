package seedu.lovebook.logic.commands;

import seedu.lovebook.logic.Messages;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.model.person.randomPredicate;

import java.util.Random;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.model.Model.PREDICATE_SHOW_ALL_PERSONS;


public class RandomCommand extends Command{
    public static final String COMMAND_WORD = "random";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generates a random date"
            + "Example: " + COMMAND_WORD ;
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Random randomGenerator = new Random();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        int randomIndex = randomGenerator.nextInt(model.getFilteredPersonList().size());
        Date person = model.getFilteredPersonList().get(randomIndex);
        model.updateFilteredPersonList(new randomPredicate(person));
        return new CommandResult(Messages.MESSAGE_RANDOM_PERSON_SUCCESS);
    }
}
