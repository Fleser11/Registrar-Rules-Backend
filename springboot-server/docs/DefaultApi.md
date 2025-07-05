# DefaultApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**auditsAuditRunPost**](DefaultApi.md#auditsAuditRunPost) | **POST** /audits/{audit}/run | Attempts to run the audit. |
| [**auditsGet**](DefaultApi.md#auditsGet) | **GET** /audits | gets audit. |
| [**auditsPost**](DefaultApi.md#auditsPost) | **POST** /audits | submits a new audit. |


<a id="auditsAuditRunPost"></a>
# **auditsAuditRunPost**
> RunConfig auditsAuditRunPost(audit, runConfig)

Attempts to run the audit.

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
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String audit = "audit_example"; // String | The name of the audit to run.
    RunConfig runConfig = new RunConfig(); // RunConfig | 
    try {
      RunConfig result = apiInstance.auditsAuditRunPost(audit, runConfig);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#auditsAuditRunPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **audit** | **String**| The name of the audit to run. | |
| **runConfig** | [**RunConfig**](RunConfig.md)|  | |

### Return type

[**RunConfig**](RunConfig.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A JSON array of user names |  -  |
| **400** | Bad Request - The request was invalid or cannot be served. |  -  |
| **500** | Internal Server Error - An unexpected error occurred. |  -  |

<a id="auditsGet"></a>
# **auditsGet**
> Audit auditsGet(name)

gets audit.

Gets the audit with the specified name.

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
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String name = "name_example"; // String | The name of the audit to retrieve.
    try {
      Audit result = apiInstance.auditsGet(name);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#auditsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **name** | **String**| The name of the audit to retrieve. | |

### Return type

[**Audit**](Audit.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A JSON array of audit names |  -  |
| **400** | Bad Request - The request was invalid or cannot be served. |  -  |
| **404** | Not Found - The specified audit does not exist. |  -  |
| **500** | Internal Server Error - An unexpected error occurred. |  -  |

<a id="auditsPost"></a>
# **auditsPost**
> Audit auditsPost(audit)

submits a new audit.

Creates a new audit with the specified name and subAudit.

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
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Audit audit = new Audit(); // Audit | 
    try {
      Audit result = apiInstance.auditsPost(audit);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#auditsPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **audit** | [**Audit**](Audit.md)|  | |

### Return type

[**Audit**](Audit.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Audit created successfully |  -  |
| **400** | Bad Request - The request was invalid or cannot be served. |  -  |
| **500** | Internal Server Error - An unexpected error occurred. |  -  |

