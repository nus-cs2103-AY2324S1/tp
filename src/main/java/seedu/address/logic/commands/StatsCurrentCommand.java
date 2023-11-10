package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Person;

/**
 * Calculates the statistics of fosterers who are currently fostering from the currently displayed list.
 */
public class StatsCurrentCommand extends StatsCommand {
    public static final String COMMAND_WORD = "current";
    public static final String MESSAGE_CURRENT_SUMMARY = "%1$d out of %2$d listed are "
            + "currently fostering (%3$.2f%%)!";

    public static final String MESSAGE_CURRENT_DETAILS = "- %1$d fostering dogs (%2$.2f%%)\n"
            + "- %3$d fostering cats (%4$.2f%%)";

    /**
     * Returns a list of fosterers who are currently fostering.
     */
    protected List<Person> getCurrentFosterers(List<Person> fosterers) {
        return fosterers.stream()
                .filter(Person::isCurrentFosterer)
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of fosterers from the given list who are fostering dogs.
     */
    protected int getCurrentDogCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAnimalType()
                                .equals(AnimalType.CURRENT_DOG))
                .count();
    }

    /**
     * Returns the number of fosterers from the given list who are fostering cats.
     */
    protected int getCurrentCatCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAnimalType()
                                .equals(AnimalType.CURRENT_CAT))
                .count();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        int total = lastShownList.size();

        if (total == 0) {
            throw new CommandException(StatsCommand.MESSAGE_NO_FOSTERERS);
        }

        List<Person> currentFosterers = getCurrentFosterers(lastShownList);
        int currentCount = currentFosterers.size();
        double currentPercent = calculatePercentage(currentCount, total);
        String resultSummary = String.format(MESSAGE_CURRENT_SUMMARY, currentCount, total, currentPercent);

        if (currentCount == 0) {
            return new CommandResult(resultSummary);
        }

        int fosteringDogsCount = getCurrentDogCount(currentFosterers);
        int fosteringCatsCount = getCurrentCatCount(currentFosterers);

        double fosteringDogsPercent = calculatePercentage(fosteringDogsCount, currentCount);
        double fosteringCatsPercent = 100.0 - fosteringDogsPercent;

        String resultDetails = String.format(MESSAGE_CURRENT_DETAILS, fosteringDogsCount,
                fosteringDogsPercent, fosteringCatsCount, fosteringCatsPercent);

        return new CommandResult(resultSummary + "\n" + resultDetails);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatsCurrentCommand)) {
            return false;
        }

        return true;
    }
}
