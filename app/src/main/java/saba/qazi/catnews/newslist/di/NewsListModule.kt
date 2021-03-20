package saba.qazi.catnews.newslist.di

import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import saba.qazi.catnews.newslist.NewsListAPI

val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

@Module
@InstallIn(FragmentComponent::class)
class NewsListModule {

    private val BASE_URL = "https://332ea193-ba5c-4be9-8981-5eca5d3c99f9.mock.pstmn.io"
    @Provides
    fun playlistApi(retrofit: Retrofit) : NewsListAPI = retrofit.create(NewsListAPI::class.java)

    @Provides
    fun retrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}