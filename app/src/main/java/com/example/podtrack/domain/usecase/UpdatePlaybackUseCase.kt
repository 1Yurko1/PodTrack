package com.example.podtrack.domain.usecase

import com.example.podtrack.data.repository.PodcastRepository
import javax.inject.Inject

class UpdatePlaybackUseCase @Inject constructor(private val repo: PodcastRepository) {
    suspend operator fun invoke(podcastId: Long, epId: String?, positionMs: Long) =
        repo.updatePlaybackState(podcastId, epId, positionMs)
}