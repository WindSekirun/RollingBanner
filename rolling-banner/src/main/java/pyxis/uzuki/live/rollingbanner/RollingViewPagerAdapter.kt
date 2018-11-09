package pyxis.uzuki.live.rollingbanner

import android.content.Context
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * RollingBanner
 * class: RollingViewPagerAdapter
 * Created by pyxis on 2017. 10. 9..
 */
abstract class RollingViewPagerAdapter<T>(val context: Context, val itemList: ArrayList<T>) : androidx.viewpager.widget.PagerAdapter() {
    private var placeHolderCount = 100000
    private var lastEnableLooping = true
    val realCount: Int
        get() = this.itemList.size

    override fun getCount(): Int {
        return itemList.size * placeHolderCount
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return if (!this.itemList.isEmpty()) {
            val v = this.getView(getRealPosition(position), getItem(getRealPosition(position)))
            container.addView(v)
            v
        } else {
            val v = View(context)
            v
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(pager: View, obj: Any): Boolean {
        return pager === obj
    }

    /**
     * get Item of position
     */
    fun getItem(position: Int): T {
        return this.itemList[position]
    }

    fun enableLooping(enabled: Boolean) {
        lastEnableLooping = enabled
        placeHolderCount = if (enabled) {
            100000
        } else {
            1
        }
    }

    /**
     * get Real position of ViewPager
     */
    fun getRealPosition(page: Int) = page % realCount

    abstract fun getView(var1: Int, item: T): View
}