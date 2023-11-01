package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.Time;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.UniqueInterviewList;

/**
 * Encapsulates the list-freetime command.
 *
 * @author Tan Kerway
 */
public class ListFreeTimeCommand extends Command {
    public static final String COMMAND_WORD = "list-freetime";
    public static final String MESSAGE_LIST_FREETIME_SUCCESS = "Free times on %s:\n%s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists out free time on a given day.\n"
            + "Parameters: DATE (Cannot be an invalid date or a date in the past)\n"
            + "Example: " + COMMAND_WORD + " 12/12/2034";

    private final LocalDateTime givenDay;

    /**
     * Constructs an instance of the ListFreeTimeCommand
     *
     * @author Tan Kerway
     * @param givenDay the day to list out free time on
     */
    public ListFreeTimeCommand(LocalDateTime givenDay) {
        this.givenDay = givenDay;
    }

    /**
     * Converts the list of free times into a nicely-formatted string to print to the command box.
     *
     * @author Tan Kerway
     * @param freeTimes the list of free times
     * @return a String of free times
     */
    private String formatFreeTime(List<List<LocalDateTime>> freeTimes) {
        StringBuilder sb = new StringBuilder();
        for (List<LocalDateTime> pocketOfFreeTime : freeTimes) {
            sb.append("from: ").append(pocketOfFreeTime.get(0).toLocalTime())
                    .append(" ").append("to: ").append(pocketOfFreeTime.get(1).toLocalTime())
                    .append("\n");
        }
        return sb.toString();
    }
    /**
     * Returns the command result of typing the list-freetime command.
     *
     * @author Tan Kerway
     * @param model {@code Model} which the command should operate on.
     * @return the command result of executing the find-freetime command
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Interview> temp = model.getFilteredInterviewList();
        UniqueInterviewList interviews = new UniqueInterviewList();
        interviews.setInterviews(temp);
        List<List<LocalDateTime>> freeTimes = Time.listPocketsOfTimeOnGivenDay(givenDay, interviews);
        String formattedFreeTime = formatFreeTime(freeTimes);
        String formattedDate = givenDay.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return new CommandResult(String.format(MESSAGE_LIST_FREETIME_SUCCESS, formattedDate, formattedFreeTime));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ListFreeTimeCommand)) {
            return false;
        }
        ListFreeTimeCommand temp = (ListFreeTimeCommand) other;
        return this.givenDay.equals(temp.givenDay);
    }
}
