package com.apachat.liquidswipe.core.base

interface Layout {
  var clipPathProvider: ClipPathProvider
  var currentRevealPercent: Float

  fun revealForPercentage(percent: Float): Unit
}