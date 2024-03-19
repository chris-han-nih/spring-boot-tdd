package chris.springboottdd

import chris.springboottdd.model.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
  private val userService: UserService
) {
  @GetMapping("/user")
  fun getUser(): ResponseEntity<User>{
    return ResponseEntity.ok(userService.getUser())
  }
}
