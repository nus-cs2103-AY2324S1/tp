package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.UniqueReminderList;

/**
 * Shows the dashboard.
 */
public class ReminderCommand extends Command {

    public static final String COMMAND_WORD = "reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows reminders. ";

    public static final String MESSAGE_SUCCESS = "Reminders shown.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Reminder> reminderList = UniqueReminderList.getInstance().asUnmodifiableObservableList();

        StringBuilder stringBuilder = new StringBuilder();
        for (Reminder reminder: reminderList) {
            stringBuilder.append(reminder);
        }

        return new CommandResult(stringBuilder.toString());
    }
}

