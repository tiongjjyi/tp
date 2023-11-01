package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.course.Course;

/**
 * Clears the student list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Student list has been cleared.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        StageManager stageManager = StageManager.getCurrent();
        Course course = stageManager.getCurrentCourse();

        course.clearStudentList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
