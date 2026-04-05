package com.example.podtrack.domain.usecase

import com.example.podtrack.data.repository.PodcastRepository
import com.example.podtrack.domain.model.Podcast
import javax.inject.Inject

class SearchPodcastsUseCase @Inject constructor(private val repo: PodcastRepository) {
    suspend operator fun invoke(query: String): List<Podcast> = repo.searchPodcasts(query)
}