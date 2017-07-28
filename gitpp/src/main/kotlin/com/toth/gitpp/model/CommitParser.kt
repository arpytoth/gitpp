package com.toth.gitpp.model

import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CommitParser {

    private val dateFormatter = SimpleDateFormat("EEE MMM d HH:mm:ss yyyy")

    fun parseTime(dateStr: String): Date {
        return dateFormatter.parse(dateStr.trim());
    }

    fun readCommits(str: String): ArrayList<Commit> {
        return readCommits(str.byteInputStream())
    }

    fun readCommits(istream: InputStream): ArrayList<Commit> {
        val commits = ArrayList<Commit>()
        var commit: Commit? = null

        istream.bufferedReader().useLines {
            it.forEach {
                if (it.startsWith("commit ")) {
                    val shaStr = it.substring(7)
                    val sha = shaStr.split(" ")

                    commit = Commit()
                    commit?.id = sha[0]
                    commit?.parentId = if (sha.size == 2) sha[1] else ""

                    commits.add(commit!!)
                } else if (it.startsWith("Date:")) {
                    commit?.timestamp = parseTime(it.substring(5))
                } else if (it.startsWith("Author:")) {
                    commit?.author = it.substring(8)
                } else if (it.startsWith("    ")) {
                    commit?.message = it.substring(4)
                }
            }
        }

        return setRenderingHints(commits)
    }


    fun computeGraph(commits: ArrayList<Commit>, graph: ArrayList<Commit>, depth: Int, root: String){

        var parent = ""

        for (commit in commits) {
            if (commit.id == root) return
            if (commit.visited) continue


            if (parent == "") {

                commit.visited = true
                commit.parentDepth = depth
                parent = commit.parentId
                graph.add(commit)

            } else if (parent.equals(commit.id)) {
                computeGraph(commits, graph, depth + 1, commit.id)

                commit.visited = true
                commit.parentDepth = depth
                parent = commit.parentId
                graph.add(commit)
            }
        }
    }

    fun setRenderingHints(commits: ArrayList<Commit>): ArrayList<Commit> {
        var graph = ArrayList<Commit>()
        computeGraph(commits, graph, 0, "")
        return graph
    }

}
