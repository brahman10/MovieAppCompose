package com.lyscraft.base

interface BaseMapper<R, E> {
    fun mapData(entity: R): E
    fun reverseMapData(content: E): R
}