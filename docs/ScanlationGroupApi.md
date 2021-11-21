# ScanlationGroupApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteGroupId**](ScanlationGroupApi.md#deleteGroupId) | **DELETE** /group/{id} | Delete Scanlation Group
[**deleteGroupIdFollow**](ScanlationGroupApi.md#deleteGroupIdFollow) | **DELETE** /group/{id}/follow | Unfollow Scanlation Group
[**getGroupId**](ScanlationGroupApi.md#getGroupId) | **GET** /group/{id} | View Scanlation Group
[**getSearchGroup**](ScanlationGroupApi.md#getSearchGroup) | **GET** /group | Scanlation Group list
[**postGroup**](ScanlationGroupApi.md#postGroup) | **POST** /group | Create Scanlation Group
[**postGroupIdFollow**](ScanlationGroupApi.md#postGroupIdFollow) | **POST** /group/{id}/follow | Follow Scanlation Group
[**putGroupId**](ScanlationGroupApi.md#putGroupId) | **PUT** /group/{id} | Update Scanlation Group


<a name="deleteGroupId"></a>
# **deleteGroupId**
> Response deleteGroupId(id)

Delete Scanlation Group

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ScanlationGroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        ScanlationGroupApi apiInstance = new ScanlationGroupApi(defaultClient);
        UUID id = new UUID(); // UUID | Scanlation Group ID
        try {
            Response result = apiInstance.deleteGroupId(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ScanlationGroupApi#deleteGroupId");
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
 **id** | [**UUID**](.md)| Scanlation Group ID |

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

<a name="deleteGroupIdFollow"></a>
# **deleteGroupIdFollow**
> Response deleteGroupIdFollow(id)

Unfollow Scanlation Group

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ScanlationGroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        ScanlationGroupApi apiInstance = new ScanlationGroupApi(defaultClient);
        UUID id = new UUID(); // UUID | 
        try {
            Response result = apiInstance.deleteGroupIdFollow(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ScanlationGroupApi#deleteGroupIdFollow");
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

<a name="getGroupId"></a>
# **getGroupId**
> ScanlationGroupResponse getGroupId(id, includes)

View Scanlation Group

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ScanlationGroupApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    ScanlationGroupApi apiInstance = new ScanlationGroupApi(defaultClient);
    UUID id = new UUID(); // UUID | Scanlation Group ID
    List<String> includes = Arrays.asList(); // List<String> | 
    try {
      ScanlationGroupResponse result = apiInstance.getGroupId(id, includes);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ScanlationGroupApi#getGroupId");
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
 **id** | [**UUID**](.md)| Scanlation Group ID |
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**ScanlationGroupResponse**](ScanlationGroupResponse.md)

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
**404** | ScanlationGroup not found |  -  |

<a name="getSearchGroup"></a>
# **getSearchGroup**
> ScanlationGroupList getSearchGroup(limit, offset, ids, name, focusedLanguage, includes)

Scanlation Group list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ScanlationGroupApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    ScanlationGroupApi apiInstance = new ScanlationGroupApi(defaultClient);
    Integer limit = 10; // Integer | 
    Integer offset = 56; // Integer | 
    List<UUID> ids = Arrays.asList(); // List<UUID> | ScanlationGroup ids (limited to 100 per request)
    String name = "name_example"; // String | 
    String focusedLanguage = "focusedLanguage_example"; // String | 
    List<String> includes = Arrays.asList(); // List<String> | 
    try {
      ScanlationGroupList result = apiInstance.getSearchGroup(limit, offset, ids, name, focusedLanguage, includes);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ScanlationGroupApi#getSearchGroup");
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
 **ids** | [**List&lt;UUID&gt;**](UUID.md)| ScanlationGroup ids (limited to 100 per request) | [optional]
 **name** | **String**|  | [optional]
 **focusedLanguage** | **String**|  | [optional]
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**ScanlationGroupList**](ScanlationGroupList.md)

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

<a name="postGroup"></a>
# **postGroup**
> ScanlationGroupResponse postGroup(contentType, createScanlationGroup)

Create Scanlation Group

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ScanlationGroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        ScanlationGroupApi apiInstance = new ScanlationGroupApi(defaultClient);
        String contentType = "\"application/json\""; // String | 
        CreateScanlationGroup createScanlationGroup = new CreateScanlationGroup(); // CreateScanlationGroup | The size of the body is limited to 16KB.
        try {
            ScanlationGroupResponse result = apiInstance.postGroup(contentType, createScanlationGroup);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ScanlationGroupApi#postGroup");
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
 **createScanlationGroup** | [**CreateScanlationGroup**](CreateScanlationGroup.md)| The size of the body is limited to 16KB. | [optional]

### Return type

[**ScanlationGroupResponse**](ScanlationGroupResponse.md)

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

<a name="postGroupIdFollow"></a>
# **postGroupIdFollow**
> Response postGroupIdFollow(id)

Follow Scanlation Group

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ScanlationGroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        ScanlationGroupApi apiInstance = new ScanlationGroupApi(defaultClient);
        UUID id = new UUID(); // UUID | 
        try {
            Response result = apiInstance.postGroupIdFollow(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ScanlationGroupApi#postGroupIdFollow");
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

<a name="putGroupId"></a>
# **putGroupId**
> ScanlationGroupResponse putGroupId(id, contentType, scanlationGroupEdit)

Update Scanlation Group

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ScanlationGroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        ScanlationGroupApi apiInstance = new ScanlationGroupApi(defaultClient);
        UUID id = new UUID(); // UUID | Scanlation Group ID
        String contentType = "\"application/json\""; // String | 
        ScanlationGroupEdit scanlationGroupEdit = new ScanlationGroupEdit(); // ScanlationGroupEdit | The size of the body is limited to 8KB.
        try {
            ScanlationGroupResponse result = apiInstance.putGroupId(id, contentType, scanlationGroupEdit);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ScanlationGroupApi#putGroupId");
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
 **id** | [**UUID**](.md)| Scanlation Group ID |
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **scanlationGroupEdit** | [**ScanlationGroupEdit**](ScanlationGroupEdit.md)| The size of the body is limited to 8KB. | [optional]

### Return type

[**ScanlationGroupResponse**](ScanlationGroupResponse.md)

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

