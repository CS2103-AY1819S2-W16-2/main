package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PinSelectCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new PinSelectCommand object
 */
public class PinSelectCommandParser implements Parser<PinSelectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PinSelectCommand
     * and returns an PinSelectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PinSelectCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new PinSelectCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PinSelectCommand.MESSAGE_USAGE), pe);
        }
    }
}
