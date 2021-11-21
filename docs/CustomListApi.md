# CustomListApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteListId**](CustomListApi.md#deleteListId) | **DELETE** /list/{id} | Delete CustomList
[**deleteMangaIdListListId**](CustomListApi.md#deleteMangaIdListListId) | **DELETE** /manga/{id}/list/{listId} | Remove Manga in CustomList
[**getListId**](CustomListApi.md#getListId) | **GET** /list/{id} | Get CustomList
[**getUserIdList**](CustomListApi.md#getUserIdList) | **GET** /user/{id}/list | Get User&#39;s CustomList list
[**getUserList**](CustomListApi.md#getUserList) | **GET** /user/list | Get logged User CustomList list
[**postList**](CustomListApi.md#postList) | **POST** /list | Create CustomList
[**postMangaIdListListId**](CustomListApi.md#postMangaIdListListId) | **POST** /manga/{id}/list/{listId} | Add Manga in CustomList
[**putListId**](CustomListApi.md#putListId) | **PUT** /list/{id} | Update CustomList


<a name="deleteListId"></a>
# **deleteListId**
> Response deleteListId(id)

Delete CustomList

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CustomListApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        CustomListApi apiInstance = new CustomListApi(defaultClient);
        UUID id = new UUID(); // UUID | CustomList ID
        try {
            Response result = apiInstance.deleteListId(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CustomListApi#deleteListId");
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
 **id** | [**UUID**](.md)| CustomList ID |

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
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="deleteMangaIdListListId"></a>
# **deleteMangaIdListListId**
> Response deleteMangaIdListListId(id, listId)

Remove Manga in CustomList

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CustomListApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        CustomListApi apiInstance = new CustomListApi(defaultClient);
        UUID id = new UUID(); // UUID | Manga ID
        UUID listId = new UUID(); // UUID | CustomList ID
        try {
            Response result = apiInstance.deleteMangaIdListListId(id, listId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CustomListApi#deleteMangaIdListListId");
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
 **listId** | [**UUID**](.md)| CustomList ID |

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
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="getListId"></a>
# **getListId**
> CustomListResponse getListId(id)

Get CustomList

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CustomListApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    CustomListApi apiInstance = new CustomListApi(defaultClient);
    UUID id = new UUID(); // UUID | CustomList ID
    try {
      CustomListResponse result = apiInstance.getListId(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomListApi#getListId");
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
 **id** | [**UUID**](.md)| CustomList ID |

### Return type

[**CustomListResponse**](CustomListResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**404** | CustomList not found |  -  |

<a name="getUserIdList"></a>
# **getUserIdList**
> CustomListList getUserIdList(id, limit, offset)

Get User&#39;s CustomList list

This will list only public CustomList

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CustomListApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    CustomListApi apiInstance = new CustomListApi(defaultClient);
    UUID id = new UUID(); // UUID | User ID
    Integer limit = 10; // Integer | 
    Integer offset = 56; // Integer | 
    try {
      CustomListList result = apiInstance.getUserIdList(id, limit, offset);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CustomListApi#getUserIdList");
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
 **id** | [**UUID**](.md)| User ID |
 **limit** | **Integer**|  | [optional] [default to 10]
 **offset** | **Integer**|  | [optional]

### Return type

[**CustomListList**](CustomListList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="getUserList"></a>
# **getUserList**
> CustomListList getUserList(limit, offset)

Get logged User CustomList list

This will list public and private CustomList

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CustomListApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        CustomListApi apiInstance = new CustomListApi(defaultClient);
        Integer limit = 10; // Integer | 
        Integer offset = 56; // Integer | 
        try {
            CustomListList result = apiInstance.getUserList(limit, offset);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CustomListApi#getUserList");
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

### Return type

[**CustomListList**](CustomListList.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="postList"></a>
# **postList**
> CustomListResponse postList(contentType, customListCreate)

Create CustomList

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CustomListApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        CustomListApi apiInstance = new CustomListApi(defaultClient);
        String contentType = "\"application/json\""; // String | 
        CustomListCreate customListCreate = new CustomListCreate(); // CustomListCreate | The size of the body is limited to 8KB.
        try {
            CustomListResponse result = apiInstance.postList(contentType, customListCreate);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CustomListApi#postList");
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
 **customListCreate** | [**CustomListCreate**](CustomListCreate.md)| The size of the body is limited to 8KB. | [optional]

### Return type

[**CustomListResponse**](CustomListResponse.md)

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

<a name="postMangaIdListListId"></a>
# **postMangaIdListListId**
> Response postMangaIdListListId(id, listId)

Add Manga in CustomList

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CustomListApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        CustomListApi apiInstance = new CustomListApi(defaultClient);
        UUID id = new UUID(); // UUID | Manga ID
        UUID listId = new UUID(); // UUID | CustomList ID
        try {
            Response result = apiInstance.postMangaIdListListId(id, listId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CustomListApi#postMangaIdListListId");
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
 **listId** | [**UUID**](.md)| CustomList ID |

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
**403** | Forbidden |  -  |
**404** | Not Found |  -  |

<a name="putListId"></a>
# **putListId**
> CustomListResponse putListId(id, contentType, customListEdit)

Update CustomList

The size of the body is limited to 8KB.

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CustomListApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        CustomListApi apiInstance = new CustomListApi(defaultClient);
        UUID id = new UUID(); // UUID | CustomList ID
        String contentType = "\"application/json\""; // String | 
        CustomListEdit customListEdit = new CustomListEdit(); // CustomListEdit | 
        try {
            CustomListResponse result = apiInstance.putListId(id, contentType, customListEdit);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CustomListApi#putListId");
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
 **id** | [**UUID**](.md)| CustomList ID |
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **customListEdit** | [**CustomListEdit**](CustomListEdit.md)|  | [optional]

### Return type

[**CustomListResponse**](CustomListResponse.md)

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

