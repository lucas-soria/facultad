package ar.edu.um.programacion2.productos;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ar.edu.um.programacion2.productos");

        noClasses()
            .that()
            .resideInAnyPackage("ar.edu.um.programacion2.productos.service..")
            .or()
            .resideInAnyPackage("ar.edu.um.programacion2.productos.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..ar.edu.um.programacion2.productos.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
