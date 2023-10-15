package seedu.address.testutil;

import seedu.address.model.StudentList;
import seedu.address.model.person.Student;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withCourse("CS2103T")
            .withTags("friends").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withCourse("CS2103T")
            .withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withCourse("CS2103T")
            .withEmail("heinz@example.com").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withCourse("CS2103T")
            .withEmail("cornelia@example.com").withTags("friends").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withCourse("CS2103T")
            .withEmail("werner@example.com").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withCourse("CS2103T")
            .withEmail("lydia@example.com").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withCourse("CS2103T")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withCourse("CS2103T")
            .withEmail("stefan@example.com").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withCourse("CS2103T")
            .withEmail("hans@example.com").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withCourse(VALID_COURSE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withCourse(VALID_COURSE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Students.
     */
    public static StudentList getTypicalStudentList() {
        StudentList sl = new StudentList();
        for (Student Student : getTypicalStudents()) {
            sl.addStudent(Student);
        }
        return sl;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
