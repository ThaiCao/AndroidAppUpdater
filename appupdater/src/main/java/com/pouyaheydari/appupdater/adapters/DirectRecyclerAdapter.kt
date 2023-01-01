package com.pouyaheydari.appupdater.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pouyaheydari.appupdater.core.pojo.UpdaterStoreList
import com.pouyaheydari.appupdater.core.utils.tf
import com.pouyaheydari.appupdater.databinding.DownloadDirectItemBinding

/**
 * Adapter to show Direct download links
 */
internal class DirectRecyclerAdapter(
    private val list: List<UpdaterStoreList>,
    private val listener: (UpdaterStoreList) -> Unit,
) : RecyclerView.Adapter<DirectRecyclerAdapter.SoresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoresViewHolder =
        DownloadDirectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { SoresViewHolder(this) }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SoresViewHolder, position: Int) = holder.onBind(list[position])

    /**
     * Direct download ViewHolder
     */
    inner class SoresViewHolder(private val binding: DownloadDirectItemBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds data to layout
         */
        fun onBind(item: UpdaterStoreList) {
            val txtDirect = binding.txtDirect
            txtDirect.text = item.title
            if (tf != null) {
                txtDirect.typeface = tf
            }
            binding.root.setOnClickListener { listener(item) }
        }
    }
}