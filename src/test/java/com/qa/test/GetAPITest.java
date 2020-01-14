package com.qa.test;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.TestUtil.TestUtils;
import com.qa.base.TestBase;
import com.qa.client.RestClient;


public class GetAPITest extends TestBase{

	TestBase testbase;
	String serviceurl;
	String apiurl;
	String url;
	RestClient restclient;
	CloseableHttpResponse closablehttpResponse;
	@BeforeMethod
	public void setup(){
		
		testbase=new TestBase();
		
		serviceurl = prop.getProperty("URL");
		apiurl = prop.getProperty("ServiceURL");
		url = serviceurl+apiurl;
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException{
		restclient = new RestClient();
		closablehttpResponse = restclient.get(url);
		
        int statusCode = closablehttpResponse.getStatusLine().getStatusCode();
		
		System.out.println("Status Code -------->" + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		
		 String responseString = EntityUtils.toString(closablehttpResponse.getEntity(),"UTF-8");
		
		 JSONObject responseJSON = new JSONObject(responseString);
		 
		 String perpage = TestUtils.getValueByJpath(responseJSON, "per_page");
		 System.out.println("perpage---------->" + perpage );
		 String Lastname = TestUtils.getValueByJpath(responseJSON, "data[1]/last_name");
		 System.out.println("perpage---------->" + Lastname );
		 System.out.println("ResponseJSON------->"+responseJSON);
	
		 Header[] allHeaders = closablehttpResponse.getAllHeaders();
		 
		 HashMap<String, String> headers = new HashMap<String, String>();
		 
		 for (Header header : allHeaders) {
			 
			 headers.put(header.getName(), header.getValue());
			
		}
		 System.out.println(headers);
		
		
	}
	
}
