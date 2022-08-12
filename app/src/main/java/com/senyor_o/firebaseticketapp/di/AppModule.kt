package com.senyor_o.firebaseticketapp.di

import android.app.Application
import androidx.room.Room
import com.senyor_o.firebaseticketapp.data.repository.TicketRepositoryImpl
import com.senyor_o.firebaseticketapp.data.room.TicketDatabase
import com.senyor_o.firebaseticketapp.domain.repository.TicketRepository
import com.senyor_o.firebaseticketapp.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTicketDatabase(app: Application) = Room.databaseBuilder(
        app,
        TicketDatabase::class.java,
        DATABASE_NAME
    )
        .build()

    @Provides
    @Singleton
    fun provideRepository(db: TicketDatabase): TicketRepository =
        TicketRepositoryImpl(db.ticketDao)


}