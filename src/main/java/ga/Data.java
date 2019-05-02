package ga;

import domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {

    private List<Room> rooms;
    private List<Instructor> instructors;
    private List<Course> courses;
    private List<Department> departments;
    private List<MeetingTime> meetingTimes;
    private int numberOfClasses = 0;


    public Data() {
        initialize();
    }

    private Data initialize() {

        Room room1 = new Room("R1", 25);
        Room room2 = new Room("R2", 45);
        Room room3 = new Room("R3", 30);
        Room room4 = new Room("R4", 35);
        rooms = new ArrayList<>(Arrays.asList(room1, room2, room3, room4));

        MeetingTime meetingTime1 = new MeetingTime("MT1", "09:00 - 10:00");
        MeetingTime meetingTime2 = new MeetingTime("MT2", "11:00 - 12:00");
        meetingTimes = new ArrayList<>(Arrays.asList(meetingTime1, meetingTime2));

        Instructor chapko = new Instructor("I1", "Chapko");
        Instructor garasym = new Instructor("I2", "Garasym");
        Instructor borachok = new Instructor("I3", "Borachok");
        Instructor mysychuk = new Instructor("I4", "Musychyk");
        instructors = new ArrayList<>(Arrays.asList(chapko, garasym, borachok, mysychuk));

        Course lir = new Course("C1", "LIR", 25, new ArrayList<>(Arrays.asList(chapko, borachok)));
        Course ml = new Course("C2", "ML", 35, new ArrayList<>(Arrays.asList(mysychuk)));
        Course numMethods = new Course("C3", "Num Methods", 25, new ArrayList<>(Arrays.asList(chapko, borachok)));
        Course programming = new Course("C4", "Programming", 30, new ArrayList<>(Arrays.asList(garasym)));
        Course genAlg = new Course("C5", "Gen Alg.", 35, new ArrayList<>(Arrays.asList(borachok)));
        Course cryptology = new Course("C6", "Cryptology", 45, new ArrayList<>(Arrays.asList(garasym, mysychuk)));
        Course os = new Course("C7", "OS", 45, new ArrayList<>(Arrays.asList(garasym)));
        courses = new ArrayList<>(Arrays.asList(lir, ml, numMethods, programming, genAlg, cryptology, os));

        Department department1 = new Department("MATH", new ArrayList<>(Arrays.asList(lir, numMethods)));
        Department department2 = new Department("PROG", new ArrayList<>(Arrays.asList(programming, os, cryptology)));
        Department department3 = new Department("ML", new ArrayList<>(Arrays.asList(ml, genAlg)));
        departments = new ArrayList<>(Arrays.asList(department1, department2, department3));
        departments.forEach(x -> numberOfClasses += x.getCourses().size());

        return this;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<MeetingTime> getMeetingTimes() {
        return meetingTimes;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }
}

