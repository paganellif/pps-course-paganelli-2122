package it.pps.course.testing

import it.pps.course.testing.Server.{Cache, Request, Response, ServerWithCache}
import org.junit.runner.RunWith
import org.scalamock.scalatest.MockFactory
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestWithMock extends AnyFunSuite with MockFactory with Matchers {
  test("Test server with cache"){
    // Mocks/stubs for dependencies
    val cacheMock = mock[Cache[Request,Response]]
    val cacheableStub = stubFunction[Request, Boolean]
    cacheableStub.when( where { (r: Request) => r.body.length < 25 } ).returns(true)

    // Wire SUT with stubbed/mocked dependencies
    val server = new ServerWithCache(cacheMock, cacheableStub)

    // Arrange
    val request = Request("Some request")
    val expectedResponse = Response(request.body+"'s Response")

    // Mock expectations
    inSequence {
      (cacheMock.cached _).expects(request).returning(false)
      (cacheMock.put _).expects(request, *)
      (cacheMock.cached _).expects(request).returning(true)
      (cacheMock.get _).expects(request).returning(expectedResponse)
    }

    // Act + Assert
    server.serve(request) shouldEqual expectedResponse
    server.serve(request) shouldEqual expectedResponse
    server.serve(Request("some long request that should not be cache"))

    cacheableStub.verify(request).twice() // (cf., test spy)
  }
}