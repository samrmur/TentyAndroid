package com.tenty.tentyandroid.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.PhoneNumberUtils
import android.telephony.SmsMessage
import android.telephony.TelephonyManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.tenty.tentyandroid.R
import com.tenty.tentyandroid.api.models.SpamResult
import com.tenty.tentyandroid.api.services.TentyService
import com.tenty.tentyandroid.db.entity.Spam
import com.tenty.tentyandroid.db.source.SpamDataSource
import com.tenty.tentyandroid.util.types.ContactType
import com.tenty.tentyandroid.util.types.getContactTypeAsString
import dagger.android.AndroidInjection
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import timber.log.Timber
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class PhoneReceiver: BroadcastReceiver() {
    @Inject
    lateinit var service: TentyService

    @Inject
    lateinit var dataSource: SpamDataSource

    private val id: AtomicInteger = AtomicInteger(0)

    override fun onReceive(context: Context?, intent: Intent?) {
        AndroidInjection.inject(this, context)

        intent?.let {
            val action: String? = it.action

            if (action.equals(android.provider.Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
                val textReceiveTime = Date().time
                val smsMessages: Array<SmsMessage> = Telephony.Sms.Intents.getMessagesFromIntent(intent)
                val number = Regex("[^0-9]").replace(smsMessages[0].originatingAddress!!, "")
                getSpamResult(number, textReceiveTime, ContactType.TEXT, context!!)
            } else {
                val state = it.extras?.getString(TelephonyManager.EXTRA_STATE)

                if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    val callStartTime = Date().time
                    var number = it.extras?.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
                    number = Regex("[^0-9]").replace(number!!, "")
                    getSpamResult(number, callStartTime, ContactType.CALL, context!!)
                }
            }
        }
    }

    private fun getSpamResult(number: String, time: Long, type: ContactType, context: Context) {
        service.getSpamResult(number)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnError { error ->
                Timber.e(error)
            }
            .subscribe(object : Observer<SpamResult> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(result: SpamResult) {
                    displayNotificationAndStore(result, time, type, context)
                }

                override fun onError(error: Throwable) {
                }
            })
    }

    private fun displayNotificationAndStore(result: SpamResult, epoch: Long, type: ContactType, context: Context) {
        if (result.isSpam) {
            val notification = NotificationCompat.Builder(context, context.getString(R.string.channel_id))
                .setContentTitle(
                    if (type == ContactType.CALL)
                        context.getString(R.string.msg_spam_call_detected)
                    else
                        context.getString(R.string.msg_spam_text_detected)
                )
                .setContentText(context.getString(R.string.msg_spam_description, PhoneNumberUtils.formatNumber(result.number, "US")))
                .setSmallIcon(R.drawable.ic_main)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build()

            with(NotificationManagerCompat.from(context)) {
                notify(id.getAndIncrement(), notification)
            }

            GlobalScope.launch(Dispatchers.IO) {
                dataSource.insertSpam(Spam(epoch, result.number, getContactTypeAsString(type)))
            }
        }
    }
}