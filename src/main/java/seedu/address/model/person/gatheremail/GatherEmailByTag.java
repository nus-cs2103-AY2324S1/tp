//@@author AlyssaPng
package seedu.address.model.person.gatheremail;

import seedu.address.model.person.Person;

/**
 * Gathers the email of {@code Person} if {@code Person}'s Tags Names contains {@code promptTag}
 * as a substring.
 */
public class GatherEmailByTag implements GatherEmailPrompt {
    private final String promptTag;

    /**
     * Constructs a new GatherEmailByTag Object
     */
    public GatherEmailByTag(String promptTag) {
        this.promptTag = promptTag;
    }

    /**
     * Gathers the email of {@code person} if prompt is a substring of any {@code person}'s tag names
     */
    @Override
    public String gatherEmails(Person person) {
        return person.gatherEmailsContainsTag(promptTag);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GatherEmailByTag)) {
            return false;
        }
        GatherEmailByTag otherGatherCommand = (GatherEmailByTag) other;
        return promptTag.equals(otherGatherCommand.promptTag);
    }

    /**
     * Returns the String representation of GatherEmailByTag Object.
     */
    @Override
    public String toString() {
        return "Tag: " + promptTag;
    }
}
