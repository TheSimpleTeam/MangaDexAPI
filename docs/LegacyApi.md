# LegacyApi

All URIs are relative to *https://api.mangadex.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**postLegacyMapping**](LegacyApi.md#postLegacyMapping) | **POST** /legacy/mapping | Legacy ID mapping


<a name="postLegacyMapping"></a>
# **postLegacyMapping**
> MappingIdResponse postLegacyMapping(contentType, mappingIdBody)

Legacy ID mapping

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.LegacyApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mangadex.org");

    LegacyApi apiInstance = new LegacyApi(defaultClient);
    String contentType = "\"application/json\""; // String | 
    MappingIdBody mappingIdBody = new MappingIdBody(); // MappingIdBody | The size of the body is limited to 10KB.
    try {
      MappingIdResponse result = apiInstance.postLegacyMapping(contentType, mappingIdBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling LegacyApi#postLegacyMapping");
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
 **mappingIdBody** | [**MappingIdBody**](MappingIdBody.md)| The size of the body is limited to 10KB. | [optional]

### Return type

[**MappingIdResponse**](MappingIdResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | This response will give you an array of mappings of resource identifiers ; the &#x60;data.attributes.newId&#x60; field corresponds to the new UUID. |  -  |
**400** | Bad Request |  -  |

