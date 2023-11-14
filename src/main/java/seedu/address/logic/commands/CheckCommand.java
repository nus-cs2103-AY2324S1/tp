package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Tutorial;

/**
 * Checks a group requirement by its group number.
 */
public class CheckCommand extends Command {

    public static final String COMMAND_WORD = "checkGroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Checks the group identified by its group number.\n"
            + "Parameters: GROUP_NUMBER\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String GROUP_NUM = "Group %1$s\n";
    public static final String MESSAGE_CHECK_GROUP_SUCCESS =
            "Group fulfils the diversity requirements of CS2103T.";
    public static final String MESSAGE_HELP =
            "You can enter the `help` command for more information on group requirements.";
    public static final String MESSAGE_CHECK_GROUP_SIZE_EMPTY =
            "Group does not have any members.\n";
    public static final String MESSAGE_CHECK_GROUP_SIZE_ONE =
            "Group has only one member.\n";
    public static final String MESSAGE_CHECK_GROUP_SIZE_UNDER =
            "Group has less than 5 members.\n";
    public static final String MESSAGE_CHECK_GROUP_SIZE_OVER =
            "Group size has exceeded limit with more than 5 members.\n";
    public static final String MESSAGE_CHECK_GROUP_NATIONALITY_WARNING =
            "Group comprises of members of the same nationality.\n";
    public static final String MESSAGE_CHECK_GROUP_GENDER_WARNING =
            "Group comprises of members of the same gender.\n";
    public static final String MESSAGE_CHECK_GROUP_TUTORIAL_WARNING =
            "Not every group member's tutorial matches the group's tutorial.\n";
    public static final String MESSAGE_CHECK_GROUP_NOT_FOUND = "Group with the provided group number not found.";
    private final GroupContainsKeywordsPredicate predicate;


    private final int groupNumber;

    /**
     * @param groupNumber unique identifier of the group
     * @param predicate check group number
     */
    public CheckCommand(int groupNumber, GroupContainsKeywordsPredicate predicate) {
        this.groupNumber = groupNumber;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredGroupList(predicate);

        // Check if the group with the provided group number exists
        Group groupToCheck = model.getGroupWithNumber(groupNumber)
                .orElseThrow(() -> new CommandException(MESSAGE_CHECK_GROUP_NOT_FOUND));

        String message = "";
        boolean isSuccess = true;
        Set<Person> groupMembers = groupToCheck.getMembers();

        // check group size
        if (groupMembers.isEmpty()) {
            isSuccess = false;
            message = MESSAGE_CHECK_GROUP_SIZE_EMPTY;
        } else if (groupMembers.size() == 1) {
            isSuccess = false;
            message = MESSAGE_CHECK_GROUP_SIZE_ONE;
        } else if (groupMembers.size() > 1 && groupMembers.size() < 5) {
            isSuccess = false;
            message = MESSAGE_CHECK_GROUP_SIZE_UNDER;
        } else if (groupMembers.size() > 5) {
            isSuccess = false;
            message = MESSAGE_CHECK_GROUP_SIZE_OVER;
        }

        if (groupMembers.size() > 1) {
            String groupTutorial = groupToCheck.getTutorial().value;
            isSuccess = hasMixNationality(groupMembers) && hasMixGender(groupMembers)
                    && hasGroupTutorial(groupMembers, groupTutorial);

            if (!hasMixNationality(groupMembers)) {
                message += MESSAGE_CHECK_GROUP_NATIONALITY_WARNING;
            }
            if (!hasMixGender(groupMembers)) {
                message += MESSAGE_CHECK_GROUP_GENDER_WARNING;
            }
            if (!hasGroupTutorial(groupMembers, groupTutorial)) {
                message += MESSAGE_CHECK_GROUP_TUTORIAL_WARNING;
            }
        }

        if (isSuccess) {
            return new CommandResult(String.format(GROUP_NUM + MESSAGE_CHECK_GROUP_SUCCESS, groupToCheck.getNumber()),
                    false, false, true, false);
        } else {
            return new CommandResult(String.format(GROUP_NUM + message + MESSAGE_HELP, groupToCheck.getNumber()),
                    false, false, true, false);
        }
    }

    /**
     * Checks if a group has a mix of local and foreigner members.
     *
     * @param groupMembers The set of persons representing the group members.
     * @return True if there is a mix of local and foreigner members, false otherwise.
     */
    private boolean hasMixNationality(Set<Person> groupMembers) {
        int localCount = 0;
        int foreignerCount = 0;
        for (Person member : groupMembers) {
            if (member.getNationality().value.equals("local")) {
                localCount++;
            } else {
                foreignerCount++;
            }
        }

        if (localCount == 0 || foreignerCount == 0) {
            return false;
        }
        return true;
    }

    /**
     * Checks if a group has a mix of male and female members.
     *
     * @param groupMembers The set of persons representing the group members.
     * @return True if there is a mix of male and female members, false otherwise.
     */
    private boolean hasMixGender(Set<Person> groupMembers) {
        int maleCount = 0;
        int femaleCount = 0;
        for (Person member : groupMembers) {
            if (member.getGender().value.equals("M")) {
                maleCount++;
            } else {
                femaleCount++;
            }
        }

        if (maleCount == 0 || femaleCount == 0) {
            return false;
        }
        return true;
    }

    /**
     * Checks if all members of a group belong to a specific tutorial.
     *
     * @param groupMembers The set of persons representing the group members.
     * @param groupTutorial The tutorial code to check against.
     * @return True if all members belong to the specified tutorial, false otherwise.
     */
    private boolean hasGroupTutorial(Set<Person> groupMembers, String groupTutorial) {
        int count = 0;
        for (Person member : groupMembers) {
            for (Tutorial tut : member.getTutorials()) {
                if (tut.value.equals((groupTutorial))) {
                    count++;
                    continue;
                }
            }
        }

        if (count != groupMembers.size()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CheckCommand)) {
            return false;
        }

        CheckCommand otherCheckCommand = (CheckCommand) other;
        return groupNumber == otherCheckCommand.groupNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("groupNumber", groupNumber)
                .toString();
    }
}
