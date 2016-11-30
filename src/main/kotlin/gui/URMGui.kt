package gui

import core.*
import gui.components.*
import javafx.event.EventHandler
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.Slider
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import tornadofx.View
import tornadofx.add
import tornadofx.plusAssign
import tornadofx.singleAssign

class URMGui : View() {
    override val root: AnchorPane by fxml()
    val programPane: AnchorPane by fxid()
    val btnStep: Button by fxid()
    val btnPlay: Button by fxid()
    val btnReset: Button by fxid()
    val sldSpeed: Slider by fxid()

    val btnAdd: Button by fxid()
    val btnCopy: Button by fxid()
    val btnZero: Button by fxid()
    val btnJump: Button by fxid()
    var guiProgramm: URMGuiProgram by singleAssign()

    init {
        btnStep.onAction = EventHandler {
            guiProgramm.Step()
        }

        btnReset.onAction = EventHandler {
            guiProgramm.Reset()
        }

        btnAdd.onAction = EventHandler {
            guiProgramm.AddCommand(URMCommandAdd(1))
        }
        btnCopy.onAction = EventHandler {
            guiProgramm.AddCommand(URMCommandCopy(1, 1))
        }
        btnZero.onAction = EventHandler {
            guiProgramm.AddCommand(URMCommandZero(1))
        }
        btnJump.onAction = EventHandler {
            guiProgramm.AddCommand(URMCommandJump(1,1,1))
        }

        guiProgramm = URMGuiProgram(URMProgram())
        AnchorPane.setBottomAnchor(guiProgramm.root, 0.0);
        AnchorPane.setLeftAnchor(guiProgramm.root, 0.0);
        AnchorPane.setRightAnchor(guiProgramm.root, 0.0);
        AnchorPane.setTopAnchor(guiProgramm.root, 0.0);
        programPane.add(guiProgramm.root)
    }

}