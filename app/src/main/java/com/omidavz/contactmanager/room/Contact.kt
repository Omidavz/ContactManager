package com.omidavz.contactmanager.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("omid_contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("contact_id")
    var id : Int ,
    @ColumnInfo("contact_name")
    var name : String,
    @ColumnInfo("contact_email")
    var email : String
)
