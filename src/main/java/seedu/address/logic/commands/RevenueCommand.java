package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.text.DecimalFormat;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class RevenueCommand extends Command {

    public static final String COMMAND_WORD = "rev";
    public static final String MESSAGE_SUCCESS = "Sucessfully calculated!!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Calculates total revenue earned from all tutees in a month.\n "
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> fullList = model.getFilteredPersonList();

        double totalRevenue = 0;
        // for every person, find revenue, add them tgt
        for (Person tutee : fullList) {
            // get revenue per lesson
            double lessonRevenue = tutee.getMonthlyRevenue();
            assert lessonRevenue >= 0;
            totalRevenue += lessonRevenue;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        totalRevenue = Double.parseDouble(df.format(totalRevenue));
        return new CommandResult(MESSAGE_SUCCESS + "\nTotal monthly revenue: $" + totalRevenue);
    }

}
