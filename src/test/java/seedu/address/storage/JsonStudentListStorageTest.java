package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.HOON;
import static seedu.address.testutil.TypicalStudents.IDA;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyStudentList;
import seedu.address.model.StudentList;

public class JsonStudentListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonStudentListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readStudentList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readStudentList(null));
    }

    private java.util.Optional<ReadOnlyStudentList> readStudentList(String filePath) throws Exception {
        return new JsonStudentListStorage(Paths.get(filePath)).readStudentList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readStudentList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readStudentList("notJsonFormatStudentList.json"));
    }

    @Test
    public void readStudentList_invalidPersonStudentList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readStudentList("invalidPersonStudentList.json"));
    }

    @Test
    public void readStudentList_invalidAndValidPersonStudentList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readStudentList("invalidAndValidPersonStudentList.json"));
    }

    @Test
    public void readAndSaveStudentList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempStudentList.json");
        StudentList original = getTypicalStudentList();
        JsonStudentListStorage jsonStudentListStorage = new JsonStudentListStorage(filePath);

        // Save in new file and read back
        jsonStudentListStorage.saveStudentList(original, filePath);
        ReadOnlyStudentList readBack = jsonStudentListStorage.readStudentList(filePath).get();
        assertEquals(original, new StudentList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonStudentListStorage.saveStudentList(original, filePath);
        readBack = jsonStudentListStorage.readStudentList(filePath).get();
        assertEquals(original, new StudentList(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonStudentListStorage.saveStudentList(original); // file path not specified
        readBack = jsonStudentListStorage.readStudentList().get(); // file path not specified
        assertEquals(original, new StudentList(readBack));

    }

    @Test
    public void saveStudentList_nullStudentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStudentList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code studentList} at the specified {@code filePath}.
     */
    private void saveStudentList(ReadOnlyStudentList studentList, String filePath) {
        try {
            new JsonStudentListStorage(Paths.get(filePath))
                    .saveStudentList(studentList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveStudentList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStudentList(new StudentList(), null));
    }
}
