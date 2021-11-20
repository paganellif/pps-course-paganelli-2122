package it.pps.course.testing

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.junit.jupiter.api.Test

class MyArchitectureTest {
  @Test
  def someArchitecturalRule(): Unit = {
    val importedClasses: JavaClasses = new ClassFileImporter().importPackages("testing")

    val rule: ArchRule  = classes().should().accessClassesThat().resideInAPackage("testing")

    rule.check(importedClasses)
  }
}