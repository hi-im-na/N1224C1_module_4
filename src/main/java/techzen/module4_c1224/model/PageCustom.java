package techzen.module4_c1224.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class PageCustom<T> {
    int pageNumber;
    int pageSize;
    long totalElements;
    int totalPages;

    public PageCustom(Page<T> page) {
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
}
