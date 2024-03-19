package chris.springboottdd

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootTddApplication

fun main(args: Array<String>) {
	runApplication<SpringBootTddApplication>(*args)
}
