package fr.anthonydurot.littlesecrets;

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
            .importPackages("fr.anthonydurot.littlesecrets");

        noClasses()
            .that()
                .resideInAnyPackage("fr.anthonydurot.littlesecrets.service..")
            .or()
                .resideInAnyPackage("fr.anthonydurot.littlesecrets.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..fr.anthonydurot.littlesecrets.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
