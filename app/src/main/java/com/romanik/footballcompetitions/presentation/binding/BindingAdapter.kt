package com.romanik.footballcompetitions.presentation.binding

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.caverock.androidsvg.SVG
import com.bumptech.glide.GenericRequestBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.StreamEncoder
import com.bumptech.glide.load.resource.file.FileToStreamDecoder
import com.romanik.footballcompetitions.presentation.core.platform.svg.SvgDecoder
import com.romanik.footballcompetitions.presentation.core.platform.svg.SvgDrawableTranscoder
import com.romanik.footballcompetitions.presentation.core.platform.svg.SvgSoftwareLayerSetter
import java.io.InputStream


private fun buildSvgLoader(context: Context): GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> {
    return Glide.with(context)
        .using(Glide.buildStreamModelLoader(Uri::class.java, context), InputStream::class.java)
        .from(Uri::class.java)
        .`as`(SVG::class.java)
        .transcode(SvgDrawableTranscoder(), PictureDrawable::class.java)
        .sourceEncoder(StreamEncoder())
        .cacheDecoder(FileToStreamDecoder<SVG>(SvgDecoder()))
        .decoder(SvgDecoder())
        .listener(SvgSoftwareLayerSetter<Uri>())
}

@BindingAdapter("app:loadImage")
fun ImageView.loadImage(url: String?) {
    url?.let {
        val uri = Uri.parse(it)
        buildSvgLoader(this.context).diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .load(uri)
            .into(this)
    }
}

