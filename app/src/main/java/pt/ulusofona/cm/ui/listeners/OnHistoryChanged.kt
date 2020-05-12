package pt.ulusofona.cm.ui.listeners

import pt.ulusofona.cm.data.local.entities.Operation

interface OnHistoryChanged {

    fun onHistoryChanged(history: List<Operation>)

}