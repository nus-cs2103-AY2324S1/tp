package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.CustomSet;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.ContainsAllPredicate;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Salary;
import seedu.address.model.name.EmployeeName;


public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_returnsFilterFunction() {
        String userInput = " " + PREFIX_DEPARTMENT;
        ContainsAllPredicate predicate = new ContainsAllPredicate(new CustomSet<>(), new HashSet<>(), new HashSet<>(),
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        String userInput = NAME_DESC_BOB;
        Set<EmployeeName> nameSet = new HashSet<>();
        nameSet.add(new EmployeeName(VALID_NAME_BOB));
        ContainsAllPredicate predicate = new ContainsAllPredicate(nameSet, new HashSet<>(), new HashSet<>(),
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        userInput = ADDRESS_DESC_AMY;
        Set<Address> addressSet = new HashSet<>();
        addressSet.add(new Address(VALID_ADDRESS_AMY));
        predicate = new ContainsAllPredicate(new HashSet<>(), new HashSet<>(), new HashSet<>(),
                addressSet, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_moreThanOneArg_returnsFilterCommand() {
        String userInput = NAME_DESC_AMY + NAME_DESC_BOB;
        Set<EmployeeName> nameSet = new HashSet<>();
        nameSet.add(new EmployeeName(VALID_NAME_BOB));
        nameSet.add(new EmployeeName(VALID_NAME_AMY));
        ContainsAllPredicate predicate = new ContainsAllPredicate(nameSet, new HashSet<>(),
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
                new HashSet<>(), new HashSet<>());
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_validSalaryArgs_returnsFilterCommand() {
        String userInput = " s/4000";
        Salary salary = new Salary("4000");
        Set<Salary> salarySet = new CustomSet<>();
        salarySet.add(salary);
        ContainsAllPredicate predicate = new ContainsAllPredicate(new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), new CustomSet<>(), salarySet, new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), new CustomSet<>());
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        userInput = " s/8000";
        salary = new Salary("8000");
        salarySet = new CustomSet<>();
        salarySet.add(salary);
        predicate = new ContainsAllPredicate(new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), new CustomSet<>(), salarySet, new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), new CustomSet<>());
        //@@author kenvynKwek
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_validSalaryAndPhoneArgs_returnsFilterCommand() {
        String userInput = " p/ 93885485 s/ 2000";
        Salary salary = new Salary("2000");
        Set<Salary> salarySet = new CustomSet<>();
        salarySet.add(salary);
        Phone phone = new Phone("93885485");
        Set<Phone> phoneSet = new CustomSet<>();
        phoneSet.add(phone);
        ContainsAllPredicate predicate = new ContainsAllPredicate(new CustomSet<>(), phoneSet,
                new CustomSet<>(), new CustomSet<>(), salarySet, new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), new CustomSet<>());
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }
}
//@@author
