package com.neo.identity.utils;

import com.neo.identity.constant.IdentityTagName;
import com.neo.identity.dto.PolicyMapper;
import com.neo.identity.dto.ServiceProvider;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.Policy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ConvertFromXmlToJson {

    public static final int IDENTITY_FACTORY = 4;
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static int time_out = 3 * 60 * 1000;
    @Value("${url.policy}")
    private String urlAllPolicys = "https://identity.kttv.gov.vn:9443/services/EntitlementPolicyAdminService";

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) throws Exception {
        ConvertFromXmlToJson c = new ConvertFromXmlToJson();
        String xml = "";
        System.out.println("Start: " + System.nanoTime()/1000000000 );
        String serviceName = "";
        List<String> listRolesByUsername = c.getListRolesByUsername("administrator");
        List<String> policyMapperList = c.getListPolicys();
        List<ServiceProvider> lstResult = new ArrayList<>();
        List<String> lstSP = c.getAllServiceProvider();

        for (String  policy : policyMapperList){
        //for(int i = 0 ; i < 1 ; i++){
            List<PolicyMapper> mappers = c.getPolicyDetails(policy);
            for(PolicyMapper mapper : mappers){
                for (String s : listRolesByUsername){
                    if(s.equals(mapper.getAttributeValue())){
                        serviceName = mappers.get(0).getAttributeValue();
                        for (String sp : lstSP){
                            if(sp.equals(serviceName)){
                                String url = "";
                                String name = "";
                                List<String> urls = c.getCallBackUrl(serviceName);
                                if(urls != null){
                                    url = urls.get(0);
                                }
//                                List<String> names = c.getServiceProviderDetails(serviceName);
//                                if (names != null){
//                                    name = names.get(0);
//                                }
                                lstResult.add(new ServiceProvider(url, name));
                                break;
                            }
                        }
                    }
                }
            }

        }

    }
    // List<PolicyMapper> mappers = getPolicyDetails("authn_role_based_policy_template");
    static List<String> policyMapperList = getListPolicys();
    static List<ServiceProvider> lstResult = new ArrayList<>();
    static List<String> lstSP = getAllServiceProvider();

    public List<ServiceProvider> getListUrl(String username){
        List<String> listRolesByUsername = getListRolesByUsername("administrator");
        for (String  policy : policyMapperList){
            callRestTemplate(policy);
            List<PolicyMapper> mappers = getPolicyDetails(policy);
            for(PolicyMapper mapper : mappers){
                for (String s : listRolesByUsername){
                    if(s.equals(mapper.getAttributeValue())){
                        for (String sp : lstSP){
                            if(sp.equals(mappers.get(0).getAttributeValue())){
                                String url = "";
                                String name = "";
                                List<String> urls = getCallBackUrl(mappers.get(0).getAttributeValue());
                                if(urls != null){
                                    url = urls.get(0);
                                }
                                lstResult.add(new ServiceProvider(url, name));

                            }
                        }
                    }
                }
            }

        }
        return lstResult;
    }

    public String callRestTemplate(String policyName){
        String createPersonUrl = "https://identity.kttv.gov.vn:9443/services/EntitlementPolicyAdminService";
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_XML);
        headers.set("SOAPAction", "urn:getPolicy");
        headers.set("Authorization", "Basic YWRtaW46MTIzNDU2YUFA");
        headers.set("Content-Type", "text/xml;charset=UTF-8");

