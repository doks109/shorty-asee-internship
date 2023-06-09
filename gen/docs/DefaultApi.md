# DefaultApi

All URIs are relative to *https://shorty_asee_internship*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getShortenedUrl**](DefaultApi.md#getShortenedUrl) | **GET** /{shortenedUrl} | GET {shortenedUrl}


<a name="getShortenedUrl"></a>
# **getShortenedUrl**
> RedirectView getShortenedUrl(shortenedUrl)

GET {shortenedUrl}

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://shorty_asee_internship");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String shortenedUrl = "shortenedUrl_example"; // String | 
    try {
      RedirectView result = apiInstance.getShortenedUrl(shortenedUrl);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getShortenedUrl");
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
 **shortenedUrl** | **String**|  |

### Return type

[**RedirectView**](RedirectView.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

