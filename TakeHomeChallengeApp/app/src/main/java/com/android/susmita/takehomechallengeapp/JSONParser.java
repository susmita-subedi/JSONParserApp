package com.android.susmita.takehomechallengeapp;


        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

/** A class to parse json data */
public class JSONParser {

    // Receives a JSONObject and returns a list

    public List<HashMap<String, Object>> parse(JSONArray jIcons){
        int iconCount = jIcons.length();
        List<HashMap<String, Object>> iconList = new ArrayList<HashMap<String,Object>>();
        HashMap<String, Object> icon = null;

        // Taking each dataset, parses and adds to list object
        for(int i=0; i<iconCount;i++){
            try {
                // Call getIcon with icon JSON object to parse the icon
                icon = getIcon((JSONObject)(jIcons.get(i)));
                iconList.add(icon);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return iconList;
    }

    // Parsing the Icon JSON object
    private HashMap<String, Object> getIcon(JSONObject jIcon){

        HashMap<String, Object> icon = new HashMap<String, Object>();
        String iconName = "";
        String image="";
        int id = 0;
        try {
            image = jIcon.getString("thumbnailUrl");
            String symbol = "-";
            iconName = jIcon.getString("title");
            id = jIcon.getInt("id");

            icon.put("title", " -  " + iconName);
            icon.put("thumbnailUrl", image);
            icon.put("id", id - 1);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return icon;
    }
}