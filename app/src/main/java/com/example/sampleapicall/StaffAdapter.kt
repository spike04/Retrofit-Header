package com.example.sampleapicall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleapicall.models.Staff
import kotlinx.android.synthetic.main.item_row.view.*

class StaffAdapter(private val staffList: List<Staff>) :
    RecyclerView.Adapter<StaffAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffAdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return StaffAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: StaffAdapterHolder, position: Int) {
        return holder.bind(staffList[position])
    }

    override fun getItemCount() = staffList.size
}

class StaffAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(staff: Staff) {
        Glide.with(itemView.context).load(staff.image).into(itemView.imgStaff)
        itemView.txtStaffName.text = staff.name
        itemView.txtStaffDesignation.text = staff.designation
    }
}