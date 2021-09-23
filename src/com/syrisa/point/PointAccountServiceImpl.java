package com.syrisa.point;

import com.syrisa.point.service.PointAccountService;

public class PointAccountServiceImpl {
    PointAccountService pointAccount = (question, time) -> {
        switch (question.getAnswer().length()){
            case 3:
                return (int)(10*time);
            case 4:
                return (int)(15*time);
            default:
                throw new Exception();
        }
    };
}
