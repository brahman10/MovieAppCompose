package com.lyscraft.data.mappers

import com.lyscraft.base.BaseMapper
import com.lyscraft.data.entities.MovieListEntity
import com.lyscraft.domain.contents.MovieListContent
import javax.inject.Inject

/**
 * Created by Yash Chaturvedi on 14/08/24.
 */
class MovieListEntityMapper @Inject constructor() : BaseMapper<MovieListEntity, MovieListContent> {
    override fun mapData(entity: MovieListEntity): MovieListContent {
        return entity.let {
            MovieListContent(
                it.backdropPath,
                it.id,
                it.title,
                it.originalTitle,
                it.overview,
                it.posterPath,
                it.mediaType,
                it.adult,
                it.originalLanguage,
                it.genreIds,
                it.popularity,
                it.releaseDate,
                it.video,
                it.voteAverage,
                it.voteCount
            )
        }
    }

    override fun reverseMapData(content: MovieListContent): MovieListEntity {
        return content.let {
            MovieListEntity(
                it.backdropPath,
                it.id,
                it.title,
                it.originalTitle,
                it.overview,
                it.posterPath,
                it.mediaType,
                it.adult,
                it.originalLanguage,
                it.genreIds,
                it.popularity,
                it.releaseDate,
                it.video,
                it.voteAverage,
                it.voteCount
            )
        }
    }
}