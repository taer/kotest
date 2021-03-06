package io.kotest.data

import io.kotest.assertions.MultiAssertionError
import io.kotest.assertions.failure

@PublishedApi
internal class ErrorCollector {
   private val errors = mutableListOf<Throwable>()

   operator fun plusAssign(t: Throwable) {
      errors += t
   }

   fun assertAll() {
      if (errors.size == 1) {
         throw errors[0]
      } else if (errors.size > 1) {
         throw MultiAssertionError(errors)
      }
   }
}

@PublishedApi
internal fun error(e: Throwable, headers: List<String>, values: List<*>): Throwable {
   val params = headers.zip(values).joinToString(", ")
   // Include class name for non-assertion errors, since the class is often meaningful and there might not
   // be a message (e.g. NullPointerException)
   val message = when (e) {
      is AssertionError -> e.message
      else -> e.toString()
   }

   return failure("Test failed for $params with error $message")
}

@PublishedApi
internal fun forNoneError(headers: List<String>, values: List<*>): Throwable {
   val params = headers.zip(values).joinToString(", ")
   return failure("Test passed for $params but expected failure")
}
