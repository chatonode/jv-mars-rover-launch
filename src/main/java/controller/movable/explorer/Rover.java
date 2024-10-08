package controller.movable.explorer;

import java.util.function.Predicate;

import utils.ValidationUtils;
import validation.ParameterValidator;
import validation.ParamIsValidMap;

import controller.environment.Position;

public class Rover extends Explorer {
    private final String producedBy;
    private final Integer producedYear;

    protected Rover(String name, Position initialPosition, String producedBy, Integer producedYear) {
        super(name, initialPosition);

        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("producedBy", checkProducedByValidity.test(producedBy));
            put("producedYear", checkProducedYearValidity.test(producedYear));
        }});

        this.producedBy = producedBy;
        this.producedYear = producedYear;
    }

    private final Predicate<String> checkProducedByValidity = ValidationUtils.checkStringValidity;
    private final Predicate<Integer> checkProducedYearValidity = ValidationUtils.checkRoverYearValidity;


    public String getProducedBy() {
        return this.producedBy;
    }

    public Integer getProducedYear() {
        return this.producedYear;
    }
}
