package cn.upd.mcxsanctify.util;

import cn.upd.mcxsanctify.McxSanctify;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.protobuf.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class NewVersionUtils {
    public static void checkNewVersion(){
        String latestVersion="1.0.0";
        try {
            McxSanctify.log("正在获取新版本信息");
            latestVersion = getLatestVersion();
        } catch (IOException e) {
            McxSanctify.warning("Failed to get GitHub-Api.");
        } finally {
            checkOutdated(latestVersion);
        }
    }
    private static String getLatestVersion() throws IOException {
        String GITHUB_API_URL = "https://api.github.com/repos/MoChenXiq/McxSanctify/releases/latest";
        URL url = new URL(GITHUB_API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.addRequestProperty("Accept", "application/vnd.github.v3+json");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return getStr(connection);
        } else {
            throw new IOException("Failed to retrieve latest version. Response code: " + responseCode);
        }
    }

    private static String getStr(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        JsonParser jsonParser = new JsonParser();
        JsonObject json = jsonParser.parse(response.toString()).getAsJsonObject();
        return json.get("tag_name").toString().replace("\"","");
    }

    private static void checkOutdated(String latestVersion) {
        McxSanctify.log("插件最新版本是:"+latestVersion);
        int pluginVersionToInt = getVersionToInt(McxSanctify.getInstance().getDescription().getVersion());
        if(pluginVersionToInt==getVersionToInt(latestVersion)){
            McxSanctify.log("您运行的是最新版本！");
        }
        if(pluginVersionToInt<getVersionToInt(latestVersion)){
            McxSanctify.warning("插件有最新版本！");
            McxSanctify.warning("请前往下载！"+"https://www.mcbbs.net/");
        }
    }
    private static int getVersionToInt(String version){
        return Integer.parseInt(version.split("\\.")[0])*100+Integer.parseInt(version.split("\\.")[1])*10+Integer.parseInt(version.split("\\.")[2]);

    }
}
