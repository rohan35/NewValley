package com.raydevelopers.newvalley.network

class NetworkResource<out T>(
    var status: Status?,
    val data: T?,
    val message: String?
) {
    companion object {
        /**
         * Success function when api returns ok result
         * @param data - type of data coming from the api
         * @param statusCode - status code coming from the api
         * @return object of NetworkResource with success Response
         */
        fun <T> success(data: T): NetworkResource<T> =
            NetworkResource(
                status = Status.SUCCESS,
                data = data,
                message = null
            )

        /**
         * Error function when api returns failure result or any exception occurr
         * @param data - type of data coming from the api
         * @param error - Error recieved from api or if any exception occur
         * @param statusCode - status code coming from the api
         * @return object of NetworkResource with Failure Response
         */
        fun <T> error(data: T?, message: String): NetworkResource<T> =
            NetworkResource(
                status = Status.ERROR,
                data = data,
                message = message
            )


        fun <T> loading(data: T?): NetworkResource<T> =
            NetworkResource(status = Status.LOADING, data = data, message = null)
    }

    /**
     * Enum containing status
     */
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}