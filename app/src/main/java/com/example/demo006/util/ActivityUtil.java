package com.example.demo006.util;

import com.example.demo006.MainActivity;
import com.example.route.Aroute;
import com.example.route.Iroute;

public class ActivityUtil implements Iroute {
    /**
     * 存入activity
     */
    @Override
    public void putActivity() {
        Aroute.getInstance().putActivity("main/main", MainActivity.class);
    }

}
