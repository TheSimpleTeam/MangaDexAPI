# ChapterApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteChapterId**](ChapterApi.md#deleteChapterId) | **DELETE** /chapter/{id} | Delete Chapter
[**getChapter**](ChapterApi.md#getChapter) | **GET** /chapter | Chapter list
[**getChapterId**](ChapterApi.md#getChapterId) | **GET** /chapter/{id} | Get Chapter
[**putChapterId**](ChapterApi.md#putChapterId) | **PUT** /chapter/{id} | Update Chapter


<a name="deleteChapterId"></a>
# **deleteChapterId**
> Response deleteChapterId(id)

Delete Chapter

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChapterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        ChapterApi apiInstance = new ChapterApi(defaultClient);
        UUID id = new UUID(); // UUID | Chapter ID
        try {
            Response result = apiInstance.deleteChapterId(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ChapterApi#deleteChapterId");
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
 **id** | [**UUID**](.md)| Chapter ID |

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
**200** | Chapter has been deleted. |  -  |
**400** | Bad Request |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="getChapter"></a>
# **getChapter**
> ChapterList getChapter(limit, offset, ids, title, groups, uploader, manga, volume, chapter, translatedLanguage, originalLanguage, excludedOriginalLanguage, contentRating, includeFutureUpdates, createdAtSince, updatedAtSince, publishAtSince, order, includes)

Chapter list

Chapter list. If you want the Chapters of a given Manga, please check the feed endpoints.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChapterApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    ChapterApi apiInstance = new ChapterApi(defaultClient);
    Integer limit = 10; // Integer | 
    Integer offset = 56; // Integer | 
    List<UUID> ids = Arrays.asList(); // List<UUID> | Chapter ids (limited to 100 per request)
    String title = "title_example"; // String | 
    List<UUID> groups = Arrays.asList(); // List<UUID> | 
    UUID uploader = new UUID(); // UUID | 
    UUID manga = new UUID(); // UUID | 
    OneOfstringarray volume = new OneOfstringarray(); // OneOfstringarray | 
    OneOfstringarray chapter = new OneOfstringarray(); // OneOfstringarray | 
    List<String> translatedLanguage = Arrays.asList(); // List<String> | 
    List<String> originalLanguage = Arrays.asList(); // List<String> | 
    List<String> excludedOriginalLanguage = Arrays.asList(); // List<String> | 
    List<String> contentRating = Arrays.asList(); // List<String> | 
    String includeFutureUpdates = "1"; // String | 
    String createdAtSince = "createdAtSince_example"; // String | 
    String updatedAtSince = "updatedAtSince_example"; // String | 
    String publishAtSince = "publishAtSince_example"; // String | 
    Order2 order = new Order2(); // Order2 | 
    List<String> includes = Arrays.asList(); // List<String> | 
    try {
      ChapterList result = apiInstance.getChapter(limit, offset, ids, title, groups, uploader, manga, volume, chapter, translatedLanguage, originalLanguage, excludedOriginalLanguage, contentRating, includeFutureUpdates, createdAtSince, updatedAtSince, publishAtSince, order, includes);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChapterApi#getChapter");
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
 **ids** | [**List&lt;UUID&gt;**](UUID.md)| Chapter ids (limited to 100 per request) | [optional]
 **title** | **String**|  | [optional]
 **groups** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **uploader** | [**UUID**](.md)|  | [optional]
 **manga** | [**UUID**](.md)|  | [optional]
 **volume** | [**OneOfstringarray**](.md)|  | [optional] [default to null]
 **chapter** | [**OneOfstringarray**](.md)|  | [optional] [default to null]
 **translatedLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **originalLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **excludedOriginalLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **contentRating** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: safe, suggestive, erotica, pornographic]
 **includeFutureUpdates** | **String**|  | [optional] [default to 1] [enum: 0, 1]
 **createdAtSince** | **String**|  | [optional]
 **updatedAtSince** | **String**|  | [optional]
 **publishAtSince** | **String**|  | [optional]
 **order** | [**Order2**](.md)|  | [optional]
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**ChapterList**](ChapterList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Chapter list |  -  |
**400** | Bad Request |  -  |
**403** | Forbidden |  -  |

<a name="getChapterId"></a>
# **getChapterId**
> ChapterResponse getChapterId(id, includes)

Get Chapter

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChapterApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    ChapterApi apiInstance = new ChapterApi(defaultClient);
    UUID id = new UUID(); // UUID | Chapter ID
    List<String> includes = Arrays.asList(); // List<String> | 
    try {
      ChapterResponse result = apiInstance.getChapterId(id, includes);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChapterApi#getChapterId");
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
 **id** | [**UUID**](.md)| Chapter ID |
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**ChapterResponse**](ChapterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**403** | Forbidden |  -  |
**404** | Chapter not found |  -  |

<a name="putChapterId"></a>
# **putChapterId**
> ChapterResponse putChapterId(id, contentType, chapterEdit)

Update Chapter

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChapterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        ChapterApi apiInstance = new ChapterApi(defaultClient);
        UUID id = new UUID(); // UUID | Chapter ID
        String contentType = "\"application/json\""; // String | 
        ChapterEdit chapterEdit = new ChapterEdit(); // ChapterEdit | The size of the body is limited to 32KB.
        try {
            ChapterResponse result = apiInstance.putChapterId(id, contentType, chapterEdit);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ChapterApi#putChapterId");
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
 **id** | [**UUID**](.md)| Chapter ID |
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **chapterEdit** | [**ChapterEdit**](ChapterEdit.md)| The size of the body is limited to 32KB. | [optional]

### Return type

[**ChapterResponse**](ChapterResponse.md)

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
**404** | Not Found |  -  |

