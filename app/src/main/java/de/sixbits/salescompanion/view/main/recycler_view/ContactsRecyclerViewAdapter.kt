package de.sixbits.salescompanion.view.main.recycler_view

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.sixbits.salescompanion.data_model.SalesContactDataModel
import de.sixbits.salescompanion.databinding.ContactRowBinding

class ContactsRecyclerViewAdapter constructor(private val contacts: List<SalesContactDataModel>) :
    RecyclerView.Adapter<ContactsRecyclerViewAdapter.ContactsRecyclerViewHolder>() {

    class ContactsRecyclerViewHolder(val binding: ContactRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsRecyclerViewHolder {
        return ContactsRecyclerViewHolder(
            ContactRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactsRecyclerViewHolder, position: Int) {
        holder.binding.tvContactRowLeading.text = contacts[position].firstName.substring(0, 1)
        val fullName = "${contacts[position].firstName} ${contacts[position].lastName}"
        val phone = "${contacts[position].phone} "
        holder.binding.tvContactRowTitle.text = fullName
        holder.binding.tvContactRowTitle.text = phone

        val color = Color.BLUE
        if (holder.binding.ivContactRowLeading.background != null)
            holder.binding.ivContactRowLeading.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }

    override fun getItemCount(): Int = contacts.size
}