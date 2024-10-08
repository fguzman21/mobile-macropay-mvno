package com.grupomacro.mvno.network.network.api.shared.model.request

import com.google.gson.annotations.SerializedName

data class BaseRequest<T>(

    @SerializedName("data")
    val data: RequestData<T>,
) {

    constructor(type: String, data: () -> T) : this(RequestData(type, data()))

    data class RequestData<T>(

        @SerializedName("type")
        val type: String,

        @SerializedName("attributes")
        val attributes: T,
    )
}
