<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Get locator Zones</name>
   <tag></tag>
   <elementGuidId>5766df5d-e72b-4aca-bb43-2de831d63edd</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>true</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;query\&quot;: \&quot;\&quot;\n}&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;,
  &quot;displayText&quot;: &quot;&quot;,
  &quot;displayVariables&quot;: &quot;&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;
}</httpBodyContent>
   <httpBodyType>GraphQL</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>f972e29f-efae-4acf-a326-478d98bc6029</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>accept</name>
      <type>Main</type>
      <value>*/*</value>
      <webElementGuid>e4cadc8c-43c7-4ebd-90c4-d4237bd66f18</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>accept-language</name>
      <type>Main</type>
      <value>en-US,en;q=0.9</value>
      <webElementGuid>527a6040-2a08-4c70-b67c-a532849ce771</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>authorization</name>
      <type>Main</type>
      <value></value>
      <webElementGuid>be889180-f415-4d65-8d91-a853960b6665</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>sec-ch-ua</name>
      <type>Main</type>
      <value>&quot;Chromium&quot;;v=&quot;116&quot;, &quot;Not)A;Brand&quot;;v=&quot;24&quot;, &quot;Google Chrome&quot;;v=&quot;116&quot;</value>
      <webElementGuid>442bb1d7-04b0-4364-bdf1-6ca1e20981d4</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>sec-ch-ua-mobile</name>
      <type>Main</type>
      <value>?0</value>
      <webElementGuid>b9f51a1b-17b8-42dc-8e51-e3c10c2e6dcf</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>sec-ch-ua-platform</name>
      <type>Main</type>
      <value>&quot;Windows&quot;</value>
      <webElementGuid>a57ba2a0-e418-4c12-859e-5b768ae7956b</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>sec-fetch-dest</name>
      <type>Main</type>
      <value>empty</value>
      <webElementGuid>d37e2dc5-24a1-4916-b85a-3bd5da7b2396</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>sec-fetch-mode</name>
      <type>Main</type>
      <value>cors</value>
      <webElementGuid>3c3eae51-4e01-43d0-9156-4b90b4865a7d</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>sec-fetch-site</name>
      <type>Main</type>
      <value>same-origin</value>
      <webElementGuid>2c10f5dd-ddaa-41a6-a16b-82a769347311</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>source-code</name>
      <type>Main</type>
      <value></value>
      <webElementGuid>c963879f-0967-4e86-803b-6ee85a90321c</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>store</name>
      <type>Main</type>
      <value>${store}</value>
      <webElementGuid>40e076cb-938d-40c3-a90a-391cc5c2c83b</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>user-agent</name>
      <type>Main</type>
      <value>Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36</value>
      <webElementGuid>a31a9df8-dcad-4585-b52d-0151c7a953e7</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>x-magento-cache-id</name>
      <type>Main</type>
      <value>null</value>
      <webElementGuid>ddd80286-2320-4804-bc8d-0bb66fdb10fe</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.6.8</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>${URL}/graphql?query=query getLocatorZones{getLocatorZones{coordinate{latitude longitude __typename}description entity_id name __typename}}&amp;operationName=getLocatorZones&amp;variables={}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>GlobalVariable.URL</defaultValue>
      <description></description>
      <id>52acdbd7-8833-4a4a-a550-87b915584e85</id>
      <masked>false</masked>
      <name>URL</name>
   </variables>
   <variables>
      <defaultValue>'tanmiah_ar'</defaultValue>
      <description></description>
      <id>02f40a24-af00-4cf4-b4b8-a4cdaa2572ae</id>
      <masked>false</masked>
      <name>store</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
