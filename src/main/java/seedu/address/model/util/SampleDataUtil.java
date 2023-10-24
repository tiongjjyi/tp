package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyStudentList;
import seedu.address.model.StudentList;
import seedu.address.model.person.*;
import seedu.address.model.tag.StudentRank;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code StudentList} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Course("CS2103T"), new Email("alexyeoh@u.nus.edu"), EMPTY_REMARK,
                    getTagSet(StudentRank.GOOD), new Attendance(true)),
            new Student(new Name("Bernice Yu"), new Course("CS2103T"), new Email("berniceyu@u.nus.edu"),
                    EMPTY_REMARK, getTagSet(StudentRank.POOR), new Attendance(true)),
            new Student(new Name("Charlotte Oliveiro"), new Course("CS2103T"), new Email("charlotte@u.nus.edu"),
                    EMPTY_REMARK, getTagSet(StudentRank.POOR), new Attendance(true)),
            new Student(new Name("David Li"), new Course("CS2103T"), new Email("lidavid@u.nus.edu"), EMPTY_REMARK,
                    getTagSet(StudentRank.AVERAGE), new Attendance(true)),
            new Student(new Name("Irfan Ibrahim"), new Course("CS2103T"), new Email("irfan@u.nus.edu"),
                    EMPTY_REMARK, getTagSet(StudentRank.GOOD), new Attendance(true)),
            new Student(new Name("Roy Balakrishnan"), new Course("CS2103T"), new Email("royb@u.nus.edu"),
                    EMPTY_REMARK, getTagSet(StudentRank.GOOD), new Attendance(true))
        };
    }

    public static ReadOnlyStudentList getSampleStudentList() {
        StudentList sampleSl = new StudentList();
        for (Student sampleStudent : getSampleStudents()) {
            sampleSl.addStudent(sampleStudent);
        }
        return sampleSl;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(StudentRank... ranking) {
        return Arrays.stream(ranking)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
