package ga;

import java.util.List;
import java.util.stream.IntStream;

public class GeneticAlgorythm {

    private Data data;

    public GeneticAlgorythm(Data data) {
        this.data = data;
    }

    public Population evolve(Population population) {
        return mutatePopulation(crossoverPopulation(population));
    }

    Population crossoverPopulation(Population population) {
        Population crossoverPopulation = new Population(population.getSchedules().size(), data);
        IntStream.range(0, Driver.NUMB_OF_ELIT_MODELS)
                .forEach(x -> crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x)));

        IntStream.range(Driver.NUMB_OF_ELIT_MODELS, population.getSchedules().size()).forEach(x -> {
            if (Driver.CROSSOVER_POSS > Math.random()) {
                Schedule schedule1 = selectPopulation(population).sortByFitness().getSchedules().get(0);
                Schedule schedule2 = selectPopulation(population).sortByFitness().getSchedules().get(0);
                crossoverPopulation.getSchedules().set(x, crossoverSchedule(schedule1, schedule2));
            } else crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x));
        });
        return crossoverPopulation;
    }

    Population selectPopulation(Population population) {
        Population resPopulation = new Population(Driver.TOURNAMENT_SELECTION_SIZE, data);
        IntStream.range(0, Driver.TOURNAMENT_SELECTION_SIZE).forEach(x -> {
            resPopulation.getSchedules().set(x, population.getSchedules().get((int) (Math.random() * population.getSchedules().size())));
        });
        return resPopulation;
    }

    Population mutatePopulation(Population population) {
        Population mutatePopulation = new Population(population.getSchedules().size(), data);
        List<Schedule> scheduleList = mutatePopulation.getSchedules();
        IntStream.range(0, Driver.NUMB_OF_ELIT_MODELS).forEach(x -> scheduleList.set(x, mutateSchedule(population.getSchedules().get(x))));
        return population;
    }

    Schedule mutateSchedule(Schedule schedule) {
        Schedule scheduleResult = new Schedule(data).initilize();
        IntStream.range(0, schedule.getClasses().size()).forEach(x -> {
            if (Driver.MULATION_POSS > Math.random()) schedule.getClasses().set(x, scheduleResult.getClasses().get(x));
        });
        return schedule;
    }

    Schedule crossoverSchedule(Schedule schedule1, Schedule schedule2) {
        Schedule crossoverSchedule = new Schedule(data).initilize();
        IntStream.range(0, crossoverSchedule.getClasses().size()).forEach(x -> {
            if (Math.random() > 0.5) crossoverSchedule.getClasses().set(x, schedule1.getClasses().get(x));
            else crossoverSchedule.getClasses().set(x, schedule2.getClasses().get(x));
        });
        return crossoverSchedule;
    }
}
