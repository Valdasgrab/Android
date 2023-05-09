package lt.vgrabauskas.androidtopics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(context: Context) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)
    private val list = mutableListOf<Item>()

    fun add(vararg item: Item) {
        list.addAll(item)
        notifyDataSetChanged()
    }

    fun add(items: List<Item>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun remove(vararg item: Item) {
        list.removeAll(item)
        notifyDataSetChanged()
    }

    fun remove(items: List<Item>) {
        list.removeAll(items)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflater.inflate(R.layout.item, parent, false)

        view.findViewById<TextView>(R.id.idTextView).text = list[position].id.toString()
        view.findViewById<TextView>(R.id.text01TextView).text = list[position].text01
        view.findViewById<TextView>(R.id.text02TextView).text = list[position].text02
        return view
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()


    override fun getCount() = list.size
}