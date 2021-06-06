package de.sixbits.salescompanion.contacts

import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import android.widget.Toast
import de.sixbits.salescompanion.data_model.SalesContactDataModel
import java.util.*
import javax.inject.Inject


class ContactService @Inject constructor(private val contentResolver: ContentResolver) {
    fun getContacts(): List<SalesContactDataModel> {

        // First request contacts
        val cur = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null
        ) ?: return listOf()


        val contacts = mutableListOf<SalesContactDataModel>()

        // If we have those, then start mapping data
        if (cur.count > 0) {
            while (cur.moveToNext()) {
                var firstName = ""
                var lastName = ""
                var phoneNumber = ""

                // If the contact doesn't have a number, well, we don't need it, so skip to the next
                if (cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                        .toInt() == 0
                ) {
                    continue
                }

                // The contact has a number, so get it
                val id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))
                val pCur: Cursor? = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    listOf(id).toTypedArray(),
                    null
                )

                while (pCur?.moveToNext() == true) {
                    phoneNumber =
                        pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    break
                }

                // Now we have the contact
                pCur?.close()

                // Now we assign the data
                val fullName =
                    cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                if (fullName.indexOf(" ") > -1) {
                    firstName = fullName.substring(0, fullName.indexOf(" "))
                    lastName = fullName.substring(fullName.indexOf(" "))
                } else {
                    firstName = fullName
                }

                // For now, it will be first name, last name and a phone
                contacts.add(
                    SalesContactDataModel(
                        lastName = lastName,
                        firstName = firstName,
                        phone = phoneNumber,
                        company = "Yes Soft Contact",
                        email = "No Email",
                        createdAt = Calendar.getInstance().time,
                        updatedAt = Calendar.getInstance().time,
                    )
                )
            }
        }

        cur.close()

        return contacts
    }
}