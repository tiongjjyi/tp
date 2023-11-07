package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalCourses.getTypicalCourses;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.person.SortCriteria;
import seedu.address.logic.parser.StageManager;
import seedu.address.testutil.SortCriteriaBuilder;

class SortCommandTest {

    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void constructor_nullSortCriteria_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null));
    }

    @Test
    void execute_nameSortCriteriaAcceptedByModel_sortSuccess() {
        SortCriteria validSortCriteria = new SortCriteriaBuilder(SortCriteria.Field.NAME).build();
        Course validCourse = getTypicalCourses().get(1);

        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);

        CommandResult commandResult = new SortCommand(validSortCriteria).execute(model);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, Messages.format(validSortCriteria));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    void execute_tagSortCriteriaAcceptedByModel_sortSuccess() {
        SortCriteria validSortCriteria = new SortCriteriaBuilder(SortCriteria.Field.TAG).build();
        Course validCourse = getTypicalCourses().get(1);

        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);

        CommandResult commandResult = new SortCommand(validSortCriteria).execute(model);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, Messages.format(validSortCriteria));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    void execute_invalidSortCriteria_throwsIllegalValueException() {
        String invalidSortCriteria = "invalid";
        assertThrows(IllegalValueException.class, () -> ParserUtil.parseSortCriteria(invalidSortCriteria));
    }


    @Test
    void equals() {
        SortCriteria validNameSortCriteria = new SortCriteriaBuilder(SortCriteria.Field.NAME).build();
        SortCriteria validTagSortCriteria = new SortCriteriaBuilder(SortCriteria.Field.TAG).build();
        final SortCommand standardCommand = new SortCommand(validNameSortCriteria);

        // same values -> returns true
        SortCommand commandWithSameValues = new SortCommand(validNameSortCriteria);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different sort criteria -> returns false
        assertFalse(standardCommand.equals(new SortCommand(validTagSortCriteria)));
    }

    @Test
    void toStringMethod() {
        SortCriteria validSortCriteria = new SortCriteriaBuilder(SortCriteria.Field.NAME).build();
        SortCommand sortCommand = new SortCommand(validSortCriteria);
        String expected = SortCommand.class.getCanonicalName() + "{sort criteria=" + validSortCriteria + "}";
        assertEquals(expected, sortCommand.toString());
    }
}
