package urm.core

import javafx.beans.property.SimpleIntegerProperty
import tornadofx.onChange

class URMRegister(var value: Int = 0) {
    var property: SimpleIntegerProperty = SimpleIntegerProperty(value)

    init {
        property.onChange { newValue ->
            value = newValue
        }
    }
}
