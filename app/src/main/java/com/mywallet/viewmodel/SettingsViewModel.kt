package com.mywallet.viewmodel


import com.mywallet.common.BaseViewModel
import com.mywallet.data.dao.SettingsDao
import com.mywallet.data.models.SettingsItem
import javax.inject.Inject

class SettingsViewModel@Inject constructor(
        private var settingsDao: SettingsDao
) : BaseViewModel() {

    fun getAllSettingsItems(): List<SettingsItem> {
        return settingsDao.loadAllSettingsItems()
    }

}