package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.stagemanager.StageManager;
import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.person.SortCriteria;

/**
 * Sorts the student list based on specified sort criteria.
 * Sort criteria can only be tag or name.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted students by %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the student list using the criteria "
            + "(tag/name) specified. "
            + "Parameters: "
            + PREFIX_SORT + "CRITERIA\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORT + "tag";

    private final SortCriteria sortCriteria;

    /**
     * Constructs a SortCommand with the specified sort criteria for sorting.
     *
     * @param sortCriteria The criteria used to sort the student list.
     */
    public SortCommand(SortCriteria sortCriteria) {
        requireNonNull(sortCriteria);
        this.sortCriteria = sortCriteria;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        StageManager stageManager = StageManager.getInstance();
        Course course = stageManager.getCurrentCourse();

        course.sortStudentsBy(sortCriteria);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(sortCriteria)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherSortCommand = (SortCommand) other;
        return sortCriteria.equals(otherSortCommand.sortCriteria);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("sort criteria", sortCriteria)
                .toString();
    }
}
