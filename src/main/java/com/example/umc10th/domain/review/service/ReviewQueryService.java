package com.example.umc10th.domain.review.service;


import com.example.umc10th.domain.review.domain.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.dao.StoreRepository;
import com.example.umc10th.domain.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public Page<Review> getReviewList(Long storeId, Integer page) {
        //Check if the store exists, if not error
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게 존재하지 않습니다."));

        //Paging setting(Page starts off at 0, so page - 1)
        //Assumes the web shows 10 per page
        Pageable pageable = PageRequest.of(page - 1, 10);

        //@Query method call
        return reviewRepository.findAllByStore(store, pageable);

    }

}
