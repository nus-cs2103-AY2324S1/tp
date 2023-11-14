package seedu.address.storage.finance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FinancesBook;
import seedu.address.model.ReadOnlyFinancesBook;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Expense;
import seedu.address.model.finance.Finance;

/**
 * An Immutable Finances that is serializable to JSON format.
 */
public class JsonSerializableFinancesBook {
    public static final String MESSAGE_DUPLICATE_FINANCE = "Finance list contains duplicate finance(s).";

    private final List<JsonAdaptedFinance> finances = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFinancesBook} with the given events.
     */
    @JsonCreator
    public JsonSerializableFinancesBook(@JsonProperty("finances") List<JsonAdaptedFinance> finances) {
        this.finances.addAll(finances);
    }

    /**
     * Converts a given {@code ReadOnlyFinancesBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFinancesBook}.
     */
    public JsonSerializableFinancesBook(ReadOnlyFinancesBook source) {
        finances.addAll(source.getFinanceList().stream()
                .map(finance -> {
                    if (finance instanceof Commission) {
                        return new JsonAdaptedCommission((Commission) finance);
                    } else if (finance instanceof Expense) {
                        return new JsonAdaptedExpense((Expense) finance);
                    }
                    return null;
                }).filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this finance book into the model's {@code FinancesBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FinancesBook toModelType() throws IllegalValueException {
        FinancesBook financesBook = new FinancesBook();

        for (JsonAdaptedFinance jsonAdaptedFinance : finances) {
            Finance finance = null;
            if (jsonAdaptedFinance instanceof JsonAdaptedCommission) {
                finance = ((JsonAdaptedCommission) jsonAdaptedFinance).toModelType();
            } else if (jsonAdaptedFinance instanceof JsonAdaptedExpense) {
                finance = ((JsonAdaptedExpense) jsonAdaptedFinance).toModelType();
            }
            financesBook.addFinance(finance);
        }

        return financesBook;
    }
}
