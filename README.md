# LiquidSwipe
LiquidSwipe is a [ViewPager](https://developer.android.com/reference/kotlin/androidx/viewpager/widget/ViewPager) library that can be used to make Awesome Onboarding designs.

## Usage
#### Set up the dependency
1. Add the mavenCentral() repository to your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		mavenCentral()
	}
}
```
2. Add the LiquidSwipe dependency in the build.gradle:
```
implementation group: 'com.apachat', name: 'liquidswipe-android', version: '1.10.6'
```

Badge:
-----
[![Maven Central](https://img.shields.io/maven-central/v/com.apachat/liquidswipe-android.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.apachat%22%20AND%20a:%22liquidswipe-android%22)

#### Use `ViewPager` instead of the normal `ViewPager`
```
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.apachat.liquidswipe.core.ViewPager
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

#### Use a `Layout` as the base container in the fragment layouts
```
<?xml version="1.0" encoding="utf-8"?>
<com.apachat.liquidswipe.core.layout.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".DummyFragment">

    <!--  Fill with your views, just like you would in a normal ConstraintLayout  -->

</com.apachat.liquidswipe.core.layout.ConstraintLayout>

<!--  Also supports FrameLayout & LinearLayout  -->
```

### And you're done, easy-peasy. ^_^

## Touch Interactive - Making the LiquidSwipe wave center Y value match the touch Y value

Rather than having the wave center Y value always be layout.height/2 , it would be more aesthetically pleasing for it to be the same as the touch Y value. 
The following code can be used to dynamically change the `waveCenterY` based on the touch position on the `ViewPager`.
(The reason this isn't done internally in the library is because the viewpager layouts don't get the touch events when said touch events are consumed directly by the viewpager)

1. In the `Activity`/`Fragment` class containing the `ViewPager`
```kotlin
// Create an array of ClipPathProvider, one for each layout in the PagerAdapter
val Customize = Array(titleArray.count()) {
    Customize()
}

// Pass the ClipPathProvider array to the adapter
viewpager.adapter = CustomPagerAdapter(this, Customize)
// Similar logic can also be applied for your custom FragmentPagerAdapter/FragmentStatePagerAdapter

// Listen to onTouch events on the viewpager and update the waveCenterY value of the ClipPathProviders
viewpager.setOnTouchListener { _, event ->
    val waveCenterY = event.y
    Customize.map {
        it.waveCenterY = waveCenterY
    }
    false
}
```
2. In the `PagerAdapter`
```kotlin
// Set the layout's clipPathProvider to the corresponding `Customize`
(layout as? Layout)?.clipPathProvider = Customize[position]
```

## Bugs and Feedback
For bugs, questions and discussions please use the [Github Issues](https://github.com/FarhamHosseini/LiquidSwipe/issues).
