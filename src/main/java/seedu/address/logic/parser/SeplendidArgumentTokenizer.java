package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Tokenizes arguments string of the form: {@code [value] [value]} <br>
 * e.g. {@code [CS2103T] [Software Engineering]}  <br>
 * 1. An argument's value cannot be an empty string.<br>
 * 2. Leading and trailing whitespaces of an argument value will be discarded.<br>
 * e.g. {@code [CS2103T]    [Software Engineering]  } will work.
 * 3. Otherwise, the format must be adhered to.
 */
public class SeplendidArgumentTokenizer {

    /**
     * Tokenizes an arguments string and returns an {@code SeplendidArgumentMap} object that maps
     * parameters to their respective argument values. The parameters specified will match the arguments
     * given in the order in which they exist in the {@code argsString}.<br>
     * If number of arguments < number of parameters, the extra parameters will have empty string values
     * in the SeplendidArgumentMap. They will return Optional#empty() through SeplendidArgumentMap#getValue.
     * If number of parameters < number of arguments, the extra arguments will be ignored.
     * The above 2 cases will subsequently lead to ParseException in their respective parsers.
     *
     * @param argsString Arguments string of the form: {@code [args1] [args2]...}
     * @param parameters Parameters to tokenize the arguments string with
     * @return SeplendidArgumentMap object that maps parameters to their arguments
     */
    public static SeplendidArgumentMap tokenize(String argsString, SeplendidParameter... parameters) {
        List<ParameterPosition> positions = findAllParameterPositions(argsString, parameters);
        return extractArguments(argsString, positions);
    }

    /**
     * Finds all zero-based parameter positions in the given arguments string.
     * Assumes: areValuesEnclosedAndNonEmpty()
     *
     * @param argsString Arguments string of the form: {@code [args1] [args2]...}
     * @param parameters Parameters to find in the arguments string
     * @return List of zero-based parameter positions in the given arguments string
     */
    private static List<ParameterPosition> findAllParameterPositions(String argsString,
                                                                     SeplendidParameter... parameters) {
        List<Integer> openSquareBracketPositions = new ArrayList<>();

        int openSquareBracketPosition = pollNextOpenSquareBracket(argsString, 0);
        while (openSquareBracketPosition != -1) {
            openSquareBracketPositions.add(openSquareBracketPosition);
            openSquareBracketPosition = pollNextOpenSquareBracket(argsString, openSquareBracketPosition + 1);
        }

        return IntStream.range(0, parameters.length)
                .mapToObj(i -> formParameterPosition(parameters[i], openSquareBracketPositions, i))
                .collect(Collectors.toList());
    }

    private static int pollNextOpenSquareBracket(String argsString, int fromIndex) {
        int openSquareBracketIndex = argsString.indexOf("[", fromIndex);
        return openSquareBracketIndex == -1
                ? -1
                : openSquareBracketIndex;
    }

    private static ParameterPosition formParameterPosition(SeplendidParameter param,
                                                           List<Integer> openSquareBracketPositions,
                                                           int index) {
        if (index >= openSquareBracketPositions.size()) {
            return new ParameterPosition(param, -1);
        } else {
            return new ParameterPosition(param, openSquareBracketPositions.get(index));
        }
    }

    private static SeplendidArgumentMap extractArguments(String argsString,
                                                         List<ParameterPosition> parameterPositions) {
        SeplendidArgumentMap parameterToArgMap = new SeplendidArgumentMap();
        parameterPositions.forEach(parameterPosition -> {
            assert ParserUtil.areValuesEnclosedAndNonEmpty(argsString) == ParserUtil
                    .AreValuesEnclosedAndNonEmptyResult.SUCCESS;
            // We hold the assumption that arguments are non-empty and enclosed.
            // Assumes: areValuesEnclosedAndNonEmpty()
            int startPosition = parameterPosition.getStartPosition();
            SeplendidParameter param = parameterPosition.getParameter();
            if (startPosition == -1) {
                // Defensive programming
                parameterToArgMap.put(param, "");
            } else {
                int closingSquareBracketPosition = argsString.indexOf("]", startPosition);
                String argumentValue = argsString.substring(startPosition + 1, closingSquareBracketPosition);
                parameterToArgMap.put(param, argumentValue);
            }
        });
        return parameterToArgMap;
    }

    /**
     * Represents a parameter's position in an arguments string.
     */
    private static class ParameterPosition {
        private int startPosition;
        private final SeplendidParameter parameter;

        ParameterPosition(SeplendidParameter parameter, int startPosition) {
            this.parameter = parameter;
            this.startPosition = startPosition;
        }

        int getStartPosition() {
            return startPosition;
        }

        SeplendidParameter getParameter() {
            return parameter;
        }
    }

}
