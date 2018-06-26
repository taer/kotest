package com.sksamuel.kotlintest

import io.kotlintest.Description
import io.kotlintest.TestCaseFilter
import io.kotlintest.TestFilterResult
import io.kotlintest.TestResult
import io.kotlintest.TestStatus
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class TestCaseFilterTest : StringSpec() {

  var a = false
  var b = false

  init {
    "aa should run" {
      a = true
    }
    "bb should be ignored" {
      1 shouldBe 2
    }
  }

  override fun afterTest(description: Description, result: TestResult) {
    when (description.name) {
      "aa should run" -> result.status shouldBe TestStatus.Success
      "bb should be ignored" -> result.status shouldBe TestStatus.Ignored
    }
    a shouldBe true
  }
}

object TestCaseFilterTestFilter : TestCaseFilter {
  override fun filter(description: Description): TestFilterResult {
    return when (description.name) {
      "bb should be ignored" -> TestFilterResult.Ignore
      else -> TestFilterResult.Include
    }
  }
}