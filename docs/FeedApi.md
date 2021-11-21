# FeedApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getListIdFeed**](FeedApi.md#getListIdFeed) | **GET** /list/{id}/feed | CustomList Manga feed
[**getUserFollowsMangaFeed**](FeedApi.md#getUserFollowsMangaFeed) | **GET** /user/follows/manga/feed | Get logged User followed Manga feed (Chapter list)


<a name="getListIdFeed"></a>
# **getListIdFeed**
> ChapterList getListIdFeed(id, limit, offset, translatedLanguage, originalLanguage, excludedOriginalLanguage, contentRating, includeFutureUpdates, createdAtSince, updatedAtSince, publishAtSince, order, includes)

CustomList Manga feed

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FeedApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    FeedApi apiInstance = new FeedApi(defaultClient);
    UUID id = new UUID(); // UUID | 
    Integer limit = 100; // Integer | 
    Integer offset = 56; // Integer | 
    List<String> translatedLanguage = Arrays.asList(); // List<String> | 
    List<String> originalLanguage = Arrays.asList(); // List<String> | 
    List<String> excludedOriginalLanguage = Arrays.asList(); // List<String> | 
    List<String> contentRating = Arrays.asList(); // List<String> | 
    String includeFutureUpdates = "1"; // String | 
    String createdAtSince = "createdAtSince_example"; // String | 
    String updatedAtSince = "updatedAtSince_example"; // String | 
    String publishAtSince = "publishAtSince_example"; // String | 
    Order4 order = new Order4(); // Order4 | 
    List<String> includes = Arrays.asList(); // List<String> | 
    try {
      ChapterList result = apiInstance.getListIdFeed(id, limit, offset, translatedLanguage, originalLanguage, excludedOriginalLanguage, contentRating, includeFutureUpdates, createdAtSince, updatedAtSince, publishAtSince, order, includes);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FeedApi#getListIdFeed");
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
 **id** | [**UUID**](.md)|  |
 **limit** | **Integer**|  | [optional] [default to 100]
 **offset** | **Integer**|  | [optional]
 **translatedLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **originalLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **excludedOriginalLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **contentRating** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: safe, suggestive, erotica, pornographic]
 **includeFutureUpdates** | **String**|  | [optional] [default to 1] [enum: 0, 1]
 **createdAtSince** | **String**|  | [optional]
 **updatedAtSince** | **String**|  | [optional]
 **publishAtSince** | **String**|  | [optional]
 **order** | [**Order4**](.md)|  | [optional]
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
**200** | OK |  -  |
**400** | Bad Request |  -  |
**401** | Unauthorized |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="getUserFollowsMangaFeed"></a>
# **getUserFollowsMangaFeed**
> ChapterList getUserFollowsMangaFeed(limit, offset, translatedLanguage, originalLanguage, excludedOriginalLanguage, contentRating, includeFutureUpdates, createdAtSince, updatedAtSince, publishAtSince, order, includes)

Get logged User followed Manga feed (Chapter list)

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FeedApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        FeedApi apiInstance = new FeedApi(defaultClient);
        Integer limit = 100; // Integer | 
        Integer offset = 56; // Integer | 
        List<String> translatedLanguage = Arrays.asList(); // List<String> | 
        List<String> originalLanguage = Arrays.asList(); // List<String> | 
        List<String> excludedOriginalLanguage = Arrays.asList(); // List<String> | 
        List<String> contentRating = Arrays.asList(); // List<String> | 
        String includeFutureUpdates = "1"; // String | 
        String createdAtSince = "createdAtSince_example"; // String | 
        String updatedAtSince = "updatedAtSince_example"; // String | 
        String publishAtSince = "publishAtSince_example"; // String | 
        Order3 order = new Order3(); // Order3 | 
        List<String> includes = Arrays.asList(); // List<String> | 
        try {
            ChapterList result = apiInstance.getUserFollowsMangaFeed(limit, offset, translatedLanguage, originalLanguage, excludedOriginalLanguage, contentRating, includeFutureUpdates, createdAtSince, updatedAtSince, publishAtSince, order, includes);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FeedApi#getUserFollowsMangaFeed");
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
 **limit** | **Integer**|  | [optional] [default to 100]
 **offset** | **Integer**|  | [optional]
 **translatedLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **originalLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **excludedOriginalLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **contentRating** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: safe, suggestive, erotica, pornographic]
 **includeFutureUpdates** | **String**|  | [optional] [default to 1] [enum: 0, 1]
 **createdAtSince** | **String**|  | [optional]
 **updatedAtSince** | **String**|  | [optional]
 **publishAtSince** | **String**|  | [optional]
 **order** | [**Order3**](.md)|  | [optional]
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**ChapterList**](ChapterList.md)

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
**404** | User not Found |  -  |

