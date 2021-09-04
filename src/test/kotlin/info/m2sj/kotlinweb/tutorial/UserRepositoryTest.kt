package info.m2sj.kotlinweb.tutorial

import org.assertj.core.api.Assertions.assertThat
import org.hibernate.Hibernate
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
@Transactional
@Rollback
class UserRepositoryTest(val userRepository: UserRepository, val groupJpaRepository: GroupJpaRepository) {

    //@Test
//    fun jpaBaseEntity() {
//        val u = userRepository.save(User("user1", 10))
//
//        TimeUnit.SECONDS.sleep(1)
//
//        u.username = "user100"
//
//        val fu = u?.id?.let {
//           val ffu = userRepository.findByIdOrNull(it)
//
//            print(ffu?.createdDate)
//            print(ffu?.updatedDate)
//        }
//    }
    @Test
    fun queryHint() {
        userRepository.save(User("user1", 10))
    }
    @Test
    fun entityGrapth() {
        userRepository.findByUsername("")
    }
    @Test
    fun fetchJoinTest() {
        userRepository.findUserFetchJoin()
    }
    @Test
    fun findMemberLazy() {
        val a = Groups("agroup")
        val b = Groups("bgroup")

        groupJpaRepository.save(a)
        groupJpaRepository.save(b)

        userRepository.save(User("user1", 10 ,a))
        userRepository.save(User("user2", 20 ,b))

        val users = userRepository.findAll()


        users.forEach { user ->
            println(Hibernate.isInitialized(user.group))
            println("==>${user.group?.name}")
        }
    }

    @Test
    fun bulkTest() {
        userRepository.save(User("u1", 10))
        userRepository.save(User("u2", 19))
        userRepository.save(User("u3", 20))
        userRepository.save(User("u4", 21))
        userRepository.save(User("u5", 40))

        val resultCount = userRepository.bulkAgePlus(20)

        assertThat(resultCount).isEqualTo(3)

    }
    
    @Test
    fun page() {
        userRepository.save(User("u1",10))
        userRepository.save(User("u2",10))
        userRepository.save(User("u3",10))
        userRepository.save(User("u4",10))
        userRepository.save(User("u5",10))

        val pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"))

        val pageUser = userRepository.findByAge(10, pageRequest)

        val content = pageUser.content

        assertThat(content.size).isEqualTo(3)
        assertThat(pageUser.totalElements).isEqualTo(5)
        assertThat(pageUser.number).isEqualTo(0)
        assertThat(pageUser.totalPages).isEqualTo(2)
        assertThat(pageUser.isFirst).isTrue
        assertThat(pageUser.hasNext()).isTrue

        val top3Users = userRepository.findTop3By()

        assertThat(top3Users.size).isEqualTo(3)

        val userdtos = userRepository.findByAge(10, pageRequest)


        val aaa = userdtos.map { m -> UserDto(id=m.id, age=m.age, username=m.username, name=m.group?.name) }
    }
    @Test
    fun findInNames() {
        val u1 = User("rick", 10)
        val u2 = User("rick", 20)

        userRepository.save(u1)
        userRepository.save(u2)

        val result = userRepository.findUsers(listOf("ric,","rick"))
        assertThat(result[0].username).isEqualTo("rick")
        assertThat(result[0].age).isEqualTo(10)
    }


    @Test
    fun findUserDto() {
        val u1 = User("rick", 10)
        val u2 = User("rick", 20)

        userRepository.save(u1)
        userRepository.save(u2)

        val result = userRepository.findUserDto()

        assertThat(result[0].username).isEqualTo("rick")
        assertThat(result[0].age).isEqualTo(10)
    }

    @Test
    fun findByUsernameAndAgeGreaterThant() {
        val u1 = User("rick", 10)
        val u2 = User("rick", 20)

        userRepository.save(u1)
        userRepository.save(u2)

        val result = userRepository.findByUsernameAndAgeGreaterThan("rick", 15)

        assertThat(result[0].username).isEqualTo("rick")
        assertThat(result[0].age).isEqualTo(20)
    }

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