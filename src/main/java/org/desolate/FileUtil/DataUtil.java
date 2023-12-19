package org.desolate.FileUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.desolate.DestinyMain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DataUtil {
    //定义文件位置
    public static String dataFolderPath = DestinyMain.getInstance().getDataFolder().getPath();
    public static String filePath = dataFolderPath + "Data.json";

    /**
     * 在JSON数据中查找值
     *
     * @param PlayerName 提供的玩家昵称
     * @param Value JSON键值
     * @return Value键的值
     */
    public String checkValue(String PlayerName, String Value) {
        String returnValue=null;
        try {
            //读取json
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            if (inputStream == null) {
                //如果不存在创建新文件
                File.createNewFile(filePath);
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line=reader.readLine())!=null) {
                    builder.append(line);
                }
                String jsonContent = builder.toString();
                JSONObject jsonObject = JSON.parseObject(jsonContent);
                JSONArray DataArray = jsonObject.getJSONArray("Data");
                //遍历行查找
                for (int i = 0; i < DataArray.size(); i++) {
                    JSONObject Data = DataArray.getJSONObject(i);
                    if (Data.getString("Data.昵称").equals(PlayerName)) {
                        returnValue = Data.getString("Data." + Value);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return returnValue;
    }
}