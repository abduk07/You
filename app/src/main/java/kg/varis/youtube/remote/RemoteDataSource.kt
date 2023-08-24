package kg.varis.youtube.remote

import kg.varis.youtube.BuildConfig
import kg.varis.youtube.core.base.BaseDataSource
import kg.varis.youtube.core.network.Resource
import kg.varis.youtube.data.Api
import kg.varis.youtube.data.model.Playlist
import org.koin.dsl.module

val remoteDataSource = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private var apiService: Api) : BaseDataSource() {

    suspend fun getPlaylist(): Resource<Playlist> {
        return getResult {
            apiService.getPlaylist(
                BuildConfig.API_KEY, "snippet,contentDetails",
                "UCWOA1ZGywLbqmigxE4Qlvuw", 10
            )
        }
    }

    suspend fun getPlaylistVideo(id: String): Resource<Playlist> {
        return getResult {
            apiService.getPlaylistVideo(
                BuildConfig.API_KEY, part = "snippet,contentDetails",
                playlistId = id, maxResults = 10
            )
        }
    }
}
