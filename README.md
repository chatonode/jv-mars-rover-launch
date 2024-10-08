# 🚀 Mars Rover Launch 🚀
## Overview

> This project is built for sending multiple rovers to the plateau of fellow planet Mars.

`jv-mars-rover-launch` is a Java console application that simulates the exploration of rovers on different planets. Users can define a plateau, add rovers to a launch station, and perform various operations on Earth and Mars, such as launching rovers and navigating them across the surface of Mars.

## Features
### Plateau Creation
- **Define Plateau**: Users can create a plateau by specifying the maximum `x` and `y` dimensions.

### Earth Operations
- **Add Rover**: Add a rover to Earth's launch station list, which will be ready for a future launch.
- **Fetch Rovers**: Retrieve and display all rovers currently on Earth.
- **Launch Rovers**: Launch all prepared rovers from Earth to Mars, to their defined `initial position`.

### Mars Operations
- **View Plateau**: Display a 2D representation of the plateau on Mars with all active rovers.
- **Move Rover**: Move a specific rover across the plateau.
- **Rotate Rover**: Rotate a specific rover to change its direction for next potential movement.
- **List All Rovers**: Display all rovers currently on Mars.
- **Filter Rovers**: List rovers on Mars based on specific filters (e.g., position, direction, produced year).

## Architecture

The application is structured with a clear separation of concerns across different layers:

- `Main`: is the entrance point of entire application, calls `run` method of **Output Manager** to initialise the application.
- `/view`: handles displaying the presentation of information to the user, consuming `orchestrator` and `input` layers.
- `/input`: Handles parsing of user inputs.
- `/controller`: implements the core business logic, exposes them outside with **Mission Control**.
  - `/controller/orchestrator`: (a.k.a. **Mission Control**) orchestrates operations between the various components, takes the responsibility of handling business-related validations, distributes atomic validations to each resource itself. 

## Testing

The project is developed with **Java 21** in **IntelliJ IDEA Community Edition** and includes comprehensive testing using **JUnit**. Most of the business logic is well-covered with unit tests to ensure the correctness and reliability of the application.

## Getting Started

### Prerequisites

- **Java 21 SDK**: Make sure you have Java 21 installed. You can download it from the [Oracle website](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) or use an open-source distribution like [Temurin](https://adoptium.net/).
- **Maven**: Install Apache Maven for dependency management and to build the project.

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/chatonode/jv-mars-rover-launch.git
    cd jv-mars-rover-launch
    ```

2. **Open the Project in IntelliJ IDEA**:
    - Launch IntelliJ IDEA.
    - Click on **File > Open...** and navigate to the cloned `jv-mars-rover-launch` directory.
    - Select the directory and click **Open**.
    - IntelliJ will automatically detect the Maven project and import it. If prompted, select to "Enable Auto-Import" for Maven dependencies.

3. **Build the Project**:
    - In IntelliJ, go to the **Maven** tool window (usually located on the right side of the IDE).
    - Click on the **Reload** button in the Maven tool window to ensure all dependencies are downloaded.

4. **Run the Application**:
    - Locate the `Main` class in your project (e.g., `src/main/java/Main.java`).
    - Right-click on the `Main` class and select **Run 'Main'**.
    - The console will display the output of the application, and you can interact with it through the IntelliJ terminal.


## Usage

After starting the application, you will be prompted to create a plateau by specifying the maximum `x` and `y` coordinates. You can then choose to perform operations on `Earth` or `Mars`, following a series of intuitive prompts.

### Example Workflow

1. **Create a plateau** with dimensions `5 and 6`.
2. **Select Earth** to add rovers to the launch station.
   - Add as much as rover you want whenever you are on Earth.
3. **Launch** all the added rovers to Mars plateau.
4. **On Mars**, you have the options of
   - view the rovers on the plateau
   - move/rotate them as needed.
5. Travel between **Mars** and **Earth** stations to accomplish one of the certain operations at above.

## Core Java Features and Complementary Techniques
During this project, the following core Java features and programming techniques were utilised:

- OOP Principles & Design Patterns
- Data Structures
- Streams & Functions
- Exception Handling
- Clean Code
- TDD & CI Practices
- Interactive Console UI

### Continuous Integration (CI)

This project uses GitHub Actions for continuous integration. The CI pipeline is defined in `.github/workflows/maven.yml` and performs the following steps, **after a Pull Request is created**:

- **Checkout the Code**: Pulls the latest code from the repository.
- **Set up Java 21**: Configures the environment with Java 21 using the Temurin distribution.
- **Build with Maven**: Compiles the project and packages it.
- **Run Tests**: Executes all JUnit tests to ensure the code is functioning correctly.

## Contribution

Contributions are welcome! If you'd like to contribute, please fork the repository and create a pull request.
