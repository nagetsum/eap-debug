
package org.example.soap.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>echoResponse complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="echoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://server.soap.example.org/}responseMessage" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "echoResponse", propOrder = {
    "_return"
})
public class EchoResponse {

    @XmlElement(name = "return")
    protected ResponseMessage _return;

    /**
     * returnプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ResponseMessage }
     *     
     */
    public ResponseMessage getReturn() {
        return _return;
    }

    /**
     * returnプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseMessage }
     *     
     */
    public void setReturn(ResponseMessage value) {
        this._return = value;
    }

}
