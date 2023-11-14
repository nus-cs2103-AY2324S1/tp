package seedu.address.storage;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.financialplan.FinancialPlan;


/**
 * Jackson-friendly version of {@link FinancialPlan}.
 */
public class JsonAdaptedFinancialPlan {

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    private final String financialPlanName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedFinancialPlan(String financialPlanName) {
        this.financialPlanName = financialPlanName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedFinancialPlan(FinancialPlan source) {
        financialPlanName = source.financialPlanName;
    }

    @JsonValue
    public String getFinancialPlanName() {
        return financialPlanName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public FinancialPlan toModelType() throws IllegalValueException {
        if (!FinancialPlan.isValidFinancialPlanName(financialPlanName)) {
            logger.warning("Invalid Financial Plan Name: " + financialPlanName);
            throw new IllegalValueException(FinancialPlan.MESSAGE_CONSTRAINTS);
        }
        return new FinancialPlan(financialPlanName);
    }
}
