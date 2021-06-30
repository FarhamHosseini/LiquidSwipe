package com.apachat.liquidswipe.core.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.apachat.liquidswipe.core.base.ClipPathProvider
import com.apachat.liquidswipe.core.base.Layout
import com.apachat.liquidswipe.core.clippathprovider.Customize

open class FrameLayout : FrameLayout, Layout {
  private var path: Path? = null

  private var _clipPathProvider: ClipPathProvider = Customize()
  private var _currentRevealPercent: Float = 100f

  override var clipPathProvider = _clipPathProvider
  override var currentRevealPercent: Float
    get() = _currentRevealPercent
    set(value) {
      revealForPercentage(value)
    }

  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
    context,
    attrs,
    defStyleAttr
  )

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
  ) : super(context, attrs, defStyleAttr, defStyleRes)

  override fun draw(canvas: Canvas?) {
    try {
      canvas?.save()
      path?.let {
        canvas?.clipPath(it, clipPathProvider.op)
      }
      super.draw(canvas)
    } finally {
      canvas?.restore()
    }
  }

  override fun revealForPercentage(percent: Float) {
    if (percent == _currentRevealPercent) return
    _currentRevealPercent = percent
    path = clipPathProvider.getPath(percent, this@FrameLayout)
    invalidate()
  }
}