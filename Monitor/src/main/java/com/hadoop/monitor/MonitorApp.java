package com.hadoop.monitor;

import com.hadoop.monitor.utils.HadoopUtil;

import java.io.IOException;

/**
 * @Author: huhu
 * @Date: 2019-06-08 18:53
 */
public class MonitorApp {

    public static void main(String[] args) throws IOException {
        StatefulHttpClient client = new StatefulHttpClient(null);
        HadoopUtil.getHdfsSummary(client).printInfo();

    }
}
