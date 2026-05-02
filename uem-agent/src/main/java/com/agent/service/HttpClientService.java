package com.agent.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HWDiskStore;
import oshi.hardware.NetworkIF;
import oshi.hardware.PowerSource;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpClientService {

    public static void post(String url, Object body) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(body);

            @SuppressWarnings("deprecation")
            URL u = new URL(url);

            HttpURLConnection con = (HttpURLConnection) u.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty(
                    "Content-Type",
                    "application/json");

            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();

            System.out.println(
                    "POST " + url + " -> " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Map> get(String url) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            URL u = new URL(url);

            HttpURLConnection con = (HttpURLConnection) u.openConnection();

            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            System.out.println("GET " + url + " -> " + responseCode);

            if (responseCode == 200) {

                return mapper.readValue(
                        con.getInputStream(),
                        List.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return List.of();
    }

    public static List<String> getListString(String url) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            URL u = new URL(url);

            HttpURLConnection con = (HttpURLConnection) u.openConnection();

            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            System.out.println("GET " + url + " -> " + responseCode);

            if (responseCode == 200) {

                return mapper.readValue(
                        con.getInputStream(),
                        List.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return List.of();
    }

    public static class SystemInfoService {

        private static final SystemInfo si = new SystemInfo();

        public static double getCpuUsage() {
            CentralProcessor cpu = si.getHardware().getProcessor();
            return cpu.getSystemCpuLoad(1000) * 100;
        }

        public static long getTotalMemory() {
            return si.getHardware().getMemory().getTotal() / (1024 * 1024);
        }

        public static long getAvailableMemory() {
            return si.getHardware().getMemory().getAvailable() / (1024 * 1024);
        }

        public static long getTotalDisk() {

            long total = 0;

            for (HWDiskStore disk : si.getHardware().getDiskStores()) {
                total += disk.getSize();
            }

            return total / (1024 * 1024);
        }

        public static long getFreeDisk() {

            long free = 0;

            for (HWDiskStore disk : si.getHardware().getDiskStores()) {
                free += disk.getSize() - disk.getWriteBytes();
            }

            return free / (1024 * 1024);
        }

        public static double getBatteryLevel() {

            List<PowerSource> ps = si.getHardware().getPowerSources();

            if (ps.isEmpty())
                return -1;

            return ps.get(0).getRemainingCapacityPercent() * 100;
        }

        public static long getBytesSent() {

            long sent = 0;

            for (NetworkIF net : si.getHardware().getNetworkIFs()) {

                net.updateAttributes();
                sent += net.getBytesSent();
            }

            return sent;
        }

        public static long getBytesReceived() {

            long received = 0;

            for (NetworkIF net : si.getHardware().getNetworkIFs()) {

                net.updateAttributes();
                received += net.getBytesRecv();
            }

            return received;
        }
    }
}