package id.afdaldev.footballmatchscheduleapp

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import java.lang.Exception

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
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

fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.setIdLeague(idLeague: String) {
    val shareViewModel = activity?.run {
        ViewModelProviders.of(this)[ShareViewModel::class.java]
    } ?: throw Exception("Invalid Activity")
    shareViewModel.setIdLeague(idLeague)
}

fun Fragment.getIdLeague(): String {
    var idLeague = "ID_LEAGUE"
    val shareViewModel = activity?.run {
        ViewModelProviders.of(this)[ShareViewModel::class.java]
    } ?: throw Exception("Invalid Activity")

    shareViewModel.idLeague.observe(this, Observer {
        idLeague = it
    })
    return idLeague
}

fun Fragment.setIdHomeTeam(idHomeTeam: String) {
    val shareViewModel = activity?.run {
        ViewModelProviders.of(this)[ShareViewModel::class.java]
    } ?: throw Exception("Invalid Activity")
    shareViewModel.setIdHomeTeam(idHomeTeam)
}

fun Fragment.getIdHomeTeam(): String {
    var idHomeTeam = "ID_HOME_TEAM"
    val shareViewModel = activity?.run {
        ViewModelProviders.of(this)[ShareViewModel::class.java]
    } ?: throw Exception("Invalid Activity")

    shareViewModel.idHomeTeam.observe(this, Observer {
        idHomeTeam = it
    })
    return idHomeTeam
}

fun Fragment.setIdAwayTeam(idAwayTeam: String) {
    val shareViewModel = activity?.run {
        ViewModelProviders.of(this)[ShareViewModel::class.java]
    } ?: throw Exception("Invalid Activity")
    shareViewModel.setIdAwayTeam(idAwayTeam)
}

fun Fragment.getIdAwayTeam(): String {
    var idAwayTeam = "ID_AWAY_TEAM"
    val shareViewModel = activity?.run {
        ViewModelProviders.of(this)[ShareViewModel::class.java]
    } ?: throw Exception("Invalid Activity")

    shareViewModel.idAwayTeam.observe(this, Observer {
        idAwayTeam = it
    })
    return idAwayTeam
}