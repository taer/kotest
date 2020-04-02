package com.sksamuel.kotest.runner.junit5

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.platform.engine.discovery.DiscoverySelectors.selectClass
import org.junit.platform.testkit.engine.EngineTestKit
import org.junit.platform.testkit.engine.Events

fun Events.shouldHaveNames(vararg names: String) =
   list().map { it.testDescriptor.displayName }.shouldContainExactly(*names)

fun Events.shouldBeEmpty() = list().shouldBeEmpty()

class StringSpecEngineKitTest : FunSpec({

   test("verify all events") {
      EngineTestKit
         .engine("kotest")
         .selectors(selectClass(StringSpecTestCase::class.java))
         .configurationParameter("allow_private", "true")
         .execute()
         .allEvents().apply {
            started().shouldHaveNames(
               "Kotest",
               "com.sksamuel.kotest.runner.junit5.StringSpecTestCase",
               "a failing test",
               "a passing test",
               "an erroring test"
            )
            skipped().shouldHaveNames("a skipped test")
            failed().shouldHaveNames(
               "a failing test",
               "an erroring test",
               "com.sksamuel.kotest.runner.junit5.StringSpecTestCase"
            )
            succeeded().shouldHaveNames("a passing test", "Kotest")
            finished().shouldHaveNames(
               "a failing test",
               "a passing test",
               "an erroring test",
               "com.sksamuel.kotest.runner.junit5.StringSpecTestCase",
               "Kotest"
            )
            aborted().shouldBeEmpty()
            dynamicallyRegistered().shouldHaveNames(
               "com.sksamuel.kotest.runner.junit5.StringSpecTestCase",
               "a failing test",
               "a passing test",
               "an erroring test",
               "a skipped test"
            )
         }
   }

   test("exception in initializer") {
      EngineTestKit
         .engine("kotest")
         .selectors(selectClass(StringSpecExceptionInInit::class.java))
         .configurationParameter("allow_private", "true")
         .execute()
         .allEvents().apply {
            count() shouldBe 8
            started().shouldHaveNames(
               "Kotest",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInInit",
               "Spec execution failed"
            )
            skipped().shouldBeEmpty()
            failed().shouldHaveNames("com.sksamuel.kotest.runner.junit5.StringSpecExceptionInInit")
            succeeded().shouldHaveNames("Kotest")
            finished().shouldHaveNames(
               "Spec execution failed",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInInit",
               "Kotest"
            )
            aborted().shouldHaveNames("Spec execution failed")
            dynamicallyRegistered().shouldHaveNames(
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInInit",
               "Spec execution failed"
            )
         }
   }

   test("exception in beforeSpec override") {

      EngineTestKit
         .engine("kotest")
         .selectors(selectClass(StringSpecExceptionInBeforeSpecOverride::class.java))
         .configurationParameter("allow_private", "true")
         .execute()
         .allEvents().apply {
            count() shouldBe 8
            started().shouldHaveNames(
               "Kotest",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeSpecOverride",
               "Spec execution failed"
            )
            skipped().shouldBeEmpty()
            failed().shouldHaveNames("com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeSpecOverride")
            succeeded().shouldHaveNames("Kotest")
            finished().shouldHaveNames(
               "Spec execution failed",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeSpecOverride",
               "Kotest"
            )
            aborted().shouldHaveNames("Spec execution failed")
            dynamicallyRegistered().shouldHaveNames(
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeSpecOverride",
               "Spec execution failed"
            )
         }
   }

   test("exception in beforeSpec function") {
      EngineTestKit
         .engine("kotest")
         .selectors(selectClass(StringSpecExceptionInBeforeSpecFunction::class.java))
         .configurationParameter("allow_private", "true")
         .execute()
         .allEvents().apply {
            count() shouldBe 8
            started().shouldHaveNames(
               "Kotest",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeSpecFunction",
               "Spec execution failed"
            )
            skipped().shouldBeEmpty()
            failed().shouldHaveNames("com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeSpecFunction")
            succeeded().shouldHaveNames("Kotest")
            finished().shouldHaveNames(
               "Spec execution failed",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeSpecFunction",
               "Kotest"
            )
            aborted().shouldHaveNames("Spec execution failed")
            dynamicallyRegistered().shouldHaveNames(
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeSpecFunction",
               "Spec execution failed"
            )
         }
   }

   test("exception in afterSpec override") {
      EngineTestKit
         .engine("kotest")
         .selectors(selectClass(StringSpecExceptionInAfterSpec::class.java))
         .configurationParameter("allow_private", "true")
         .execute()
         .allEvents().apply {
            count() shouldBe 11
            started().shouldHaveNames(
               "Kotest",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterSpec",
               "a failing test",
               "a passing test"
            )
            skipped().shouldBeEmpty()
            failed().shouldHaveNames(
               "a failing test",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterSpec"
            )
            succeeded().shouldHaveNames("a passing test", "Kotest")
            finished().shouldHaveNames(
               "a failing test",
               "a passing test",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterSpec",
               "Kotest"
            )
            aborted().shouldBeEmpty()
            dynamicallyRegistered().shouldHaveNames(
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterSpec",
               "a failing test",
               "a passing test"
            )
         }
   }

   test("exception in afterSpec function") {
      EngineTestKit
         .engine("kotest")
         .selectors(selectClass(StringSpecExceptionInAfterSpecFunction::class.java))
         .configurationParameter("allow_private", "true")
         .execute()
         .allEvents().apply {
            count() shouldBe 11
            started().shouldHaveNames(
               "Kotest",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterSpecFunction",
               "a failing test",
               "a passing test"
            )
            skipped().shouldBeEmpty()
            failed().shouldHaveNames(
               "a failing test",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterSpecFunction"
            )
            succeeded().shouldHaveNames("a passing test", "Kotest")
            finished().shouldHaveNames(
               "a failing test",
               "a passing test",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterSpecFunction",
               "Kotest"
            )
            aborted().shouldBeEmpty()
            dynamicallyRegistered().shouldHaveNames(
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterSpecFunction",
               "a failing test",
               "a passing test"
            )
         }
   }

   test("exception in beforeTest override") {
      EngineTestKit
         .engine("kotest")
         .selectors(selectClass(StringSpecExceptionInBeforeTest::class.java))
         .configurationParameter("allow_private", "true")
         .execute()
         .allEvents().apply {
            count() shouldBe 11
            started().shouldHaveNames(
               "Kotest",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeTest",
               "a failing test",
               "a passing test"
            )
            skipped().shouldBeEmpty()
            failed().shouldHaveNames(
               "a failing test",
               "a passing test",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeTest"
            )
            succeeded().shouldHaveNames("Kotest")
            finished().shouldHaveNames(
               "a failing test",
               "a passing test",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeTest",
               "Kotest"
            )
            aborted().shouldBeEmpty()
            dynamicallyRegistered().shouldHaveNames(
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeTest", "a failing test", "a passing test"
            )
         }
   }

   test("exception in beforeTest function") {
      EngineTestKit
         .engine("kotest")
         .selectors(selectClass(StringSpecExceptionInBeforeTestFunction::class.java))
         .configurationParameter("allow_private", "true")
         .execute()
         .allEvents().apply {
            count() shouldBe 11
            started().shouldHaveNames(
               "Kotest",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeTestFunction",
               "a failing test",
               "a passing test"
            )
            skipped().shouldBeEmpty()
            failed().shouldHaveNames(
               "a failing test",
               "a passing test",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeTestFunction"
            )
            succeeded().shouldHaveNames("Kotest")
            finished().shouldHaveNames(
               "a failing test",
               "a passing test",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeTestFunction",
               "Kotest"
            )
            aborted().shouldBeEmpty()
            dynamicallyRegistered().shouldHaveNames(
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInBeforeTestFunction",
               "a failing test",
               "a passing test"
            )
         }
   }

   test("exception in afterTest override") {
      EngineTestKit
         .engine("kotest")
         .selectors(selectClass(StringSpecExceptionInAfterTest::class.java))
         .configurationParameter("allow_private", "true")
         .execute()
         .allEvents().apply {
            count() shouldBe 11
            started().shouldHaveNames(
               "Kotest",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterTest",
               "a failing test",
               "a passing test"
            )
            skipped().shouldBeEmpty()
            failed().shouldHaveNames(
               "a failing test",
               "a passing test",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterTest"
            )
            succeeded().shouldHaveNames("Kotest")
            finished().shouldHaveNames(
               "a failing test",
               "a passing test",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterTest",
               "Kotest"
            )
            aborted().shouldBeEmpty()
            dynamicallyRegistered().shouldHaveNames(
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterTest",
               "a failing test",
               "a passing test"
            )
         }
   }

   test("exception in afterTest function") {
      EngineTestKit
         .engine("kotest")
         .selectors(selectClass(StringSpecExceptionInAfterTestFunction::class.java))
         .configurationParameter("allow_private", "true")
         .execute()
         .allEvents().apply {
            count() shouldBe 11
            started().shouldHaveNames(
               "Kotest",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterTestFunction",
               "a failing test",
               "a passing test"
            )
            skipped().shouldBeEmpty()
            failed().shouldHaveNames(
               "a failing test",
               "a passing test",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterTestFunction"
            )
            succeeded().shouldHaveNames("Kotest")
            finished().shouldHaveNames(
               "a failing test",
               "a passing test",
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterTestFunction",
               "Kotest"
            )
            aborted().shouldBeEmpty()
            dynamicallyRegistered().shouldHaveNames(
               "com.sksamuel.kotest.runner.junit5.StringSpecExceptionInAfterTestFunction",
               "a failing test",
               "a passing test"
            )
         }
   }

})

