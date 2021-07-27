# MTOM sample code

Environment:
* JDK11
* JBoss EAP 7

You can see the following SOAP request message with MTOM style attachment:
```
POST /mtom-server/FileReceiverService HTTP/1.1
Content-Type: multipart/related; type="application/xop+xml"; boundary="uuid:9e68f87a-7a3c-4abe-9765-767cef3827ca"; start="<root.message@cxf.apache.org>"; start-info="text/xml"
Accept: */*
SOAPAction: ""
User-Agent: Apache-CXF/3.3.7.redhat-00001
Cache-Control: no-cache
Pragma: no-cache
Host: localhost:8080
Connection: keep-alive
Transfer-Encoding: chunked

ff9

--uuid:9e68f87a-7a3c-4abe-9765-767cef3827ca
Content-Type: application/xop+xml; charset=UTF-8; type="text/xml"
Content-Transfer-Encoding: binary
Content-ID: <root.message@cxf.apache.org>

<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:uploadFile xmlns:ns2="http://server.soap.example.org/"><arg0><xop:Include xmlns:xop="http://www.w3.org/2004/08/xop/include" href="cid:13fbe1a7-9865-42a5-9d57-8ee5033b4f95-2@cxf.apache.org"/></arg0><arg1>duke.png</arg1></ns2:uploadFile></soap:Body></soap:Envelope>
--uuid:9e68f87a-7a3c-4abe-9765-767cef3827ca
Content-Type: image/jpeg
Content-Transfer-Encoding: binary
Content-ID: <13fbe1a7-9865-42a5-9d57-8ee5033b4f95-2@cxf.apache.org>
Content-Disposition: attachment;name="duke.jpg"

... (fille contents)
--uuid:9e68f87a-7a3c-4abe-9765-767cef3827ca--
```
