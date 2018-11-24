package per.cyh.course.thrift;

import org.apache.thrift.TServiceClient;
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
    private String userServiceIp;

    @Value("${thrift.user.port}")
    private int userServicePort;

    private enum ServiceType {
        USER
    }

    public UserService.Client getUserService() {
        return (UserService.Client) getService(userServiceIp, userServicePort, ServiceType.USER);
    }


    private <T> T getService(String host, int port, ServiceType serviceType) {

        TSocket socket = new TSocket(host, port, 3000);

        TTransport transport = new TFramedTransport(socket);

        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }

        TProtocol protocol = new TBinaryProtocol(transport);
        TServiceClient client = null;
        client = new UserService.Client(protocol);

        return (T) client;
    }

}
