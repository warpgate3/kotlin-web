package info.m2sj.kotlinweb.fps

import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
internal class FbsServiceTest(
    val fbsService: FbsService,
    val fbsRepository: FbsRepository
) {

    @Test
    fun save() {
        val fbsDto = FbsDto(null, 2, 92.1f)
        val result = fbsService.save(fbsDto)
        assertThat(fbsDto.bloodSugar, `is`(equalTo(result.bloodSugar)))
        assertThat(0, `is`(lessThan<Long>(result.id)))
        println(">>>" + result.id)
//        val searched = result.id?.let {
//            fbsRepository.findById(it).get()
//        }
        val searched = fbsRepository.findById(result.id!!).get()
        assertThat(fbsDto.bloodSugar, `is`(equalTo(searched.bloodSugar)))
    }
}