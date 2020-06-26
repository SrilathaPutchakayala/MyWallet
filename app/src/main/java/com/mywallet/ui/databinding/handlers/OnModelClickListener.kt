package com.mywallet.ui.databinding.handlers

interface OnModelClickListener<in T : Any> {
    fun onClick(model: T)
}