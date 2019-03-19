package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Remark;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

public class RemarkCommand extends Command {
    public static final String COMMAND_WORD = "remark";
    private final Index index;
    private final Remark remark;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": ADD Remarks "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 ";
    public static final String MESSAGE_SUCCESS = COMMAND_WORD + ": Remark Added"
            + "Example: " + COMMAND_WORD + " 1 ";
    public static final String MESSAGE_NO_REMARK = COMMAND_WORD + ": ADD Remarks "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 ";
    /**
     * @param index of the person in the filtered person list to edit
     * @param remark details to edit the person with
     */
    public RemarkCommand(Index index, String remark) {
        requireNonNull(index);
        requireNonNull(remark);

        this.index = index;
        this.remark = new Remark(remark);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasRemark(remark)) {
            throw new CommandException(MESSAGE_NO_REMARK);
        }

        model.addRemark(remark);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, remark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemarkCommand // instanceof handles nulls
                && remark.equals(((RemarkCommand) other).remark));
    }
}
