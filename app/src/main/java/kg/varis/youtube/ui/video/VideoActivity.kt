package kg.varis.youtube.ui.video

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import kg.varis.youtube.core.base.BaseActivity
import kg.varis.youtube.data.model.Playlist
import kg.varis.youtube.databinding.ActivityVideoBinding
import kg.varis.youtube.ui.video.viewmodel.VideoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoActivity : BaseActivity<ActivityVideoBinding, VideoViewModel>() {

    private val modelPlaylist by lazy { intent.extras?.getSerializable(KEY) as Playlist.Item }

    override fun inflateViewBinding(): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(layoutInflater)
    }

    override val viewModel: VideoViewModel by viewModel()

    override fun initViewModel() {
        super.initViewModel()
       // binding.tvTitle.text = modelPlaylist.snippet.title
        //binding.tvPlaylist.text = modelPlaylist.snippet.description
        binding.tvBack.setOnClickListener {
            finish()
        }

        lifecycle.addObserver(binding.videoView);

        binding.videoView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(modelPlaylist.contentDetails.videoId, 0f)
            }
        })
        binding.btnDownload.setOnClickListener {
        }
            // binding.ivImage.load(modelPlaylist.snippet.thumbnails.standard)
    }

    companion object {
        private const val KEY = "KEY_FOR_SECOND_ACTIVITY"
    }

}