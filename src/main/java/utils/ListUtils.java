package utils;

import business.movable.explorer.Rovers;

import java.util.function.BiFunction;


public class ListUtils {
    public static class Rover {
        public static final BiFunction<Rovers, String, Rovers> getFilteredRovers = ((rovers, searchFilter) -> (Rovers) rovers.stream().filter(
                        rover -> rover.getId().toLowerCase().contains(searchFilter)
                                || rover.getProducedBy().toLowerCase().contains(searchFilter)
                                || rover.getProducedYear().toString().toLowerCase().contains(searchFilter)
                )
                .toList()
        );
    }
}
