package chris.springboottdd

import chris.springboottdd.model.User
import org.springframework.stereotype.Service

@Service
class UserService (
  private val userRepository: UserRepository
) {
  fun getUser(): User {
    return userRepository.findAll().first().toUser()
  }
}
