package gui.controlls

import javafx.scene.control.IndexRange
import javafx.scene.control.TextField

class IntegerTextField : TextField() {
    private fun validate(text: String): Boolean {
        return (text.isNullOrEmpty() || text.matches("""\d+""".toRegex()))
    }

    override fun replaceText(start: Int, end: Int, text: String?) {
        val oldValue = this.text
        super.replaceText(start, end, text)
        val newValue = this.text
        if (newValue.isNullOrEmpty() || newValue.isNullOrBlank() ) {
            this.text = "0"
        } else {
            if (!validate(newValue)) {
                this.text = oldValue
            }
        }
    }
}

