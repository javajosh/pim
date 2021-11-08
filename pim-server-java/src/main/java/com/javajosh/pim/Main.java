package com.javajosh.pim;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Main {
  public static void main(String[] args) {
    Enumeration<NetworkInterface> interfaces;
    try {
      interfaces = NetworkInterface.getNetworkInterfaces();
    } catch (SocketException ex) {
      System.out.println("Operation failed.");
      ex.printStackTrace();
      return;
    }

    while (interfaces.hasMoreElements()) {
      NetworkInterface networkInterface = interfaces.nextElement();
      Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
      while (inetAddresses.hasMoreElements()) {
        InetAddress inetAddress = inetAddresses.nextElement();
        System.out.format("interface %s with address %s%n",
          networkInterface.getDisplayName(), inetAddress.getHostAddress());

      }
    }
  }
}
