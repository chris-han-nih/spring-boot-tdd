package chris.springboottdd

import chris.springboottdd.model.User
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.test.web.reactive.server.WebTestClient

class UserControllerTest : DescribeSpec({

  describe("getUser") {
    
    val userService = mockk<UserService>()
    every { userService.getUser() } returns User("Chris")
    
    val webTestClient = WebTestClient.bindToController(UserController(
      userService
    )).build()
    
    val performRequest = { webTestClient.get().uri("/user").exchange() }

    context("저장된 데이터가 Chris 한명인 상황에서 요청한 경우") {
      val response = performRequest()

      verify { userService.getUser() }

      it("요청은 성공한다") {
        response.expectStatus().isOk
      }
      it("반환 값은 Chris이다") {
        response.expectBody().json("""{"name":"Chris"}""")
      }
    }
  }
})

// https://velog.io/@jaeyun-jo/Kotest-MockK%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%BD%94%ED%8B%80%EB%A6%B0-%EB%8B%A8%EC%9C%84-%ED%85%8C%EC%8A%A4%ED%8A%B8
