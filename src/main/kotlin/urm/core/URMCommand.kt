package urm.core

import javafx.beans.property.SimpleBooleanProperty

open class URMCommand(var program: URMProgram?) {
    open fun Execute() {
        if (program != null) {
            program!!.currentCommandIndex++;
        }
    }

    fun Remove() {
        program?.RemoveCommand(this)
    }

    var isActive : SimpleBooleanProperty = SimpleBooleanProperty(false)
}