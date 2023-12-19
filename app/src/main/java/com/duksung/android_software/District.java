package com.duksung.android_software;

import java.util.HashMap;
import java.util.Map;

public class District {
    private Map<String, String> districtMap;

    public District() {
        districtMap = new HashMap<>();
        initializeDistrictMap();
    }

    private void initializeDistrictMap() {
        districtMap.put("111123", "종로구");
        districtMap.put("111121", "중구");
        districtMap.put("111131", "용산구");
        districtMap.put("111142", "성동구");
        districtMap.put("111141", "광진구");
        districtMap.put("111152", "동대문구");
        districtMap.put("111151", "중랑구");
        districtMap.put("111161", "성북구");
        districtMap.put("111291", "강북구");
        districtMap.put("111171", "도봉구");
        districtMap.put("111311", "노원구");
        districtMap.put("111181", "은평구");
        districtMap.put("111191", "서대문구");
        districtMap.put("111201", "마포구");
        districtMap.put("111301", "양천구");
        districtMap.put("111212", "강서구");
        districtMap.put("111221", "구로구");
        districtMap.put("111281", "금천구");
        districtMap.put("111231", "영등포구");
        districtMap.put("111241", "동작구");
        districtMap.put("111251", "관악구");
        districtMap.put("111262", "서초구");
        districtMap.put("111261", "강남구");
        districtMap.put("111273", "송파구");
        districtMap.put("111274", "강동구");
    }

    public String getDistrictName(String code) {
        return districtMap.get(code);
    }

    public Map<String, String> getDistrictMap() {
        return districtMap;
    }

}
