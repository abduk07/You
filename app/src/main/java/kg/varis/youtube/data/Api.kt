package kg.varis.youtube.data

import kg.varis.youtube.data.model.Playlist
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("playlists")
    suspend fun getPlaylist(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int
    ): Response<Playlist>



    @GET("playlistItems")
    suspend fun getPlaylistVideo(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: Int
    ): Response<Playlist>

}