package com.example.podtrack.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface PodcastApi {
    @GET("search")
    suspend fun searchPodcasts(
        @Query("term") term: String,
        @Query("media") media: String = "podcast",
        @Query("limit") limit: Int = 50
    ): SearchResponse
}

data class SearchResponse(
    val resultCount: Int,
    val results: List<ITunesPodcastResult>
)

data class ITunesPodcastResult(
    val collectionId: Long,
    val collectionName: String,
    val artistName: String,
    val artworkUrl600: String,
    val feedUrl: String? = null
)