package com.wrathbyte.questify

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform