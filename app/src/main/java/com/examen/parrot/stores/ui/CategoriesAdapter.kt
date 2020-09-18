package com.examen.parrot.stores.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.examen.parrot.R
import com.examen.parrot.databinding.ListGroupBinding
import com.examen.parrot.databinding.ListItemBinding
import com.examen.parrot.login.framework.UserPreferences
import com.examen.parrot.stores.domain.RequestUpdateProduct
import com.examen.parrot.stores.domain.ResponseUpdateProduct
import com.examen.parrot.stores.framework.Product
import com.examen.parrot.stores.interactors.GetStores
import com.examen.parrot.utils.StatusProduct
import kotlinx.coroutines.Job
import javax.inject.Singleton


@Singleton
class CategoriesAdapter internal constructor(
    private val context: Context,
    private var titleList: MutableList<String>,
    private var dataList: MutableMap<String, MutableList<Product>>,private val lifecycleOwner: LifecycleOwner,private val updateProduct:(MutableLiveData<ResponseUpdateProduct>,String,String,RequestUpdateProduct)-> Job
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

        val binding = DataBindingUtil.inflate<ListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item,
            parent,
            false
        )

        var product = getChild(listPosition, expandedListPosition) as Product
        binding.product=product
        binding.lifecycleOwner=lifecycleOwner
        Glide.with(context).load(product.imageUrl).into(binding.imageProduct)

        val token = UserPreferences.getInstance(context).getValue(UserPreferences.DataType.TOKEN) as String
        binding.status.setOnClickListener {
            Log.d("Info","Se hizp click")

            val statusProduct = if(binding.status.text == "AVAILABLE"){
                StatusProduct.UNAVAILABLE
            }else{
                StatusProduct.AVAILABLE
            }

            var response= MutableLiveData<ResponseUpdateProduct>()

           updateProduct(response,token,product.uuid,RequestUpdateProduct(statusProduct.name))

            response.observe(lifecycleOwner, Observer {
                Log.d("Info","Se actualizo yeihhh $it")
                product=it.result
            })

        }

        return binding.root

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

        val binding = DataBindingUtil.inflate<ListGroupBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_group,
            parent,
            false
        )


        val listTitle = getGroup(listPosition) as String
        val itemsCount= dataList?.get(this.titleList?.get(listPosition))?.size ?: 0

        binding.title=listTitle
        binding.items=itemsCount.toString()
        binding.lifecycleOwner=lifecycleOwner
        binding.invalidateAll()

        return binding.root
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