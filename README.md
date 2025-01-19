# League Rankings App

## Overview
This is a Kotlin-based command-line application that calculates league rankings based on game results. It supports various input methods and can be run through IntelliJ, Gradle, or Docker.

## Features
- Calculate league rankings based on match results.
- Supports tie-breaking by alphabetical order.
- Accepts input from a file or the command line.
- Interactive CLI mode for user input.

---

## Requirements
- **JDK 17**
- **Gradle**
- **Docker** (optional)

---

## Running the Application

### 1. Using IntelliJ IDEA
1. Open the project in IntelliJ.
2. Ensure the Kotlin plugin is installed.
3. Set up the run configuration:
    - Go to `Run > Edit Configurations`.
    - Click `+` and select `Application`.
    - Set the `Main class` to `MainKt`.
    - (Optional) Pass a file as a program argument (e.g., `sample.txt`).
4. Click the green run button to execute the program.

### 2. Using Gradle

#### a. Run the Application
```
./gradlew run --args="sample.txt"
```
Replace `sample.txt` with the path to your input file.

#### b. Run Tests
```
./gradlew test
```

### 3. Using Docker

#### a. Build the Docker Image
```bash
docker build -t kotlin-leagues-app .
```

#### b. Run the Application
Interactive CLI mode:
```bash
docker run -it kotlin-leagues-app
```
To provide a file:
```bash
docker run -it kotlin-leagues-app sample.txt
```

---

## File Structure
- `src/main/kotlin/Main.kt`: Main application logic.
- `src/test/kotlin/LeagueRanksTest.kt`: Test suite.
- `build.gradle.kts`: Build configuration.
- `Dockerfile`: Docker container definition.

---

## Input and Output
### Input
Provide match results in the following format:
```
Lions 3, Snakes 3
Tarantulas 1, FC Awesome 0
Lions 1, FC Awesome 1
Tarantulas 3, Snakes 1
Lions 4, Grouches 0
```

### Output
The application outputs rankings in the format:
```
1. Tarantulas, 6 pts
2. Lions, 5 pts
3. FC Awesome, 1 pt
3. Snakes, 1 pt
5. Grouches, 0 pts
```