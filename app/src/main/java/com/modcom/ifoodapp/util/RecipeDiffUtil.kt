package com.modcom.ifoodapp.util

import androidx.recyclerview.widget.DiffUtil
import com.modcom.ifoodapp.foodrecipe.Result

class RecipeDiffUtil(
    private val newList: List<Result>,
    private val oldList: List<Result>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

}