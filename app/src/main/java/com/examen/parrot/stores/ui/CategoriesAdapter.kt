package com.examen.parrot.stores.ui

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.examen.parrot.R
import com.examen.parrot.stores.framework.Product
import javax.inject.Singleton


@Singleton
class CategoriesAdapter internal constructor(
    private val context: Context,
    private var titleList: MutableList<String>,
    private var dataList: MutableMap<String, MutableList<Product>>
) : BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return this.dataList?.get(this.titleList?.get(listPosition))!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }




    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val product = getChild(listPosition, expandedListPosition) as Product
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_item, null)
        }
        val name = convertView!!.findViewById<TextView>(R.id.name)
        val image=convertView!!.findViewById<ImageView>(R.id.image_product)
        val price = convertView!!.findViewById<TextView>(R.id.price)
        val status = convertView!!.findViewById<TextView>(R.id.status)

        name.text=product.name
        price.text=product.price
        status.text=product.availability


        Glide.with(convertView.context).load(product.imageUrl).into(image)

        return convertView

    }

    override fun getChildrenCount(listPosition: Int): Int {
       val child =this.dataList?.get(this.titleList?.get(listPosition))!!
        return child.size ?: 0
    }

    override fun getGroup(listPosition: Int): String? {
        return this.titleList?.get(listPosition)
    }

    override fun getGroupCount(): Int {
        return this.titleList?.size ?: 0
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_group, null)
        }
        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.name)
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle


        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }


    fun updateChildList(keys:MutableList<String>,products:MutableMap<String,MutableList<Product>> ) {

        this.titleList=keys
        this.dataList=products

        notifyDataSetChanged()

    }



}