package org.desolate.FileUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.desolate.DestinyMain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static cn.nukkit.utils.Utils.writeFile;

public class DataUtil {
    //定义文件位置
    public static String dataFolderPath = DestinyMain.getInstance().getDataFolder().getPath();
    public static String filePath = dataFolderPath + "Data.json";

    JSONArray DataArray;

    {
        try {
            DataArray = readDataJSON();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 在JSON数据中查找值
     *
     * @param PlayerName 提供的玩家昵称
     * @param Value      JSON键值
     * @return Value键的值
     */
    public String checkValue(String PlayerName, String Value) {
        String returnValue = null;
        //遍历行查找
        for (int i = 0; i < DataArray.size(); i++) {
            JSONObject Data = DataArray.getJSONObject(i);
            if (Data.getString("Data.昵称").equals(PlayerName)) {
                returnValue = Data.getString("Data." + Value);
            }
        }
        return returnValue;
    }

    /**
     * @param PlayerName 玩家昵称
     * @param Value      需要设置的键名
     * @param setValue   设置的String键值
     * @throws IOException 抛出异常
     */
    public void setValue(String PlayerName, String Value, Object setValue) throws IOException {
        Map<String, Object> dataMap = new HashMap<>();
        for (int i = 0; i < DataArray.size(); i++) {
            JSONObject Data = DataArray.getJSONObject(i);
            if (Data.getString("Data.昵称").equals(PlayerName)) {
                dataMap.put("Data.昵称." + Value, setValue);
                String jsonString = JSONUtils.convertObjectToJSON(dataMap);
                writeFile(filePath, jsonString);
            }
        }
    }

    /**
     * 讀取JSON方法
     *
     * @return DataArray
     */
    public JSONArray readDataJSON() throws IOException {
        JSONArray DataArray = new JSONArray();
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        if (inputStream == null) {
            File.createNewFile(filePath);
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String jsonContent = builder.toString();
            JSONObject jsonObject = JSON.parseObject(jsonContent);
            DataArray = jsonObject.getJSONArray("Data");

        }
        return DataArray;
    }
}