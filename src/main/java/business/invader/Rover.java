package business.invader;

import java.util.function.Predicate;

import utils.ValidationUtils;
import validation.ArgumentValidator;
import validation.IsValidArgumentNameMap;

import common.Position;

public class Rover extends Invader {
    private String producedBy;
    private Integer producedYear;

    public Rover(String name, Position initialPosition, String producedBy, Integer producedYear) {
        super(name, initialPosition);

        ArgumentValidator.validateArgs(new IsValidArgumentNameMap() {{
            put(checkProducedByValidity.test(producedBy), "producedBy");
            put(checkProducedYearValidity.test(producedYear), "producedYear");
        }});

        this.producedBy = producedBy;
        this.producedYear = producedYear;
    }

    private final Predicate<String> checkProducedByValidity = ValidationUtils.checkStringValidity;
    private final Predicate<Integer> checkProducedYearValidity = ValidationUtils.checkRoverYearValidity;

    @Override
    public boolean move(Position nextPosition) {
        return false;
    }

    @Override
    public Position getInitialPosition() {
        return this.initialPosition;
    }

    @Override
    public Position getCurrentPosition() {
        return this.currentPosition;
    }

    public String getProducedBy() {
        return producedBy;
    }

    public Integer getProducedYear() {
        return producedYear;
    }
}
