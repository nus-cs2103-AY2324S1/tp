package seedu.address.testutil;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.fields.ApplicationOutcome;
import seedu.address.model.fields.Cycle;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.Location;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Pay;
import seedu.address.model.fields.Role;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A class to create new InternshipRole objects
 */
public class InternshipRoleBuilder {
    private static final String DEFAULT_NAME = "Google";
    private static final String DEFAULT_CYCLE = "Summer";
    private static final String DEFAULT_DESCRIPTION = "STEP internship";
    private static final String DEFAULT_ROLE = "Backend Engineer";
    private static final Outcome DEFAULT_OUTCOME = Outcome.FOLLOW_UP;
    private static final BigDecimal DEFAULT_PAY = new BigDecimal("3000.00");
    private static final String DEFAULT_LOCATION = "Pasir Panjang";

    private Name name;
    private Role role;
    private Cycle cycle;
    private Description description;
    private ApplicationOutcome outcome;
    private Pay pay;
    private Location location;
    private Set<Tag> tags;

    /**
     * Creates an InternshipRoleBuilder with default fields
     */
    public InternshipRoleBuilder() {
        name = new Name(DEFAULT_NAME);
        cycle = new Cycle(DEFAULT_CYCLE);
        description = new Description(DEFAULT_DESCRIPTION);
        role = new Role(DEFAULT_ROLE);
        outcome = new ApplicationOutcome(DEFAULT_OUTCOME);
        pay = new Pay(DEFAULT_PAY);
        location = new Location(DEFAULT_LOCATION);
        tags = new HashSet<>();
    }

    /**
     * Creates an InternshipRoleBuilder with the given input
     * @param roleToCopy over to the builder
     */
    public InternshipRoleBuilder(InternshipRole roleToCopy) {
        name = roleToCopy.getName();
        cycle = roleToCopy.getCycle();
        role = roleToCopy.getRole();
        description = roleToCopy.getDescription();
        outcome = roleToCopy.getApplicationOutcome();
        pay = roleToCopy.getPay();
        location = roleToCopy.getLocation();
        tags = roleToCopy.getTags();
    }

    /**
     * @param name of the role
     * @return a InternshipRoleBuilder object with the given name
     */
    public InternshipRoleBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * @param role of the Internship role
     * @return a InternshipRoleBuilder object with the given role
     */
    public InternshipRoleBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * @param cycle of the Internship role
     * @return a InternshipRoleBuilder object with the given cycle
     */
    public InternshipRoleBuilder withCycle(String cycle) {
        this.cycle = new Cycle(cycle);
        return this;
    }

    /**
     * @param outcome of the role
     * @return a InternshipRoleBuilder object with the given outcome
     */
    public InternshipRoleBuilder withOutcome(Outcome outcome) {
        this.outcome = new ApplicationOutcome(outcome);
        return this;
    }

    /**
     * @param pay of the role
     * @return a InternshipRoleBuilder object with the given pay
     */
    public InternshipRoleBuilder withPay(BigDecimal pay) {
        this.pay = new Pay(pay);
        return this;
    }

    /**
     * @param location of the role
     * @return a InternshipRoleBuilder object with the given location
     */
    public InternshipRoleBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * @param desc of the role
     * @return a InternshipRoleBuilder object with the given description
     */
    public InternshipRoleBuilder withDescription(String desc) {
        this.description = new Description(desc);
        return this;
    }

    /**
     * @param tags of the role
     * @return a InternshipRoleBuilder object with the given tags
     */
    public InternshipRoleBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * @return an InternshipRole with the current fields
     */
    public InternshipRole build() {
        return new InternshipRole(name, role, cycle, description, pay, outcome, location, tags);
    }

}
