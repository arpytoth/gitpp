package com.toth.gitpp.components

import com.toth.gitpp.model.Commit
import javax.swing.table.AbstractTableModel

class CommitTableModel: AbstractTableModel {

    val commits: ArrayList<Commit>

    constructor(commits: ArrayList<Commit>) {
        this.commits = commits
    }

    override fun getRowCount(): Int {
        return commits.size
    }

    override fun getColumnCount(): Int {
        return 3
    }

    override fun getValueAt(row: Int, col: Int): Any {
        return when (col) {
            0 -> commits[row]
            1 -> commits[row].message
            2 -> commits[row].id
            else -> ""
        }
    }


}
