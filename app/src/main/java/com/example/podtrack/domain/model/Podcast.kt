package com.example.podtrack.domain.model

data class Podcast(
    val id: Long,
    val title: String,
    val author: String,
    val imageUrl: String,
    val feedUrl: String,
    val isFavorite: Boolean = false,
    val lastPlayedEpisodeId: String? = null,
    val playbackPositionMs: Long = 0L
)
