# CoverApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteCover**](CoverApi.md#deleteCover) | **DELETE** /cover/{coverId} | Delete Cover
[**editCover**](CoverApi.md#editCover) | **PUT** /cover/{coverId} | Edit Cover
[**getCover**](CoverApi.md#getCover) | **GET** /cover | CoverArt list
[**getCoverId**](CoverApi.md#getCoverId) | **GET** /cover/{coverId} | Get Cover
[**uploadCover**](CoverApi.md#uploadCover) | **POST** /cover/{mangaId} | Upload Cover


<a name="deleteCover"></a>
# **deleteCover**
> Response deleteCover(coverId)

Delete Cover

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CoverApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        CoverApi apiInstance = new CoverApi(defaultClient);
        UUID coverId = new UUID(); // UUID | 
        try {
            Response result = apiInstance.deleteCover(coverId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CoverApi#deleteCover");
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
 **coverId** | [**UUID**](.md)|  |

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
**400** | Bad Request |  -  |
**403** | Forbidden |  -  |

<a name="editCover"></a>
# **editCover**
> CoverResponse editCover(coverId, contentType, coverEdit)

Edit Cover

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CoverApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        CoverApi apiInstance = new CoverApi(defaultClient);
        UUID coverId = new UUID(); // UUID | 
        String contentType = "\"application/json\""; // String | 
        CoverEdit coverEdit = new CoverEdit(); // CoverEdit | The size of the body is limited to 2KB.
        try {
            CoverResponse result = apiInstance.editCover(coverId, contentType, coverEdit);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CoverApi#editCover");
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
 **coverId** | [**UUID**](.md)|  |
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **coverEdit** | [**CoverEdit**](CoverEdit.md)| The size of the body is limited to 2KB. | [optional]

### Return type

[**CoverResponse**](CoverResponse.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**400** | Bad Request |  -  |
**403** | Forbidden |  -  |

<a name="getCover"></a>
# **getCover**
> CoverList getCover(limit, offset, manga, ids, uploaders, order, includes)

CoverArt list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CoverApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    CoverApi apiInstance = new CoverApi(defaultClient);
    Integer limit = 10; // Integer | 
    Integer offset = 56; // Integer | 
    List<UUID> manga = Arrays.asList(); // List<UUID> | Manga ids (limited to 100 per request)
    List<UUID> ids = Arrays.asList(); // List<UUID> | Covers ids (limited to 100 per request)
    List<UUID> uploaders = Arrays.asList(); // List<UUID> | User ids (limited to 100 per request)
    Order5 order = new Order5(); // Order5 | 
    List<String> includes = Arrays.asList(); // List<String> | 
    try {
      CoverList result = apiInstance.getCover(limit, offset, manga, ids, uploaders, order, includes);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CoverApi#getCover");
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
 **limit** | **Integer**|  | [optional] [default to 10]
 **offset** | **Integer**|  | [optional]
 **manga** | [**List&lt;UUID&gt;**](UUID.md)| Manga ids (limited to 100 per request) | [optional]
 **ids** | [**List&lt;UUID&gt;**](UUID.md)| Covers ids (limited to 100 per request) | [optional]
 **uploaders** | [**List&lt;UUID&gt;**](UUID.md)| User ids (limited to 100 per request) | [optional]
 **order** | [**Order5**](.md)|  | [optional]
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**CoverList**](CoverList.md)

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
**403** | Forbidden |  -  |

<a name="getCoverId"></a>
# **getCoverId**
> CoverResponse getCoverId(coverId, includes)

Get Cover

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CoverApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    CoverApi apiInstance = new CoverApi(defaultClient);
    UUID coverId = new UUID(); // UUID | 
    List<String> includes = Arrays.asList(); // List<String> | 
    try {
      CoverResponse result = apiInstance.getCoverId(coverId, includes);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CoverApi#getCoverId");
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
 **coverId** | [**UUID**](.md)|  |
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**CoverResponse**](CoverResponse.md)

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
**403** | Forbidden |  -  |
**404** | CoverArt not found |  -  |

<a name="uploadCover"></a>
# **uploadCover**
> CoverResponse uploadCover(mangaId, contentType, file, volume, description)

Upload Cover

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CoverApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        CoverApi apiInstance = new CoverApi(defaultClient);
        UUID mangaId = new UUID(); // UUID | 
        String contentType = "\"multipart/form-data\""; // String | 
        File file = new File("/path/to/file"); // File | 
        String volume = "volume_example"; // String | 
        String description = "description_example"; // String | 
        try {
            CoverResponse result = apiInstance.uploadCover(mangaId, contentType, file, volume, description);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CoverApi#uploadCover");
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
 **mangaId** | [**UUID**](.md)|  |
 **contentType** | **String**|  | [default to &quot;multipart/form-data&quot;]
 **file** | **File**|  | [optional]
 **volume** | **String**|  | [optional]
 **description** | **String**|  | [optional]

### Return type

[**CoverResponse**](CoverResponse.md)

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
**403** | Forbidden |  -  |

