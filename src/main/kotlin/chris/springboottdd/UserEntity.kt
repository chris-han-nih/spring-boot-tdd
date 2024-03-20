package chris.springboottdd

import chris.springboottdd.model.User
import jakarta.persistence.*

@Entity
@Table(name = "user")
class UserEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,
  @Column(nullable = false)
  val name: String,
)

fun UserEntity.toUser() = User(name)