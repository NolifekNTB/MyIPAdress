import java.net.Inet4Address
import java.net.NetworkInterface
import java.util.*


fun main(args: Array<String>) {
    println("IP Address V4 - ${IPHelper.IPv4Adresses}")
    println("Subnet mask - ${IPHelper.SubnetMask}")
    println("Network - ${IPHelper.Network}")
    println("Broadcast- ${IPHelper.Broadcast}")
    println("First host - ${IPHelper.FirstHost}")
    println("Last host - ${IPHelper.LastHost}")
}

object IPHelper {
    val IPv4Adresses: String
        get() = GetAdresses("IPv4")
    val SubnetMask: String
        get() = GetAdresses("Mask")
    val Network: String
        get() = GetAdresses("Network")
    val Broadcast: String
        get() = GetAdresses("Broadcast")
    val FirstHost: String
        get() = GetAdresses("FirstHost")
    val LastHost: String
        get() = GetAdresses("LastHost")


    private fun GetAdresses(choice: String): String {
        when (choice){
            "IPv4" -> {
                Collections.list(NetworkInterface.getNetworkInterfaces()).forEach(){
                    Collections.list(it.inetAddresses).forEach(){
                        val sAddr = it.hostAddress
                        val isIPv4 = sAddr.indexOf(':') < 0 && !(it.isLoopbackAddress)
                        if (isIPv4) return sAddr
                    }
                }
            }
            "Mask" -> return ""
            "Network" -> {

            }
            "Broadcast" -> {
                Collections.list(NetworkInterface.getNetworkInterfaces()).forEach(){
                    if (!it.isLoopback) {
                        for (i in it.interfaceAddresses)
                        {
                            return (i.broadcast).toString().removePrefix("/")
                        }
                    }
                }
                return ""
            }
            "FirstHost" ->
            {
                val Char = IPv4Adresses.get(IPv4Adresses.length-1)
                val Index = IPv4Adresses.lastIndexOf(Char)
                val Host = IPv4Adresses.substring(0, Index) + (Char+1)  + IPv4Adresses.substring(Index + 1)
                return Host
            }

            "LastHost" -> {
                val Char = Broadcast.get(Broadcast.length-1)
                val Index = Broadcast.lastIndexOf(Char)
                val Host = Broadcast.substring(0, Index) + (Char-1)  + Broadcast.substring(Index + 1)
                return Host
            }
            else -> return "Brak"
        }
        return ""
    }
}
