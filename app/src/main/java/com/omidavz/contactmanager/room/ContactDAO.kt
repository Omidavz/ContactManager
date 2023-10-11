package com.omidavz.contactmanager.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDAO {

    @Insert
    suspend fun addContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM omid_contact")
    fun getAllContactsInDB():LiveData<List<Contact>>

    @Query("DELETE FROM omid_contact")
    suspend fun deleteAll()

}