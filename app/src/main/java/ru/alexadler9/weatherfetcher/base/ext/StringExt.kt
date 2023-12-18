package ru.alexadler9.weatherfetcher.base

import android.text.Editable

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)