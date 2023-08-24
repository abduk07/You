package kg.varis.youtube.ui.playlistvideo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import kg.varis.youtube.core.base.BaseActivity
import kg.varis.youtube.core.network.Status.*
import kg.varis.youtube.data.model.Playlist
import kg.varis.youtube.databinding.ActivityPlaylistVideoBinding
import kg.varis.youtube.ui.playlistvideo.adapter.PlaylistVideoAdapter
import kg.varis.youtube.ui.playlistvideo.viewmodel.PlaylistVideoViewModel
import kg.varis.youtube.ui.video.VideoActivity
import kg.varis.youtube.utils.ConnectionLiveData
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistVideo : BaseActivity<ActivityPlaylistVideoBinding, PlaylistVideoViewModel>() {

    private val modelPlaylist by lazy { intent.extras?.getSerializable(KEY_) as Playlist.Item }


    override fun inflateViewBinding(): ActivityPlaylistVideoBinding {
        return ActivityPlaylistVideoBinding.inflate(layoutInflater)
    }

    override val viewModel: PlaylistVideoViewModel by viewModel()

    private val adapter = PlaylistVideoAdapter(this::onClick)

    private fun onClick(video: Playlist.Item) {
        val intent = Intent(this, VideoActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(KEY, video)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getVideo(modelPlaylist.id).observe(this) {

            when (it.status) {
                SUCCESS -> {
                    binding.progressBar.isVisible = false
                    binding.recyclerView.adapter = adapter
                    binding.tvTitle.text = modelPlaylist.snippet.title
                    binding.tvDescription.text = modelPlaylist.snippet.description
                    binding.tvCountVideo.text = modelPlaylist.contentDetails.itemCount.toString()
                    adapter.addData(it.data?.items as MutableList<Playlist.Item>)
                    binding.tvBack.setOnClickListener {
                        finish()
                    }
                }
                ERROR -> {
                    binding.progressBar.isVisible = false
                }
                LOADING -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }

    override fun isConnection() {
        super.isConnection()
        ConnectionLiveData(application).observe(this) {
            if (it) {
                binding.btnPlay.setOnClickListener {
                    binding.internetConnection.visibility = View.VISIBLE
                    binding.internetConnection.visibility = View.GONE
                }
            } else {
                binding.internetConnection.visibility = View.GONE
                binding.notConnection.visibility = View.VISIBLE
                initViewModel()
            }
        }
    }


    companion object {
        private const val KEY_ = "KEY_FOR_SECOND_ACTIVITY"

        private const val KEY = "KEY_FOR_SECOND_ACTIVITY"
    }
}
