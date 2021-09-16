package com.syrisa.point.service;


import java.util.Arrays;
import java.util.List;

public interface PointWheel {
    List<String> pointWheel = Arrays.asList("0", "2500", "BANKRUPTCY", "100", "200", "0", "550", "650", "750", "300",
            "PASS", "700", "800", "900", "100", "200", "0", "1000", "150", "250", "350", "1500", "PASS", "2000", "PASS",
            "2500", "BANKRUPTCY", "400", "PASS", "500", "600", "3000", "PASS", "4000", "BANKRUPTCY", "5000", "PASS",
            "150", "250", "350", "450", "550", "1000", "650", "750", "850", "950");
    int POINT_WHELL_SIZE = pointWheel.size();
}
