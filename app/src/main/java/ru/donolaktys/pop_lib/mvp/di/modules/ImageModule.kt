package ru.donolaktys.pop_lib.mvp.di.modules

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.donolaktys.pop_lib.mvp.model.image.IImageLoader
import ru.donolaktys.pop_lib.ui.image.GlideImageLoader
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun loadImage() : IImageLoader<ImageView> = GlideImageLoader()
}