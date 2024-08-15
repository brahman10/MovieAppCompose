package com.lyscraft.data.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Yash Chaturvedi on 14/08/24.
 */
data class MovieListResponse(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<MovieListEntity> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)

data class MovieListEntity(
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("media_type") var mediaType: String? = null,
    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("genre_ids") var genreIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("video") var video: Boolean? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null
)