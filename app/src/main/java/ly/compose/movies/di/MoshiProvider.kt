package ly.compose.movies.di

import androidx.activity.ComponentActivity
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoshiProvider {

    @Provides
    @Singleton
    fun moshiProvider() = Moshi.Builder().build()
}