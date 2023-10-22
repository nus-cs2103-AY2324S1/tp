package seedu.address.model.person;

/**
 * Gather the email of {@code Person} if {@code Person}'s Tags Names contains {@code promptTag}
 * as a substring.
 */
public class GatherEmailsByTag implements GatherEmails {
    private final String promptTag;
    public GatherEmailsByTag(String promptTag) {
        this.promptTag = promptTag;
    }
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
        if (!(other instanceof GatherEmailsByTag)) {
            return false;
        }
        GatherEmailsByTag otherGatherCommand = (GatherEmailsByTag) other;
        return promptTag.equals(otherGatherCommand.promptTag);
    }

    @Override
    public String toString() {
        return "Tag: " + promptTag;
    }
}
