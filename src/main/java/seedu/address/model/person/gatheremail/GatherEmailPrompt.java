package seedu.address.model.person.gatheremail;

import seedu.address.model.person.Person;

/**
 * Gather the email of {@code Person} if {@code Person}'s matches a specific prompt.
 */
public interface GatherEmailPrompt {
    public String gatherEmails(Person person);
}
