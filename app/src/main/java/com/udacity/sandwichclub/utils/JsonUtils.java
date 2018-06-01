package com.udacity.sandwichclub.utils;


import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String KEY_NAME = "name";
    private static final String KEY_MAIN_NAME = "mainName";
    private static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_INGREDIENTS = "ingredients";
    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject sandwichNames = jsonObject.getJSONObject(KEY_NAME);
            String mainName = sandwichNames.getString(KEY_MAIN_NAME);

            JSONArray akaArray = sandwichNames.getJSONArray(KEY_ALSO_KNOWN_AS);
            List<String> alsoKnownAs = new ArrayList<>();
            for(int i=0;i<akaArray.length();i++){
                alsoKnownAs.add(akaArray.getString(i));
            }

            String placeOfOrigin = jsonObject.getString(KEY_PLACE_OF_ORIGIN);
            String description = jsonObject.getString(KEY_DESCRIPTION);
            String imageUrl = jsonObject.getString(KEY_IMAGE);

            JSONArray ingredientsArray = jsonObject.getJSONArray(KEY_INGREDIENTS);
            List<String> ingredients = new ArrayList<>();
            for (int i=0;i<ingredientsArray.length();i++){
                ingredients.add(ingredientsArray.getString(i));
            }

            sandwich = new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,imageUrl,ingredients);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}
