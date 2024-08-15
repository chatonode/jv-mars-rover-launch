package business.invader;

import common.Position;
import common.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

public class Rover extends Invader {
    private String producedBy;
    private Integer producedYear;

    public Rover(String name, Position initialPosition, String producedBy, Integer producedYear) {
        super(name, initialPosition);

        validateAdditionals(producedBy, producedYear);

        this.producedBy = producedBy;
        this.producedYear = producedYear;
    }

    private void validateAdditionals(String producedBy, Integer producedYear) {
        boolean isProducedByValid = ValidationUtils.checkStringValidity.test(producedBy);
        boolean isProducedYearValid = ValidationUtils.checkRoverYearValidity.test(producedYear);

        List<String> errArgs = new ArrayList<>();
        if (isProducedByValid) errArgs.add("producedBy");
        if (!isProducedYearValid) errArgs.add("producedYear");

        super.validateFinally(!isProducedByValid || !isProducedYearValid, errArgs);
    }

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
