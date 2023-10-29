package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.CourseList;
import seedu.address.model.Model;

/**
 * Clears the course list.
 */
public class ClearCourseCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Course list has been cleared";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setCourseList(new CourseList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
