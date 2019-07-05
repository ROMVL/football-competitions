package com.romanik.footballcompetitions.core.extentions

import java.text.SimpleDateFormat
import java.util.*

fun Date.toStringYYYY(): String = SimpleDateFormat("yyyy", Locale.US).format(this)