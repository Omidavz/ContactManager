package com.omidavz.contactmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omidavz.contactmanager.repository.ContactRepository

class ContactViewModelFactory(private val repository: ContactRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactViewModel(repository) as T
    }
}