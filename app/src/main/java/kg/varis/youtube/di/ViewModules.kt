package kg.varis.youtube.di

import kg.varis.youtube.ui.playlists.viewmodel.PlaylistViewModel
import kg.varis.youtube.ui.playlistvideo.viewmodel.PlaylistVideoViewModel
import kg.varis.youtube.ui.video.viewmodel.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel {PlaylistVideoViewModel(get())}
    viewModel {VideoViewModel(get())}
}