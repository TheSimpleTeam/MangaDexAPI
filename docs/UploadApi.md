# UploadApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**abandonUploadSession**](UploadApi.md#abandonUploadSession) | **DELETE** /upload/{uploadSessionId} | Abandon upload session
[**beginEditSession**](UploadApi.md#beginEditSession) | **POST** /upload/begin/{chapterId} | Start an edit chapter session
[**beginUploadSession**](UploadApi.md#beginUploadSession) | **POST** /upload/begin | Start an upload session
[**commitUploadSession**](UploadApi.md#commitUploadSession) | **POST** /upload/{uploadSessionId}/commit | Commit the upload session and specify chapter data
[**deleteUploadedSessionFile**](UploadApi.md#deleteUploadedSessionFile) | **DELETE** /upload/{uploadSessionId}/{uploadSessionFileId} | Delete an uploaded image from the Upload Session
[**deleteUploadedSessionFiles**](UploadApi.md#deleteUploadedSessionFiles) | **DELETE** /upload/{uploadSessionId}/batch | Delete a set of uploaded images from the Upload Session
[**getUploadSession**](UploadApi.md#getUploadSession) | **GET** /upload | Get the current User upload session
[**putUploadSessionFile**](UploadApi.md#putUploadSessionFile) | **POST** /upload/{uploadSessionId} | Upload images to the upload session


<a name="abandonUploadSession"></a>
# **abandonUploadSession**
> Response abandonUploadSession(uploadSessionId)

Abandon upload session

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UploadApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        UploadApi apiInstance = new UploadApi(defaultClient);
        UUID uploadSessionId = new UUID(); // UUID | 
        try {
            Response result = apiInstance.abandonUploadSession(uploadSessionId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UploadApi#abandonUploadSession");
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
 **uploadSessionId** | [**UUID**](.md)|  |

### Return type

[**Response**](Response.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="beginEditSession"></a>
# **beginEditSession**
> UploadSession beginEditSession(chapterId, contentType, beginEditSession)

Start an edit chapter session

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UploadApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        UploadApi apiInstance = new UploadApi(defaultClient);
        UUID chapterId = new UUID(); // UUID | 
        String contentType = "\"application/json\""; // String | 
        BeginEditSession beginEditSession = new BeginEditSession(); // BeginEditSession | The size of the body is limited to 1KB.
        try {
            UploadSession result = apiInstance.beginEditSession(chapterId, contentType, beginEditSession);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UploadApi#beginEditSession");
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
 **chapterId** | [**UUID**](.md)|  |
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **beginEditSession** | [**BeginEditSession**](BeginEditSession.md)| The size of the body is limited to 1KB. | [optional]

### Return type

[**UploadSession**](UploadSession.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**400** | Bad Request if Chapter&#39;s Manga is unpublished |  -  |
**401** | Unauthorized if user does not have upload permissions or has no rights to edit the chapter (needs to be uploader or group member) |  -  |

<a name="beginUploadSession"></a>
# **beginUploadSession**
> UploadSession beginUploadSession(contentType, beginUploadSession)

Start an upload session

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UploadApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        UploadApi apiInstance = new UploadApi(defaultClient);
        String contentType = "\"application/json\""; // String | 
        BeginUploadSession beginUploadSession = new BeginUploadSession(); // BeginUploadSession | The size of the body is limited to 4KB.
        try {
            UploadSession result = apiInstance.beginUploadSession(contentType, beginUploadSession);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UploadApi#beginUploadSession");
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
 **beginUploadSession** | [**BeginUploadSession**](BeginUploadSession.md)| The size of the body is limited to 4KB. | [optional]

### Return type

[**UploadSession**](UploadSession.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="commitUploadSession"></a>
# **commitUploadSession**
> Chapter commitUploadSession(uploadSessionId, contentType, commitUploadSession)

Commit the upload session and specify chapter data

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UploadApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        UploadApi apiInstance = new UploadApi(defaultClient);
        UUID uploadSessionId = new UUID(); // UUID | 
        String contentType = "\"application/json\""; // String | 
        CommitUploadSession commitUploadSession = new CommitUploadSession(); // CommitUploadSession | The size of the body is limited to 4KB.
        try {
            Chapter result = apiInstance.commitUploadSession(uploadSessionId, contentType, commitUploadSession);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UploadApi#commitUploadSession");
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
 **uploadSessionId** | [**UUID**](.md)|  |
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **commitUploadSession** | [**CommitUploadSession**](CommitUploadSession.md)| The size of the body is limited to 4KB. | [optional]

### Return type

[**Chapter**](Chapter.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="deleteUploadedSessionFile"></a>
# **deleteUploadedSessionFile**
> Response deleteUploadedSessionFile(uploadSessionId, uploadSessionFileId)

Delete an uploaded image from the Upload Session

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UploadApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        UploadApi apiInstance = new UploadApi(defaultClient);
        UUID uploadSessionId = new UUID(); // UUID | 
        UUID uploadSessionFileId = new UUID(); // UUID | 
        try {
            Response result = apiInstance.deleteUploadedSessionFile(uploadSessionId, uploadSessionFileId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UploadApi#deleteUploadedSessionFile");
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
 **uploadSessionId** | [**UUID**](.md)|  |
 **uploadSessionFileId** | [**UUID**](.md)|  |

### Return type

[**Response**](Response.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="deleteUploadedSessionFiles"></a>
# **deleteUploadedSessionFiles**
> Response deleteUploadedSessionFiles(uploadSessionId, contentType, UUID)

Delete a set of uploaded images from the Upload Session

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UploadApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        UploadApi apiInstance = new UploadApi(defaultClient);
        UUID uploadSessionId = new UUID(); // UUID | 
        String contentType = "\"application/json\""; // String | 
        List<UUID> UUID = Arrays.asList(); // List<UUID> | The size of the body is limited to 20KB.
        try {
            Response result = apiInstance.deleteUploadedSessionFiles(uploadSessionId, contentType, UUID);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UploadApi#deleteUploadedSessionFiles");
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
 **uploadSessionId** | [**UUID**](.md)|  |
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **UUID** | [**List&lt;UUID&gt;**](UUID.md)| The size of the body is limited to 20KB. | [optional]

### Return type

[**Response**](Response.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="getUploadSession"></a>
# **getUploadSession**
> UploadSession getUploadSession()

Get the current User upload session

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UploadApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        UploadApi apiInstance = new UploadApi(defaultClient);
        try {
            UploadSession result = apiInstance.getUploadSession();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UploadApi#getUploadSession");
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

[**UploadSession**](UploadSession.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="putUploadSessionFile"></a>
# **putUploadSessionFile**
> InlineResponse2009 putUploadSessionFile(uploadSessionId, contentType, file)

Upload images to the upload session

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UploadApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        UploadApi apiInstance = new UploadApi(defaultClient);
        UUID uploadSessionId = new UUID(); // UUID | 
        String contentType = "\"application/json\""; // String | 
        File file = new File("/path/to/file"); // File | 
        try {
            InlineResponse2009 result = apiInstance.putUploadSessionFile(uploadSessionId, contentType, file);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UploadApi#putUploadSessionFile");
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
 **uploadSessionId** | [**UUID**](.md)|  |
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **file** | **File**|  | [optional]

### Return type

[**InlineResponse2009**](InlineResponse2009.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**400** | Bad Request |  -  |

