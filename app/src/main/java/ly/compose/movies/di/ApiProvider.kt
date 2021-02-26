package ly.compose.movies.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.DefineComponent
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiProvider  {

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi) = RetrofitBuilder.services(moshi = moshi)
}