private class StringSpecExceptionInBeforeSpecOverride : StringSpec() {

   init {
      "a failing test" {
         1 shouldBe 2
      }

      "a passing test" {
         1 shouldBe 1
      }
   }

   override fun beforeSpec(spec: Spec) {
      throw RuntimeException("zopp!!")
   }

}

private class StringSpecExceptionInBeforeSpecFunction : StringSpec() {
   init {
      "a failing test" {
         1 shouldBe 2
      }

      "a passing test" {
         1 shouldBe 1
      }

      beforeSpec {
         throw RuntimeException("zopp!!")
      }
   }
}

private class StringSpecExceptionInAfterTest : StringSpec() {

   init {
      "a failing test" {
         1 shouldBe 2
      }

      "a passing test" {
         1 shouldBe 1
      }
   }

   override fun afterTest(testCase: TestCase, result: TestResult) {
      throw RuntimeException("craack!!")
   }
}

private class StringSpecExceptionInAfterTestFunction : StringSpec() {

   init {
      "a failing test" {
         1 shouldBe 2
      }

      "a passing test" {
         1 shouldBe 1
      }

      afterTest {
         throw RuntimeException("craack!!")
      }
   }
}


private class StringSpecExceptionInAfterSpec : StringSpec() {

   init {
      "a failing test" {
         1 shouldBe 2
      }

      "a passing test" {
         1 shouldBe 1
      }

   }

   override fun afterSpec(spec: Spec) {
      throw RuntimeException("splatt!!")
   }

}

private class StringSpecExceptionInAfterSpecFunction : StringSpec() {

   init {
      "a failing test" {
         1 shouldBe 2
      }

      "a passing test" {
         1 shouldBe 1
      }

      afterSpec {
         throw RuntimeException("splatt!!")
      }
   }
}

private class StringSpecExceptionInBeforeTest : StringSpec() {

   init {
      "a failing test" {
         1 shouldBe 2
      }

      "a passing test" {
         1 shouldBe 1
      }
   }

   override fun beforeTest(testCase: TestCase) {
      throw RuntimeException("oooff!!")
   }
}

private class StringSpecExceptionInBeforeTestFunction : StringSpec() {

   init {
      "a failing test" {
         1 shouldBe 2
      }

      "a passing test" {
         1 shouldBe 1
      }

      beforeTest {
         throw RuntimeException("oooff!!")
      }
   }
}

private class StringSpecTestCase : StringSpec({

   "a failing test" {
      1 shouldBe 2
   }

   "a passing test" {
      1 shouldBe 1
   }

   "an erroring test" {
      throw RuntimeException()
   }

   "a skipped test".config(enabled = false) {
   }

})
