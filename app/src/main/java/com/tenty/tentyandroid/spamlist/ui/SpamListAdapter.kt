package com.tenty.tentyandroid.spamlist.ui

import android.telephony.PhoneNumberUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tenty.tentyandroid.R
import com.tenty.tentyandroid.db.entity.Spam
import com.tenty.tentyandroid.util.DateHelper
import com.tenty.tentyandroid.util.extensions.inflate
import com.tenty.tentyandroid.util.types.ContactType
import com.tenty.tentyandroid.util.types.getStringAsContactType
import kotlinx.android.synthetic.main.item_view_spam.view.*

class SpamListAdapter(
    private val listener: (Spam) -> Unit
): RecyclerView.Adapter<SpamListAdapter.ViewHolder>() {
    private var dataSet: MutableList<Spam> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_view_spam))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(spam: Spam) {
            bindText(PhoneNumberUtils.formatNumber(spam.phoneNumber, "US"), itemView.phone_number)
            bindText(DateHelper.getDateFromEpoch(spam.date), itemView.date)
            bindImage(getStringAsContactType(spam.contactType)!!, itemView.contact_type)
            itemView.setOnClickListener { listener(spam) }
        }
    }

    fun updateList(list: List<Spam>) {
        dataSet = list.toMutableList()
        notifyDataSetChanged()
    }

    private fun bindText(text: String, view: TextView) {
        view.text = text
    }

    private fun bindImage(type: ContactType, view: ImageView) {
        when (type) {
            ContactType.CALL -> view.setImageResource(R.drawable.ic_phone_in_talk)
            ContactType.TEXT -> view.setImageResource(R.drawable.ic_message)
        }
    }
}