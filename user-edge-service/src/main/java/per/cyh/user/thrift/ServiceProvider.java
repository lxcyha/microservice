package per.cyh.user.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import per.cyh.thrift.user.UserService;

/**
 * Created by cyh on 2018/10/26.
 */
@Component
public class ServiceProvider {

    @Value("${thrift.user.ip}")
    private String serviceIp;

    @Value("${thrift.user.port}")
    private int servicePort;


    public UserService.Client getUserService() {
        TSocket socket = new TSocket(serviceIp, servicePort, 3000);

        TTransport transport = new TFramedTransport(socket);

        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }

        TProtocol protocol = new TBinaryProtocol(transport);

        UserService.Client client = new UserService.Client(protocol);

        return client;

    }
}
