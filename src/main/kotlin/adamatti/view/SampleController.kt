package adamatti.view

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.ws.rs.core.MediaType

@RestController
@RequestMapping(path = ["/person"], produces = [MediaType.APPLICATION_JSON])
class SampleController {
    @GetMapping
    fun list() = listOf(
        Person("Marcelo"),
        Person("Adamatti")
    )
}

data class Person(val name: String, val age: Int = 37)
