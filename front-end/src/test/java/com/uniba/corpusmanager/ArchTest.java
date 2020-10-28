package com.uniba.corpusmanager;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.uniba.corpusmanager");

        noClasses()
            .that()
                .resideInAnyPackage("com.uniba.corpusmanager.service..")
            .or()
                .resideInAnyPackage("com.uniba.corpusmanager.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.uniba.corpusmanager.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
