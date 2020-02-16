package ru.geekbrains.kotlinnotes.ui.splash

import ru.geekbrains.kotlinnotes.ui.base.BaseViewState

class SplashViewState(authenticated: Boolean? = null, error: Throwable? = null) : BaseViewState<Boolean?>(authenticated, error)