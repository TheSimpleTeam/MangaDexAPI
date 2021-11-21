# AuthorApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteAuthorId**](AuthorApi.md#deleteAuthorId) | **DELETE** /author/{id} | Delete Author
[**getAuthor**](AuthorApi.md#getAuthor) | **GET** /author | Author list
[**getAuthorId**](AuthorApi.md#getAuthorId) | **GET** /author/{id} | Get Author
[**postAuthor**](AuthorApi.md#postAuthor) | **POST** /author | Create Author
[**putAuthorId**](AuthorApi.md#putAuthorId) | **PUT** /author/{id} | Update Author


<a name="deleteAuthorId"></a>
# **deleteAuthorId**
> Response deleteAuthorId(id)

Delete Author

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthorApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        AuthorApi apiInstance = new AuthorApi(defaultClient);
        UUID id = new UUID(); // UUID | Author ID
        try {
            Response result = apiInstance.deleteAuthorId(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthorApi#deleteAuthorId");
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
 **id** | [**UUID**](.md)| Author ID |

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

<a name="getAuthor"></a>
# **getAuthor**
> AuthorList getAuthor(limit, offset, ids, name, order, includes)

Author list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthorApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    AuthorApi apiInstance = new AuthorApi(defaultClient);
    Integer limit = 10; // Integer | 
    Integer offset = 56; // Integer | 
    List<UUID> ids = Arrays.asList(); // List<UUID> | Author ids (limited to 100 per request)
    String name = "name_example"; // String | 
    Order6 order = new Order6(); // Order6 | 
    List<String> includes = Arrays.asList(); // List<String> | 
    try {
      AuthorList result = apiInstance.getAuthor(limit, offset, ids, name, order, includes);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthorApi#getAuthor");
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
 **ids** | [**List&lt;UUID&gt;**](UUID.md)| Author ids (limited to 100 per request) | [optional]
 **name** | **String**|  | [optional]
 **order** | [**Order6**](.md)|  | [optional]
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**AuthorList**](AuthorList.md)

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

<a name="getAuthorId"></a>
# **getAuthorId**
> AuthorResponse getAuthorId(id, includes)

Get Author

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthorApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    AuthorApi apiInstance = new AuthorApi(defaultClient);
    UUID id = new UUID(); // UUID | Author ID
    List<String> includes = Arrays.asList(); // List<String> | 
    try {
      AuthorResponse result = apiInstance.getAuthorId(id, includes);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthorApi#getAuthorId");
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
 **id** | [**UUID**](.md)| Author ID |
 **includes** | [**List&lt;String&gt;**](String.md)|  | [optional]

### Return type

[**AuthorResponse**](AuthorResponse.md)

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
**404** | Author no content |  -  |

<a name="postAuthor"></a>
# **postAuthor**
> AuthorResponse postAuthor(contentType, authorCreate)

Create Author

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthorApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        AuthorApi apiInstance = new AuthorApi(defaultClient);
        String contentType = "\"application/json\""; // String | 
        AuthorCreate authorCreate = new AuthorCreate(); // AuthorCreate | The size of the body is limited to 2KB.
        try {
            AuthorResponse result = apiInstance.postAuthor(contentType, authorCreate);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthorApi#postAuthor");
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
 **authorCreate** | [**AuthorCreate**](AuthorCreate.md)| The size of the body is limited to 2KB. | [optional]

### Return type

[**AuthorResponse**](AuthorResponse.md)

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

<a name="putAuthorId"></a>
# **putAuthorId**
> AuthorResponse putAuthorId(id, contentType, authorEdit)

Update Author

### Example

```java
// Import classes:

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AuthorApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.mangadex.org");

        // Configure HTTP bearer authorization: Bearer
        HttpBearerAuth Bearer = (HttpBearerAuth) defaultClient.getAuthentication("Bearer");
        Bearer.setBearerToken("BEARER TOKEN");

        AuthorApi apiInstance = new AuthorApi(defaultClient);
        UUID id = new UUID(); // UUID | Author ID
        String contentType = "\"application/json\""; // String | 
        AuthorEdit authorEdit = new AuthorEdit(); // AuthorEdit | The size of the body is limited to 2KB.
        try {
            AuthorResponse result = apiInstance.putAuthorId(id, contentType, authorEdit);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthorApi#putAuthorId");
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
 **id** | [**UUID**](.md)| Author ID |
 **contentType** | **String**|  | [default to &quot;application/json&quot;]
 **authorEdit** | [**AuthorEdit**](AuthorEdit.md)| The size of the body is limited to 2KB. | [optional]

### Return type

[**AuthorResponse**](AuthorResponse.md)

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

