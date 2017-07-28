package com.toth.gitpp.model

import java.text.SimpleDateFormat
import java.util.*

class Commit {

    companion object {
        val dateFormatter = SimpleDateFormat("EEE MMM yyyy HH:mm:ss")
    }

    // Rendering Stuff
    var parentDepth: Int = 0
    var visited = false

    // Actual data
    var message: String = ""
    var author: String = ""
    var email: String = ""
    var timestamp: Date = Date()
    var id: String = ""
    var parentId: String = ""


    override fun toString(): String {
        var fd = dateFormatter.format(timestamp)
        return "Commit(message='$message', author='$author', email='$email', timestamp=$fd, id='$id', parentId='$parentId')"
    }

}