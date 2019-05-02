package domain;

import java.util.List;
import java.util.Objects;

public class Course {

    private String number;

    private String name;

    private int maxNumbOfStudents;

    private List<Instructor> instructors;

        public Course(String number, String name, int maxNumbOfStudents, List<Instructor> instructors) {
        this.number = number;
        this.name = name;
        this.maxNumbOfStudents = maxNumbOfStudents;
        this.instructors = instructors;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getMaxNumbOfStudents() {
        return maxNumbOfStudents;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return maxNumbOfStudents == course.maxNumbOfStudents &&
                Objects.equals(number, course.number) &&
                Objects.equals(name, course.name) &&
                Objects.equals(instructors, course.instructors);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, name, maxNumbOfStudents, instructors);
    }

    @Override
    public String toString() {
        return name;
    }
}
