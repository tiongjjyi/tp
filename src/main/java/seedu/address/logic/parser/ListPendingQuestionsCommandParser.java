package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HomeCommand;
import seedu.address.logic.commands.ListPendingQuestionsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.AllPendingQuestionPredicate;

/**
 * Parses input arguments and creates a new ListPendingQuestionsCommand object
 */
public class ListPendingQuestionsCommandParser implements Parser<ListPendingQuestionsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListPendingQuestionsCommand
     * and returns a ListPendingQuestionsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListPendingQuestionsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (trimmedArgs.equalsIgnoreCase("pq")) {
            return new ListPendingQuestionsCommand(new AllPendingQuestionPredicate());
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HomeCommand.MESSAGE_USAGE));
    }
}
