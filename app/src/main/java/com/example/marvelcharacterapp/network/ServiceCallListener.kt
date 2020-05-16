package com.example.marvelcharacterapp.network

typealias Success<T> = ((T?) -> Unit)
typealias Failure<T> = ((T?) -> Unit)

open class ServiceCallListener<S, F> {

    var success: Success<S>? = null
        private set
    var failure: Failure<F>? = null
        private set

    open fun onSuccess(action: Success<S>): ServiceCallListener<S, F> {
        success = action
        return this
    }

    open fun onFailure(action: Failure<F>): ServiceCallListener<S, F> {
        failure = action
        return this
    }

}

fun <S, F> listen(`this`: ServiceCallListener<S, F>.() -> Unit): ServiceCallListener<S, F> {
    return ServiceCallListener<S, F>().apply { `this`() }
}

typealias SuccessListener = (Any?) -> Unit
typealias FailureListener = (Exception) -> Unit