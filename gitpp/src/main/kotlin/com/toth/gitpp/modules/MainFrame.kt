package com.toth.gitpp.modules

import com.toth.gitpp.components.CommitCellRenderer
import com.toth.gitpp.components.CommitTableModel
import com.toth.gitpp.model.CommitParser
import com.toth.gitpp.model.Repository
import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTable


class MainFrame : JFrame {

    constructor() {
        title = "Hello World"
        defaultCloseOperation = EXIT_ON_CLOSE

        var repo = Repository()
        repo.setProjectDir("playground")

        var log = repo.logAll()
        var parser = CommitParser()
        val commits = parser.readCommits(log)


        val table = JTable()
        table.model = CommitTableModel(commits)
        val col = table.columnModel.getColumn(0)
        col.cellRenderer = CommitCellRenderer()


        val scrollPane = JScrollPane(table)

        add(scrollPane, BorderLayout.CENTER)
        setSize(800, 600)
    }

}
