package seedu.address.logic.parser.internship.role;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.internship.role.AddInternshipRoleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
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


public class AddInternshipRoleParserTest {
    private final AddInternshipRoleParser parser = new AddInternshipRoleParser();

    private final String multiplePrefixMsg = "Multiple values specified for the following single-valued field(s): ";

    private final String invalidPayMsg = "Pay must be a non negative number, e.g. 0, 1600.50";

    @Test
    public void parse_allFieldsPresentSingleTag_success() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer o/accepted  d/description  p/1000.00 l/Singapore t/tag";
        InternshipRole role = new InternshipRole(new Name("Tiktok"),
                new Role("SWE"), new Cycle("Summer"),
                new Description("description"), new Pay(new BigDecimal("1000.00")),
                new ApplicationOutcome(Outcome.ACCEPTED), new Location("Singapore"), Set.of(new Tag("tag")));

        assertParseSuccess(parser, validInput, new AddInternshipRoleCommand(role));
    }

    @Test
    public void parse_allFieldsPresentMultipleTag_success() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer o/ghosted  d/description  p/1000.00 l/Singapore t/tag1 t/tag2";
        InternshipRole role = new InternshipRole(new Name("Tiktok"),
                new Role("SWE"), new Cycle("Summer"),
                new Description("description"), new Pay(new BigDecimal("1000.00")),
                new ApplicationOutcome(Outcome.GHOSTED), new Location("Singapore"),
                Set.of(new Tag("tag1"), new Tag("tag2")));

        assertParseSuccess(parser, validInput, new AddInternshipRoleCommand(role));
    }

    @Test
    public void parseZeroPay_success() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer o/ghosted  d/description  p/0.00 l/Singapore t/tag1 t/tag2";
        InternshipRole role = new InternshipRole(new Name("Tiktok"),
                new Role("SWE"), new Cycle("Summer"),
                new Description("description"), new Pay(new BigDecimal("0.00")),
                new ApplicationOutcome(Outcome.GHOSTED), new Location("Singapore"),
                Set.of(new Tag("tag1"), new Tag("tag2")));

        assertParseSuccess(parser, validInput, new AddInternshipRoleCommand(role));
    }

    @Test
    public void parse_onlyCompulsoryFieldPresent_success() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer";
        InternshipRole role = new InternshipRole(new Name("Tiktok"),
                new Role("SWE"), new Cycle("Summer"),
                new Description(""), new Pay(null),
                new ApplicationOutcome(Outcome.AWAITING), new Location(" "),
                Set.of());

        assertParseSuccess(parser, validInput, new AddInternshipRoleCommand(role));
    }

    @Test
    public void parse_compulsoryAndOutcomeFieldPresent_success() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer o/rejected";
        InternshipRole role = new InternshipRole(new Name("Tiktok"),
                new Role("SWE"), new Cycle("Summer"),
                new Description(""), new Pay(null),
                new ApplicationOutcome(Outcome.REJECTED), new Location(" "),
                Set.of());

        assertParseSuccess(parser, validInput, new AddInternshipRoleCommand(role));
    }

    @Test
    public void parse_compulsoryAndDescriptionFieldPresent_success() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer d/desc";
        InternshipRole role = new InternshipRole(new Name("Tiktok"),
                new Role("SWE"), new Cycle("Summer"),
                new Description("desc"), new Pay(null),
                new ApplicationOutcome(Outcome.AWAITING), new Location(" "),
                Set.of());

        assertParseSuccess(parser, validInput, new AddInternshipRoleCommand(role));
    }

    @Test
    public void parse_compulsoryAndPayFieldPresent_success() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer p/999";
        InternshipRole role = new InternshipRole(new Name("Tiktok"),
                new Role("SWE"), new Cycle("Summer"),
                new Description(""), new Pay(new BigDecimal("999")),
                new ApplicationOutcome(Outcome.AWAITING), new Location(" "),
                Set.of());

        assertParseSuccess(parser, validInput, new AddInternshipRoleCommand(role));
    }

    @Test
    public void parse_compulsoryAndLocationFieldPresent_success() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer l/Singapore";
        InternshipRole role = new InternshipRole(new Name("Tiktok"),
                new Role("SWE"), new Cycle("Summer"),
                new Description(""), new Pay(null),
                new ApplicationOutcome(Outcome.AWAITING), new Location("Singapore"),
                Set.of());

        assertParseSuccess(parser, validInput, new AddInternshipRoleCommand(role));
    }

    @Test
    public void parseDuplicateName_failure() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer o/accepted  d/description  p/1000.00 l/Singapore t/tag";
        assertParseFailure(parser, validInput + " n/name", multiplePrefixMsg + "n/");
    }

    @Test
    public void parseDuplicateRole_failure() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer o/accepted  d/description  p/1000.00 l/Singapore t/tag";
        assertParseFailure(parser, validInput + " r/tag", multiplePrefixMsg + "r/");
    }

    @Test
    public void parseDuplicateCycle_failure() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer o/accepted d/description  p/1000.00 l/Singapore t/tag";
        assertParseFailure(parser, validInput + " c/Winter", multiplePrefixMsg + "c/");
    }

    @Test
    public void parseDuplicateOutcome_failure() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer o/accepted d/description  p/1000.00 l/Singapore t/tag";
        assertParseFailure(parser, validInput + " o/ghosted", multiplePrefixMsg + "o/");
    }

    @Test
    public void parseDuplicateDescription_failure() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer o/accepted d/description  p/1000.00 l/Singapore t/tag";
        assertParseFailure(parser, validInput + " d/desc", multiplePrefixMsg + "d/");
    }

    @Test
    public void parseDuplicatePay_failure() throws ParseException {
        String validInput = " n/Tiktok r/SWE c/Summer o/accepted d/description  p/1000.00 l/Singapore t/tag";
        assertParseFailure(parser, validInput + " p/999.99", multiplePrefixMsg + "p/");
    }

    @Test
    public void parseEmptyName_failure() throws ParseException {
        String validInput = " n/ r/SWE c/Summer o/accepted d/description  p/1000.00 l/Singapore t/tag";
        assertParseFailure(parser, validInput, "Name cannot be empty");
    }

    @Test
    public void parseEmptyRole_failure() throws ParseException {
        String validInput = " n/GovTech r/ c/Summer o/accepted d/description  p/1000.00 l/Singapore t/tag";
        assertParseFailure(parser, validInput, "Role cannot be empty");
    }

    @Test
    public void parseEmptyCycle_failure() throws ParseException {
        String validInput = " n/GovTech  r/SWE c/ o/accepted d/description  p/1000.00 l/Singapore t/tag";
        assertParseFailure(parser, validInput, "Cycle cannot be empty");
    }

    @Test
    public void parseEmptyLocation_failure() throws ParseException {
        String validInput = " n/GovTech  r/SWE c/Summer o/accepted d/description  p/1000.00 l/ t/tag";
        assertParseFailure(parser, validInput, "Location cannot be empty");
    }

    @Test
    public void parseMissingName_failure() throws ParseException {
        String validInput = " r/SWE c/Summer";
        assertParseFailure(parser, validInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddInternshipRoleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseMissingRole_failure() throws ParseException {
        String validInput = " n/Fb c/Summer";
        assertParseFailure(parser, validInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddInternshipRoleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseMissingCycle_failure() throws ParseException {
        String validInput = " n/Fb r/DevOps ";
        assertParseFailure(parser, validInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddInternshipRoleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseInvalidOutcome_failure() throws ParseException {
        String validInput = " n/Fb r/DevOps c/Summer o/Invalid";
        assertParseFailure(parser, validInput,
                "Enter a valid outcome: o/follow-up, ghosted, rejected, offered, accepted, awaiting");
    }

    @Test
    public void parseNegativePay_failure() throws ParseException {
        String validInput = " n/GovTech r/SWE c/Summer p/-1000.00 ";
        assertParseFailure(parser, validInput, invalidPayMsg);
    }

    @Test
    public void parseInvalidPay_failure() throws ParseException {
        String validInput = " n/GovTech r/SWE c/Summer p/.00 ";
        assertParseFailure(parser, validInput, invalidPayMsg);
    }

}
