package com.example.podtrack.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "podcasts")
data class PodcastEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val author: String,
    val imageUrl: String,
    val feedUrl: String,
    val isFavorite: Boolean = false,
    val lastPlayedEpisodeId: String? = null,
    val playbackPositionMs: Long = 0L
)
