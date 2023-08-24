package kg.varis.youtube.ui.video.viewmodel

import androidx.lifecycle.LiveData
import kg.varis.youtube.core.base.BaseViewModel
import kg.varis.youtube.core.network.Resource
import kg.varis.youtube.data.model.Playlist
import kg.varis.youtube.repository.Repository

class VideoViewModel(private val repository: Repository) : BaseViewModel() {
    fun getPlaylistVideo(): LiveData<Resource<Playlist>> {
        return repository.getPlaylists()
    }
}