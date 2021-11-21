# AuthApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAuthCheck**](AuthApi.md#getAuthCheck) | **GET** /auth/check | Check token
[**postAuthLogin**](AuthApi.md#postAuthLogin) | **POST** /auth/login | Login
[**postAuthLogout**](AuthApi.md#postAuthLogout) | **POST** /auth/logout | Logout
[**postAuthRefresh**](AuthApi.md#postAuthRefresh) | **POST** /auth/refresh | Refresh token


<a name="getAuthCheck"></a>
# **getAuthCheck**
> CheckResponse getAuthCheck()

Check token

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        AuthApi apiInstance = new AuthApi(defaultClient);
        try {
            CheckResponse result = apiInstance.getAuthCheck();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthApi#getAuthCheck");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**CheckResponse**](CheckResponse.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="postAuthLogin"></a>
# **postAuthLogin**
> LoginResponse postAuthLogin(contentType, login)

Login

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    AuthApi apiInstance = new AuthApi(defaultClient);
    String contentType = "\"application/json\""; // String | 
    Login login = new Login(); // Login | The size of the body is limited to 2KB.
    try {
      LoginResponse result = apiInstance.postAuthLogin(contentType, login);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthApi#postAuthLogin");
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
 **login** | [**Login**](Login.md)| The size of the body is limited to 2KB. | [optional]

### Return type

[**LoginResponse**](LoginResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**400** | Bad Request |  -  |
**401** | Unauthorized |  -  |

<a name="postAuthLogout"></a>
# **postAuthLogout**
> LogoutResponse postAuthLogout()

Logout

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        AuthApi apiInstance = new AuthApi(defaultClient);
        try {
            LogoutResponse result = apiInstance.postAuthLogout();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthApi#postAuthLogout");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**LogoutResponse**](LogoutResponse.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**503** | Service Unavailable |  -  |

<a name="postAuthRefresh"></a>
# **postAuthRefresh**
> RefreshResponse postAuthRefresh(contentType, refreshToken)

Refresh token

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    AuthApi apiInstance = new AuthApi(defaultClient);
    String contentType = "\"application/json\""; // String | 
    RefreshToken refreshToken = new RefreshToken(); // RefreshToken | The size of the body is limited to 2KB.
    try {
      RefreshResponse result = apiInstance.postAuthRefresh(contentType, refreshToken);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthApi#postAuthRefresh");
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
 **refreshToken** | [**RefreshToken**](RefreshToken.md)| The size of the body is limited to 2KB. | [optional]

### Return type

[**RefreshResponse**](RefreshResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**400** | Bad Request |  -  |
**401** | Unauthorized |  -  |
**403** | Forbidden |  -  |

