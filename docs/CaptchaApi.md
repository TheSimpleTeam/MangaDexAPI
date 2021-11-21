# CaptchaApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**postCaptchaSolve**](CaptchaApi.md#postCaptchaSolve) | **POST** /captcha/solve | Solve Captcha


<a name="postCaptchaSolve"></a>
# **postCaptchaSolve**
> InlineResponse2004 postCaptchaSolve(contentType, inlineObject4)

Solve Captcha

Captchas can be solved explicitly through this endpoint, another way is to add a &#x60;X-Captcha-Result&#x60; header to any request. The same logic will verify the captcha and is probably more convenient because it takes one less request.  Authentication is optional. Captchas are tracked for both the client ip and for the user id, if you are logged in you want to send your session token but that is not required.

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CaptchaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        CaptchaApi apiInstance = new CaptchaApi(defaultClient);
        String contentType = "\"application/json\""; // String | 
        InlineObject4 inlineObject4 = new InlineObject4(); // InlineObject4 | 
        try {
            InlineResponse2004 result = apiInstance.postCaptchaSolve(contentType, inlineObject4);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CaptchaApi#postCaptchaSolve");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **inlineObject4** | [**InlineObject4**](InlineObject4.md)|  | [optional]

### Return type

[**InlineResponse2004**](InlineResponse2004.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK: Captcha has been solved |  -  |
**400** | Bad Request: Captcha challenge result was wrong, the Captcha Verification service was down or other, refer to the error message and the errorCode inside the error context |  -  |

