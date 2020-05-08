package io.yamsergey.appbar

import android.content.Context
import android.transition.TransitionManager
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.appbar.AppBarLayout

internal class AnimatedCollapsibleAppBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), AppBarLayout.OnOffsetChangedListener {

    private var closedResourceId: Int
    private var openedResourceId: Int
    private var transitionThreshold = 0.35f
    private var lastPosition = 0
    private var toolbarOpen = true

    private val openConstraintSet = ConstraintSet()
    private val closeConstraintSet = ConstraintSet()

    init {
        val attrsArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.AnimatedCollapsibleAppBar,
                0,
                0
        )

        try {
            openedResourceId = attrsArray.getResourceId(
                    R.styleable.AnimatedCollapsibleAppBar_opened_template,
                    R.layout.animated_collapsible_appbar_default_opened
            )

            if (openedResourceId == R.layout.animated_collapsible_appbar_default_opened) {
                openConstraintSet.clone(this)
            }

            closedResourceId = attrsArray.getResourceId(
                    R.styleable.AnimatedCollapsibleAppBar_collapsed_template,
                    R.layout.animated_collapsible_appbar_default_collapsed
            )

        } finally {
            attrsArray.recycle()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (parent is AppBarLayout) {
            val appBarLayout = parent as AppBarLayout
            appBarLayout.addOnOffsetChangedListener(this)
            if (openedResourceId != R.layout.animated_collapsible_appbar_default_opened) openConstraintSet.clone(context, openedResourceId)
            closeConstraintSet.clone(context, closedResourceId)
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (lastPosition == verticalOffset) return

        lastPosition = verticalOffset
        val progress = Math.abs(verticalOffset / appBarLayout.height.toFloat())

        if (toolbarOpen && progress > transitionThreshold) {
            TransitionManager.beginDelayedTransition(this)
            closeConstraintSet.applyTo(this)
            toolbarOpen = false
        } else if (!toolbarOpen && progress < transitionThreshold) {
            TransitionManager.beginDelayedTransition(this)
            openConstraintSet.applyTo(this)
            toolbarOpen = true
        }
    }
}