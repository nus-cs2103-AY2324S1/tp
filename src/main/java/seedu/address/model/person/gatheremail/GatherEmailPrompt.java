//@@author AlyssaPng
package seedu.address.model.person.gatheremail;

import seedu.address.model.person.Person;

/**
 * Gathers the email of {@code Person} if {@code Person}'s matches a specific prompt.
 */
public interface GatherEmailPrompt {
    /**
     * Gathers the email of {@code person} if prompt is a substring of any {@code person}'s financial plan or tag names
     */
    public String gatherEmails(Person person);
}
