package saba.qazi.catnews.newslist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import saba.qazi.catnews.newslist.NewsListAPI

@Module
@InstallIn(FragmentComponent::class)
class NewsListModule {

    private val BASE_URL = "https://f9d9eff0-df75-4a8c-96f7-bff4d86bb684.mock.pstmn.io"
    @Provides
    fun playlistApi(retrofit: Retrofit) : NewsListAPI = retrofit.create(NewsListAPI::class.java)

    @Provides
    fun retrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}