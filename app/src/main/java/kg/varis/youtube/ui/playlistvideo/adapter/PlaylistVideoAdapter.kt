package kg.varis.youtube.ui.playlistvideo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import kg.varis.youtube.data.model.Playlist
import kg.varis.youtube.databinding.ItemPlaylistVideoBinding

class PlaylistVideoAdapter(val onClick: (Playlist.Item) -> Unit) :
    Adapter<PlaylistVideoAdapter.PlaylistVideoViewHolder>() {

    private var videoList = mutableListOf<Playlist.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(lists: List<Playlist.Item>) {
        this.videoList = lists as MutableList<Playlist.Item>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaylistVideoAdapter.PlaylistVideoViewHolder {
        return PlaylistVideoViewHolder(
            ItemPlaylistVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: PlaylistVideoAdapter.PlaylistVideoViewHolder,
        position: Int
    ) {
        holder.onBind(videoList[position])
    }

    override fun getItemCount(): Int =
        videoList.size


   inner class PlaylistVideoViewHolder(private val binding: ItemPlaylistVideoBinding) :
        ViewHolder(binding.root) {
        fun onBind(video: Playlist.Item) {
            with(binding) {
                ivVideo.load(video.snippet.thumbnails.default.url)
                tvVideoName.text = video.snippet.title

                itemView.setOnClickListener {
                    onClick(video)
                }
            }
        }
    }
}