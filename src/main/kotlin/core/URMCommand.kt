package core

import javafx.beans.property.SimpleBooleanProperty

open class URMCommand(var program: URMProgram?) {
    open fun Execute() {
        if (program != null) {
            program!!.currentCommandIndex++;
        }
    }

    var isActive : SimpleBooleanProperty = SimpleBooleanProperty(false)
}