package com.example.podtrack.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.podtrack.data.api.PodcastApi
import com.example.podtrack.data.local.PodcastDao
import com.example.podtrack.data.mapper.toDomain
import com.example.podtrack.data.mapper.toEntity
import com.example.podtrack.domain.model.Podcast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PodcastRepository @Inject constructor(
    private val api: PodcastApi,
    private val dao: PodcastDao,
    private val dataStore: DataStore<Preferences>
) {
    suspend fun searchPodcasts(query: String): List<Podcast> {
        val response = api.searchPodcasts(query)
        val entities = response.results.map { it.toEntity() }
        // Кэшируем найденное в Room (UPSERT перезапишет только изменившиеся поля)
        entities.forEach { dao.upsert(it) }
        return entities.map { it.toDomain() }
    }

    fun getFavorites(): Flow<List<Podcast>> =
        dao.getFavorites().map { entities -> entities.map { it.toDomain() } }

    suspend fun toggleFavorite(podcast: Podcast) {
        val updated = podcast.copy(isFavorite = !podcast.isFavorite)
        dao.upsert(updated.toEntity())
    }

    suspend fun updatePlaybackState(podcastId: Long, epId: String?, positionMs: Long) {
        dao.updatePlaybackState(podcastId, epId, positionMs)
    }

    suspend fun <T> saveSetting(key: Preferences.Key<T>, value: T) {
        dataStore.edit { it[key] = value }
    }
}