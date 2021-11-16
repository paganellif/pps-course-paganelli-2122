package it.pps.course.u07lab.e5more

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition._
import org.junit.jupiter.api.Test

class MyArchitectureTest {
  @Test
  def someArchitecturalRule() {
    val importedClasses: JavaClasses = new ClassFileImporter().importPackages("u07lab")

    val rule: ArchRule  = classes().should().accessClassesThat().resideInAPackage("u07lab")

    rule.check(importedClasses)
  }
}