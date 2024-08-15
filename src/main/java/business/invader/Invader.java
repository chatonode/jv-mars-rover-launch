package business.invader;

import common.InvalidAttributeException;
import common.Movable;
import common.Position;
import common.ValidationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public abstract class Invader implements Movable {
    protected final String id;
    protected final String name;
    protected final Position initialPosition;
    protected Position currentPosition;

    public Invader(String name, Position initialPosition) {
        validateInitials(name, initialPosition);

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.initialPosition = initialPosition;

        this.printLandingMessage();
    }

    private void validateInitials(String name, Position initialPosition) throws InvalidAttributeException {
        boolean isNameValid = checkNameValidity.test(name);
        boolean isInitialPositionValid = checkInitialPositionValidity.test(initialPosition);

        List<String> errArgs = new ArrayList<>();
        if (isNameValid) errArgs.add("name");
        if (!isInitialPositionValid) errArgs.add("initialPosition");

        validateFinally(!isNameValid || !isInitialPositionValid, errArgs);
    }

    protected final void validateFinally(boolean areArgsInvalid, List<String> resultErrArgs) {
        if (areArgsInvalid) throw new InvalidAttributeException("Invalid argument(s)", resultErrArgs);
    }

    private Predicate<String> checkNameValidity = ValidationUtils.checkStringValidity;
    private Predicate<Position> checkInitialPositionValidity = ValidationUtils.checkPositionValidity;

    protected void printLandingMessage() {
        System.out.printf("%s (<%s>) is landed in these coordinates on the plateau X=%d <-> Y=%d, facing %s direction.",
                this.name,
                this.getClass().getSimpleName(),
                this.initialPosition.getX(),
                this.initialPosition.getY(),
                this.initialPosition.getFacingDirection()
        );
    }
}
