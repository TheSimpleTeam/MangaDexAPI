# FollowsApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getUserFollowsGroup**](FollowsApi.md#getUserFollowsGroup) | **GET** /user/follows/group | Get logged User followed Groups
[**getUserFollowsGroupId**](FollowsApi.md#getUserFollowsGroupId) | **GET** /user/follows/group/{id} | Check if logged User follows a Group
[**getUserFollowsGroupId_0**](FollowsApi.md#getUserFollowsGroupId_0) | **GET** /user/follows/user/{id} | Check if logged User follows a User
[**getUserFollowsManga**](FollowsApi.md#getUserFollowsManga) | **GET** /user/follows/manga | Get logged User followed Manga list
[**getUserFollowsMangaId**](FollowsApi.md#getUserFollowsMangaId) | **GET** /user/follows/manga/{id} | Check if logged User follows a Manga
[**getUserFollowsUser**](FollowsApi.md#getUserFollowsUser) | **GET** /user/follows/user | Get logged User followed User list


<a name="getUserFollowsGroup"></a>
# **getUserFollowsGroup**
> ScanlationGroupList getUserFollowsGroup(limit, offset, includes)

Get logged User followed Groups

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FollowsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        FollowsApi apiInstance = new FollowsApi(defaultClient);
        Integer limit = 10; // Integer | 
        Integer offset = 56; // Integer | 
        List<String> includes = Arrays.asList(); // List<String> | 
        try {
            ScanlationGroupList result = apiInstance.getUserFollowsGroup(limit, offset, includes);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FollowsApi#getUserFollowsGroup");
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
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**ScanlationGroupList**](ScanlationGroupList.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="getUserFollowsGroupId"></a>
# **getUserFollowsGroupId**
> Response getUserFollowsGroupId(id)

Check if logged User follows a Group

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FollowsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        FollowsApi apiInstance = new FollowsApi(defaultClient);
        UUID id = new UUID(); // UUID | Scanlation Group id
        try {
            Response result = apiInstance.getUserFollowsGroupId(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FollowsApi#getUserFollowsGroupId");
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
 **id** | [**UUID**](.md)| Scanlation Group id |

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
**200** | The User follow that Group |  -  |
**404** | The User doesn&#39;t follow that Group |  -  |

<a name="getUserFollowsGroupId_0"></a>
# **getUserFollowsGroupId_0**
> Response getUserFollowsGroupId_0(id)

Check if logged User follows a User

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FollowsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        FollowsApi apiInstance = new FollowsApi(defaultClient);
        UUID id = new UUID(); // UUID | User id
        try {
            Response result = apiInstance.getUserFollowsGroupId_0(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FollowsApi#getUserFollowsGroupId_0");
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
 **id** | [**UUID**](.md)| User id |

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
**200** | The User follow that User |  -  |
**404** | The User doesn&#39;t follow that User |  -  |

<a name="getUserFollowsManga"></a>
# **getUserFollowsManga**
> MangaList getUserFollowsManga(limit, offset, includes)

Get logged User followed Manga list

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FollowsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        FollowsApi apiInstance = new FollowsApi(defaultClient);
        Integer limit = 10; // Integer | 
        Integer offset = 56; // Integer | 
        List<String> includes = Arrays.asList(); // List<String> | 
        try {
            MangaList result = apiInstance.getUserFollowsManga(limit, offset, includes);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FollowsApi#getUserFollowsManga");
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
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**MangaList**](MangaList.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="getUserFollowsMangaId"></a>
# **getUserFollowsMangaId**
> Response getUserFollowsMangaId(id)

Check if logged User follows a Manga

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FollowsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        FollowsApi apiInstance = new FollowsApi(defaultClient);
        UUID id = new UUID(); // UUID | Manga id
        try {
            Response result = apiInstance.getUserFollowsMangaId(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FollowsApi#getUserFollowsMangaId");
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
 **id** | [**UUID**](.md)| Manga id |

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
**200** | The User follow that Manga |  -  |
**404** | The User doesn&#39;t follow that Manga |  -  |

<a name="getUserFollowsUser"></a>
# **getUserFollowsUser**
> UserList getUserFollowsUser(limit, offset)

Get logged User followed User list

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FollowsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        FollowsApi apiInstance = new FollowsApi(defaultClient);
        Integer limit = 10; // Integer | 
        Integer offset = 56; // Integer | 
        try {
            UserList result = apiInstance.getUserFollowsUser(limit, offset);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FollowsApi#getUserFollowsUser");
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

[**UserList**](UserList.md)

### Authorization

[Bearer](../README.md#Bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

