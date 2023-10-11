package com.omidavz.contactmanager.viewmodel

import android.text.TextUtils
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omidavz.contactmanager.repository.ContactRepository
import com.omidavz.contactmanager.room.Contact
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository) :
    ViewModel(), Observable {

    val contacts = repository.getAllContacts()

    private var isUpdateOrDelete = false
    private lateinit var contactToUpdateOrDelete: Contact

    @Bindable
    val inputName = MutableLiveData<String?>()


    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String?>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String?>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear"
    }


    fun saveOrUpdate() {
        if (isUpdateOrDelete) { // false = update / true = new
            // Update Contact
            contactToUpdateOrDelete.name = inputName.value!!
            contactToUpdateOrDelete.email = inputEmail.value!!
            update(contactToUpdateOrDelete)


        } else {
            // New Contact
            if (!TextUtils.isEmpty(inputName.value) && !TextUtils.isEmpty(inputEmail.value!!)) {

                val name = inputName.value!!
                val email = inputEmail.value!!
                var contact = Contact(0, name, email)
                insert(contact)
                inputName.value = null
                inputEmail.value = null
            }
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(contactToUpdateOrDelete)

        } else {
            deleteAll()
        }

    }


    fun insert(contact: Contact) =
        viewModelScope.launch {
            repository.insert(contact)


        }

    fun update(contact: Contact) =
        viewModelScope.launch {
            repository.update(contact)

            // Resetting the Buttons and Fields
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "SAVE"
            clearAllOrDeleteButtonText.value = "Clear"

        }

    fun delete(contact: Contact) =
        viewModelScope.launch {
            repository.delete(contact)

            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "SAVE"
            clearAllOrDeleteButtonText.value = "Clear"
        }

    fun deleteAll() =
        viewModelScope.launch {
            repository.deleteAll()


        }


    fun initUpdateAndDelete(selectedItem: Contact) {
        inputName.value = selectedItem.name
        inputEmail.value = selectedItem.email
        isUpdateOrDelete = true
        contactToUpdateOrDelete = selectedItem
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}