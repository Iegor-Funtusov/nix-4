package ua.com.alevel.view.container;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.util.WebRequestUtil;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class PageDataRequestContainer {

    private int page;
    private int size;
    private String sort;
    private String order;
    private Map<String, Object> parameters;

    public PageDataRequestContainer(WebRequest request) {
        PageAndSizeContainer pageAndSizeContainer = WebRequestUtil.generatePageAndSizeData(request);
        SortAndOrderContainer sortAndOrderContainer = WebRequestUtil.generateSortData(request);
        this.page = pageAndSizeContainer.getPage();
        this.size = pageAndSizeContainer.getSize();
        this.sort = sortAndOrderContainer.getSort();
        this.order = sortAndOrderContainer.getOrder();
    }
}
