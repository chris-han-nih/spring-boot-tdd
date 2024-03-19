package chris.springboottdd

import chris.springboottdd.model.User
import org.springframework.stereotype.Service

@Service
class UserService {
  fun getUser(): User {
    return User("Chris")
  }
}
