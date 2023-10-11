package com.omidavz.contactmanager.repository

import com.omidavz.contactmanager.room.Contact
import com.omidavz.contactmanager.room.ContactDatabase

class ContactRepository(private val db : ContactDatabase) {


    fun getAllContacts() = db.getContactDao().getAllContactsInDB()

    suspend fun insert(contact: Contact)=
        db.getContactDao().addContact(contact)

    suspend fun delete(contact: Contact)=
        db.getContactDao().deleteContact(contact)

    suspend fun update(contact: Contact)=
        db.getContactDao().updateContact(contact)

    suspend fun deleteAll() = db.getContactDao().deleteAll()

}