import data.Saying
import io.dropwizard.client.JerseyClientBuilder
import io.dropwizard.testing.ResourceHelpers
import io.dropwizard.testing.junit.DropwizardAppRule
import org.junit.ClassRule
import org.junit.Test
import javax.ws.rs.client.Entity
import kotlin.test.assertEquals

class DocumentReviewApplicationTest {
    companion object {
        @ClassRule
        @JvmField
        val RULE = DropwizardAppRule(
            DocumentReviewApplication::class.java,
            ResourceHelpers.resourceFilePath("test-config.yaml")
        )
    }

    @Test
    fun `test GET with param`() {
        val client = JerseyClientBuilder(RULE.environment).build("test client")
        val name = "yanay"
        val response = client.target(
            String.format("http://localhost:%d/hello-world?name=$name", RULE.localPort)
        )
            .request()
            .get()

        assertEquals(200, response.status)
        val entity = response.readEntity(Saying::class.java)
        assertEquals(1, entity.id)
        assertEquals("Hello, yanay!", entity.content)
    }

    @Test
    fun `test GET without param`() {
        val client = JerseyClientBuilder(RULE.environment).build("test client")
        val response = client.target(
            String.format("http://localhost:%d/hello-world", RULE.localPort)
        )
            .request()
            .get()

        assertEquals(200, response.status)
        val entity = response.readEntity(Saying::class.java)
        assertEquals(1, entity.id)
        assertEquals("Hello, Stranger!", entity.content)
    }

    @Test
    fun `test POST`() {
        val client = JerseyClientBuilder(RULE.environment).build("test client")

        val response = client.target(
            String.format("http://localhost:%d/hello-world", RULE.localPort)
        )
            .request()
            .post(Entity.json("""{"id": 1, "content" : "bob"}"""))

        assertEquals(200, response.status)
        val entity = response.readEntity(Saying::class.java)
        assertEquals(1, entity.id)
        assertEquals("bye bob", entity.content)
    }
}