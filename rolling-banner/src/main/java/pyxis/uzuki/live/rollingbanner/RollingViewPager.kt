package pyxis.uzuki.live.rollingbanner

import android.content.Context
import android.os.Handler
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller
import pyxis.uzuki.live.richutilskt.utils.tryCatch

/**
 * RollingBanner
 * class: RollingViewPager
 * Created by pyxis on 2017. 10. 9..
 */
class RollingViewPager constructor(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {
    private var flingAble = true
    private var smoothScroll = true
    private var scrollHandler: Handler = Handler()
    private var delay = 0L
    private var lastEnableRolling: Boolean = false

    private val autoScrolling = Runnable {
        setCurrentItem(currentItem + 1, smoothScroll)
        startAutoScrolling()
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return try {
            this.flingAble && super.onInterceptTouchEvent(event)
        } catch (var3: Exception) {
            false
        }
    }

    override fun onTouchEvent(arg0: MotionEvent): Boolean {
        return this.flingAble && super.onTouchEvent(arg0)
    }

    private fun startAutoScrolling() {
        this.scrollHandler.removeCallbacks(this.autoScrolling)
        this.scrollHandler.postDelayed(this.autoScrolling, this.delay)
    }

    private fun stopAutoScrolling() {
        this.scrollHandler.removeCallbacks(this.autoScrolling)
    }

    internal fun setScrollingDelay(millis: Int) {
        tryCatch {
            val viewpager = ViewPager::class.java
            val scroller = viewpager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(this, DelayScroller(context, millis))
        }
    }

    internal fun setFlingAble(flag: Boolean) {
        this.flingAble = flag
    }

    internal fun enableRolling(enable: Boolean) {
        lastEnableRolling = enable
        if (enable) {
            this.startAutoScrolling()
        } else {
            this.stopAutoScrolling()
        }
    }

    internal fun notifyDataSetChanged() {
        adapter.notifyDataSetChanged()
        enableRolling(lastEnableRolling)
    }

    internal fun setAutoScrollingSmooth(smoothScroll: Boolean) {
        this.smoothScroll = smoothScroll
    }

    internal fun setDelay(delay: Long) {
        this.delay = delay
    }

    private inner class DelayScroller(context: Context, val durationScroll: Int = 250) : Scroller(context, DecelerateInterpolator()) {
        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
            super.startScroll(startX, startY, dx, dy, durationScroll)
        }

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, durationScroll)
        }
    }
}