//        JSONObject personJsonObject = new JSONObject();
//        personJsonObject.put("id", 1);
//        personJsonObject.put("name", "John");
        String body = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.axis2/xsd\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <xsd:getPolicy>\n" +
                "         <!--Optional:-->\n" +
                "         <xsd:policyId>authn_role_based_policy_template</xsd:policyId>\n" +
                "         <!--Optional:-->\n" +
                "         <xsd:isPDPPolicy>false</xsd:isPDPPolicy>\n" +
                "      </xsd:getPolicy>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        HttpEntity<String> request =
                new HttpEntity<String>(body, headers);
        String personResultAsJsonStr =
                restTemplate.postForObject(createPersonUrl, request, String.class);
        return personResultAsJsonStr;

    }
    public List<String> getListRolesByUsername(String username){
        String url = "https://identity.kttv.gov.vn:9443/services/RemoteUserStoreManagerService";
        StringBuilder request = new StringBuilder();
        request.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ws.um.carbon.wso2.org\">");
        request.append("<soapenv:Header/><soapenv:Body>");
        request.append("<ser:getRoleListOfUser>");
        request.append("<ser:userName>").append(username).append("</ser:userName>");
        request.append("</ser:getRoleListOfUser>");
        request.append("</soapenv:Body></soapenv:Envelope>");
        try {
            return getFullNameFromXml(callSoapHttp(request.toString(), url), "ns:return");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean checkExistsServiceProvider(List<String> lstSP, String value){
        for(String s : lstSP){
            if(s.equals(value)){
                return true;
            }
        }
        return false;
    }

    public List<String> getCallBackUrl(String serviceName){
        String url = "https://identity.kttv.gov.vn:9443/services/OAuthAdminService";
        StringBuilder request = new StringBuilder();
        request.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.axis2/xsd\">");
        request.append("<soapenv:Header/><soapenv:Body>");
        request.append("<xsd:getOAuthApplicationDataByAppName>");
        request.append("<xsd:appName>").append(serviceName).append("</xsd:appName>");
        request.append("</xsd:getOAuthApplicationDataByAppName>");
        request.append("</soapenv:Body></soapenv:Envelope>");
        try {
            return getFullNameFromXml(callSoapHttp(request.toString(), url), IdentityTagName.SERVICE.CALL_BACK_URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getServiceProviderDetails(String serviceName){
        String url = "https://identity.kttv.gov.vn:9443/services/IdentityApplicationManagementService";
        StringBuilder request = new StringBuilder();
        request.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.axis2/xsd\">");
        request.append("<soapenv:Header/><soapenv:Body>");
        request.append("<xsd:getApplication>");
        request.append("<xsd:applicationName>").append(serviceName).append("</xsd:applicationName>");
        request.append("</xsd:getApplication>");
        request.append("</soapenv:Body></soapenv:Envelope>");
        try {
            List<String> rs = getFullNameFromXml(callSoapHttp(request.toString(), url), IdentityTagName.SERVICE.DESCRIPTION);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getAllServiceProvider(){
        String url = "https://identity.kttv.gov.vn:9443/services/IdentityApplicationManagementService";
        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.axis2/xsd\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <xsd:getAllApplicationBasicInfo/>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        try {
            return getFullNameFromXml(callSoapHttp(request, url), "ax2169:applicationName");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getListPolicys(){
        String url = "https://identity.kttv.gov.vn:9443/services/EntitlementPolicyAdminService";
        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.axis2/xsd\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <xsd:getAllPolicyIds>\n" +
                "         <!--Optional:-->\n" +
                "         <xsd:searchString>*</xsd:searchString>\n" +
                "      </xsd:getAllPolicyIds>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        try {
            List<String> getAllPolicys = getFullNameFromXml(callSoapHttp(request, url), "ns:return");

            return getAllPolicys;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<PolicyMapper> getPolicyDetails(String policyName){
        String url = "https://identity.kttv.gov.vn:9443/services/EntitlementPolicyAdminService";
        StringBuilder request = new StringBuilder();
        request.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.axis2/xsd\">");
        request.append("<soapenv:Header/><soapenv:Body>");
        request.append("<xsd:getPolicy>");
        request.append("<xsd:policyId>").append(policyName).append("</xsd:policyId>");
        request.append("<xsd:isPDPPolicy>false</xsd:isPDPPolicy>");
        request.append("</xsd:getPolicy>");
        request.append("</soapenv:Body></soapenv:Envelope>");
        try {
            List<PolicyMapper> policyMapperList = new ArrayList<>();
            String resultHttpSoap = callSoapHttp(request.toString(), url);
            List<String> listDataType = getFullNameFromXml(resultHttpSoap, IdentityTagName.POLICY.ATTRIBUTE_DATA_TYPE);
            List<String> listId = getFullNameFromXml(resultHttpSoap, IdentityTagName.POLICY.ATTRIBUTE_ID);
            List<String> listValue = getFullNameFromXml(resultHttpSoap, IdentityTagName.POLICY.ATTRIBUTE_VALUE);
            List<String> listCategory = getFullNameFromXml(resultHttpSoap, IdentityTagName.POLICY.CATEGORY);
            for (int j = 0 ; j < listDataType.size() ; j++){
                PolicyMapper policyMapper = new PolicyMapper();
                policyMapper.setAttributeDataType(listDataType.get(j));
                policyMapper.setAttributeId(listId.get(j));
                policyMapper.setAttributeValue(listValue.get(j));
                policyMapper.setCategory(listCategory.get(j));
                policyMapperList.add(policyMapper);
            }
            return policyMapperList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }
    public static String callSoapHttp(String xml, String url_api) {
        String v = "";
        StringBuilder response = new StringBuilder();
        Date d = new Date();
        long time = System.currentTimeMillis();
        try {
            URL url = new URL(url_api);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            httpConn.setConnectTimeout(time_out);

            httpConn.setRequestMethod("POST");
            //httpConn.setRequestProperty("Content-Length", String.valueOf(xml.length()));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");

            httpConn.setRequestProperty("Authorization", "Basic YWRtaW46MTIzNDU2YUFA");
            httpConn.setRequestProperty("SOAPAction", "");

            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();
            out.write(xml.getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
            //Read the response:
            if (httpConn.getResponseCode() == 200) {
                InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
                BufferedReader in = new BufferedReader(isr);

                String value = null;
                while ((value = in.readLine()) != null) {
                    response.append(value);
                }
                in.close();
                v = response.toString();
            } else {
                v = "ERRWS_01:" + httpConn.getResponseCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            v = "ERRWS_02:" + e.toString();
        } finally {
            time = System.currentTimeMillis() - time;
            StringBuilder str = new StringBuilder();
            str.append(sdf.format(d)).append(", call cmd: ").append(xml).append(", response: ").append(v).append(", time_ms: ").append(time);
            //LogObj logObj = new LogObj(thread_id, str.toString());
            // if (mngService!=null) mngService.pushLog(logObj);
            //log.writeToLog(str.toString(), "logs/log");
        }
        return v;
    }

    public String callSoapIdentityWSO2(String request) {
        try {
            String resp = callSoapHttp(request, urlAllPolicys);
            if (resp != null) {
                String value = getValueResult(resp);
                resp = parserXmlFormat(value);
                return resp;
            } else {
                return "-1";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }

    public static String parserXmlFormat(String v) {
        StringBuilder str = new StringBuilder();
        try {
            if (v == null) return "";
            if (v.length() >= 100) {
                int size = v.length();
                for (int i = 0; i < size; i++) {
                    char c = v.charAt(i);
                    if (c == '<') {
                        str.append("&lt;");
                    } else if (c == '&') {
                        str.append("&#38;");
                    } else {
                        str.append(c);
                    }
                }//End for.
            } else {
                str.append(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR:parserXmlFormat: " + v);
        }
        return str.toString();
    }

    public String getValueResult(String xml) {
        String v = "";
        if (xml == null) return "";
        try {
            int id = xml.indexOf("<ns:return>");
            if (id > 0) {
                v = xml.substring(id + 11, xml.indexOf("</ns:return>"));
            } else {
                v = xml;
            }
        } catch (Exception e) {
            e.printStackTrace();
            v = xml;
        }
        return v;
    }

    public  static Document loadXMLString(String response)
    {
        DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = db.parse(new org.xml.sax.InputSource(new StringReader(response)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static List<String> getFullNameFromXml(String response, String tagName) throws Exception {
        Document xmlDoc = loadXMLString(response);
        NodeList nodeList = xmlDoc.getElementsByTagName(tagName);
        List<String> ids = new ArrayList<>(nodeList.getLength());
        for(int i = 0 ; i < nodeList.getLength() ; i++) {
            Node x = nodeList.item(i);
            ids.add(x.getFirstChild().getNodeValue());
        }
        return ids;
    }
}
