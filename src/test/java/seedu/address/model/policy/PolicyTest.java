package seedu.address.model.policy;

import static seedu.address.logic.commands.CommandTestUtil.VALID_HEALTH_INSURANCE_POLICY;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PolicyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Policy(null, null, 10000, null, null));
    }

    @Test
    public void valid_healthInsurance_policy() {
        Policy policy = Policy.fromString(VALID_HEALTH_INSURANCE_POLICY);
        assert(policy.policyName.equals("Health Insurance"));
        assert(policy.policyValue == 100000);
    }


    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Policy.isValidPolicyName(null));
    }

}
