# FlyGen

FlyGen is a Java 17+ CLI utility for generating Flyway migration filenames and creating SQL migration files with action-specific starter comments in Maven projects.

## Overview

The tool is built with Java 21 and uses PicoCLI to expose a simple command-line interface. Its main purpose is to generate Flyway-style migration names such as:

```text
V20261011123456789__create_users_table.sql
```

The CLI creates versioned migration files for common migration intents: creating, altering, dropping schema objects, and adding or updating data.

Run FlyGen from a Maven project root containing `pom.xml`. The default migration location is `src/main/resources/db/migration`; override it with a project-relative location when required:

```bash
java -jar target/flygen-1.0.0.jar migration create add_users_table --location database/migrations
```

Absolute locations and locations that escape the project root are rejected. FlyGen never overwrites an existing migration file.

## Requirements

- Java 17+
- Maven 3.9+

For step-by-step installation and usage instructions, see [USER_MANUAL.md](USER_MANUAL.md).

Windows release ZIPs include `flygen.cmd`; keep it beside the JAR and run it from a Maven project root.

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

Use action-oriented commands to make the migration history easier to read:

```bash
java -jar target/flygen-1.0.0.jar migration alter users_table
java -jar target/flygen-1.0.0.jar migration drop legacy_users_table
java -jar target/flygen-1.0.0.jar migration data seed_roles
```

These commands generate names such as `__alter_users_table.sql` and add an SQL comment template. FlyGen never connects to or changes a database.

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

5. Validates that the working directory is a Maven project root containing `pom.xml`.
6. Creates the requested migration directory when it does not exist.
7. Creates the SQL file atomically in the resolved migration directory.

Migration names must start with a letter or number and may otherwise contain letters, numbers, underscores, and hyphens. A duplicate migration is rejected rather than overwritten.

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
Generates a timestamp-based version number in `yyyyMMddHHmmssSSS` format.

### MigrationWriter
Performs file creation.

### ProjectRootFinder and MigrationDirectoryFinder
Resolve the working project root and the expected migration directory path.

## Notes

Exit codes: `0` means success; `1` means a runtime failure such as an unsupported project, duplicate migration, or inaccessible migration directory; and `2` means invalid arguments or a validation failure.

Run `mvn verify` before opening a pull request. GitHub Actions runs this verification on Windows, macOS, and Linux. Dependabot checks Maven and GitHub Actions dependencies monthly.

For a release, update `CHANGELOG.md` and the Maven version, run `mvn verify`, create an annotated tag (for example `v1.0.0`), then publish the executable JAR from `target/`.

FlyGen does not connect to a database, execute SQL, or modify existing migration files. Review generated SQL before applying it.

## License

Copyright 2026 adzli-necius. FlyGen is licensed under the [Apache License 2.0](LICENSE). See [NOTICE](NOTICE) for bundled-runtime attribution notices.

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
