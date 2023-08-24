package kg.varis.youtube.ui.playlists.viewmodel

import androidx.lifecycle.LiveData
import kg.varis.youtube.data.model.Playlist
import kg.varis.youtube.App
import kg.varis.youtube.core.base.BaseViewModel
import kg.varis.youtube.core.network.Resource
import kg.varis.youtube.repository.Repository

class PlaylistViewModel(private val repository: Repository) : BaseViewModel() {

    fun getPlayList(): LiveData<Resource<Playlist>> {
        return repository.getPlaylists()
    }
}