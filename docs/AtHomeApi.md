# AtHomeApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAtHomeServerChapterId**](AtHomeApi.md#getAtHomeServerChapterId) | **GET** /at-home/server/{chapterId} | Get MangaDex@Home server URL


<a name="getAtHomeServerChapterId"></a>
# **getAtHomeServerChapterId**
> InlineResponse2005 getAtHomeServerChapterId(chapterId, forcePort443)

Get MangaDex@Home server URL

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AtHomeApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    AtHomeApi apiInstance = new AtHomeApi(defaultClient);
    UUID chapterId = new UUID(); // UUID | Chapter ID
    Boolean forcePort443 = false; // Boolean | Force selecting from MangaDex@Home servers that use the standard HTTPS port 443.  While the conventional port for HTTPS traffic is 443 and servers are encouraged to use it, it is not a hard requirement as it technically isn't anything special.  However, some misbehaving school/office network will at time block traffic to non-standard ports, and setting this flag to `true` will ensure selection of a server that uses these.
    try {
      InlineResponse2005 result = apiInstance.getAtHomeServerChapterId(chapterId, forcePort443);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AtHomeApi#getAtHomeServerChapterId");
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
 **chapterId** | [**UUID**](.md)| Chapter ID |
 **forcePort443** | **Boolean**| Force selecting from MangaDex@Home servers that use the standard HTTPS port 443.  While the conventional port for HTTPS traffic is 443 and servers are encouraged to use it, it is not a hard requirement as it technically isn&#39;t anything special.  However, some misbehaving school/office network will at time block traffic to non-standard ports, and setting this flag to &#x60;true&#x60; will ensure selection of a server that uses these. | [optional] [default to false]

### Return type

[**InlineResponse2005**](InlineResponse2005.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**404** | Not Found |  -  |

