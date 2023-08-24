package kg.varis.youtube.ui.playlists.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kg.varis.youtube.data.model.Playlist
import kg.varis.youtube.databinding.ItemPlaylistBinding
import kg.varis.youtube.loadImage

class PlaylistAdapter(val onClick: (Playlist.Item) -> Unit) : Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private var list = mutableListOf<Playlist.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(lists: List<Playlist.Item>) {
        this.list = lists as MutableList<Playlist.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int =
        list.size


    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class PlaylistViewHolder(private val binding: ItemPlaylistBinding) :
        ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(item: Playlist.Item) {
            with(binding) {
                binding.ivPlaylistImage.loadImage(item.snippet.thumbnails.default.url)
                binding.namePlaylist.text = item.snippet.title
                binding.tvAmountVideo.text = item.contentDetails.itemCount.toString() + " video series"

                itemView.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }
}