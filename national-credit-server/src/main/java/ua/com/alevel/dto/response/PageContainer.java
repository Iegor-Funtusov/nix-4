package ua.com.alevel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageContainer<T> {

    private int currentPage;
    private int pageSize;
    private long collectionSize;
    private List<T> items;

    public PageContainer() {
        this.currentPage = 1;
        this.pageSize = 10;
        this.collectionSize = 0;
        this.items = Collections.emptyList();
    }

    public PageContainer(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.collectionSize = 0;
        this.items = Collections.emptyList();
    }
}
