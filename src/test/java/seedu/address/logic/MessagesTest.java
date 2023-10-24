package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Student;
import seedu.address.testutil.StudentBuilder;

public class MessagesTest {
    @Test
    void getErrorMessageForDuplicatePrefixes() {
        Prefix prefix1 = new Prefix("n/");
        Prefix prefix2 = new Prefix("c/");

        Prefix[] duplicatePrefixes = {prefix1, prefix2, prefix1};

        String errorMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicatePrefixes);

        String expectedErrorMessage = Messages.MESSAGE_DUPLICATE_FIELDS + "n/ c/";
        assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    void format() {
        Student student = new StudentBuilder().build();
        assertEquals(Messages.format(student), "Amy Bee; Email: amy@gmail.com; Tags: ");
    }
}
