package business.invader;

import java.util.function.Predicate;

import utils.ValidationUtils;
import validation.ParameterValidator;
import validation.ParamIsValidMap;

import common.Position;

public class Rover extends Invader {
    private String producedBy;
    private Integer producedYear;

    public Rover(String name, Position initialPosition, String producedBy, Integer producedYear) {
        super(name, initialPosition);

        ParameterValidator.validateArgs(new ParamIsValidMap() {{
            put("producedBy", checkProducedByValidity.test(producedBy));
            put("producedYear", checkProducedYearValidity.test(producedYear));
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
