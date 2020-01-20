package adamatti

import org.amshove.kluent.`should be`
import org.ff4j.FF4j
import org.junit.Test

class SampleTest {
    @Test
    fun `test toggle`(){
        val ff4j = FF4j("ff4j.xml")
        ff4j.check("hello") `should be` true
        ff4j.check("userStory3_1") `should be` false
        ff4j.check("userStory3_2") `should be` false
    }
}
