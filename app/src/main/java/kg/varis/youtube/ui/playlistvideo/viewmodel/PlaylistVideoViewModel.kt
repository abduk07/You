package kg.varis.youtube.ui.playlistvideo.viewmodel

import androidx.lifecycle.LiveData
import kg.varis.youtube.core.base.BaseViewModel
import kg.varis.youtube.core.network.Resource
import kg.varis.youtube.data.model.Playlist
import kg.varis.youtube.repository.Repository

class PlaylistVideoViewModel(private val repository: Repository) : BaseViewModel() {

    fun getVideo(id: String): LiveData<Resource<Playlist>> {
        return repository.getPlaylistVideo(id)
    }
}