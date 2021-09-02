import io.dropwizard.Configuration

class DocumentReviewConfiguration: Configuration() {
    var template: String = ""
    var defaultName: String = "Stranger"
}