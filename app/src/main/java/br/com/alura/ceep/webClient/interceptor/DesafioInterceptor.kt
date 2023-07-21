package br.com.alura.ceep.webClient.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer

class DesafioInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val requestBody = requestBodyToString(request)
        val responseBody = responseBodyToString(response)

        return response.newBuilder()
            .body(ResponseBody.create(response.body?.contentType(), responseBody))
            .build()
    }

    private fun requestBodyToString(request: Request): String {
        val buffer = Buffer()
        request.body?.writeTo(buffer) ?: return "Request body is empty."
        return buffer.readUtf8()
    }

    private fun responseBodyToString(response: Response): String {
        val responseBody = response.body
        return responseBody?.string() ?: "Response body is empty."
    }
}