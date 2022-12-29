package com.pouyaheydari.appupdater.core.stores

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.pouyaheydari.appupdater.core.pojo.Store
import com.pouyaheydari.appupdater.core.pojo.UpdaterStoreList

const val MI_APP_STORE_URL = "mimarket://details?id="

/**
 * shows apk in [Xiaomi GetApp store](https://global.app.mi.com/)
 */
class MiGetAppStore : Stores() {
    override fun setStoreData(context: Context?, item: UpdaterStoreList) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("$MI_APP_STORE_URL${item.packageName}"))
        showStore(context, intent, item, Store.MI_GET_APP_STORE)
    }
}
