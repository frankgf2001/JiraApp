package com.aquadevs.jira.core.function

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.aquadevs.jira.core.function.Constants.JIRA_DATA_STORE
import com.aquadevs.jira.core.function.Constants.JIRA_PASSWORD
import com.aquadevs.jira.core.function.Constants.JIRA_REMEMBER_PASSWORD
import com.aquadevs.jira.core.function.Constants.JIRA_USER

object DataStorePreferences {
    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = JIRA_DATA_STORE)

    suspend fun Context.saveValuesRememberPassword(isRememberPassword:Boolean, user:String, password:String){
        dataStore.edit { pref ->
            pref[stringPreferencesKey(name = JIRA_USER)] = if (isRememberPassword) user else ""
            pref[stringPreferencesKey(name = JIRA_PASSWORD)] = if (isRememberPassword) password else ""
            pref[booleanPreferencesKey(name = JIRA_REMEMBER_PASSWORD)] = isRememberPassword
        }
    }

    suspend fun Context.getValuesRememberPassword(onResponse:(String, String, Boolean) -> Unit){
        this.dataStore.data.collect { pref ->
            onResponse(
                pref[stringPreferencesKey(JIRA_USER)].orEmpty(),
                pref[stringPreferencesKey(JIRA_PASSWORD)].orEmpty(),
                pref[booleanPreferencesKey(JIRA_REMEMBER_PASSWORD)] ?: false
            )
        }
    }

    suspend fun Context.updatePassword(password:String){
        dataStore.edit { pref ->
            pref[stringPreferencesKey(name = JIRA_PASSWORD)] = password
        }
    }
}