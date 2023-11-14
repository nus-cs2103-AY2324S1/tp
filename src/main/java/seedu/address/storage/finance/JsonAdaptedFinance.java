package seedu.address.storage.finance;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import seedu.address.model.finance.Commission;

/**
 * Jackson-friendly version of {@link Commission}.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = JsonAdaptedCommission.class, name = "Commission"),
    @JsonSubTypes.Type(value = JsonAdaptedExpense.class, name = "Expense")
})
public abstract class JsonAdaptedFinance {
}
