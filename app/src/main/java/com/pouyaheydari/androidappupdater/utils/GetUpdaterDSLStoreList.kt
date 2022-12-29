package com.pouyaheydari.androidappupdater.utils

import android.content.Context
import com.pouyaheydari.androidappupdater.R
import com.pouyaheydari.appupdater.core.pojo.Store
import com.pouyaheydari.appupdater.dsl.store

fun getDSLList(context: Context) = listOf(
    store {
        store = Store.DIRECT_URL
        title = context.getString(R.string.direct_download, "1")
        icon = R.mipmap.ic_launcher
        url = APK_URL
        packageName = SAMPLE_PACKAGE_NAME
    },
    store {
        store = Store.DIRECT_URL
        title = context.getString(R.string.direct_download, "2")
        icon = R.mipmap.ic_launcher
        url = APK_URL
        packageName = SAMPLE_PACKAGE_NAME
    },
    store {
        store = Store.GOOGLE_PLAY
        title = context.getString(R.string.play)
        icon = R.drawable.appupdater_ic_google_play
        packageName = SAMPLE_PACKAGE_NAME
        url = WEBSITE_URL
    },
    store {
        store = Store.CAFE_BAZAAR
        title = context.getString(R.string.bazaar)
        icon = R.drawable.appupdater_ic_bazar
        packageName = SAMPLE_PACKAGE_NAME
        url = WEBSITE_URL
    },
    store {
        store = Store.MYKET
        title = context.getString(R.string.myket)
        icon = R.drawable.appupdater_ic_myket
        packageName = SAMPLE_PACKAGE_NAME
        url = WEBSITE_URL
    },
    store {
        store = Store.HUAWEI_APP_GALLERY
        title = context.getString(R.string.app_gallery)
        icon = R.drawable.appupdater_ic_app_gallery
        packageName = SAMPLE_PACKAGE_NAME
        url = WEBSITE_URL
    },
    store {
        store = Store.SAMSUNG_GALAXY_STORE
        title = context.getString(R.string.galaxy_store)
        icon = R.drawable.appupdater_ic_galaxy_store
        packageName = SAMPLE_PACKAGE_NAME
        url = WEBSITE_URL
    },
    store {
        store = Store.AMAZON_APP_STORE
        title = context.getString(R.string.amazon_store)
        icon = R.drawable.appupdater_ic_amazon_app_store
        packageName = SAMPLE_PACKAGE_NAME
        url = WEBSITE_URL
    },
    store {
        store = Store.APTOIDE
        title = context.getString(R.string.aptoide)
        icon = R.drawable.appupdater_ic_aptoide
        packageName = SAMPLE_PACKAGE_NAME
        url = WEBSITE_URL
    },
    store {
        store = Store.FDROID
        title = context.getString(R.string.fdroid)
        icon = R.drawable.appupdater_ic_fdroid
        packageName = FDROID_SAMPLE_PACKAGE_NAME
        url = WEBSITE_URL
    },
    store {
        store = Store.MI_GET_APP_STORE
        title = context.getString(R.string.mi_get_app)
        icon = R.drawable.appupdater_ic_get_app_store
        packageName = GET_APP_SAMPLE_PACKAGE_NAME
        url = WEBSITE_URL
    },
    store {
        store = Store.ONE_STORE_APP_MARKET
        title = context.getString(R.string.one_store)
        icon = R.drawable.appupdater_ic_one_store
        packageName = ONE_STORE_SAMPLE_PACKAGE_NAME
        url = WEBSITE_URL
    },
    store {
        store = Store.OPPO_APP_MARKET
        title = context.getString(R.string.oppo_app_market)
        icon = R.drawable.appupdater_ic_oppo_app_market
        packageName = SAMPLE_PACKAGE_NAME
        url = WEBSITE_URL
    },
)