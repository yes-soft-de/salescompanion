package de.sixbits.salescompanion.view.main.recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.sixbits.salescompanion.R
import de.sixbits.salescompanion.callbacks.OnContactClickListener
import de.sixbits.salescompanion.data_model.SalesContactDataModel
import de.sixbits.salescompanion.databinding.ContactRowBinding

private const val TAG = "ContactsRecyclerViewAda"

class ContactsRecyclerViewAdapter constructor(
    private var contacts: List<SalesContactDataModel>,
    private val onContactClickListener: OnContactClickListener
) :
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
        var phone = ""
        if (contacts[position].phone != null) {
            phone = "${contacts[position].phone}"
        }
        holder.binding.tvContactRowTitle.text = fullName
        holder.binding.tvContactRowPhone.text = phone
        if (contacts[position].synced) {
            holder.binding.ivContactRowLeading.setImageResource(R.drawable.ic_circle_synced)
        }

        holder.binding.root.setOnClickListener {
            onContactClickListener.onContactClick(contact = contacts[position])
        }
    }

    fun replaceContacts(newContacts: List<SalesContactDataModel>) {
        Log.d(TAG, "replaceContacts: Replacing Contacts ${newContacts.size}")
        contacts = newContacts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = contacts.size
}