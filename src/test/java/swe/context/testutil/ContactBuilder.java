package swe.context.testutil;

import static swe.context.testutil.TestData.DEFAULT_EMAIL;
import static swe.context.testutil.TestData.DEFAULT_NAME;
import static swe.context.testutil.TestData.DEFAULT_NOTE;
import static swe.context.testutil.TestData.DEFAULT_PHONE;

import java.util.HashSet;
import java.util.Set;

import swe.context.model.alternate.AlternateContact;
import swe.context.model.contact.Contact;
import swe.context.model.contact.Email;
import swe.context.model.contact.Name;
import swe.context.model.contact.Note;
import swe.context.model.contact.Phone;
import swe.context.model.tag.Tag;
import swe.context.model.util.SampleContactsUtil;

/**
 * Builds {@link Contact}s more conveniently.
 */
public class ContactBuilder {
    private Name name;
    private Phone phone;
    private Email email;
    private Note note;
    private Set<Tag> tags;
    private Set<AlternateContact> alternateContacts;

    /**
     * Creates a {@code ContactBuilder} with the default details.
     */
    public ContactBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        note = new Note(DEFAULT_NOTE);
        tags = new HashSet<>();
        alternateContacts = new HashSet<>();
    }

    /**
     * Initializes the ContactBuilder with the data of {@code contactToCopy}.
     */
    public ContactBuilder(Contact contactToCopy) {
        name = contactToCopy.getName();
        phone = contactToCopy.getPhone();
        email = contactToCopy.getEmail();
        note = contactToCopy.getNote();
        tags = new HashSet<>(contactToCopy.getTags());
        alternateContacts = new HashSet<>(contactToCopy.getAlternates());
    }

    /**
     * Sets the {@code Name} of the {@code Contact} that we are building.
     */
    public ContactBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Contact} that we are building.
     */
    public ContactBuilder withTags(String ... tags) {
        this.tags = SampleContactsUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Contact} that we are building.
     */
    public ContactBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Contact} that we are building.
     */
    public ContactBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the Note of the Contact being built.
     */
    public ContactBuilder withNote(String _note) {
        this.note = new Note(_note);
        return this;
    }

    /**
     * Parses the {@code alternateContacts} into a {@code Set<AlternateContact>}
     * and set it to the {@code Contact} that we are building.
     */
    public ContactBuilder withAlternateContacts(String... alternateContacts) {
        this.alternateContacts = SampleContactsUtil.getAlternateContactSet(alternateContacts);
        return this;
    }

    public Contact build() {
        return new Contact(name, phone, email, note, tags, alternateContacts);
    }
}
