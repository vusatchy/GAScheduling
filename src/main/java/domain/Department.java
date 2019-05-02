package domain;

import java.util.List;
import java.util.Objects;

public class Department {
    private String name;
    private List<Course> courses;

    public Department(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(courses, that.courses);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, courses);
    }
}
