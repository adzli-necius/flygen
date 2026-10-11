package com.adzli.flygen.generator;

import com.adzli.flygen.finder.MigrationDirectoryFinder;
import com.adzli.flygen.finder.ProjectRootFinder;
import com.adzli.flygen.writer.MigrationWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Supplier;

public class MigrationGenerator {

    private final FlyWayVersionGenerator flyWayVersionGenerator;
    private final FilenameGenerator filenameGenerator;
    private final MigrationWriter migrationWriter;
    private final Supplier<Path> projectRootSupplier;
    private final MigrationDirectoryFinder migrationDirectoryFinder;

    public MigrationGenerator() {
        this(
                new FlyWayVersionGenerator(),
                new FilenameGenerator(),
                new MigrationWriter(),
                new ProjectRootFinder()::find,
                new MigrationDirectoryFinder()
        );
    }

    MigrationGenerator(
            FlyWayVersionGenerator flyWayVersionGenerator,
            FilenameGenerator filenameGenerator,
            MigrationWriter migrationWriter,
            Supplier<Path> projectRootSupplier,
            MigrationDirectoryFinder migrationDirectoryFinder
    ) {
        this.flyWayVersionGenerator = Objects.requireNonNull(flyWayVersionGenerator);
        this.filenameGenerator = Objects.requireNonNull(filenameGenerator);
        this.migrationWriter = Objects.requireNonNull(migrationWriter);
        this.projectRootSupplier = Objects.requireNonNull(projectRootSupplier);
        this.migrationDirectoryFinder = Objects.requireNonNull(migrationDirectoryFinder);
    }

    public Path generate(String migrationName) throws IOException {
        String version = flyWayVersionGenerator.generate();
        String filename = filenameGenerator.generate(version, migrationName);

        Path projectRoot = projectRootSupplier.get();
        Path migrationDirectory = migrationDirectoryFinder.find(projectRoot);

        return migrationWriter.write(migrationDirectory, filename);
    }
}
