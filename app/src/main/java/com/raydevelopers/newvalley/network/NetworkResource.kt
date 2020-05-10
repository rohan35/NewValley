package com.raydevelopers.newvalley.network

class NetworkResource<Any>(
    var statusCode: Int?,
    var status: NetworkResource.Status?,
    var data: Any?,
    error: Throwable?
) {
    companion object {
        /**
         * Success function when api returns ok result
         * @param data - type of data coming from the api
         * @param statusCode - status code coming from the api
         * @return object of NetworkResource with success Response
         */
        fun <Any> success(data: Any, statusCode: Int): NetworkResource<Any> {
            return NetworkResource(
                statusCode,
                Status.SUCCESS,
                data,
                null as Throwable?
            )
        }

        /**
         * Error function when api returns failure result or any exception occurr
         * @param data - type of data coming from the api
         * @param error - Error recieved from api or if any exception occur
         * @param statusCode - status code coming from the api
         * @return object of NetworkResource with Failure Response
         */
        fun <Any> error(data: Any, error: Throwable, statusCode: Int): NetworkResource<Any> {
            return NetworkResource(statusCode, Status.ERROR, data, error)
        }
    }

    /**
     * Enum containing status
     */
    enum class Status {
        SUCCESS,
        ERROR
    }
}