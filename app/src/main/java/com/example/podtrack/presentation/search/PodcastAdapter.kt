package com.example.podtrack.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.podtrack.databinding.ItemPodcastBinding
import com.example.podtrack.domain.model.Podcast

class PodcastAdapter(
    private val onItemClick: (Podcast) -> Unit,
    private val onFavoriteToggle: (Podcast) -> Unit
) : ListAdapter<Podcast, PodcastAdapter.VH>(PodcastDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemPodcastBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VH(private val b: ItemPodcastBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(item: Podcast) {
            b.tvTitle.text = item.title
            b.tvAuthor.text = item.author
            b.btnFavorite.setImageResource(
                if (item.isFavorite) android.R.drawable.btn_star_big_on
                else android.R.drawable.btn_star_big_off
            )
            Glide.with(b.ivCover)
                .load(item.imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(b.ivCover)

            b.root.setOnClickListener { onItemClick(item) }
            b.btnFavorite.setOnClickListener {
                onFavoriteToggle(item)
                // Визуальный фидбек без ожидания БД
                b.btnFavorite.setImageResource(
                    if (!item.isFavorite) android.R.drawable.btn_star_big_on
                    else android.R.drawable.btn_star_big_off
                )
            }
        }
    }

    class PodcastDiff : DiffUtil.ItemCallback<Podcast>() {
        override fun areItemsTheSame(oldItem: Podcast, newItem: Podcast) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Podcast, newItem: Podcast) =
            oldItem == newItem
    }
}