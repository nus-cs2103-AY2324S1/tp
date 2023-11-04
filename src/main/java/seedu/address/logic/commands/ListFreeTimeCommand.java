package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_PAST_DATE;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.util.Pair;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Time;

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

    private final Time givenDay;

    /**
     * Constructs an instance of the ListFreeTimeCommand
     *
     * @author Tan Kerway
     * @param givenDay the day to list out free time on
     */
    public ListFreeTimeCommand(Time givenDay) {
        this.givenDay = givenDay;
    }

    /**
     * Converts the list of free times into a nicely-formatted string to print to the command box.
     *
     * @author Tan Kerway
     * @param freeTimes the list of free times
     * @return a String of free times
     */
    private String formatFreeTime(List<Pair<Time, Time>> freeTimes) {
        System.out.println(freeTimes);
        StringBuilder sb = new StringBuilder();
        for (Pair<Time, Time> pocketOfFreeTime : freeTimes) {
            sb.append("from: ").append(pocketOfFreeTime.getKey().getTime())
                    .append(" ").append("to: ").append(pocketOfFreeTime.getValue().getTime())
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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (givenDay.isPast()) {
            throw new CommandException(MESSAGE_PAST_DATE);
        }
        List<Pair<Time, Time>> freeTimes = model.listPocketsOfTimeOnGivenDay(givenDay);
        String formattedFreeTime = formatFreeTime(freeTimes);
        String formattedDate = givenDay.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
