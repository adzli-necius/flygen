# FlyGen

FlyGen is a small Java CLI utility for generating Flyway migration filenames and creating SQL migration files for a Spring or Java project.

## Overview

The tool is built with Java 21 and uses PicoCLI to expose a simple command-line interface. Its main purpose is to generate Flyway-style migration names such as:

```text
V20261011123456789__create_users_table.sql
```

The CLI is intentionally minimal and focuses on one action: creating a migration file from a descriptive migration name.

## Requirements

- Java 21+
- Maven 3.9+

## Build

From the project root:

```bash
mvn package
```

This creates an executable JAR in `target/`.

## Usage

Show the application help:

```bash
java -jar target/flygen-1.0.0.jar --help
```

Create a migration:

```bash
java -jar target/flygen-1.0.0.jar migration create create_users_table
```

The command expects a required argument:

```text
<migrationName>
```

Example:

```bash
java -jar target/flygen-1.0.0.jar migration create add_users_table
```

This generates a file name similar to:

```text
V20261011123456789__add_users_table.sql
```

## Current implementation details

The project currently does the following:

1. Generates a timestamp-based Flyway version using the current time.
2. Creates a migration filename in the form `V<timestamp>__<migrationName>.sql`.
3. Resolves the current working directory as the project root.
4. Resolves a migration directory path as:

```text
src/main/resources/db/migration
```

5. Attempts to create the SQL file.

The directory logic is implemented in the finder classes, but the actual write operation currently creates the file in the working directory rather than in the resolved Flyway migration directory.

## Project structure

```text
flygen/
├── pom.xml
├── README.md
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── adzli/
│                   └── flygen/
│                       ├── FlygenApplication.java
│                       ├── FlygenCommand.java
│                       ├── command/
│                       │   └── migration/
│                       │       ├── CreateCommand.java
│                       │       └── MigrationCommand.java
│                       ├── finder/
│                       │   ├── MigrationDirectoryFinder.java
│                       │   └── ProjectRootFinder.java
│                       ├── generator/
│                       │   ├── FilenameGenerator.java
│                       │   ├── FlyWayVersionGenerator.java
│                       │   └── MigrationGenerator.java
│                       └── writer/
│                           └── MigrationWriter.java
└── target/
```

## Main classes

### FlygenApplication
Starts the CLI entry point and delegates to PicoCLI.

### FlygenCommand
Defines the root command (`flygen`) and registers the `migration` subcommand.

### MigrationCommand
Groups migration-related commands.

### CreateCommand
Accepts a migration name parameter and triggers generation.

### MigrationGenerator
Coordinates the generation flow: version, filename, project root, migration directory, and file creation.

### FilenameGenerator
Creates Flyway-compliant migration file names.

### FlyWayVersionGenerator
Generates a timestamp-based version number.

### MigrationWriter
Performs file creation.

### ProjectRootFinder and MigrationDirectoryFinder
Resolve the working project root and the expected migration directory path.

## Notes

This project is a lightweight starter tool and is useful as a base for expanding into richer migration management features, such as:

- creating the migration directory if it does not exist,
- writing to the correct Flyway folder,
- validating project roots,
- supporting configurable migration locations,
- adding tests for generation behavior.

## Example

```bash
java -jar target/flygen-1.0.0.jar migration create add_users_table
```

Generates a file like:

```text
V20261011123456789__add_users_table.sql
```

and is intended to fit a Flyway folder structure such as:

```text
src/main/resources/db/migration/
```
