package info.m2sj.kotlinweb.tutorial

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
@Transactional
@Rollback
internal class UserJpaRepositoryTest(
    private val userJpaRepository: UserJpaRepository,
) {

    @Test
    fun findByUsernameAndAgeGreaterThant() {
        val u1 = User("rick", 10)
        val u2 = User("rick", 20)

        userJpaRepository.save(u1)
        userJpaRepository.save(u2)

        val result = userJpaRepository.findByUsernameAndAgeGreaterThan("rick", 15)

        assertThat(result[0].username).isEqualTo("rick")
        assertThat(result[0].age).isEqualTo(20)
    }

    @Test
    fun testMember() {
        val u = User("memberA")
        val savedUser = userJpaRepository.save(u)

        val findMember = savedUser.id?.let {
            userJpaRepository.find(it)
        }

        assertThat(findMember!!.id).isEqualTo(savedUser.id)
        assertThat(findMember.username).isEqualTo(savedUser.username)
        assertThat(findMember).isEqualTo(savedUser)
    }

    @Test
    fun basicCRUD() {
        val u1 = User("user1")
        val u2 = User("user2")

        userJpaRepository.save(u1)
        userJpaRepository.save(u2)

        u1.id?.let {
            val fu1 = userJpaRepository.findById(it)
            assertThat(u1).isEqualTo(fu1)
        }
        u2.id?.let {
            val fu2 = userJpaRepository.findById(it)
            assertThat(u2).isEqualTo(fu2)
        }

        val all = userJpaRepository.findAll()

        assertThat(all.size).isEqualTo(2L)

        assertThat(userJpaRepository.count()).isEqualTo(2L)

        userJpaRepository.delete(u1)
        userJpaRepository.delete(u2)

        assertThat(userJpaRepository.count()).isEqualTo(0)

    }

}