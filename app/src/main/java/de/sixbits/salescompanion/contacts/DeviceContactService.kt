package de.sixbits.salescompanion.contacts

import android.content.ContentResolver
import android.content.ContentUris
import android.provider.ContactsContract
import de.sixbits.salescompanion.data_model.SalesContactDataModel
import java.util.*
import javax.inject.Inject


class DeviceContactService @Inject constructor(private val contentResolver: ContentResolver) {
    fun getContacts(): List<SalesContactDataModel> {

        val contactList: MutableList<SalesContactDataModel> = ArrayList()
        val contentResolver: ContentResolver = contentResolver

        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.CUSTOM_RINGTONE,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
        val cursor =
            contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                ContactsContract.Contacts.HAS_PHONE_NUMBER + ">0 AND LENGTH(" + ContactsContract.CommonDataKinds.Phone.NUMBER + ")>0",
                null,
                "display_name ASC"
            )

        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                val id =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))
                val person =
                    ContentUris.withAppendedId(
                        ContactsContract.Contacts.CONTENT_URI,
                        id.toLong()
                    )
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))

                var firstName = name
                var lastName = " "
                if (name.contains(" ")) {
                    firstName = name.split(" ")[0]
                    lastName = name.substring(firstName.length)
                }

                val phone =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                contactList.add(
                    SalesContactDataModel(
                        firstName = firstName,
                        lastName = lastName,
                        phone = phone,
                        email = "email",
                        company = " ",
                        createdAt = Calendar.getInstance().time,
                        updatedAt = Calendar.getInstance().time,
                    )
                )
            }
            cursor.close()
        }

        return contactList
    }
}
