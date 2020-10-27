package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ContactTaskTagCommand;
import seedu.address.logic.commands.ContactTaskTagCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ContactTaskTagCommand.EditTaskDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

public class ContactTaskTagParser implements Parser<ContactTaskTagCommand> {
    public ContactTaskTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] splitArgs = args.trim().split(" ", 1);
        if (splitArgs.length < 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ContactTaskTagCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + splitArgs[0], PREFIX_TAG,
                PREFIX_CONTACT_INDEX, PREFIX_TASK_INDEX);

        List<String> tag = argMultimap.getAllValues(PREFIX_TAG);
        if (tag.size() < 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ContactTaskTagCommand.MESSAGE_USAGE));
        }
        Index contactIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT_INDEX).get());
        Index taskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK_INDEX).get());

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setTags(ParserUtil.parseTags(tag));

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        editTaskDescriptor.setTags(ParserUtil.parseTags(tag));

        return new ContactTaskTagCommand(contactIndex, taskIndex, editPersonDescriptor, editTaskDescriptor);
    }
}
