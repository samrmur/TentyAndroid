package com.tenty.tentyandroid.api.services

import com.tenty.tentyandroid.api.models.SpamResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface TentyService {
    @GET("exist/{number}")
    fun getSpamResult(@Path("number") number: String): Observable<SpamResult>
}