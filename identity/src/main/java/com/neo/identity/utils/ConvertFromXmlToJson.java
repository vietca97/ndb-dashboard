package com.neo.identity.utils;

import com.sun.net.ssl.SSLContext;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertFromXmlToJson {

    public static final int IDENTITY_FACTORY = 4;
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static int time_out = 3 * 60 * 1000;
    @Value("${url.policy}")
    private String urlSoap = "https://identity.kttv.gov.vn:9443/services/EntitlementPolicyAdminService";

    public  String convert(String xml){
        JSONObject jsonObject = XML.toJSONObject(xml);
        String obj = jsonObject.toString(IDENTITY_FACTORY);
        return obj;
    }

    public static void main(String[] args) {
        ConvertFromXmlToJson c = new ConvertFromXmlToJson();
        String result = c.callSoapPolicyDetails("TB5");
        System.out.println(result);
    }

    public String callSoapHttp(String xml, String url_api) {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
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

    public String callSoapPolicyDetails(String appName) {
        StringBuilder str_soap = new StringBuilder();
        str_soap.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.axis2/xsd\">");
        str_soap.append("<soapenv:Header/><soapenv:Body>");
        str_soap.append("<xsd:getOAuthApplicationDataByAppName");
        str_soap.append("<xsd:appName>").append(appName).append("</xsd:appName>");
        str_soap.append("</xsd:getOAuthApplicationDataByAppName>");
        str_soap.append("</xsd::getOAuthApplicationDataByAppName></soapenv:Body></soapenv:Envelope>");

        try {
            String resp = callSoapHttp(str_soap.toString(), urlSoap);
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
}
