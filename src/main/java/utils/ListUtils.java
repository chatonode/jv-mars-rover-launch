package utils;

import business.environment.Position;
import business.movable.explorer.Rover;
import business.movable.explorer.Rovers;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;


public class ListUtils {
    public static class Rover {
        public static final BiFunction<Rovers, String, Rovers> getFilteredRovers = ((rovers, searchFilter) -> (Rovers) rovers.stream().filter(
                        rover -> rover.getId().toLowerCase().contains(searchFilter)
                                || rover.getProducedBy().toLowerCase().contains(searchFilter)
                                || rover.getProducedYear().toString().toLowerCase().contains(searchFilter)
                )
                .toList()
        );

        public static final BiPredicate<Rovers, Position> checkLandingPositionIsFree = (currentRovers, position) ->
                currentRovers.stream()
                        .noneMatch(currentRover -> currentRover.getCurrentPosition().getX() == position.getX()
                                && currentRover.getCurrentPosition().getY() == position.getY()
                        );
    }
}
