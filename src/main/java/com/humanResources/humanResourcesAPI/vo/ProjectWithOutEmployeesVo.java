package com.humanResources.humanResourcesAPI.vo;

import java.util.Date;

public record ProjectWithOutEmployeesVo(
        Long id,
        String title,
        String descr,
        Date startDate,
        Date endDate
) {
}
