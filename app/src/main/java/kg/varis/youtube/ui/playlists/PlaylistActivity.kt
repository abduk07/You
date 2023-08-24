package kg.varis.youtube.ui.playlists

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import kg.varis.youtube.core.base.BaseActivity
import kg.varis.youtube.core.network.Status
import kg.varis.youtube.data.model.Playlist
import kg.varis.youtube.databinding.ActivityPlaylistBinding
import kg.varis.youtube.ui.playlists.adapter.PlaylistAdapter
import kg.varis.youtube.ui.playlists.viewmodel.PlaylistViewModel
import kg.varis.youtube.ui.playlistvideo.PlaylistVideo
import kg.varis.youtube.utils.ConnectionLiveData
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistActivity : BaseActivity<ActivityPlaylistBinding, PlaylistViewModel>() {

    private val adapter by lazy { PlaylistAdapter(this::onClick) }

    private fun onClick(playlist: Playlist.Item) {
        val intent = Intent(this, PlaylistVideo::class.java)
        val bundle = Bundle()
        bundle.putSerializable(KEY_, playlist)
        intent.putExtras(bundle)
        startActivity(intent)
    }


    override fun inflateViewBinding(): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    override val viewModel: PlaylistViewModel by viewModel()


    override fun isConnection() {
        super.isConnection()
        ConnectionLiveData(application).observe(this) {
            if (it) {
                binding.internetConnection.visibility = View.VISIBLE
                binding.noConnection.visibility = View.GONE
            } else {
                binding.internetConnection.visibility = View.GONE
                binding.noConnection.visibility = View.VISIBLE
                initViewModel()
            }
        }
    }

    override fun initViews() {
        super.initViews()
        binding.recyclerView.adapter = adapter
        isConnection()
    }


    override fun initViewModel() {
        super.initViewModel()
        viewModel.getPlayList().observe(this) { playlists ->
            when (playlists.status) {
                Status.SUCCESS -> {
//                    adapter.addData(playlists.data?.items as MutableList<Playlist.Item>)
                    playlists.data?.items?.let { adapter.addData(it) }
                    binding.progressBar.isVisible = false
                }
                Status.ERROR -> {
                    Toast.makeText(this, playlists.message, Toast.LENGTH_SHORT).show()
                    Log.e("OLOLO", "initViewModel: ${playlists.message}")
                    Throwable(playlists.message)
                    binding.progressBar.isVisible = false
                }

                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }

    companion object {
        private const val KEY_ = "KEY_FOR_SECOND_ACTIVITY"
    }
}
