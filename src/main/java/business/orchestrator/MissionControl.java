package business.orchestrator;

import java.util.function.Predicate;

import business.environment.Plateau;
import utils.ValidationUtils;
import validation.ParamIsValidMap;
import validation.ParameterValidator;


public class MissionControl {
    private final String username;
    private final Plateau plateau;

    public MissionControl(String username, int maximumX, int maximumY) {
        ParameterValidator.validateArgs(new ParamIsValidMap() {{
            put("username", checkUsernameValidity.test(username));
        }});

        this.username = username;
        this.plateau = new Plateau(maximumX, maximumY);
    }

    private static final Predicate<String> checkUsernameValidity = ValidationUtils.checkStringValidity;

    public String getUsername() {
        return this.username;
    }

    public Plateau getPlateau() {
        return this.plateau;
    }
}
