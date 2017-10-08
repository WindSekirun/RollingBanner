package pyxis.uzuki.live.rollingbanner

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * RollingBanner
 * class: RollingViewPagerAdapter
 * Created by pyxis on 2017. 10. 9..
 */
abstract class RollingViewPagerAdapter<T>(val itemList: ArrayList<T>) : PagerAdapter() {
    private val placeHolderCount = 100000
    val realCount: Int
        get() = this.itemList.size

    override fun getCount(): Int {
        return itemList.size * placeHolderCount
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any? {
        return if (!this.itemList.isEmpty()) {
            val v = this.getView(getRealPosition(position))
            container.addView(v)
            v
        } else {
            null
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

    /**
     * get Real position of ViewPager
     */
    fun getRealPosition(page: Int) = page % realCount

    abstract fun getView(var1: Int): View


}