package com.agent.service;

import java.util.List;

import com.agent.service.intraface.WebsiteBlockStrategy;
import com.agent.service.intraface.impl.LinuxBlockStrategy;
import com.agent.service.intraface.impl.MacBlockStrategy;
import com.agent.service.intraface.impl.WindowsBlockStrategy;
import com.agent.util.OSUtil;

public class WebsiteBlockService {

    public static void applyPolicy(List<String> blockedSites) {

        WebsiteBlockStrategy strategy;

        if (OSUtil.isWindows()) {
            strategy = new WindowsBlockStrategy();
        } else if (OSUtil.isLinux()) {
            strategy = new LinuxBlockStrategy();
        } else if (OSUtil.isMac()) {
            strategy = new MacBlockStrategy();
        } else {
            throw new RuntimeException("Unsupported OS");
        }

        strategy.apply(blockedSites);
    }
}