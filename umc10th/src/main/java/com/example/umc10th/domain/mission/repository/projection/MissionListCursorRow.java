package com.example.umc10th.domain.mission.repository.projection;

public interface MissionListCursorRow {

    Long getStoreId();

    String getStoreName();

    Integer getMinPrice();

    Integer getPointPercent();

    Boolean getIsComplete();
}
