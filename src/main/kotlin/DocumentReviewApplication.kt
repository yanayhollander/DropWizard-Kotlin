import application.HWHealthCheck
import io.dropwizard.Application
import io.dropwizard.setup.Environment
import resources.HWResource

class DocumentReviewApplication : Application<DocumentReviewConfiguration>() {

    override fun getName() = "document-review-service"

    override fun run(config: DocumentReviewConfiguration, env: Environment) {
        val hwResource = HWResource(config.template, config.defaultName)
        val healthCheck = HWHealthCheck(config.template)

        env.jersey().register(hwResource)
        env.healthChecks().register("hcTemplate", healthCheck)
    }
}

fun main(args: Array<String>) = DocumentReviewApplication().run(*args)
