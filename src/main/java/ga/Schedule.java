package ga;

import domain.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Schedule {

    private List<Class> classes;
    private boolean isFitnessChanged = true;
    private double fitness = -1;
    private Data data;
    private int classNumb = 0;
    private int numbOfConflicts = 0;

    public Schedule(Data data) {
        this.data = data;
        classes = new ArrayList<>(data.getNumberOfClasses());
    }

    public Schedule initilize() {
        new ArrayList<>(data.getDepartments()).forEach(department -> {
            department.getCourses().forEach(course -> {
                Class newClass = new Class(classNumb++, department, course);
                newClass.setMeetingTime(data.getMeetingTimes().get((int) (data.getMeetingTimes().size() * Math.random())));
                newClass.setRoom(data.getRooms().get((int) (data.getRooms().size() * Math.random())));
                newClass.setInstructor(data.getInstructors().get((int) (data.getInstructors().size() * Math.random())));
                classes.add(newClass);
            });
        });
        return this;
    }

    private double calculateFitness() {
        numbOfConflicts = 0;
        classes.forEach(x -> {
            if (x.getRoom().getSeatingCapacity() < x.getCourse().getMaxNumbOfStudents()) numbOfConflicts++;
            if (!x.getCourse().getInstructors().contains(x.getInstructor())) numbOfConflicts++;
            if (hasRoomColissions(x)) numbOfConflicts++;
        });

        //менше 3 пар
        classes.stream()
                .map(Class::getInstructor)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k, v) -> {
                    if (v > Driver.MAX_PAIRS) {
                        numbOfConflicts++;
                    }
                });


        return 1 / (double) (numbOfConflicts + 1);
    }

    private boolean hasRoomColissions(Class clazz) {
        boolean result = false;
        for (Class clazzElemet : classes) {
            if (!clazzElemet.equals(clazz)) {
                result = clazz.getRoom().equals(clazzElemet.getRoom())
                        && clazz.getMeetingTime().equals(clazzElemet.getMeetingTime());
                if (result) {
                    return result;
                }
            }
        }
        return false;
    }

    public List<Class> getClasses() {
        isFitnessChanged = true;
        return classes;
    }

    public double getFitness() {
        if (isFitnessChanged) {
            fitness = calculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }


    public Data getData() {
        return data;
    }

    public int getNumbOfConflicts() {
        return numbOfConflicts;
    }

    @Override
    public String toString() {
        String result = new String();
        for (int x = 0; x < classes.size() - 1; x++) result += classes.get(x) + ",";
        result += classes.get(classes.size() - 1);
        return result;
    }
}
