package ua.kovcom.motivation

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService.RemoteViewsFactory
import java.text.SimpleDateFormat
import java.util.*

class MyFactory internal constructor(var context: Context, intent: Intent) :
    RemoteViewsFactory {
    var data: ArrayList<String>? = null
    var sdf: SimpleDateFormat
    var widgetID: Int

    override fun onCreate() {
        data = ArrayList()
    }

    override fun getCount(): Int {
        return data!!.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewAt(position: Int): RemoteViews {
        val rView = RemoteViews(
            context.packageName,
            R.layout.list_layout
        )
        rView.setTextViewText(R.id.list_item, data!![position])
        return rView
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun onDataSetChanged() {
        data!!.clear()
        data!!.add(sdf.format(Date(System.currentTimeMillis())))
        data!!.add(hashCode().toString())
        data!!.add(widgetID.toString())
        for (i in 3..14) {
            data!!.add("Item $i")
        }
    }

    override fun onDestroy() {}

    init {
        sdf = SimpleDateFormat("HH:mm:ss")
        widgetID = intent.getIntExtra(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        )
    }
}