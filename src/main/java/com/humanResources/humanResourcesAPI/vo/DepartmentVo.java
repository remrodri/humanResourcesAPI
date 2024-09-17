package com.humanResources.humanResourcesAPI.vo;

import com.humanResources.humanResourcesAPI.model.entity.Position;

import java.util.Set;

public record DepartmentVo(
Long id,
String name,
String descr,
Set<Position>positions
) {
}
