package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.Tutorial;

/**
 * Represents a Student in the address book.
 */
public class Student extends Person {
    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Set<Tag> tags,
                   Set<Module> modules, Set<Tutorial> tutorials, StudentNumber studentNumber, Telegram telegram) {
        super(name, phone, email, tags, modules, tutorials, studentNumber, telegram);
    }
}
