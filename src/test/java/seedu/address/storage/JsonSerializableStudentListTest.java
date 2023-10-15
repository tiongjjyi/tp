package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.StudentList;
import seedu.address.testutil.TypicalStudents;

public class JsonSerializableStudentListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableStudentListTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsStudentList.json");
    private static final Path INVALID_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("invalidStudentStudentList.json");
    private static final Path DUPLICATE_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentStudentList.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableStudentList dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableStudentList.class).get();
        StudentList addressBookFromFile = dataFromFile.toModelType();
        StudentList typicalPersonsAddressBook = TypicalStudents.getTypicalStudentList();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentList dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENTS_FILE,
                JsonSerializableStudentList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENTS_FILE,
                JsonSerializableStudentList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStudentList.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
