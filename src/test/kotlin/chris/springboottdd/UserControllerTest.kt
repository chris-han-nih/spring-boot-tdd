package chris.springboottdd

import chris.springboottdd.model.User
import io.kotest.core.spec.style.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.test.web.reactive.server.WebTestClient

class UserControllerTestWithDescribeSpec : DescribeSpec({

  describe("getUser") {

    lateinit var userService: UserService
    lateinit var webTestClient: WebTestClient

    beforeContainer {
      // Mock 서비스 설정
      userService = mockk<UserService>()
      every { userService.getUser() } returns User("Chris")

      // 테스트를 위한 WebTestClient 설정
      webTestClient = WebTestClient.bindToController(UserController(userService)).build()
    }
    
    val performRequest = { webTestClient.get().uri("/user").exchange() }

    context("저장된 데이터가 Chris 한명인 상황에서 요청한 경우") {
      val response = performRequest()

      verify { userService.getUser() }

      it("요청은 성공해야 한다") {
        response.expectStatus().isOk
      }
      it("반환 값은 Chris이어야 한다") {
        response.expectBody().json("""{"name":"Chris"}""")
      }
    }
  }
})

class UserControllerTestWithBehaviorSpec : BehaviorSpec({

  lateinit var userService: UserService
  lateinit var webTestClient: WebTestClient

  beforeContainer {
    userService = mockk<UserService>()
    every { userService.getUser() } returns User("Chris")

    webTestClient = WebTestClient.bindToController(UserController(userService)).build()
  }

  given("저장된 데이터가 Chris 한명인 상황에서 요청한 경우") {
    `when`("GET 요청이 /user로 보내진다") {
      val response = webTestClient.get().uri("/user").exchange()

      verify { userService.getUser() }

      then("요청은 성공해야 한다") {
        response.expectStatus().isOk
      }

      then("반환 값은 Chris이어야 한다") {
        response.expectBody().json("""{"name":"Chris"}""")
      }
    }
  }
})

class UserControllerTestWithFunSpec : FunSpec({
  lateinit var userService: UserService
  lateinit var webTestClient: WebTestClient

//  beforeContainer { FunSpec에서는 beforeContainer를 사용할 수 없다. }
  beforeTest {
    userService = mockk<UserService>()
    every { userService.getUser() } returns User("Chris")

    webTestClient = WebTestClient.bindToController(UserController(userService)).build()
  }

  test("저장된 데이터가 Chris 한명인 상황에서 요청한 경우") {
    val response = webTestClient.get().uri("/user").exchange()

    response.expectStatus().isOk
    response.expectBody().json("""{"name":"Chris"}""")
  }
})

class UserControllerTestWithShouldSpec : ShouldSpec({

  lateinit var userService: UserService
  lateinit var webTestClient: WebTestClient

//  beforeContainer { FunSpec에서는 beforeContainer를 사용할 수 없다. }
  beforeSpec {
    userService = mockk<UserService>()
    every { userService.getUser() } returns User("Chris")

    webTestClient = WebTestClient.bindToController(UserController(userService)).build()
  }

  should("저장된 데이터가 Chris 한명인 상황에서 요청한 경우") {
    val response = webTestClient.get().uri("/user").exchange()

    response.expectStatus().isOk
    response.expectBody().json("""{"name":"Chris"}""")
  }
})

// https://velog.io/@jaeyun-jo/Kotest-MockK%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%BD%94%ED%8B%80%EB%A6%B0-%EB%8B%A8%EC%9C%84-%ED%85%8C%EC%8A%A4%ED%8A%B8
