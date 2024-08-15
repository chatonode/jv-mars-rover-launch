package common;

public enum Instruction {
    L {
        public String toPrettyString() {
            return "Turn Left!";
        }
    },
    R {
        public String toPrettyString() {
            return "Turn Right!";
        }
    },
    M {
        public String toPrettyString() {
            return "Move!";
        }
    }
}
