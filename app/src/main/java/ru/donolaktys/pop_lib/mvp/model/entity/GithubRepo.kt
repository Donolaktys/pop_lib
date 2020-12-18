package ru.donolaktys.pop_lib.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize


@Parcelize
data class GithubRepo(
    @Expose val id: String? = null,
    @Expose val name: String? = null
): Parcelable