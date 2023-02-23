import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException
import java.net.UnknownHostException


fun main(args: Array<String>) {
    val localHost = Inet4Address.getLocalHost()
    val networkInterface = NetworkInterface.getByInetAddress(localHost)
    val x = networkInterface.getInterfaceAddresses().get(1).getNetworkPrefixLength();
    println(x)
}


