package ua.kovcom.motivation

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import java.text.SimpleDateFormat
import java.util.*


/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [MotivationWidgetConfigureActivity]
 */
class MotivationWidget : AppWidgetProvider() {

    var sdf: SimpleDateFormat = SimpleDateFormat("HH:mm:ss")

    override fun onUpdate(
        context: Context, appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        for (i in appWidgetIds) {
            updateWidget(context, appWidgetManager, i)
        }
    }

    fun updateWidget(
        context: Context, appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val rv = RemoteViews(
            context.packageName,
            R.layout.motivation_widget
        )
        setUpdateTV(rv, context, appWidgetId)
        setList(rv, context, appWidgetId)
        setListClick(rv, context, appWidgetId)
        appWidgetManager.updateAppWidget(appWidgetId, rv)
    }

    fun setUpdateTV(
        rv: RemoteViews,
        context: Context?,
        appWidgetId: Int
    ) {
        rv.setTextViewText(
            R.id.tvUpdate,
            sdf.format(Date(System.currentTimeMillis()))
        )
        val updIntent = Intent(context, MotivationWidget::class.java)
        updIntent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        updIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, intArrayOf(appWidgetId))
        val updPIntent = PendingIntent.getBroadcast(
            context,
            appWidgetId, updIntent, 0
        )
        rv.setOnClickPendingIntent(R.id.tvUpdate, updPIntent)
    }

    fun setList(
        rv: RemoteViews,
        context: Context?,
        appWidgetId: Int
    ) {
        val adapter = Intent(context, MyService::class.java)
        adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        rv.setRemoteAdapter(R.id.list_view, adapter)
    }

    private fun setListClick(
        rv: RemoteViews?,
        context: Context?,
        appWidgetId: Int
    ) {
    }
}