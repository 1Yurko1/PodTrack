package com.example.podtrack.domain.usecase

import com.example.podtrack.data.repository.PodcastRepository
import com.example.podtrack.domain.model.Podcast
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val repo: PodcastRepository) {
    operator fun invoke(): Flow<List<Podcast>> = repo.getFavorites()
}