package domain;

import java.util.Objects;

public class Class {
    private int id;
    private Department department;
    private Course course;
    private Instructor instructor;
    private MeetingTime meetingTime;
    private Room room;

    public Class(int id, Department department, Course course) {
        this.id = id;
        this.department = department;
        this.course = course;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setMeetingTime(MeetingTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public Department getDepartment() {
        return department;
    }

    public Course getCourse() {
        return course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public MeetingTime getMeetingTime() {
        return meetingTime;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return id == aClass.id &&
                Objects.equals(department, aClass.department) &&
                Objects.equals(course, aClass.course) &&
                Objects.equals(instructor, aClass.instructor) &&
                Objects.equals(meetingTime, aClass.meetingTime) &&
                Objects.equals(room, aClass.room);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, department, course, instructor, meetingTime, room);
    }

    @Override
    public String toString() {
        return "{" + department.getName() +
                "," + course.getNumber() +
                "," + room.getNumber()+
                "," + instructor.getId() +
                "," + meetingTime.getId() +
                '}';
    }
}

