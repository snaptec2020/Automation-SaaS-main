<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>getLandingPage</name>
   <tag></tag>
   <elementGuidId>834061a7-4ebc-42ec-bbae-0649d4b962ee</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;query\&quot;: \&quot;query getLandingPage($landingCategoryId:Int!){getLandingPage(input:{landing_category_id:$landingCategoryId}){landing_page_id landing_page{landing_type landing_title landing_show_title landing_position_title banner{banner_id banner_name banner_url banner_image banner_image_mobile __typename}cms_block{title identifier content background_image __typename}slider{banner_code banner_id banner_image banner_image_mobile banner_name banner_url height height_mobile matrix_height_percent matrix_height_percent_mobile matrix_width_percent matrix_width_percent_mobile sort_order status width width_mobile __typename}product_list{productlist_id category_url list_type category_id list_title list_show_title list_position_title list_image list_image_tablet location slider_rows products{total_count items{id name sku url_key url_suffix url_rewrites{url __typename}stock_status salable_qty price{minimalPrice{amount{currency value __typename}__typename}maximalPrice{amount{currency value __typename}__typename}regularPrice{amount{currency value __typename}__typename}__typename}price_range{minimum_price{regular_price{value currency __typename}final_price{value currency __typename}__typename}__typename}label_am_list{image label_id name position product_id size style txt customer_group_ids __typename}...on ConfigurableProduct{variants{attributes{code value_index __typename}product{id media_gallery_entries{id disabled file label position __typename}sku stock_status salable_qty price{regularPrice{amount{currency value __typename}__typename}__typename}__typename}__typename}__typename}special_price special_from_date special_to_date rating_summary review_count image{disabled label position url __typename}small_image{disabled label position url __typename}thumbnail{disabled label position url __typename}special_to_date __typename}__typename}__typename}__typename}__typename}}\&quot;,\n  \&quot;variables\&quot;: \&quot;{\\\&quot;landingCategoryId\\\&quot;:2}\&quot;\n}&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;,
  &quot;displayText&quot;: &quot;query getLandingPage($landingCategoryId:Int!){getLandingPage(input:{landing_category_id:$landingCategoryId}){landing_page_id landing_page{landing_type landing_title landing_show_title landing_position_title banner{banner_id banner_name banner_url banner_image banner_image_mobile __typename}cms_block{title identifier content background_image __typename}slider{banner_code banner_id banner_image banner_image_mobile banner_name banner_url height height_mobile matrix_height_percent matrix_height_percent_mobile matrix_width_percent matrix_width_percent_mobile sort_order status width width_mobile __typename}product_list{productlist_id category_url list_type category_id list_title list_show_title list_position_title list_image list_image_tablet location slider_rows products{total_count items{id name sku url_key url_suffix url_rewrites{url __typename}stock_status salable_qty price{minimalPrice{amount{currency value __typename}__typename}maximalPrice{amount{currency value __typename}__typename}regularPrice{amount{currency value __typename}__typename}__typename}price_range{minimum_price{regular_price{value currency __typename}final_price{value currency __typename}__typename}__typename}label_am_list{image label_id name position product_id size style txt customer_group_ids __typename}...on ConfigurableProduct{variants{attributes{code value_index __typename}product{id media_gallery_entries{id disabled file label position __typename}sku stock_status salable_qty price{regularPrice{amount{currency value __typename}__typename}__typename}__typename}__typename}__typename}special_price special_from_date special_to_date rating_summary review_count image{disabled label position url __typename}small_image{disabled label position url __typename}thumbnail{disabled label position url __typename}special_to_date __typename}__typename}__typename}__typename}__typename}}&quot;,
  &quot;displayVariables&quot;: &quot;{\&quot;landingCategoryId\&quot;:2}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;
}</httpBodyContent>
   <httpBodyType>GraphQL</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>e888ea57-2557-4ec0-81a5-fb3e54f85dd0</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>store</name>
      <type>Main</type>
      <value>&quot;${store}&quot;</value>
      <webElementGuid>0054da8d-0974-452a-bf59-893754a9611a</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.5.5</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${URL}graphql</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>GlobalVariable.store</defaultValue>
      <description></description>
      <id>589d25c2-f2e8-47a5-8c0a-d3243b792a92</id>
      <masked>false</masked>
      <name>store</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.URL</defaultValue>
      <description></description>
      <id>80427bf4-4b16-4a89-b0c0-b7f83b30dc07</id>
      <masked>false</masked>
      <name>URL</name>
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
