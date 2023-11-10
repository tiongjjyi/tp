package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PENDING_QUESTION;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.student.Student;

/**
 * Finds and lists all students in the class whose pending question field is not empty.
 */
public class ListPendingQuestionsCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all students with non-empty pending question fields\n"
            + "Parameters: " + PREFIX_PENDING_QUESTION + "\n"
            + "Usage: list pq/";



    private final Predicate<Student> predicate;

    /**
     * Constructs a ListPendingQuestionsCommand with the required predicate for filtering by pending questions.
     *
     * @param predicate A predicate that filters entities based on pending questions.
     */
    public ListPendingQuestionsCommand(Predicate <Student> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        StageManager stageManager = StageManager.getInstance();
        Course course = stageManager.getCurrentCourse();

        course.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, course.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListPendingQuestionsCommand)) {
            return false;
        }

        ListPendingQuestionsCommand otherListPendingQuestionsCommand = (ListPendingQuestionsCommand) other;

        return predicate.equals(otherListPendingQuestionsCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
