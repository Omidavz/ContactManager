package com.omidavz.contactmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.omidavz.contactmanager.R
import com.omidavz.contactmanager.databinding.CardItemBinding
import com.omidavz.contactmanager.room.Contact

class ContactAdapter(var contactList: List<Contact> ,private val clickListener : (Contact)->Unit) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {


    class ContactViewHolder(val binding: CardItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(contact: Contact , clickListener: (Contact) -> Unit){
            binding.nameTextView.text = contact.name
            binding.emailTextView.text = contact.email

            binding.listItemList.setOnClickListener(){
                clickListener(contact)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding : CardItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.card_item,parent,false)

        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position], clickListener)

    }


}