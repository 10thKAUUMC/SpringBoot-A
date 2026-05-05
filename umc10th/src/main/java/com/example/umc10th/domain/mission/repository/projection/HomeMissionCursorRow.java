package com.example.umc10th.domain.mission.repository.projection;

import java.time.LocalDate;

public interface HomeMissionCursorRow {

    Long getStoreId();

    String getStoreName();

    String getStoreCategory();

    LocalDate getDDay();

    Integer getMinPrice();

    Integer getPoint();

    Long getUserMissionId();

    String getCursorValue();
}
