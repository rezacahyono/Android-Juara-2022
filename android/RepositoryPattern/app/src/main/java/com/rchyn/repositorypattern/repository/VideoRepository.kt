package com.rchyn.repositorypattern.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.rchyn.repositorypattern.database.VideosDatabase
import com.rchyn.repositorypattern.database.asDomainModel
import com.rchyn.repositorypattern.domain.DevByteVideo
import com.rchyn.repositorypattern.network.DevByteNetwork
import com.rchyn.repositorypattern.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository(
    private val database: VideosDatabase
) {

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlist = DevByteNetwork.devbytes.getPlaylist()
            database.videoDao().insertAll(playlist.asDatabaseModel())
        }
    }

    val videos: LiveData<List<DevByteVideo>> =
        Transformations.map(database.videoDao().getVideos()) {
            it.asDomainModel()
        }
}