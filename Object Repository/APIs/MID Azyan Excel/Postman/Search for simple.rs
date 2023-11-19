<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Search for simple</name>
   <tag></tag>
   <elementGuidId>5bffd3cd-0ff3-452a-be43-40f8936b2c5b</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <authorizationRequest>
      <authorizationInfo>
         <entry>
            <key>bearerToken</key>
            <value>${token}</value>
         </entry>
      </authorizationInfo>
      <authorizationType>Bearer</authorizationType>
   </authorizationRequest>
   <autoUpdateContent>true</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>authority</name>
      <type>Main</type>
      <value>admin.thenineten.co</value>
      <webElementGuid>5ff2446b-9672-4978-95e2-212899edd950</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>accept</name>
      <type>Main</type>
      <value>application/json, text/plain, */*</value>
      <webElementGuid>6f362446-0a43-4c0f-b812-0755361dc692</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>accept-language</name>
      <type>Main</type>
      <value>en-US,en;q=0.9</value>
      <webElementGuid>8e33c8ed-38e3-4b56-9e25-0b2c5c9ea775</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>origin</name>
      <type>Main</type>
      <value>https://mid.thenineten.co</value>
      <webElementGuid>ad010d26-7fc0-48cc-9a18-6625a7c43398</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>referer</name>
      <type>Main</type>
      <value>https://mid.thenineten.co/</value>
      <webElementGuid>0937fe48-a2bd-4834-beb8-7fd7ffbbb68c</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>sec-ch-ua</name>
      <type>Main</type>
      <value>&quot;Chromium&quot;;v=&quot;118&quot;, &quot;Google Chrome&quot;;v=&quot;118&quot;, &quot;Not=A?Brand&quot;;v=&quot;99&quot;</value>
      <webElementGuid>459ca266-e974-4e48-aff3-3ce864ef5d25</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>sec-ch-ua-mobile</name>
      <type>Main</type>
      <value>?0</value>
      <webElementGuid>5cb4df58-00b4-43e3-b312-86c263aa9736</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>sec-ch-ua-platform</name>
      <type>Main</type>
      <value>&quot;Windows&quot;</value>
      <webElementGuid>9f564df0-84a3-41c6-bdc9-2d33d2915f1f</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>sec-fetch-dest</name>
      <type>Main</type>
      <value>empty</value>
      <webElementGuid>471d6a28-61f8-4462-ad46-5e38a5b2c9b3</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>sec-fetch-mode</name>
      <type>Main</type>
      <value>cors</value>
      <webElementGuid>2cf317fd-c4ab-4cdb-b1af-2e37dca64e58</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>sec-fetch-site</name>
      <type>Main</type>
      <value>same-site</value>
      <webElementGuid>4d0a3cf1-1d5c-4598-8ade-9ecbe8c61fc7</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>user-agent</name>
      <type>Main</type>
      <value>Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36</value>
      <webElementGuid>aa7840f9-3de8-4834-b32a-87d068014620</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${token}</value>
      <webElementGuid>6d3bbb46-78b6-4933-9a96-2b3e26a45f87</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.6.8</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>https://admin.thenineten.co/index.php/rest/V1/snaptec/product?searchCriteria[filterGroups][0][filters][0][field]=store_id&amp;searchCriteria[filterGroups][0][filters][0][value]=48,49&amp;searchCriteria[filterGroups][0][filters][0][conditionType]=in&amp;searchCriteria[filterGroups][1][filters][0][field]=sku&amp;searchCriteria[filterGroups][1][filters][0][value]=%${simpleProductId}%&amp;searchCriteria[filterGroups][1][filters][0][conditionType]=like&amp;searchCriteria[pageSize]=20&amp;searchCriteria\[currentPage\]=1</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'0nzmruwkvfiiiv5ghaxioh9de1ndaaow'</defaultValue>
      <description></description>
      <id>eb5478e5-85f7-43a2-81eb-4d40c222037a</id>
      <masked>false</masked>
      <name>token</name>
   </variables>
   <variables>
      <defaultValue>'3666131433215'</defaultValue>
      <description></description>
      <id>6a773642-966e-4ede-9404-d17c0bbb8cbe</id>
      <masked>false</masked>
      <name>simpleProductId</name>
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
