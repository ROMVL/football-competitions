package com.romanik.footballcompetitions.core.platform.svg

import android.graphics.drawable.PictureDrawable

import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder
import com.caverock.androidsvg.SVG
import com.romanik.footballcompetitions.core.extentions.emptyString

class SvgDrawableTranscoder : ResourceTranscoder<SVG, PictureDrawable> {
    override fun transcode(toTranscode: Resource<SVG>): Resource<PictureDrawable> {
        val svg = toTranscode.get()
        val picture = svg.renderToPicture()
        val drawable = PictureDrawable(picture)
        return SimpleResource(drawable)
    }

    override fun getId(): String = String.emptyString()
}