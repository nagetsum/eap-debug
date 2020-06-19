package org.example.soap.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class EchoWebService  {
    @WebMethod
    public ResponseMessage echo(RequestMessage requestMessage) {
        ResponseMessage res = new ResponseMessage();
        res.setId(requestMessage.getId());
        res.setMessage(requestMessage.getMessage());
        return res;
    }
}
