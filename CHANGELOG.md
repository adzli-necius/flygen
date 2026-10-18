# Changelog

All notable changes to FlyGen are documented in this file.

## [Unreleased]

### Added

- Action-oriented migration commands: `create`, `alter`, `drop`, and `data`.
- Action-specific SQL starter comments in generated migration files.
- Apache-2.0 license and bundled-runtime attribution notices.
- Configurable project-relative migration locations through `--location`.
- Maven project-root verification.
- Cross-platform continuous integration and automated dependency-update configuration.

### Changed

- Java 17 is now the compilation baseline; the CLI supports Java 17 through 21.
- Migration versions remain timestamp-based: `yyyyMMddHHmmssSSS`.
- Clear errors and non-zero exit codes for invalid projects, unsafe locations, duplicate migrations, and inaccessible locations.

## [1.0.0] - 2026-10-11

### Added

- Initial executable FlyGen CLI.
- Safe migration creation, filename validation, and core tests.
