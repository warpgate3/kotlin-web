package info.m2sj.kotlinweb

import org.junit.jupiter.api.Test

class Test {

    @Test
    fun test(): Unit {
//        val l = listOf(1, 2, 3, 4, 5)
//       // l.forEach { println(it) }
//
//        val l2 = l.map { "[$it]" }
//
//        val s = l.sumOf { it * 2 }

        val b = listOf(Pair(120, 80), Pair(110, 70))

        val rdc = b.runningReduce { acc, pair ->
            Pair((acc.first + pair.first) / 2, (acc.second + pair.second) / 2)
        }.last()

        println(rdc)


        //println(l2)
    }
}