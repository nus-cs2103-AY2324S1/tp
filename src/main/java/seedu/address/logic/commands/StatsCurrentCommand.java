package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Calculates the statistics of fosterers who are currently fostering from the currently displayed list.
 */
public class StatsCurrentCommand extends StatsCommand {
    public static final String COMMAND_WORD = "current";
    public static final String MESSAGE_CURRENT_SUCCESS = "%1$d out of %2$d listed fosterers"
            + " are currently fostering (%3$.1f%%)!\n"
            + "- %4$d fostering dogs (%5$.1f%%)\n"
            + "- %6$d fostering cats (%7$.1f%%)";


    public List<Person> getCurrentFosterers(List<Person> fosterers) {
        return fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAvailability()
                                .equals(Availability.NOT_AVAILABLE))
                .collect(Collectors.toList());
    }

    public int getCurrentDogCount(List<Person> fosterers) {
        return (int) fosterers.stream()
                .filter(fosterer ->
                        fosterer.getAnimalType()
                                .equals(AnimalType.CURRENT_DOG))
                .count();
    }

    public int getCurrentCatCount(List<Person> fosterers) {
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

        List<Person> currentFosterers = getCurrentFosterers(lastShownList);
        int currentCount = currentFosterers.size();
        int fosteringDogsCount = getCurrentDogCount(currentFosterers);
        int fosteringCatsCount = getCurrentCatCount(currentFosterers);

        float currentPercent = calculatePercentage(currentCount, total);
        float fosteringDogsPercent = calculatePercentage(fosteringDogsCount, currentCount);
        float fosteringCatsPercent = calculatePercentage(fosteringCatsCount, currentCount);

        String result = String.format(MESSAGE_CURRENT_SUCCESS, currentCount, total, currentPercent,
                fosteringDogsCount, fosteringDogsPercent, fosteringCatsCount, fosteringCatsPercent);

        return new CommandResult(result);
    }
}
