package com.toth.gitpp.model

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


class Repository {

    private var workingDir: File? = null

    fun setProjectDir(projectDir: String) {
        this.workingDir = File(projectDir)
    }

    fun init() {
        execute("git", "init")
    }

    fun clone(url: String) {
        execute("git", "clone", url)
    }

    fun gitAddAll() {
        execute("git", "add", "--all")
    }

    fun gitCommit(message: String) {
        execute("git", "commit", "-a", "-m", message)
    }

    fun log(): String {
        return execute("git", "log")
    }

    fun logAll(): String {
        return execute("git", "log", "--all", "--parents")
    }

    private fun execute(vararg commands: String): String {
        var output: String = ""
        try {
            val rt = Runtime.getRuntime()
            val p = rt.exec(commands, arrayOfNulls<String>(0), workingDir)
            output = p.inputStream.bufferedReader().use { it.readText() }
        } catch (t: Throwable) {
            t.printStackTrace()
        }
        return output
    }
}