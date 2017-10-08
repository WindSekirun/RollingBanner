package pyxis.uzuki.live.rollingbanner

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout

/**
 * RollingBanner
 * class: RollingViewPagerIndicator
 * Created by pyxis on 2017. 10. 9..
 */
class RollingViewPagerIndicator constructor(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {
    private var margin = 10
    private var resId = View.NO_ID
    private lateinit var viewPager: ViewPager
    private val adapter: RollingViewPagerAdapter<*>
        get() = viewPager.adapter as RollingViewPagerAdapter<*>

    fun setIndicatorResource(resId: Int, margin: Int) {
        this.margin = margin
        this.resId = resId
    }

    fun setViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                selectIndicator(adapter.getRealPosition(position))
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    fun selectIndicator(position: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.isSelected = i == position
        }
    }


    fun notifyDataSetChanged() {
        removeAllViews()
        if (adapter.realCount < 2) {
            visibility = View.GONE
        } else {
            visibility = View.VISIBLE
            addIndicator()
        }
    }

    private fun addIndicator() {
        val currentPosition = adapter.getRealPosition(viewPager.currentItem)
        for (i in 0 until adapter.realCount) {
            val imageView = ImageView(context)
            if (resId == View.NO_ID) {
                imageView.setImageResource(R.drawable.default_indicator)
            } else {
                imageView.setImageResource(resId)
            }

            if (currentPosition == i) {
                imageView.isSelected = true
            }

            if (i != adapter.realCount - 1) {
                val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.rightMargin = margin
                imageView.layoutParams = params
            }

            imageView.setOnClickListener { viewPager.currentItem = i }
            addView(imageView)
        }
    }

}