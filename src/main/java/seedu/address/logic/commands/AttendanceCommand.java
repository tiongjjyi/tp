package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Student;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
/**
 * Marks the attendance of a student identified by index.
 */
public class AttendanceCommand extends Command {

    public static final String COMMAND_WORD = "attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance of the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_ATTENDANCE_SUCCESS = "Marked attendance for Student: %1$s";

    private final Index targetIndex;
    private final Attendance isPresent;

    public AttendanceCommand(Index targetIndex, Attendance isPresent) {
        this.targetIndex = targetIndex;
        this.isPresent = isPresent;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        // Mark the attendance for the student at the specified index
        Student studentToEdit = lastShownList.get(targetIndex.getZeroBased());
        Student editedPerson = new Student(studentToEdit.getName(), studentToEdit.getCourse(), studentToEdit.getEmail(),
                studentToEdit.getRemark(), studentToEdit.getTags(), isPresent); // Implement this method in the Student class
        model.setStudent(studentToEdit, editedPerson);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Student personToEdit) {
        //String message = !isPresent.isPresent().isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format("Success!", personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendanceCommand)) {
            return false;
        }

        AttendanceCommand otherAttendanceCommand = (AttendanceCommand) other;
        return targetIndex.equals(otherAttendanceCommand.targetIndex);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + targetIndex;
    }
}
