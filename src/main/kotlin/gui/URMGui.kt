package gui

import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import tornadofx.View

class URMGui : View() {
    override val root: BorderPane by fxml()
    val btnStep: Button by fxid()
    val btnPlay: Button by fxid()
}