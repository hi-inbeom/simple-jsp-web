package com.example.demo.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

    private int page;           // 현재 페이지 번호
    private int size;           // 한 페이지에 보여줄 게시글 수
    private int totalCount;     // 전체 게시글 수
    private int totalPages;     // 전체 페이지 수
    private int startPage;      // 화면에 보여줄 시작 페이지 번호
    private int endPage;        // 화면에 보여줄 끝 페이지 번호
    private int startIndex;     // DB 쿼리에서 사용할 시작 인덱스 (0부터 시작)

    private final int pageBlock = 5; // 한 번에 보여줄 페이지 블록 개수 (예: 1~5, 6~10)

    public Pagination(int page, int size, int totalCount) {
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;

        // 전체 페이지 수 계산
        this.totalPages = (int) Math.ceil((double) totalCount / size);

        // 현재 블록 기준 startPage, endPage 계산
        int currentBlock = (int) Math.ceil((double) page / pageBlock);
        this.startPage = (currentBlock - 1) * pageBlock + 1;
        this.endPage = Math.min(startPage + pageBlock - 1, totalPages);

        // DB에서 조회할 시작 인덱스
        this.startIndex = (page - 1) * size;
    }
}
