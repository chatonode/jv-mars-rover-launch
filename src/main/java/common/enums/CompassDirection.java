package common.enums;

public enum CompassDirection {
    N {
        public String toPrettyString() {
            return "North";
        }
    },
    E {
        public String toPrettyString() {
            return "East";
        }
    },
    S {
        public String toPrettyString() {
            return "South";
        }
    },
    W {
        public String toPrettyString() {
            return "West";
        }
    };

    public String toReadableString() {
        return switch (this) {
            case N -> "North";
            case E -> "East";
            case S -> "South";
            case W -> "West";
        };
    }
}
