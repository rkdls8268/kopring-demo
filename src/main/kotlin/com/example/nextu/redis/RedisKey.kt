package com.example.nextu.redis

data class RedisKey(val key: Key, val id: Int) {
    override fun toString(): String {
        return "${key.keyName}:$id"
    }
}

enum class Key(val keyName: String) {
    REFRESH_TOKEN("refreshToken");
}