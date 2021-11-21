# AccountApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAccountActivateCode**](AccountApi.md#getAccountActivateCode) | **POST** /account/activate/{code} | Activate account
[**postAccountActivateResend**](AccountApi.md#postAccountActivateResend) | **POST** /account/activate/resend | Resend Activation code
[**postAccountCreate**](AccountApi.md#postAccountCreate) | **POST** /account/create | Create Account
[**postAccountRecover**](AccountApi.md#postAccountRecover) | **POST** /account/recover | Recover given Account
[**postAccountRecoverCode**](AccountApi.md#postAccountRecoverCode) | **POST** /account/recover/{code} | Complete Account recover


<a name="getAccountActivateCode"></a>
# **getAccountActivateCode**
> AccountActivateResponse getAccountActivateCode(code)

Activate account

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    AccountApi apiInstance = new AccountApi(defaultClient);
    String code = "code_example"; // String | 
    try {
      AccountActivateResponse result = apiInstance.getAccountActivateCode(code);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#getAccountActivateCode");
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
 **code** | **String**|  |

### Return type

[**AccountActivateResponse**](AccountActivateResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**400** | Bad Request |  -  |
**404** | Not Found |  -  |

<a name="postAccountActivateResend"></a>
# **postAccountActivateResend**
> AccountActivateResponse postAccountActivateResend(contentType, sendAccountActivationCode)

Resend Activation code

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    AccountApi apiInstance = new AccountApi(defaultClient);
    String contentType = "\"application/json\""; // String | 
    SendAccountActivationCode sendAccountActivationCode = new SendAccountActivationCode(); // SendAccountActivationCode | The size of the body is limited to 1KB.
    try {
      AccountActivateResponse result = apiInstance.postAccountActivateResend(contentType, sendAccountActivationCode);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#postAccountActivateResend");
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
 **sendAccountActivationCode** | [**SendAccountActivationCode**](SendAccountActivationCode.md)| The size of the body is limited to 1KB. | [optional]

### Return type

[**AccountActivateResponse**](AccountActivateResponse.md)

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

<a name="postAccountCreate"></a>
# **postAccountCreate**
> UserResponse postAccountCreate(contentType, createAccount)

Create Account

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    AccountApi apiInstance = new AccountApi(defaultClient);
    String contentType = "\"application/json\""; // String | 
    CreateAccount createAccount = new CreateAccount(); // CreateAccount | The size of the body is limited to 4KB.
    try {
      UserResponse result = apiInstance.postAccountCreate(contentType, createAccount);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#postAccountCreate");
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
 **createAccount** | [**CreateAccount**](CreateAccount.md)| The size of the body is limited to 4KB. | [optional]

### Return type

[**UserResponse**](UserResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Created |  -  |
**400** | Bad Request |  -  |

<a name="postAccountRecover"></a>
# **postAccountRecover**
> AccountActivateResponse postAccountRecover(contentType, sendAccountActivationCode)

Recover given Account

You can only request Account Recovery once per Hour for the same Email Address

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    AccountApi apiInstance = new AccountApi(defaultClient);
    String contentType = "\"application/json\""; // String | 
    SendAccountActivationCode sendAccountActivationCode = new SendAccountActivationCode(); // SendAccountActivationCode | The size of the body is limited to 1KB.
    try {
      AccountActivateResponse result = apiInstance.postAccountRecover(contentType, sendAccountActivationCode);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#postAccountRecover");
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
 **sendAccountActivationCode** | [**SendAccountActivationCode**](SendAccountActivationCode.md)| The size of the body is limited to 1KB. | [optional]

### Return type

[**AccountActivateResponse**](AccountActivateResponse.md)

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

<a name="postAccountRecoverCode"></a>
# **postAccountRecoverCode**
> AccountActivateResponse postAccountRecoverCode(code, contentType, recoverCompleteBody)

Complete Account recover

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    AccountApi apiInstance = new AccountApi(defaultClient);
    String code = "code_example"; // String | 
    String contentType = "\"application/json\""; // String | 
    RecoverCompleteBody recoverCompleteBody = new RecoverCompleteBody(); // RecoverCompleteBody | The size of the body is limited to 2KB.
    try {
      AccountActivateResponse result = apiInstance.postAccountRecoverCode(code, contentType, recoverCompleteBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#postAccountRecoverCode");
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
 **code** | **String**|  |
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **recoverCompleteBody** | [**RecoverCompleteBody**](RecoverCompleteBody.md)| The size of the body is limited to 2KB. | [optional]

### Return type

[**AccountActivateResponse**](AccountActivateResponse.md)

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

