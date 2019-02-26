package com.example.konrad.bitcoinmarket.injection.module

import android.app.Application
import android.content.Context
import com.example.konrad.bitcoinmarket.base.BaseView
import dagger.Module
import dagger.Provides

/**
 * Module which provides all dependencies about context
 */
@Module
@Suppress("unused")
object ContextModule {

    /**
     * Provides the context
     * @param baseView used to provide context
     * @return Context
     */
    @Provides
    @JvmStatic
    internal fun provideContext(baseView: BaseView): Context{
        return baseView.getContext()
    }

    /**
    * Provides the Application Context
    * @param context Context in which the application is running
    * @return the Application Context to be provided
    */
    @Provides
    @JvmStatic
    internal fun provideApplication(context: Context): Application{
        return context.applicationContext as Application
    }
}