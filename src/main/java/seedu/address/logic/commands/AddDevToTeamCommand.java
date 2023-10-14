//This class deals with adding an existing developer to the specified team.
//package seedu.address.logic.commands;
//
//import static java.util.Objects.requireNonNull;
//import static seedu.address.logic.parser.CliSyntax.*;
//
//import seedu.address.commons.util.ToStringBuilder;
//import seedu.address.logic.Messages;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.Model;
//import seedu.address.model.person.Developer;
//import seedu.address.model.person.Person;
//import seedu.address.model.person.Team;
//
///**
// * Adds a person to the address book.
// */
//public class AddDevToTeamCommand extends Command {
//
//    public static final String COMMAND_WORD = "dev2team";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds this developer to the Team. "
//            + "Parameters: "
//            + PREFIX_NAME + "Developer Name "
//            + PREFIX_TEAMNAME + "Team Name "
//            + "\n"
//            + "Example: " + COMMAND_WORD + " "
//            + PREFIX_NAME + "John Doe "
//            + PREFIX_TEAMNAME + "ABC ";
//
//    public static final String MESSAGE_SUCCESS = "New developer added: %1$s";
//    public static final String MESSAGE_DUPLICATE_PERSON = "This developer already exists in this team";
//
//    private final Developer devToAdd;
//    private final Team teamToAddTo;
//
//    /**
//     * Creates an AddCommand to add the specified {@code Person}
//     */
//    public AddDevToTeamCommand(Team team, Developer developer) {
//        requireNonNull(developer);
//        requireNonNull(team);
//        devToAdd = developer;
//        teamToAddTo = team;
//    }
//
//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//        //need a way to check if duplicate person exists in the team
//        if (model.hasTeam(teamToAddTo) && model.hasPerson(devToAdd)) {
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
