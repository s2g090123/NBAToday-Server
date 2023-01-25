package com.example

import com.example.client.client
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.client.plugins.cookies.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.runBlocking

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
    runBlocking {
        client.cookies("http://0.0.0.0:8080/")
    }
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}
