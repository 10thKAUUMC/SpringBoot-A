package com.example.umc10th.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

public class StoreResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeViewDTO {
        String storeName;
        Double rating;
        String category;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeViewListDTO {
        List<HomeViewDTO> storeList; // list where it stores multiple store's info
    }
}