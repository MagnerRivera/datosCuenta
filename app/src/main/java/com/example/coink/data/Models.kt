package com.example.coink.data

import java.util.*

data class Request(val documentNumber: String,
                   val documentType: String,
                   val expeditionDate: Date,
                   val birthDate: Date,
)

data class PayLoad( val payload: String)