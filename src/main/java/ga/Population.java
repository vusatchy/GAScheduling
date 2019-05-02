package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Population {
    private List<Schedule> schedules;

    public Population(int size, Data data) {
        schedules = new ArrayList<>(size);
        IntStream.range(0, size).forEach(x -> schedules.add(new Schedule(data).initilize()));
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public Population sortByFitness() {
        schedules.sort((schedules1, schedules2) -> {
            int returnValue = 0;
            if (schedules1.getFitness() > schedules2.getFitness()) returnValue = -1;
            else if (schedules1.getFitness() < schedules2.getFitness()) returnValue = 1;
            return returnValue;
        });
        return this;
    }
}
