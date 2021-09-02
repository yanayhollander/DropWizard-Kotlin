package resources

import com.codahale.metrics.annotation.Timed
import data.Saying
import java.util.concurrent.atomic.AtomicLong
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/hello-world")
@Produces(APPLICATION_JSON)
class HWResource(private var template: String, private var defaultName: String) {

    private val counter = AtomicLong()

    @GET
    @Timed
    fun getHello(@QueryParam("name") name: String?): Saying {
        val value = String.format(template, name?:defaultName)
        return Saying(counter.incrementAndGet(), value)
    }

    @POST
    @Timed
    fun postHello(@Valid saying: Saying): Saying {
        return Saying(saying.id, "bye ${saying.content}")
    }
}