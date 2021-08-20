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
internal class UserServiceImplTest(
    private val userJpaRepository: UserJpaRepository,
    private val userRepository: UserRepository
) {

    @Test
    fun testMember() {
        val member = User (null, "rick")
        val savedMember = userJpaRepository.save(member)

        var findedMember = userJpaRepository.find(savedMember.id!!)

        assertThat(findedMember.id).isEqualTo(savedMember.id)
        assertThat(findedMember.username).isEqualTo(savedMember.username)

        assertThat(findedMember).isEqualTo(savedMember)
    }

    @Test
    fun testMember1() {
        val member = User(null,"rick")
        val savedMember = userRepository.save(member)

        val findedMember = savedMember.id?.let {
            //JAVA JPA RETURN TYPE 이 Optional 인데 Kotlin 에서는 Optional 을 사용하지 않고 nullable 참조형을
            //사용한다 그러기에 findByIdOrNull 메서드를 이용한다. Spring boot 2.1.4 부터 지원한다.
            userRepository.findByIdOrNull(it)
        }

        findedMember.let {
            assertThat(it?.id?:0).isEqualTo(savedMember.id)
        }

    }
}