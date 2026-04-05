package com.example.podtrack.domain.usecase

import com.example.podtrack.data.repository.PodcastRepository
import com.example.podtrack.domain.model.Podcast
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(private val repo: PodcastRepository) {
    suspend operator fun invoke(podcast: Podcast) = repo.toggleFavorite(podcast)
}