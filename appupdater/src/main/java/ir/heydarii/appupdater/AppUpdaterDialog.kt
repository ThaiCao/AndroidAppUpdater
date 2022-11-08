package ir.heydarii.appupdater

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import ir.heydarii.appupdater.directlink.DirectLinkDownload
import ir.heydarii.appupdater.pojo.Store.*
import ir.heydarii.appupdater.pojo.UpdaterFragmentModel
import ir.heydarii.appupdater.pojo.UpdaterStoreList
import ir.heydarii.appupdater.stores.CafeBazaarStore
import ir.heydarii.appupdater.stores.GooglePlayStore
import ir.heydarii.appupdater.stores.IranAppsStore
import ir.heydarii.appupdater.stores.MyketStore
import ir.heydarii.appupdater.utils.serializable
import ir.heydarii.appupdater.utils.typeface

/**
 * Shows ForceUpdate Dialog Fragment
 */

private const val DATA_LIST = "DATA_LIST"

class AppUpdaterDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // setting isCancelable
        val data = arguments?.serializable<UpdaterFragmentModel>(DATA_LIST)
        setDialogCancelable(data?.isForceUpdate)

        // Set background for the dialog
        dialog?.window?.setBackgroundDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.dialog_background)
        )
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        val view = inflater.inflate(R.layout.fragment_app_updater_dialog, container, false)
        return view.apply {
            typeface?.let {
                findViewById<TextView>(R.id.txtTitle).typeface = it
                findViewById<TextView>(R.id.txtDescription).typeface = it
                findViewById<TextView>(R.id.txtOr).typeface = it
                findViewById<TextView>(R.id.txtStore).typeface = it
            }
        }
    }

    override fun onStart() {
        super.onStart()

        // make dialog's width matchParent
        dialog?.window?.setLayout(MATCH_PARENT, WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // getData that user set's via constructor
        getData()
    }

    private fun getData() {
        val data = arguments?.serializable<UpdaterFragmentModel>(DATA_LIST)
        val title = data?.title
        val description = data?.description
        val list = data?.list
        checkNotNull(list)
        setUpProperties(title, description, list)
    }

    private fun setDialogCancelable(cancelableMode: Boolean?) {
        cancelableMode?.let { isCancelable = it }
    }

    private fun setUpProperties(
        title: String?,
        description: String?,
        list: List<UpdaterStoreList>
    ) {
        requireView().findViewById<TextView>(R.id.txtTitle)?.text = title
        requireView().findViewById<TextView>(R.id.txtDescription)?.text = description

        hideOrLayoutIfNeeded(checkIfDirectAndStoreAvailable(list))

        setUpBothRecyclers(list)
    }

    private fun setUpBothRecyclers(list: List<UpdaterStoreList>?) {
        val directLinks by lazy { ArrayList<UpdaterStoreList>() }
        val storeLinks by lazy { ArrayList<UpdaterStoreList>() }

        list?.forEach {
            if (it.store == DIRECT_URL)
                directLinks.add(it)
            else
                storeLinks.add(it)
        }

        requireView().findViewById<RecyclerView>(R.id.recyclerDirect)?.adapter =
            DirectRecyclerAdapter(directLinks) { onListListener(it) }

        requireView().findViewById<RecyclerView>(R.id.recyclerStores)?.adapter =
            StoresRecyclerAdapter(storeLinks) { onListListener(it) }
    }

    private fun hideOrLayoutIfNeeded(storeAndDirectAvailable: Boolean) {
        requireView().findViewById<LinearLayout>(R.id.linearLayout).visibility =
            if (storeAndDirectAvailable) View.VISIBLE else View.GONE
    }

    private fun checkIfDirectAndStoreAvailable(list: List<UpdaterStoreList>) =
        list.map { it.store }
            .distinct()
            .toList()
            .partition {
                it == DIRECT_URL
            }.run {
                first.isNotEmpty() && second.isNotEmpty()
            }

    private fun onListListener(item: UpdaterStoreList) {
        when (item.store) {
            DIRECT_URL ->
                DirectLinkDownload().getApk(item.url, activity, parentFragmentManager)
            GOOGLE_PLAY ->
                GooglePlayStore().setStoreData(context, item)
            CAFE_BAZAAR ->
                CafeBazaarStore().setStoreData(context, item)
            MYKET ->
                MyketStore().setStoreData(context, item)
            IRAN_APPS ->
                IranAppsStore().setStoreData(context, item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        typeface = null
    }

    companion object {

        // fragment variable to make this dialog singleton
        private val fragment = AppUpdaterDialog()

        /**
         * get Instance method
         */
        fun getInstance(
            title: String? = "",
            description: String? = "",
            list: List<UpdaterStoreList> = listOf(),
            isForce: Boolean = false,
            tf: Typeface? = null
        ): AppUpdaterDialog {

            // set typeface in utils class to use later in application
            typeface = tf

            // bundle to add data to our dialog
            val bundle = Bundle()
            val data = UpdaterFragmentModel(title, description, list, !isForce)
            bundle.putSerializable(DATA_LIST, data)
            fragment.arguments = bundle
            return fragment
        }
    }
}
