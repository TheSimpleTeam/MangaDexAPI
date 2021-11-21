# MangaApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**commitMangaDraft**](MangaApi.md#commitMangaDraft) | **POST** /manga/draft/{id}/commit | Submit a Manga Draft
[**deleteMangaId**](MangaApi.md#deleteMangaId) | **DELETE** /manga/{id} | Delete Manga
[**deleteMangaIdFollow**](MangaApi.md#deleteMangaIdFollow) | **DELETE** /manga/{id}/follow | Unfollow Manga
[**deleteMangaRelationId**](MangaApi.md#deleteMangaRelationId) | **DELETE** /manga/{mangaId}/relation/{id} | Delete Manga relation
[**getMangaDrafts**](MangaApi.md#getMangaDrafts) | **GET** /manga/draft | Get a list of Manga Drafts
[**getMangaId**](MangaApi.md#getMangaId) | **GET** /manga/{id} | View Manga
[**getMangaIdDraft**](MangaApi.md#getMangaIdDraft) | **GET** /manga/draft/{id} | Get a specific Manga Draft
[**getMangaIdFeed**](MangaApi.md#getMangaIdFeed) | **GET** /manga/{id}/feed | Manga feed
[**getMangaIdStatus**](MangaApi.md#getMangaIdStatus) | **GET** /manga/{id}/status | Get a Manga reading status
[**getMangaRandom**](MangaApi.md#getMangaRandom) | **GET** /manga/random | Get a random Manga
[**getMangaRelation**](MangaApi.md#getMangaRelation) | **GET** /manga/{mangaId}/relation | Manga relation list
[**getMangaStatus**](MangaApi.md#getMangaStatus) | **GET** /manga/status | Get all Manga reading status for logged User
[**getMangaTag**](MangaApi.md#getMangaTag) | **GET** /manga/tag | Tag list
[**getSearchManga**](MangaApi.md#getSearchManga) | **GET** /manga | Manga list
[**mangaIdAggregateGet**](MangaApi.md#mangaIdAggregateGet) | **GET** /manga/{id}/aggregate | Get Manga volumes &amp; chapters
[**postManga**](MangaApi.md#postManga) | **POST** /manga | Create Manga
[**postMangaIdFollow**](MangaApi.md#postMangaIdFollow) | **POST** /manga/{id}/follow | Follow Manga
[**postMangaIdStatus**](MangaApi.md#postMangaIdStatus) | **POST** /manga/{id}/status | Update Manga reading status
[**postMangaRelation**](MangaApi.md#postMangaRelation) | **POST** /manga/{mangaId}/relation | Create Manga relation
[**putMangaId**](MangaApi.md#putMangaId) | **PUT** /manga/{id} | Update Manga


<a name="commitMangaDraft"></a>
# **commitMangaDraft**
> MangaResponse commitMangaDraft(id, inlineObject3)

Submit a Manga Draft

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        MangaApi apiInstance = new MangaApi(defaultClient);
        UUID id = new UUID(); // UUID | 
        InlineObject3 inlineObject3 = new InlineObject3(); // InlineObject3 | 
        try {
            MangaResponse result = apiInstance.commitMangaDraft(id, inlineObject3);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MangaApi#commitMangaDraft");
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
 **inlineObject3** | [**InlineObject3**](InlineObject3.md)|  | [optional]

### Return type

[**MangaResponse**](MangaResponse.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | OK |  -  |
**400** | BadRequest |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="deleteMangaId"></a>
# **deleteMangaId**
> Response deleteMangaId(id)

Delete Manga

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        MangaApi apiInstance = new MangaApi(defaultClient);
        UUID id = new UUID(); // UUID | Manga ID
        try {
            Response result = apiInstance.deleteMangaId(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MangaApi#deleteMangaId");
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
 **id** | [**UUID**](.md)| Manga ID |

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
**200** | Manga has been deleted. |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="deleteMangaIdFollow"></a>
# **deleteMangaIdFollow**
> Response deleteMangaIdFollow(id)

Unfollow Manga

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        MangaApi apiInstance = new MangaApi(defaultClient);
        UUID id = new UUID(); // UUID | 
        try {
            Response result = apiInstance.deleteMangaIdFollow(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MangaApi#deleteMangaIdFollow");
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
**404** | Not Found |  -  |

<a name="deleteMangaRelationId"></a>
# **deleteMangaRelationId**
> Response deleteMangaRelationId(mangaId, id)

Delete Manga relation

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        MangaApi apiInstance = new MangaApi(defaultClient);
        UUID mangaId = new UUID(); // UUID | 
        UUID id = new UUID(); // UUID | 
        try {
            Response result = apiInstance.deleteMangaRelationId(mangaId, id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MangaApi#deleteMangaRelationId");
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
 **id** | [**UUID**](.md)|  |

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
**200** | Manga relation has been deleted. |  -  |
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="getMangaDrafts"></a>
# **getMangaDrafts**
> MangaResponse getMangaDrafts(limit, offset, user, state, order, includes)

Get a list of Manga Drafts

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        MangaApi apiInstance = new MangaApi(defaultClient);
        Integer limit = 10; // Integer | 
        Integer offset = 56; // Integer | 
        UUID user = new UUID(); // UUID | 
        String state = "state_example"; // String | 
        Order8 order = new Order8(); // Order8 | 
        List<String> includes = Arrays.asList(); // List<String> | 
        try {
            MangaResponse result = apiInstance.getMangaDrafts(limit, offset, user, state, order, includes);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MangaApi#getMangaDrafts");
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
 **user** | [**UUID**](.md)|  | [optional]
 **state** | **String**|  | [optional] [enum: draft, submitted, rejected]
 **order** | [**Order8**](.md)|  | [optional] [default to {&quot;createdAt&quot;:&quot;desc&quot;}]
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**MangaResponse**](MangaResponse.md)

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

<a name="getMangaId"></a>
# **getMangaId**
> MangaResponse getMangaId(id, includes)

View Manga

View Manga.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    MangaApi apiInstance = new MangaApi(defaultClient);
    UUID id = new UUID(); // UUID | Manga ID
    List<String> includes = Arrays.asList(); // List<String> | 
    try {
      MangaResponse result = apiInstance.getMangaId(id, includes);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MangaApi#getMangaId");
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
 **id** | [**UUID**](.md)| Manga ID |
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**MangaResponse**](MangaResponse.md)

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
**404** | Manga no content |  -  |

<a name="getMangaIdDraft"></a>
# **getMangaIdDraft**
> MangaResponse getMangaIdDraft(id, includes)

Get a specific Manga Draft

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        MangaApi apiInstance = new MangaApi(defaultClient);
        UUID id = new UUID(); // UUID | 
        List<String> includes = Arrays.asList(); // List<String> | 
        try {
            MangaResponse result = apiInstance.getMangaIdDraft(id, includes);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MangaApi#getMangaIdDraft");
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
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**MangaResponse**](MangaResponse.md)

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

<a name="getMangaIdFeed"></a>
# **getMangaIdFeed**
> ChapterList getMangaIdFeed(id, limit, offset, translatedLanguage, originalLanguage, excludedOriginalLanguage, contentRating, includeFutureUpdates, createdAtSince, updatedAtSince, publishAtSince, order, includes)

Manga feed

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    MangaApi apiInstance = new MangaApi(defaultClient);
    UUID id = new UUID(); // UUID | Manga ID
    Integer limit = 100; // Integer | 
    Integer offset = 56; // Integer | 
    List<String> translatedLanguage = Arrays.asList(); // List<String> | 
    List<String> originalLanguage = Arrays.asList(); // List<String> | 
    List<String> excludedOriginalLanguage = Arrays.asList(); // List<String> | 
    List<String> contentRating = Arrays.asList(); // List<String> | 
    String includeFutureUpdates = "\"1\""; // String | 
    String createdAtSince = "createdAtSince_example"; // String | 
    String updatedAtSince = "updatedAtSince_example"; // String | 
    String publishAtSince = "publishAtSince_example"; // String | 
    Order7 order = new Order7(); // Order7 | 
    List<String> includes = Arrays.asList(); // List<String> | 
    try {
      ChapterList result = apiInstance.getMangaIdFeed(id, limit, offset, translatedLanguage, originalLanguage, excludedOriginalLanguage, contentRating, includeFutureUpdates, createdAtSince, updatedAtSince, publishAtSince, order, includes);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MangaApi#getMangaIdFeed");
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
 **id** | [**UUID**](.md)| Manga ID |
 **limit** | **Integer**|  | [optional] [default to 100]
 **offset** | **Integer**|  | [optional]
 **translatedLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **originalLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **excludedOriginalLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **contentRating** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: safe, suggestive, erotica, pornographic]
 **includeFutureUpdates** | **String**|  | [optional] [default to &quot;1&quot;]
 **createdAtSince** | **String**|  | [optional]
 **updatedAtSince** | **String**|  | [optional]
 **publishAtSince** | **String**|  | [optional]
 **order** | [**Order7**](.md)|  | [optional]
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

<a name="getMangaIdStatus"></a>
# **getMangaIdStatus**
> InlineResponse2007 getMangaIdStatus(id)

Get a Manga reading status

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        MangaApi apiInstance = new MangaApi(defaultClient);
        UUID id = new UUID(); // UUID | 
        try {
            InlineResponse2007 result = apiInstance.getMangaIdStatus(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MangaApi#getMangaIdStatus");
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

[**InlineResponse2007**](InlineResponse2007.md)

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

<a name="getMangaRandom"></a>
# **getMangaRandom**
> MangaResponse getMangaRandom(includes)

Get a random Manga

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    MangaApi apiInstance = new MangaApi(defaultClient);
    List<String> includes = Arrays.asList(); // List<String> | 
    try {
      MangaResponse result = apiInstance.getMangaRandom(includes);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MangaApi#getMangaRandom");
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
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**MangaResponse**](MangaResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="getMangaRelation"></a>
# **getMangaRelation**
> MangaRelationList getMangaRelation(mangaId)

Manga relation list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    MangaApi apiInstance = new MangaApi(defaultClient);
    UUID mangaId = new UUID(); // UUID | 
    try {
      MangaRelationList result = apiInstance.getMangaRelation(mangaId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MangaApi#getMangaRelation");
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

### Return type

[**MangaRelationList**](MangaRelationList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Manga relation list |  -  |
**400** | Bad Request |  -  |

<a name="getMangaStatus"></a>
# **getMangaStatus**
> InlineResponse2006 getMangaStatus(status)

Get all Manga reading status for logged User

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        MangaApi apiInstance = new MangaApi(defaultClient);
        String status = "status_example"; // String | Used to filter the list by given status
        try {
            InlineResponse2006 result = apiInstance.getMangaStatus(status);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MangaApi#getMangaStatus");
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
 **status** | **String**| Used to filter the list by given status | [optional] [enum: reading, on_hold, plan_to_read, dropped, re_reading, completed]

### Return type

[**InlineResponse2006**](InlineResponse2006.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="getMangaTag"></a>
# **getMangaTag**
> TagResponse getMangaTag()

Tag list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    MangaApi apiInstance = new MangaApi(defaultClient);
    try {
      TagResponse result = apiInstance.getMangaTag();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MangaApi#getMangaTag");
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

[**TagResponse**](TagResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="getSearchManga"></a>
# **getSearchManga**
> MangaList getSearchManga(limit, offset, title, authors, artists, year, includedTags, includedTagsMode, excludedTags, excludedTagsMode, status, originalLanguage, excludedOriginalLanguage, availableTranslatedLanguage, publicationDemographic, ids, contentRating, createdAtSince, updatedAtSince, order, includes, hasAvailableChapters)

Manga list

Search a list of Manga.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    MangaApi apiInstance = new MangaApi(defaultClient);
    Integer limit = 10; // Integer | 
    Integer offset = 56; // Integer | 
    String title = "title_example"; // String | 
    List<UUID> authors = Arrays.asList(); // List<UUID> | 
    List<UUID> artists = Arrays.asList(); // List<UUID> | 
    Integer year = 56; // Integer | Year of release
    List<UUID> includedTags = Arrays.asList(); // List<UUID> | 
    String includedTagsMode = "AND"; // String | 
    List<UUID> excludedTags = Arrays.asList(); // List<UUID> | 
    String excludedTagsMode = "OR"; // String | 
    List<String> status = Arrays.asList(); // List<String> | 
    List<String> originalLanguage = Arrays.asList(); // List<String> | 
    List<String> excludedOriginalLanguage = Arrays.asList(); // List<String> | 
    List<String> availableTranslatedLanguage = Arrays.asList(); // List<String> | 
    List<String> publicationDemographic = Arrays.asList(); // List<String> | 
    List<UUID> ids = Arrays.asList(); // List<UUID> | Manga ids (limited to 100 per request)
    List<String> contentRating = Arrays.asList(); // List<String> | 
    String createdAtSince = "createdAtSince_example"; // String | 
    String updatedAtSince = "updatedAtSince_example"; // String | 
    Order order = new Order(); // Order | 
    List<String> includes = Arrays.asList(); // List<String> | 
    String hasAvailableChapters = "hasAvailableChapters_example"; // String | 
    try {
      MangaList result = apiInstance.getSearchManga(limit, offset, title, authors, artists, year, includedTags, includedTagsMode, excludedTags, excludedTagsMode, status, originalLanguage, excludedOriginalLanguage, availableTranslatedLanguage, publicationDemographic, ids, contentRating, createdAtSince, updatedAtSince, order, includes, hasAvailableChapters);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MangaApi#getSearchManga");
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
 **title** | **String**|  | [optional]
 **authors** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **artists** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **year** | **Integer**| Year of release | [optional]
 **includedTags** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **includedTagsMode** | **String**|  | [optional] [default to AND] [enum: AND, OR]
 **excludedTags** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]
 **excludedTagsMode** | **String**|  | [optional] [default to OR] [enum: AND, OR]
 **status** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: ongoing, completed, hiatus, cancelled]
 **originalLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **excludedOriginalLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **availableTranslatedLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **publicationDemographic** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: shounen, shoujo, josei, seinen, none]
 **ids** | [**List&lt;UUID&gt;**](UUID.md)| Manga ids (limited to 100 per request) | [optional]
 **contentRating** | [**List&lt;String&gt;**](String.md)|  | [optional] [enum: safe, suggestive, erotica, pornographic]
 **createdAtSince** | **String**|  | [optional]
 **updatedAtSince** | **String**|  | [optional]
 **order** | [**Order**](.md)|  | [optional] [default to {&quot;latestUploadedChapter&quot;:&quot;desc&quot;}]
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **hasAvailableChapters** | **String**|  | [optional] [enum: 0, 1, true, false]

### Return type

[**MangaList**](MangaList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Manga list |  -  |
**400** | Bad Request |  -  |

<a name="mangaIdAggregateGet"></a>
# **mangaIdAggregateGet**
> InlineResponse200 mangaIdAggregateGet(id, translatedLanguage, groups)

Get Manga volumes &amp; chapters

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    MangaApi apiInstance = new MangaApi(defaultClient);
    UUID id = new UUID(); // UUID | Manga ID
    List<String> translatedLanguage = Arrays.asList(); // List<String> | 
    List<UUID> groups = Arrays.asList(); // List<UUID> | 
    try {
      InlineResponse200 result = apiInstance.mangaIdAggregateGet(id, translatedLanguage, groups);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MangaApi#mangaIdAggregateGet");
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
 **id** | [**UUID**](.md)| Manga ID |
 **translatedLanguage** | [**List&lt;String&gt;**](String.md)|  | [optional]
 **groups** | [**List&lt;UUID&gt;**](UUID.md)|  | [optional]

### Return type

[**InlineResponse200**](InlineResponse200.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="postManga"></a>
# **postManga**
> MangaResponse postManga(contentType, mangaCreate)

Create Manga

Create a new Manga.

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        MangaApi apiInstance = new MangaApi(defaultClient);
        String contentType = "\"application/json\""; // String | 
        MangaCreate mangaCreate = new MangaCreate(); // MangaCreate | The size of the body is limited to 16KB.
        try {
            MangaResponse result = apiInstance.postManga(contentType, mangaCreate);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MangaApi#postManga");
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
 **mangaCreate** | [**MangaCreate**](MangaCreate.md)| The size of the body is limited to 16KB. | [optional]

### Return type

[**MangaResponse**](MangaResponse.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Manga Created |  -  |
**400** | Bad Request |  -  |
**403** | Forbidden |  -  |

<a name="postMangaIdFollow"></a>
# **postMangaIdFollow**
> Response postMangaIdFollow(id)

Follow Manga

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        MangaApi apiInstance = new MangaApi(defaultClient);
        UUID id = new UUID(); // UUID | 
        try {
            Response result = apiInstance.postMangaIdFollow(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MangaApi#postMangaIdFollow");
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
**404** | Not Found |  -  |

<a name="postMangaIdStatus"></a>
# **postMangaIdStatus**
> Response postMangaIdStatus(id, contentType, updateMangaStatus)

Update Manga reading status

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        MangaApi apiInstance = new MangaApi(defaultClient);
        UUID id = new UUID(); // UUID | 
        String contentType = "\"application/json\""; // String | 
        UpdateMangaStatus updateMangaStatus = new UpdateMangaStatus(); // UpdateMangaStatus | Using a `null` value in `status` field will remove the Manga reading status. The size of the body is limited to 2KB.
        try {
            Response result = apiInstance.postMangaIdStatus(id, contentType, updateMangaStatus);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MangaApi#postMangaIdStatus");
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
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **updateMangaStatus** | [**UpdateMangaStatus**](UpdateMangaStatus.md)| Using a &#x60;null&#x60; value in &#x60;status&#x60; field will remove the Manga reading status. The size of the body is limited to 2KB. | [optional]

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
**400** | Bad Request |  -  |
**404** | Not Found |  -  |

<a name="postMangaRelation"></a>
# **postMangaRelation**
> MangaRelationResponse postMangaRelation(mangaId, contentType, mangaRelationCreate)

Create Manga relation

Create a new Manga relation.

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        MangaApi apiInstance = new MangaApi(defaultClient);
        UUID mangaId = new UUID(); // UUID | 
        String contentType = "\"application/json\""; // String | 
        MangaRelationCreate mangaRelationCreate = new MangaRelationCreate(); // MangaRelationCreate | The size of the body is limited to 8KB.
        try {
            MangaRelationResponse result = apiInstance.postMangaRelation(mangaId, contentType, mangaRelationCreate);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MangaApi#postMangaRelation");
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
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **mangaRelationCreate** | [**MangaRelationCreate**](MangaRelationCreate.md)| The size of the body is limited to 8KB. | [optional]

### Return type

[**MangaRelationResponse**](MangaRelationResponse.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Manga relation created |  -  |
**400** | Bad Request |  -  |
**403** | Forbidden |  -  |

<a name="putMangaId"></a>
# **putMangaId**
> MangaResponse putMangaId(id, contentType, UNKNOWN_BASE_TYPE)

Update Manga

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MangaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        MangaApi apiInstance = new MangaApi(defaultClient);
        UUID id = new UUID(); // UUID | Manga ID
        String contentType = "\"application/json\""; // String | 
        UNKNOWN_BASE_TYPE UNKNOWN_BASE_TYPE = new UNKNOWN_BASE_TYPE(); // UNKNOWN_BASE_TYPE | The size of the body is limited to 16KB.
        try {
            MangaResponse result = apiInstance.putMangaId(id, contentType, UNKNOWN_BASE_TYPE);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MangaApi#putMangaId");
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
 **id** | [**UUID**](.md)| Manga ID |
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **UNKNOWN_BASE_TYPE** | [**UNKNOWN_BASE_TYPE**](UNKNOWN_BASE_TYPE.md)| The size of the body is limited to 16KB. | [optional]

### Return type

[**MangaResponse**](MangaResponse.md)

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

