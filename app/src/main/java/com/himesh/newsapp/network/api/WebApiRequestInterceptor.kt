
import com.himesh.newsapp.utill.NewsAppConstants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class WebApiRequestInterceptor : Interceptor { //to check all API Response and details
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request
        val builder = chain.request().newBuilder()
        request = builder.build()
        builder.addHeader(
            NewsAppConstants.AUTH_TOKEN_CONTENT_TYPE,
            NewsAppConstants.AUTH_TOKEN_CONTENT_TYPE_VALUE_JSON
        )
        builder.addHeader(
            NewsAppConstants.AUTH_CONNECTION_TYPE,
            NewsAppConstants.AUTH_CONNECTION_TYPE_VALUE
        )
        return chain.proceed(request)
    }
}