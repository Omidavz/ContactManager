package com.omidavz.contactmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.omidavz.contactmanager.adapter.ContactAdapter
import com.omidavz.contactmanager.databinding.ActivityMainBinding
import com.omidavz.contactmanager.repository.ContactRepository
import com.omidavz.contactmanager.room.Contact
import com.omidavz.contactmanager.room.ContactDatabase
import com.omidavz.contactmanager.viewmodel.ContactViewModel
import com.omidavz.contactmanager.viewmodel.ContactViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setUpViewModel()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        displayUserList()
    }

    private fun displayUserList() {
        contactViewModel.contacts.observe(this, Observer {
            binding.recyclerView.adapter = ContactAdapter(
                it , {selectedItem:Contact -> listItemClicked(selectedItem)}
            )
        })
    }

    private fun listItemClicked(selectedItem: Contact) {

        Toast.makeText(this,"Selected Name is ${selectedItem.name}",
            Toast.LENGTH_LONG).show()

        contactViewModel.initUpdateAndDelete(selectedItem)

    }

    private fun setUpViewModel() {
        val repository = ContactRepository(ContactDatabase(this))

        val factory = ContactViewModelFactory(repository)

        contactViewModel = ViewModelProvider(this, factory).get(ContactViewModel::class.java)

        binding.contactViewModel=contactViewModel
        binding.lifecycleOwner = this
    }
}