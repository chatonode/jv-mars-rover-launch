package business.movable.explorer;

import java.util.UUID;
import java.util.function.Predicate;

import business.movable.Movable;
import validation.ParamIsValidMap;
import validation.ParameterValidator;
import utils.ValidationUtils;

import business.environment.Position;

public abstract class Explorer implements Movable {
    protected final String id;
    protected final String name;
    protected final Position initialPosition;
    protected Position currentPosition;

    public Explorer(String name, Position initialPosition) {
        ParameterValidator.validateArgs(new ParamIsValidMap() {{
            put("name", checkNameValidity.test(name));
            put("initialPosition", checkInitialPositionValidity.test(initialPosition));
        }});

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.initialPosition = initialPosition;
        this.currentPosition = this.initialPosition;

        this.printLandingMessage();
    }

    protected final Predicate<String> checkNameValidity = ValidationUtils.checkStringValidity;
    protected final Predicate<Position> checkInitialPositionValidity = ValidationUtils.checkPositionValidity;

    protected void printLandingMessage() {
        System.out.printf("%s (%s) is landed in these coordinates on the plateau X:%d - Y:%d, facing %s direction.\n",
                this.name,
                this.getClass().getSimpleName(),
                this.initialPosition.getX(),
                this.initialPosition.getY(),
                this.initialPosition.getFacingDirection()
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Position getInitialPosition() {
        return initialPosition;
    }

    @Override
    public Position getCurrentPosition() {
        return currentPosition;
    }
}
