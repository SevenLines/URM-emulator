import tornadofx.Stylesheet
import tornadofx.importStylesheet

class Styles : Stylesheet() {
    init {
        importStylesheet("/resources/style.css")
    }
}