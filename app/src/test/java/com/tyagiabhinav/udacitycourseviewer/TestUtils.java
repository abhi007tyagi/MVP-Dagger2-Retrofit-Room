package com.tyagiabhinav.udacitycourseviewer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class TestUtils {

    private static TestUtils INSTANCE = new TestUtils();

    private  static Gson TEST_GSON = new GsonBuilder().create();

    private TestUtils(){}

    public static <T> T loadJson(String path, Type type){
        try {
            String json = getFileString(path);
//            type = new TypeToken<ApiResponse>(){}.getType();
            return (T) TEST_GSON.fromJson(json, type);
        }catch(Exception e){
            throw new IllegalArgumentException(" could not deserialize "+path+" into " + type);
        }
    }

    private static String getFileString (String path){
        try{
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    INSTANCE.getClass().getClassLoader().getResourceAsStream(path)));
            String line;
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
            return sb.toString();
        }catch (IOException e){
            throw new IllegalArgumentException("could not read from resource at "+ path);
        }
    }

}
