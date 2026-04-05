package com.example.podtrack.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PodcastDao {
    @Query("SELECT * FROM podcasts WHERE isFavorite = 1 ORDER BY id DESC")
    fun getFavorites(): Flow<List<PodcastEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(podcast: PodcastEntity)

    @Query("UPDATE podcasts SET lastPlayedEpisodeId = :epId, playbackPositionMs = :position WHERE id = :podcastId")
    suspend fun updatePlaybackState(podcastId: Long, epId: String?, position: Long)

    @Query("SELECT * FROM podcasts WHERE id = :id LIMIT 1")
    suspend fun getPodcastById(id: Long): PodcastEntity?
}