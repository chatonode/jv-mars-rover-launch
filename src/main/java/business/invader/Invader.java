package business.invader;

import java.util.UUID;
import java.util.function.Predicate;

import validation.IsValidArgumentNameMap;
import validation.ArgumentValidator;
import utils.ValidationUtils;

import common.Movable;
import common.Position;


public abstract class Invader implements Movable {
    protected final String id;
    protected final String name;
    protected final Position initialPosition;
    protected Position currentPosition;

    public Invader(String name, Position initialPosition) {
        ArgumentValidator.validateArgs(new IsValidArgumentNameMap() {{
            put(checkNameValidity.test(name), "name");
            put(checkInitialPositionValidity.test(initialPosition), "initialPosition");
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
}
