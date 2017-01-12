# loxone-jclient

**loxone-jclient** is a simple java client to connect with a [Loxone Mini Server](https://www.loxone.com/enen/).

# Samples

```java
public void main() throws Exception {
  try (LoxoneMiniServer server = new LoxoneMiniServer(new LoxoneClient(HOST, PORT, USERNAME, PASSWORD))) {

    System.out.println(server.getStatus());
    System.out.println(server.getSystemLogs());
    System.out.println(server.readSensorValue("vc1"));

  }
}
```
