package com.costular.weathertest.data.model.mapper

interface Mapper<in R, out T> {
    fun transform(input: R): T
    fun transformList(inputList: List<R>): List<T>
}