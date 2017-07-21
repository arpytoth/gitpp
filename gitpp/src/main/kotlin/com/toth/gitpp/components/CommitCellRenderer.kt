package com.toth.gitpp.components

import com.toth.gitpp.model.Commit
import javax.swing.*
import javax.swing.table.DefaultTableCellRenderer
import java.awt.*

class CommitCellRenderer : DefaultTableCellRenderer() {

    lateinit var model: Commit

    override fun getTableCellRendererComponent(table: JTable?,
                                               value: Any,
                                               isSelected: Boolean,
                                               hasFocus: Boolean,
                                               row: Int,
                                               col: Int): Component {

        if (value is Commit) model = value

        val c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col)
        val s = table!!.model.getValueAt(row, col).toString()

        if (s.equals("yellow", ignoreCase = true)) {
            c.foreground = Color.YELLOW
        } else {
            c.foreground = Color.RED
        }

        return c
    }

    override fun paintComponent(g: Graphics?) {
        if (g == null) {
            super.paintComponent(g)
            return
        }

        val pos = model.parentDepth * 10

        g.fillOval(pos, 0, 10, 10)
    }
}