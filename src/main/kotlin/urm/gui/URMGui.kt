package urm.gui

import javafx.beans.property.SimpleBooleanProperty
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.control.Slider
import javafx.scene.layout.AnchorPane
import tornadofx.View
import tornadofx.add
import tornadofx.singleAssign
import urm.core.*
import urm.gui.components.URMGuiProgram
import urm.gui.components.URMGuiRegisters

class URMGui : View() {
    override val root: AnchorPane by fxml()
    val programPane: AnchorPane by fxid()
    val registersWrapper: ScrollPane by fxid()

    val btnStep: Button by fxid()
    val btnPlay: Button by fxid()
    val btnReset: Button by fxid()
    val sldSpeed: Slider by fxid()

    private var progress = SimpleBooleanProperty(false);

    val btnAdd: Button by fxid()
    val btnCopy: Button by fxid()
    val btnZero: Button by fxid()
    val btnJump: Button by fxid()
    var guiProgram: URMGuiProgram by singleAssign()
    var guiRegisters: URMGuiRegisters by singleAssign()

    init {
        btnStep.onAction = EventHandler {
            guiProgram.Step()
        }

        btnReset.onAction = EventHandler {
            progress.value = false;
//            guiRegisters.Reset()
            guiProgram.Reset()
        }

        btnPlay.onAction = EventHandler {
            if (!progress.value) {
                guiProgram.Reset()
                runAsync {
                    while (guiProgram.Step()) {
                        if (!progress.value)
                            return@runAsync
                        Thread.sleep(sldSpeed.value.toLong())
                        if (!progress.value)
                            return@runAsync
                    }
                    progress.value = false
                    guiProgram.Reset()
                }
            }
            progress.value = !progress.value
        }

        registersWrapper.onScroll = EventHandler {
            registersWrapper.hvalue -= it.getDeltaY();
        }

        btnAdd.onAction = EventHandler {
            guiProgram.AddCommand(URMCommandAdd(1))
        }
        btnCopy.onAction = EventHandler {
            guiProgram.AddCommand(URMCommandCopy(1, 1))
        }
        btnZero.onAction = EventHandler {
            guiProgram.AddCommand(URMCommandZero(1))
        }
        btnJump.onAction = EventHandler {
            guiProgram.AddCommand(URMCommandJump(1,1,1))
        }

        guiProgram = URMGuiProgram(URMProgram())
        AnchorPane.setBottomAnchor(guiProgram.root, 0.0);
        AnchorPane.setLeftAnchor(guiProgram.root, 0.0);
        AnchorPane.setRightAnchor(guiProgram.root, 0.0);
        AnchorPane.setTopAnchor(guiProgram.root, 0.0);
        programPane.add(guiProgram.root)

        val registers = guiProgram.program?.registers!!
        guiRegisters = URMGuiRegisters(registers)
        registers.registerListeners.add(guiRegisters)
        registersWrapper.add(guiRegisters.root)

        guiProgram.Reset(true)

        registersWrapper.hmaxProperty().bind(guiRegisters.root.widthProperty())
    }

}