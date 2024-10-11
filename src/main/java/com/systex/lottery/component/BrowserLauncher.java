package com.systex.lottery.component;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 啟動spring後，嘗試用瀏覽器開啟頁面
 */
@Component
class BrowserLauncher implements CommandLineRunner {

	/**
	 * 網址 port
	 */
    @Value("${server.port:8080}")
    private String port;

    /**
     * 專案名稱
     */
    @Value("${server.servlet.context-path:/}")
    private String contextPath; 
    
    /**
     * 是否在啟動 spring 後，嘗試用瀏覽器開啟頁面(.properties)
     */
    @Value("${custom.open-browser-after-started:true}")
    private boolean openBrowserAfterStarted;
    
    @Override
    public void run(String... args) throws Exception {
		if(openBrowserAfterStarted) {
			browse(String.format("http://localhost:%s%s", port, contextPath));
		}
    }

    /**
     * 嘗試自動開啟瀏覽器
     * @param url 網址
     */
    public void browse(String url) {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                processBuilder = new ProcessBuilder("cmd", "/c", "start", url);
            } else if (os.contains("mac")) {
                processBuilder = new ProcessBuilder("open", url);
            } else {
                processBuilder = new ProcessBuilder("xdg-open", url);
            }

            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}