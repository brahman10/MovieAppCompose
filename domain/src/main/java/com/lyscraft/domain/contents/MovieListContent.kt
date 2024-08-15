package com.lyscraft.domain.contents

data class MovieListContent(
    val backdropPath: String?,
    val id: Int,
    val title: String?,
    val originalTitle: String?,
    val overview: String?,
    val posterPath: String?,
    val mediaType: String?,
    val adult: Boolean?,
    val originalLanguage: String?,
    val genreIds: ArrayList<Int>,
    val popularity: Double?,
    val releaseDate: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)