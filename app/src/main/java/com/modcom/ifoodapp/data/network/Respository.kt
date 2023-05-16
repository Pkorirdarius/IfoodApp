package com.modcom.ifoodapp.data.network

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Respository @Inject constructor(
    remoteDataSource: RemoteDataSource,
){
    val remote = remoteDataSource
}