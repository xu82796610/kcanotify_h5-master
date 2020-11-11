package com.antest1.kcanotify.h5.dto

enum class EConfigType(val value: Int) {
    VMESS(1),
    CUSTOM(2),
    SHADOWSOCKS(3),
    SOCKS(4);

    companion object {
        fun fromInt(value: Int) = values().firstOrNull { it.value == value }
    }
}