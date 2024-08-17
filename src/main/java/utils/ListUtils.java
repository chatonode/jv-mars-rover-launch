package utils;


import business.movable.explorer.Rover;

import java.util.List;
import java.util.function.BiFunction;


public class ListUtils {
    public static class Rover {
        public static final BiFunction<List<business.movable.explorer.Rover>, String, List<business.movable.explorer.Rover>> getFilteredRovers = ((rovers, searchFilter) -> rovers.stream().filter(
                        rover -> rover.getId().toLowerCase().contains(searchFilter)
                                || rover.getProducedBy().toLowerCase().contains(searchFilter)
                                || rover.getProducedYear().toString().toLowerCase().contains(searchFilter)
                )
                .toList()
        );
    }
}
