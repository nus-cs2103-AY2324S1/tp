package seedu.address.model.person;

/**
 * Gather the email of {@code Person} if {@code Person}'s matches a specific prompt.
 */
public interface GatherEmails {
    public String gatherEmails(Person person);
}
