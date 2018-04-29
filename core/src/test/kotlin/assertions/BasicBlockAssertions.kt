package assertions

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*

internal object BasicBlockAssertions : Spek({
  describe("isNull assertion") {
    it("passes if the target is null") {
      shouldPass {
        val target: String? = null
        expect(target) {
          isNull()
        }
      }
    }
    it("fails if the target is not null") {
      shouldFail {
        val target = "covfefe"
        expect(target) {
          isNull()
        }
      }
    }
  }

  describe("isNotNull assertion") {
    it("fails if the target is null") {
      shouldFail {
        val target: String? = null
        expect(target) {
          isNotNull()
        }
      }
    }
    it("passes if the target is not null") {
      shouldPass {
        val target = "covfefe"
        expect(target) {
          isNotNull()
        }
      }
    }
  }

  describe("isA assertion") {
    it("fails if the target is null") {
      shouldFail {
        val target: Any? = null
        expect(target) {
          isA<String>()
        }
      }
    }
    it("fails if the target is a different type") {
      shouldFail {
        val target: Any? = 1L
        expect(target) {
          isA<String>()
        }
      }
    }
    it("passes if the target is the same exact type") {
      shouldPass {
        val target: Any? = "covfefe"
        expect(target) {
          isA<String>()
        }
      }
    }
    it("passes if the target is a sub-type") {
      shouldPass {
        val target: Any? = 1L
        expect(target) {
          isA<Number>()
        }
      }
    }
  }
})

internal fun shouldPass(function: (() -> Unit)) {
  function.invoke()
}

internal fun shouldFail(function: (() -> Unit)) {
  try {
    function.invoke()
    throw AssertionError("Should have failed")
  } catch (e: AssertionError) {
    // expected
  }
}