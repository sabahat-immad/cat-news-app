package saba.qazi.catnews.storydetail.di

import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import saba.qazi.catnews.storydetail.StoryDetailAPI


@Module
@InstallIn(FragmentComponent::class)
class StoryDetailModule {

    @Provides
    fun playlistDetailsAPI(retrofit: Retrofit) = retrofit.create(StoryDetailAPI::class.java)
}