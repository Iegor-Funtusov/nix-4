package ua.com.alevel.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.request.PageAndSizeDto;
import ua.com.alevel.dto.request.SortDto;

import java.util.Objects;

public class WebRequestUtil {

    private static final String PAGE_PARAM = "page";
    private static final String SIZE_PARAM = "size";
    private static final String SORT_PARAM = "sort";
    private static final String ORDER_PARAM = "order";
    private static final int DEFAULT_PAGE_PARAM_VALUE = 0;
    private static final int DEFAULT_SIZE_PARAM_VALUE = 10;
    private static final String DEFAULT_SORT_PARAM_VALUE = "createTime";
    private static final String DEFAULT_ORDER_PARAM_VALUE = "desc";

    public static PageAndSizeDto generatePageAndSizeModel(WebRequest webRequest) {
        int page = webRequest.getParameter(PAGE_PARAM) != null ? Integer.parseInt(Objects.requireNonNull(webRequest.getParameter(PAGE_PARAM))) : DEFAULT_PAGE_PARAM_VALUE;
        int size = webRequest.getParameter(SIZE_PARAM) != null ? Integer.parseInt(Objects.requireNonNull(webRequest.getParameter(SIZE_PARAM))) : DEFAULT_SIZE_PARAM_VALUE;
        return new PageAndSizeDto(page, size);
    }

    public static SortDto generateSortModel(WebRequest webRequest) {
        String sort = StringUtils.isNotBlank(webRequest.getParameter(SORT_PARAM)) ? Objects.requireNonNull(webRequest.getParameter(SORT_PARAM)) : DEFAULT_SORT_PARAM_VALUE;
        String order = StringUtils.isNotBlank(webRequest.getParameter(ORDER_PARAM)) ? Objects.requireNonNull(webRequest.getParameter(ORDER_PARAM)) : DEFAULT_ORDER_PARAM_VALUE;
        return new SortDto(sort, order);
    }
}
