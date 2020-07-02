package ua.kovcom.motivation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

internal class MyAdapter(val items: List<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): String {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        view: View,
        container: ViewGroup
    ): View? {
        var convertView: View? = view
        if (convertView == null) {
            convertView =
                LayoutInflater.from(convertView).inflate(R.layout.list_layout, container, false)
        }
        (convertView!!.findViewById<View>(R.id.list_item) as TextView).text = getItem(position)
        return convertView
    }
}