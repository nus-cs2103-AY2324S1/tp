package seedu.address.model.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Details;
import seedu.address.model.person.Email;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Profession;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.interaction.Interaction;
import seedu.address.model.person.lead.Lead;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
                new Person.PersonBuilder(new Name("Alex Yeoh"), new Phone("87438807"),
                        new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("friends")).withTelegram(new TelegramHandle("@Allyeo"))
                        .withProfession(new Profession("Software Engineer")).withIncome(new Income(80000))
                        .withDetails(new Details("Looking for automated solutions for project management"))
                        .withLead(new Lead("COLD")).addInteraction(new Interaction(
                                "setting next meeting", Interaction.Outcome.valueOf("INTERESTED"),
                                LocalDate.of(2023, 11, 12)
                        )).build(),
                new Person.PersonBuilder(new Name("Bernice Yu"), new Phone("99272758"),
                        new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("colleagues", "friends")).withTelegram(new TelegramHandle("@yuyubern"))
                        .withProfession(new Profession("Graphic Designer")).withIncome(new Income(60000))
                        .withDetails(new Details("Interested in design software with real-time collaboration features"))
                        .withLead(new Lead("HOT")).build(),
                new Person.PersonBuilder(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                        new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("neighbours")).withTelegram(new TelegramHandle("@yuyubern"))
                        .withProfession(new Profession("Graphic Designer")).withIncome(new Income(60000))
                        .withDetails(new Details("Interested in design software with real-time collaboration features"))
                        .withLead(new Lead("WARM")).addInteraction(new Interaction(
                                "willing to meet again", Interaction.Outcome.valueOf("NOT_INTERESTED"),
                                LocalDate.of(2022, 1, 11)
                        )).addInteraction(new Interaction(
                                "bought", Interaction.Outcome.valueOf("CLOSED"),
                                LocalDate.of(2023, 12, 15))
                        ).build(),
                new Person.PersonBuilder(new Name("David Li"), new Phone("91031282"),
                        new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("family")).withTelegram(new TelegramHandle("@pyonpyondav"))
                        .withProfession(new Profession("Financial Analyst")).withIncome(new Income(90000))
                        .withDetails(new Details("Wants investment tracking and portfolio management tools"))
                        .withLead(new Lead("COLD")).build(),
                new Person.PersonBuilder(new Name("Irfan Ibrahim"), new Phone("92492021"),
                        new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagSet("classmates")).withTelegram(new TelegramHandle("@yuyubern"))
                        .withProfession(new Profession("Registered nurse")).withIncome(new Income(70000))
                        .withDetails(new Details("Interested in medical equipment")).withLead(new Lead("WARM"))
                        .build(),
                new Person.PersonBuilder(new Name("Roy Balakrishnan"), new Phone("92624417"),
                        new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagSet("colleagues")).withTelegram(new TelegramHandle("@arsyadbala"))
                        .withProfession(new Profession("Digital Marketing Specialist")).withIncome(new Income(75000))
                        .withDetails(new Details("Looking for a comprehensive CRM platform with email marketing"))
                        .withLead(new Lead("WARM"))
                        .addInteraction(new Interaction(
                                "Still exploring", Interaction.Outcome.valueOf("FOLLOWUP_REQUIRED"),
                                LocalDate.of(2023, 11, 22)
                        )).build(),
                new Person.PersonBuilder(new Name("Oliver Ho"), new Phone("94321678"),
                        new Email("oliverh@example.com"), new Address("Blk 77 Orchard Road #08-01"),
                        getTagSet("schoolmate"))
                        .withProfession(new Profession("Real Estate Agent"))
                        .withIncome(new Income(110000))
                        .withDetails(new Details("Interested in real estate CRM software"))
                        .withLead(new Lead("WARM"))
                        .addInteraction(new Interaction(
                                "Asked for CRM features",
                                Interaction.Outcome.valueOf("INTERESTED"),
                                LocalDate.of(2023, 10, 15)
                        ))
                        .addInteraction(new Interaction(
                                "Requested a pricing plan",
                                Interaction.Outcome.valueOf("FOLLOWUP_REQUIRED"),
                                LocalDate.of(2023, 10, 22)
                        )).build(),
                new Person.PersonBuilder(new Name("Emma Yong"), new Phone("92481237"),
                        new Email("emmay@example.com"), new Address("Blk 91 Marina Bay Street 10 #16-01"),
                        getTagSet("gymbuddy"))
                        .withLead(new Lead("HOT"))
                        .addInteraction(new Interaction(
                                "Showed interest in personal training packages",
                                Interaction.Outcome.valueOf("INTERESTED"),
                                LocalDate.of(2023, 10, 7)
                        )).build(),
                new Person.PersonBuilder(new Name("Mason Lim"), new Phone("93125468"),
                        new Email("masonl@example.com"), new Address("Blk 35 Tampines Street 5 #05-11"),
                        getTagSet("colleague"))
                        .withDetails(new Details("Looking for coding bootcamp info"))
                        .withLead(new Lead("COLD")).build(),
                new Person.PersonBuilder(new Name("Zoe Tan"), new Phone("94851239"),
                        new Email("zoet@example.com"), new Address("Blk 112 Bukit Batok Street 3 #02-07"),
                        getTagSet("acquaintances"))
                        .withDetails(new Details("Enquired about photography classes"))
                        .withLead(new Lead("WARM"))
                        .addInteraction(new Interaction(
                                "Requested class schedules", Interaction.Outcome.valueOf("FOLLOWUP_REQUIRED"),
                                LocalDate.of(2023, 10, 9)
                        )).build(),
                new Person.PersonBuilder(new Name("Liam Ng"), new Phone("91247856"),
                        new Email("liamn@example.com"), new Address("Blk 23 Ghim Moh Road #06-00"),
                        getTagSet("relatives"))
                        .withProfession(new Profession("Interior Designer"))
                        .withIncome(new Income(85000))
                        .withDetails(new Details("Interested in 3D rendering tools"))
                        .withLead(new Lead("HOT"))
                        .addInteraction(new Interaction(
                                "Enquired about software versions", Interaction.Outcome.valueOf("FOLLOWUP_REQUIRED"),
                                LocalDate.of(2023, 9, 15)
                        )).build(),
                new Person.PersonBuilder(new Name("Ava Wong"), new Phone("94561234"),
                        new Email("avaw@example.com"), new Address("Blk 50 Jurong West Street 61 #13-04"),
                        getTagSet("neighbors"))
                        .withDetails(new Details("Interested in organic food subscriptions"))
                        .withLead(new Lead("COLD"))
                        .build(),
                new Person.PersonBuilder(new Name("Lucas Chen"), new Phone("93214576"),
                        new Email("lucasc@example.com"), new Address("Blk 20 Woodlands Street 13 #09-33"),
                        getTagSet("family")).withTelegram(new TelegramHandle("@lucaswoods"))
                        .withLead(new Lead("WARM")).build(),
                new Person.PersonBuilder(new Name("Mia Tan"), new Phone("93671245"),
                        new Email("miat@example.com"), new Address("Blk 88 Serangoon Ave 4 #05-08"),
                        getTagSet("friends"))
                        .withIncome(new Income(78000))
                        .withDetails(new Details("Looking for car insurance deals"))
                        .withLead(new Lead("COLD"))
                        .build(),
                new Person.PersonBuilder(new Name("Amelia Seah"), new Phone("94567812"),
                        new Email("amelias@example.com"), new Address("Blk 17 Dover Road #02-29"),
                        getTagSet("volunteers"))
                        .withDetails(new Details("Seeks information on sustainable products"))
                        .withLead(new Lead("WARM"))
                        .addInteraction(new Interaction(
                                "Asked about product sourcing", Interaction.Outcome.valueOf("FOLLOWUP_REQUIRED"),
                                LocalDate.of(2023, 11, 4)
                        ))
                        .build(),
                new Person.PersonBuilder(new Name("Harper Lee"), new Phone("92234567"),
                        new Email("harperl@example.com"), new Address("Blk 12 Kent Ridge Drive #14-06"),
                        getTagSet("schoolmate"))
                        .withProfession(new Profession("Graduate Student"))
                        .withDetails(new Details("Interested in language exchange programs"))
                        .withLead(new Lead("HOT"))
                        .addInteraction(new Interaction(
                                "Inquired about program levels", Interaction.Outcome.valueOf("INTERESTED"),
                                LocalDate.of(2023, 9, 20)
                        ))
                        .build(),
                new Person.PersonBuilder(new Name("Elijah Koh"), new Phone("93124567"),
                        new Email("elijahk@example.com"), new Address("Blk 56 Pasir Ris Drive 1 #03-111"),
                        getTagSet("neighbors")).withTelegram(new TelegramHandle("@elijahkoh"))
                        .withProfession(new Profession("Music Teacher"))
                        .withIncome(new Income(65000))
                        .withDetails(new Details("Looking for new music instruments stores"))
                        .withLead(new Lead("COLD"))
                        .addInteraction(new Interaction(
                                "Discussed discount possibilities", Interaction.Outcome.valueOf("FOLLOWUP_REQUIRED"),
                                LocalDate.of(2023, 10, 20)
                        ))
                        .build(),
                new Person.PersonBuilder(new Name("Charlotte Goh"), new Phone("92345678"),
                        new Email("charlotteg@example.com"), new Address("Blk 65 Choa Chu Kang Loop #12-02"),
                        getTagSet("friend"))
                        .withDetails(new Details("Enquired about book club membership tiers"))
                        .withLead(new Lead("WARM"))
                        .addInteraction(new Interaction(
                                "Showed interest in premium membership", Interaction.Outcome.valueOf("INTERESTED"),
                                LocalDate.of(2023, 9, 30)
                        ))
                        .addInteraction(new Interaction(
                                "Requested information on member benefits",
                                Interaction.Outcome.valueOf("FOLLOWUP_REQUIRED"),
                                LocalDate.of(2023, 10, 15)
                        ))
                        .build(),
                new Person.PersonBuilder(new Name("James Soh"), new Phone("91000000"),
                        new Email("jamess@example.com"), new Address("Blk 27 Jurong East Street 32 #08-111"),
                        getTagSet("colleagues"))
                        .withIncome(new Income(92000))
                        .withDetails(new Details("Interested in joining a professional networking group"))
                        .withLead(new Lead("HOT"))
                        .addInteraction(new Interaction(
                                "Requested event details", Interaction.Outcome.valueOf("INTERESTED"),
                                LocalDate.of(2023, 8, 22)
                        ))
                        .build(),
                new Person.PersonBuilder(new Name("Isabelle Quek"), new Phone("91234500"),
                        new Email("isabelleq@example.com"), new Address("Blk 34 Telok Blangah Way #10-00"),
                        getTagSet("acquaintances"))
                        .withProfession(new Profession("Dietitian"))
                        .withDetails(new Details("Looking for software for diet planning"))
                        .withLead(new Lead("COLD"))
                        .build(),
                new Person.PersonBuilder(new Name("Benjamin Yeo"), new Phone("91230000"),
                        new Email("benjaminy@example.com"), new Address("Blk 101 Bishan Street 12 #06-03"),
                        getTagSet("gymbuddy")).withTelegram(new TelegramHandle("@benyeo"))
                        .withProfession(new Profession("Personal Trainer"))
                        .withIncome(new Income(86000))
                        .withDetails(new Details("Searching for gym management systems"))
                        .withLead(new Lead("HOT"))
                        .addInteraction(new Interaction(
                                "Discussed system features", Interaction.Outcome.valueOf("INTERESTED"),
                                LocalDate.of(2023, 7, 25)
                        ))
                        .addInteraction(new Interaction(
                                "Attended a product demo", Interaction.Outcome.valueOf("FOLLOWUP_REQUIRED"),
                                LocalDate.of(2023, 8, 15)
                        )).build()
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an interaction set containing the list of strings given.
     */
    public static List<Interaction> getInteractionList(String... strings) {
        return Arrays.stream(strings)
                .map(Interaction::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
