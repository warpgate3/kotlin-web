package info.m2sj.kotlinweb.tutorial

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.lang.UsesSunHttpServer
import java.util.*

interface UserRepository: JpaRepository<User, Long> {
    @Query("select u from User u where u.username= :username and u.age= :age")
    fun findUser(@Param("username") username: String, @Param("age") age: Int): MutableList<User>

    fun findByUsernameAndAgeGreaterThan(username: String, age: Int): MutableList<User>

    @Query("select new info.m2sj.kotlinweb.tutorial.UserDto(u.id, u.username, g.name, u.age) " +
            "from User u left outer join u.group g")
    fun findUserDto(): MutableList<UserDto>

    @Query("select u from User u where u.username in :names")
    fun findUsers(@Param("names") names:List<String>): MutableList<User>

    fun findByAge(age: Int, pageable: Pageable): Page<User>

    @Query(value = "select u from User u", countQuery = "select count(u.username) from User u")
    fun findUserAllCountBy(pageable: Pageable): Page<User>

    fun findTop3By(): MutableList<User>

    /*
    * @Modifying 예외 발생 default=false 이지만 벌크 연산후 영속성 컨텍스트와 불일치 해결을 위해 필요
    */
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.age = u.age + 1 where u.age >= :age")
    fun bulkAgePlus(@Param("age") age:Int)

    @Query("select u from User u left join fetch u.group")
    fun findUserFetchJoin(): MutableList<User>

    @EntityGraph(attributePaths = ["group"])
    fun findByUsername(@Param("username") username:String)
}