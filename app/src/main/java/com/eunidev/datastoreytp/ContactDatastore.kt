package com.eunidev.datastoreytp

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactDatastore(context: Context) {

    private val datastore = context.createDataStore(name = "contact")

    companion object {
        val NAME_KEY = stringPreferencesKey("name")
        val PHONE_NUMBER_KEY = intPreferencesKey("phone_number")
    }

    suspend fun setName(name: String) {
        datastore.edit {
            it[NAME_KEY] = name
        }
    }

    suspend fun setPhoneNumber(num: Int) {
        datastore.edit {
            it[PHONE_NUMBER_KEY] = num
        }
    }

    val getName: Flow<String> = datastore.data.map {
        it[NAME_KEY] ?: ""
    }

    val getPhoneNumber: Flow<Int> = datastore.data.map {
        it[PHONE_NUMBER_KEY] ?: 0
    }

}