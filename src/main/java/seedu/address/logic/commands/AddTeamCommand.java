//package seedu.address.logic.commands;
//
//
//import static java.util.Objects.requireNonNull;
//import static seedu.address.logic.parser.CliSyntax.*;
//
//import seedu.address.commons.util.ToStringBuilder;
//import seedu.address.logic.Messages;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.Model;
//import seedu.address.model.person.Person;
//
///**
// * Adds a person to the address book.
// */
//public class AddTeamCommand extends Command {
//
//    public static final String COMMAND_WORD = "newteam";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new team. "
//            + "Parameters: "
//            + PREFIX_TEAMNAME + "TEAMNAME "
//            + PREFIX_TEAMLEAD + "TEAMLEADER "
//            + "Example: " + COMMAND_WORD + " "
//            + PREFIX_NAME + "ABC "
//            + PREFIX_PHONE + "JOHN DOE ";
//
//    public static final String MESSAGE_SUCCESS = "New team added: %1$s";
//    public static final String MESSAGE_DUPLICATE_PERSON = "This team already exists in the project. Choose a new name!";
//
//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//
//        if (model.hasPerson(devToAdd)) {
//            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
//        }
//
//        model.addPerson(devToAdd);
//        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(devToAdd)));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof AddDevCommand)) {
//            return false;
//        }
//
//        AddDevCommand otherAddCommand = (AddDevCommand) other;
//        return devToAdd.equals(otherAddCommand.devToAdd);
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .add("devToAdd", devToAdd)
//                .toString();
//    }
//}
