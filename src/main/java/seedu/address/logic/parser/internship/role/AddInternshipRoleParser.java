package seedu.address.logic.parser.internship.role;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_CYCLE;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_OUTCOME;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_PAY;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.internship.role.AddInternshipRoleCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.common.Prefix;
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

/**
 * Parser to create an InternshipRole from the given input
 */
public class AddInternshipRoleParser implements Parser<AddInternshipRoleCommand> {
    @Override
    public AddInternshipRoleCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ROLE, PREFIX_CYCLE,
                PREFIX_DESCRIPTION, PREFIX_PAY, PREFIX_OUTCOME, PREFIX_LOCATION, PREFIX_TAG);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_NAME, PREFIX_ROLE, PREFIX_CYCLE)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddInternshipRoleCommand.MESSAGE_USAGE));
        }

        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ROLE, PREFIX_CYCLE,
                PREFIX_DESCRIPTION, PREFIX_PAY, PREFIX_OUTCOME, PREFIX_LOCATION);

        Name name = ParserUtil.parseName(argumentMultimap.getValue(PREFIX_NAME).get());

        Role role = ParserUtil.parseRole(argumentMultimap.getValue(PREFIX_ROLE).get());

        Cycle cycle = ParserUtil.parseCycle(argumentMultimap.getValue(PREFIX_CYCLE).get());

        Description description = ParserUtil.parseDescription(argumentMultimap.getValue(PREFIX_DESCRIPTION)
                .orElse(""));


        Pay pay = argumentMultimap.getValue(PREFIX_PAY).isEmpty()
                ? new Pay(null)
                : ParserUtil.parsePay(argumentMultimap.getValue(PREFIX_PAY).get());

        ApplicationOutcome outcome = argumentMultimap.getValue(PREFIX_OUTCOME).isEmpty()
                ? new ApplicationOutcome(Outcome.AWAITING)
                : ParserUtil.parseApplicationOutcome(argumentMultimap.getValue(PREFIX_OUTCOME).get());

        // To be looked through again
        Location location = argumentMultimap.getValue(PREFIX_LOCATION).isEmpty()
                ? new Location(" ")
                : ParserUtil.parseLocation(argumentMultimap.getValue(PREFIX_LOCATION).get());

        Set<Tag> tagList = ParserUtil.parseTags(argumentMultimap.getAllValues(PREFIX_TAG));

        InternshipRole internshipRole = new InternshipRole(name, role, cycle, description,
                pay, outcome, location, tagList);

        return new AddInternshipRoleCommand(internshipRole);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
