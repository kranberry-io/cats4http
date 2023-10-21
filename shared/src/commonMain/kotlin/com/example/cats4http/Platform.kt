package com.example.cats4http

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform