package kg.varis.youtube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.varis.youtube.core.network.Resource
import kg.varis.youtube.data.Api
import kg.varis.youtube.data.model.Playlist
import kg.varis.youtube.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers

class Repository(private val apiService: Api, private val dataSource: RemoteDataSource) {


    fun getPlaylists(): LiveData<Resource<Playlist>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlaylist()
            emit(response)
        }
    }

    fun getPlaylistVideo(id: String): LiveData<Resource<Playlist>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlaylistVideo(id)
            emit(response)
        }
    }
}
