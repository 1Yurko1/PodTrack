package com.example.podtrack.data.mapper

import com.example.podtrack.data.api.ITunesPodcastResult
import com.example.podtrack.data.local.PodcastEntity
import com.example.podtrack.domain.model.Podcast

fun ITunesPodcastResult.toEntity(): PodcastEntity = PodcastEntity(
    id = collectionId,
    title = collectionName,
    author = artistName,
    imageUrl = artworkUrl600,
    feedUrl = feedUrl ?: "",
    isFavorite = false
)

fun PodcastEntity.toDomain(): Podcast = Podcast(
    id = id,
    title = title,
    author = author,
    imageUrl = imageUrl,
    feedUrl = feedUrl,
    isFavorite = isFavorite,
    lastPlayedEpisodeId = lastPlayedEpisodeId,
    playbackPositionMs = playbackPositionMs
)

fun Podcast.toEntity(): PodcastEntity = PodcastEntity(
    id = id,
    title = title,
    author = author,
    imageUrl = imageUrl,
    feedUrl = feedUrl,
    isFavorite = isFavorite,
    lastPlayedEpisodeId = lastPlayedEpisodeId,
    playbackPositionMs = playbackPositionMs
)