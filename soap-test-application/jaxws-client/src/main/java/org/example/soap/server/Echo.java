
package org.example.soap.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>echo complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="echo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://server.soap.example.org/}requestMessage" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "echo", propOrder = {
    "arg0"
})
public class Echo {

    protected RequestMessage arg0;

    /**
     * arg0プロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link RequestMessage }
     *     
     */
    public RequestMessage getArg0() {
        return arg0;
    }

    /**
     * arg0プロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link RequestMessage }
     *     
     */
    public void setArg0(RequestMessage value) {
        this.arg0 = value;
    }

}
