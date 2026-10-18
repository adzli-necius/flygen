# FlyGen User Manual

FlyGen creates Flyway SQL migration files with a small action-specific starter comment for Maven projects.

## 1. What you need

- Java 17 or newer
- The FlyGen executable: `flygen-1.0.0.jar`
- A Maven project: a folder containing `pom.xml`

Check Java is installed:

```bash
java --version
```

The output must show version 17 or newer.

## 2. Get FlyGen

If you received a release ZIP, keep these files together:

```text
flygen-1.0.0/
├── flygen.cmd
├── flygen-1.0.0.jar
├── README.md
├── LICENSE
└── NOTICE
```

To build the JAR yourself, from the FlyGen source folder run:

```bash
mvn clean package
```

The executable JAR is created at:

```text
target/flygen-1.0.0.jar
```

## 3. Check that FlyGen works

After adding FlyGen to your `Path`, run:

```bash
flygen --help
```

You should see the FlyGen command help.

## 3a. Use a release ZIP on Windows

If a friend sends you the FlyGen release ZIP:

1. Extract it somewhere permanent, for example `C:\Tools\FlyGen`.
2. Keep `flygen.cmd` and `flygen-1.0.0.jar` in the same extracted folder.
3. Open PowerShell in the Maven project where you want to create a migration.
4. Run FlyGen using its extracted path:

```powershell
& "C:\Tools\FlyGen\flygen.cmd" --help
& "C:\Tools\FlyGen\flygen.cmd" migration create users_table
```

The second command creates the migration in the Maven project you opened PowerShell in, not in `C:\Tools\FlyGen`.

To make `flygen` work like the `java` command, add `C:\Tools\FlyGen` to the Windows `Path` environment variable. Open a new terminal afterward, then run:

```powershell
flygen --help
flygen migration create users_table
```

## 4. Create your first migration

Open a terminal in the root of the Maven project that needs the migration. The current folder must contain `pom.xml`.

Run:

```bash
flygen migration create users_table
```

On Windows, an example is:

```powershell
flygen migration create users_table
```

FlyGen creates a SQL file with a starter comment in the default Flyway location:

```text
src/main/resources/db/migration/
```

The file name uses a timestamp version:

```text
V20261017203644123__create_users_table.sql
```

Open the new file and add the SQL migration you need.

## 4a. Choose a migration action

Use an action command to make the purpose of each migration clear in Git history and Flyway filenames.

```powershell
flygen migration create users_table
flygen migration alter users_table
flygen migration drop legacy_users_table
flygen migration data seed_roles
```

These create files with descriptive names and starter comments:

| Command | Example file suffix | Starter comment |
| --- | --- | --- |
| `create users_table` | `__create_users_table.sql` | `-- Creates users_table.` |
| `alter users_table` | `__alter_users_table.sql` | `-- Alters users_table.` |
| `drop legacy_users_table` | `__drop_legacy_users_table.sql` | `-- Drops legacy_users_table.` |
| `data seed_roles` | `__data_seed_roles.sql` | `-- Adds or updates data for seed_roles.` |

FlyGen creates the file only. You must write and review the actual SQL yourself.

## 5. Use a custom migration location

Use `--location` when the project stores migrations somewhere else. The path must be relative to the current Maven project root.

```bash
java -jar path/to/flygen-1.0.0.jar migration create add_email_to_users --location database/migrations
```

This creates the file in:

```text
database/migrations/
```

Absolute paths and paths that go outside the project, such as `../other-project`, are rejected for safety.

## 6. Name migrations correctly

Good migration names:

```text
create_users_table
add-email-to-users
V2_migration
```

Migration names must start with a letter or number. After that they may use letters, numbers, underscores, and hyphens.

Invalid names include:

```text
../outside_project
create users table
_starts_with_underscore
```

Do not rename, edit, or reuse a migration after it has been applied to a shared database.

## 7. Exit codes

| Exit code | Meaning |
| --- | --- |
| `0` | Migration was created. |
| `1` | Runtime problem, such as an unsupported project folder, duplicate migration, or write-permission issue. |
| `2` | Invalid command, migration name, or migration location. |

## 8. Troubleshooting

### `Unsupported project directory`

Run the command from the Maven project root, where `pom.xml` is located.

### `Migration already exists`

FlyGen will not overwrite a file. Choose a different migration name or inspect the existing migration before continuing.

### `Cannot write to migration location`

Check that you have permission to write to the project folder and that no part of the migration path is a file.

### `java is not recognized` or `java: command not found`

Install a Java 17 or newer JDK, then open a new terminal and try `java --version` again.

## 9. Safe usage

FlyGen only creates SQL files and starter comments. It does not connect to a database or execute SQL. Always review the SQL you write before applying it to any environment.

## License

FlyGen is licensed under Apache License 2.0. See `LICENSE` and `NOTICE` in the release bundle.
