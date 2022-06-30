# Site Clearing Simulation

JDK request is 14

## Improvements of the requirements

- Added 2 specific descriptions on exit caused by the operator visited a protected trees and out of boundary

## Design

### Models

#### Concepts
- SiteMap
- SimulateRunner: Do the formatting and output to console
- Operator: the trainee who do the simulation
- Square: Different types of the cells of the land like "o", "t", "T", "r"
- Move: Different operations of the bulldozer like "turn left", "turn right", "advance *N*", "quit"
- Position: The position of the bulldozer
- Report: The cost report

#### Call Relationships of the Models

- Main class is doing
  1. SiteMap loading
  2. Create Operator and pass SiteMap in
  3. Create Simulator and pass Operator in
- SimulateRunner class is doing
  1. Load operator
  2. Run simulate
  3. Formatting output to console
- Operator class is doing
  1. Move the bulldozer and report related metrics to Report
  2. Generate cost Report when exiting

## Test

Note: The test coverage tool is jacoco. The line coverage is 97%. The branch coverage is 93%.

### How to run
Use mvn to test

```bash
mvn clean test
```

### Coverage report
```bash
target/site/jacoco/index.html
```

## Build

Use mvn to build

```bash
mvn clean package
```

## Usage

Note: Example site map file is in the root layer of the code repo with name *sitemap.txt*

### CLI

```bash
java -jar ./target/oracle_homework-1.0-jar-with-dependencies.jar <sitemap_file_full_path>
```

### Intellj
Config in Run menu
1. Choose to create a new Application item and give a name to it
2. JDK must be 14
3. Entry class is com.vic.Main
