package info.m2sj.kotlinweb

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KotlinWebApplicationTests {

    @Test
    fun contextLoads() {
        val l = listOf(1, 2, 3, 4, 5)

        l.forEach {
            println(it)
        }

    }

}
