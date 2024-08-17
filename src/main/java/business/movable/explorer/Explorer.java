package business.movable.explorer;

import java.util.UUID;
import java.util.function.Predicate;

import business.movable.Movable;
import common.enums.OperationResult;
import validation.ParamIsValidMap;
import validation.ParameterValidator;
import utils.ValidationUtils;

import business.orchestrator.Position;

public abstract class Explorer implements Movable {
    protected final String id;
    protected final String name;
    protected final Position initialPosition;
    protected Position currentPosition;

    public Explorer(String name, Position initialPosition) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("name", checkNameValidity.test(name));
            put("initialPosition", checkInitialPositionValidity.test(initialPosition));
        }});

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.initialPosition = initialPosition;
        this.currentPosition = this.initialPosition;

        this.printCreationMessage();
    }

    protected static final Predicate<String> checkNameValidity = ValidationUtils.checkStringValidity;
    protected static final Predicate<Position> checkPositionValidity = position -> position != null;
    protected static final Predicate<Position> checkInitialPositionValidity = checkPositionValidity;
    protected static final Predicate<Position> checkNextPositionValidity = checkPositionValidity;

    protected void printCreationMessage() {
        String creationMessage = String.format("%s: %s (%s) | Initial Coordinates: %s",
                OperationResult.CREATED,
                this.name,
                this.getClass().getSimpleName(),
                this.initialPosition.toString());
        System.out.println(creationMessage);
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

    @Override
    public final boolean moveTo(Position nextPosition) {
        ParameterValidator.validateParams(new ParamIsValidMap() {{
            put("nextPosition", checkNextPositionValidity.test(nextPosition));
        }});

        this.currentPosition = nextPosition;
        return true;
    }
}
