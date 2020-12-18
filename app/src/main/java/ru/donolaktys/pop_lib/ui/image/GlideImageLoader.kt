package ru.donolaktys.pop_lib.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.donolaktys.pop_lib.mvp.model.image.IImageLoader

class GlideImageLoader: IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide
            .with(container.context)
            .load(url)
            .into(container)
    }
}