package id.afdaldev.footballmatchscheduleapp.utils

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.room.TypeConverter
import com.squareup.picasso.Picasso

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun Fragment.addFragment(fragment: Fragment, frameId: Int) {
    fragmentManager?.beginTransaction()?.replace(frameId, fragment)?.addToBackStack(null)?.commit()
}

fun Fragment.replaceFragment(fragment: Fragment, frameId: Int) {
    requireActivity().supportFragmentManager.beginTransaction().replace(frameId, fragment)
        .addToBackStack(null).commit()
}

fun imgPicasso(load: String, target: ImageView) {
    Picasso.get().load(load).fit().into(target)
}

object Converter {
    @TypeConverter
    @JvmStatic
    fun toString(data: Any?): String? {
        return data?.toString()
    }

    @TypeConverter
    @JvmStatic
    fun toAny(data: String?): Any? = data
}