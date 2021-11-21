# ChapterReadMarkerApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**chapterIdRead**](ChapterReadMarkerApi.md#chapterIdRead) | **POST** /chapter/{id}/read | Mark Chapter read
[**chapterIdUnread**](ChapterReadMarkerApi.md#chapterIdUnread) | **DELETE** /chapter/{id}/read | Mark Chapter unread
[**getMangaChapterReadmarkers**](ChapterReadMarkerApi.md#getMangaChapterReadmarkers) | **GET** /manga/{id}/read | Manga read markers
[**getMangaChapterReadmarkers2**](ChapterReadMarkerApi.md#getMangaChapterReadmarkers2) | **GET** /manga/read | Manga read markers
[**postMangaChapterReadmarkers**](ChapterReadMarkerApi.md#postMangaChapterReadmarkers) | **POST** /manga/{id}/read | Manga read markers batch


<a name="chapterIdRead"></a>
# **chapterIdRead**
> InlineResponse2004 chapterIdRead(id)

Mark Chapter read

Mark chapter as read for the current user

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChapterReadMarkerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        ChapterReadMarkerApi apiInstance = new ChapterReadMarkerApi(defaultClient);
        UUID id = new UUID(); // UUID | 
        try {
            InlineResponse2004 result = apiInstance.chapterIdRead(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ChapterReadMarkerApi#chapterIdRead");
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

### Return type

[**InlineResponse2004**](InlineResponse2004.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |

<a name="chapterIdUnread"></a>
# **chapterIdUnread**
> InlineResponse2004 chapterIdUnread(id)

Mark Chapter unread

Mark chapter as unread for the current user

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChapterReadMarkerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        ChapterReadMarkerApi apiInstance = new ChapterReadMarkerApi(defaultClient);
        UUID id = new UUID(); // UUID | 
        try {
            InlineResponse2004 result = apiInstance.chapterIdUnread(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ChapterReadMarkerApi#chapterIdUnread");
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

### Return type

[**InlineResponse2004**](InlineResponse2004.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |

<a name="getMangaChapterReadmarkers"></a>
# **getMangaChapterReadmarkers**
> InlineResponse2001 getMangaChapterReadmarkers(id)

Manga read markers

A list of chapter ids that are marked as read for the specified manga

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChapterReadMarkerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        ChapterReadMarkerApi apiInstance = new ChapterReadMarkerApi(defaultClient);
        UUID id = new UUID(); // UUID | 
        try {
            InlineResponse2001 result = apiInstance.getMangaChapterReadmarkers(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ChapterReadMarkerApi#getMangaChapterReadmarkers");
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

### Return type

[**InlineResponse2001**](InlineResponse2001.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="getMangaChapterReadmarkers2"></a>
# **getMangaChapterReadmarkers2**
> InlineResponse2003 getMangaChapterReadmarkers2(ids, grouped)

Manga read markers

A list of chapter ids that are marked as read for the given manga ids

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChapterReadMarkerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        ChapterReadMarkerApi apiInstance = new ChapterReadMarkerApi(defaultClient);
        List<UUID> ids = Arrays.asList(); // List<UUID> | Manga ids
        Boolean grouped = true; // Boolean | Group results by manga ids
        try {
            InlineResponse2003 result = apiInstance.getMangaChapterReadmarkers2(ids, grouped);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ChapterReadMarkerApi#getMangaChapterReadmarkers2");
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
 **ids** | [**List&lt;UUID&gt;**](UUID.md)| Manga ids |
 **grouped** | **Boolean**| Group results by manga ids | [optional]

### Return type

[**InlineResponse2003**](InlineResponse2003.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="postMangaChapterReadmarkers"></a>
# **postMangaChapterReadmarkers**
> InlineResponse2002 postMangaChapterReadmarkers(id, chapterReadMarkerBatch)

Manga read markers batch

Send a lot of chapter ids for one manga to mark as read and/or unread

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChapterReadMarkerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        ChapterReadMarkerApi apiInstance = new ChapterReadMarkerApi(defaultClient);
        UUID id = new UUID(); // UUID | 
        ChapterReadMarkerBatch chapterReadMarkerBatch = new ChapterReadMarkerBatch(); // ChapterReadMarkerBatch | The size of the body is limited to 10KB.
        try {
            InlineResponse2002 result = apiInstance.postMangaChapterReadmarkers(id, chapterReadMarkerBatch);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ChapterReadMarkerApi#postMangaChapterReadmarkers");
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
 **chapterReadMarkerBatch** | [**ChapterReadMarkerBatch**](ChapterReadMarkerBatch.md)| The size of the body is limited to 10KB. | [optional]

### Return type

[**InlineResponse2002**](InlineResponse2002.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

