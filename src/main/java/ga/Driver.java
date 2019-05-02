package ga;

import domain.Class;

import java.util.List;

public class Driver {

    public static final int POPULATION_SIZE = 100;
    public static final double MULATION_POSS = 0.1;
    public static final double CROSSOVER_POSS = 0.9;
    public static final int TOURNAMENT_SELECTION_SIZE = 5;
    public static final int NUMB_OF_ELIT_MODELS = 30;
    public static final long MAX_PAIRS = 2;

    private int scheduleNumb = 0;
    private int classNumb = 1;
    private Data data;

    public static void main(String[] args) {
        int solutionIteration = 1;
        Driver driver = new Driver();
        driver.data = new Data();
        int generationNumber = 0;
        driver.printAvaliableData();
        Schedule bestSchedule = null;
        System.out.print("Genetation #: " + generationNumber + 1);
        System.out.print("   ga.Schedule # |:                                    ");
        System.out.print("Classes [dept,class,root,instructor,meeting-time]      ");
        System.out.println("                      | Fitness | Conflicts");
        System.out.print("------------------------------------------------------------");
        System.out.println("------------------------------------------------------------");
        GeneticAlgorythm geneticAlgorythm = new GeneticAlgorythm(driver.data);
        Population population = new Population(Driver.POPULATION_SIZE, driver.data).sortByFitness();
        population.getSchedules().forEach(schedule -> {
            System.out.println("      " + driver.scheduleNumb++ +
                    "     | " + schedule + " |  " +
                    String.format("%.5f", schedule.getFitness()) +
                    "  |  " + schedule.getNumbOfConflicts());
        });
        bestSchedule = population.getSchedules().get(0);
        driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
        for (int i = 0; i < 5000; i++) {
            population = geneticAlgorythm.evolve(population).sortByFitness();
            population.getSchedules().forEach(schedule -> {
                System.out.println("      " + driver.scheduleNumb++ +
                        "     | " + schedule + " |  " +
                        String.format("%.5f", schedule.getFitness()) +
                        "  |  " + schedule.getNumbOfConflicts());
            });
            driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
            Schedule schedule = population.getSchedules().get(0);
            if (bestSchedule.getFitness() < schedule.getFitness()) {
                bestSchedule = schedule;
                solutionIteration = i;
            }

            if (bestSchedule.getFitness() == 1) {
                break;
            }

        }
        System.out.println("Best schedule on iteration: " + solutionIteration);
        System.out.println("      " + driver.scheduleNumb++ +
                "     | " + bestSchedule + " |  " +
                String.format("%.5f", bestSchedule.getFitness()) +
                "  |  " + bestSchedule.getNumbOfConflicts());
        driver.printScheduleAsTable(bestSchedule, 1);
    }

    private void printScheduleAsTable(Schedule schedule, int generation) {
        List<Class> classes = schedule.getClasses();
        System.out.print("\n                         ");
        System.out.println("Class #  |  Dept  | Course (max number of sudtents) | Room (Capacity) |   Instructor  (Id)   |  Meeting Time   ");
        System.out.print("                         ");
        System.out.print("--------------------------------------------------------");
        System.out.println("----------------------------------------------------------");
        classes.forEach(x -> {
            int majorIndex = data.getDepartments().indexOf(x.getDepartment());
            int coursesIndex = data.getCourses().indexOf(x.getCourse());
            int roomsIndex = data.getRooms().indexOf(x.getRoom());
            int instructorsIndex = data.getInstructors().indexOf(x.getInstructor());
            int meetingTimeIndex = data.getMeetingTimes().indexOf(x.getMeetingTime());
            System.out.print("                       ");

            System.out.print(String.format("  %1$02d  ", classNumb) + "    |  ");
            System.out.print(String.format("%1$4s", data.getDepartments().get(majorIndex).getName()) + "  |  ");
            System.out.print(String.format("%1$24s", data.getCourses().get(coursesIndex).getName() +
                    " (" + data.getCourses().get(coursesIndex).getNumber() + ", " +
                    x.getCourse().getMaxNumbOfStudents()) + ") " + "     | ");
            System.out.print(String.format("%1$11s", data.getRooms().get(roomsIndex).getNumber() +
                    " (" + x.getRoom().getSeatingCapacity()) + ") " + "   | ");
            System.out.print(String.format("%1$18s", data.getInstructors().get(instructorsIndex).getName() +
                    " (" + data.getInstructors().get(instructorsIndex).getId() + ")") + "   | ");


            System.out.println(data.getMeetingTimes().get(meetingTimeIndex).getTime() + " (" + data.getMeetingTimes().get(meetingTimeIndex).getId() + ")");
            classNumb++;
        });
        System.out.print("                         ");
        System.out.print("--------------------------------------------------------");
        System.out.println("----------------------------------------------------------");
    }

    private void printAvaliableData() {
        System.out.println("Available Departments ==>");
        data.getDepartments().forEach(x -> System.out.println("name: " + x.getName() + " , courses: " + x.getCourses()));

        System.out.println("\n Available Courses ==>");
        data.getCourses().forEach(x -> System.out.println("course #: " + x.getNumber() + " , name: " + x.getName() + " , max # of students: " +
                +x.getMaxNumbOfStudents() + " , instructors: " + x.getInstructors()));

        System.out.println("\n Available Rooms ==>");
        data.getRooms().forEach(x -> System.out.println("room #: " + x.getNumber() + " , max seating capacity: " + x.getSeatingCapacity()));


        System.out.println("\n Available Instructors ==>");
        data.getInstructors().forEach(x -> System.out.println("id:  " + x.getId() + " , name: " + x.getName()));

        System.out.println("\n Available Meeting Times ==>");
        data.getMeetingTimes().forEach(x -> System.out.println("id: " + x.getId() + " , meeting time:" + x.getTime()));
        System.out.print("-------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------");
    }

}
