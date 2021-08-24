package info.m2sj.kotlinweb.tutorial

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
@Transactional
@Rollback
class UserRepositoryTest(val userRepository: UserRepository) {
    @Test
    fun testMember() {
        val m = User("rick")
        userRepository.save(m)

        val findedUser = m.id?.let {
            userRepository.findByIdOrNull(it)
        }

        assertThat(findedUser?.id).isEqualTo(m.id)
        assertThat(findedUser?.username).isEqualTo(m.username)
        assertThat(findedUser).isEqualTo(m)
    }

    @Test
    fun basicCrud() {
        val u = User("rick")
        val u2 = User("david")
        userRepository.save(u)
        userRepository.save(u2)

        val f = userRepository.findByIdOrNull(u.id)
        val f2 = userRepository.findByIdOrNull(u2.id)

        assertThat(u).isEqualTo(f)
        assertThat(u2).isEqualTo(f2)

        val list = userRepository.findAll()

        assertThat(list.size).isEqualTo(2)

        val count = userRepository.count()
        assertThat(count).isEqualTo(2)

        userRepository.delete(u)
        userRepository.delete(u2)

        val finalCount = userRepository.count()

        assertThat(finalCount).isEqualTo(0)
    }
}