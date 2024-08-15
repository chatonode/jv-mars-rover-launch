package common;

public enum Instruction {
    L { // Spins the Rover 90 degrees Left without moving from the current coordinate point

        public String toPrettyString() {
            return "Turn Left!";
        }
    },
    R { // Spins the Rover 90 degrees Right without moving from the current coordinate point

        public String toPrettyString() {
            return "Turn Right!";
        }
    },
    M { // Moves the Rover forward by one grid point, maintaining the same heading/orientation
        public String toPrettyString() {
            return "Move!";
        }
    }
}
