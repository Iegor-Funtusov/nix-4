package ua.com.alevel.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.controller.DateRangeModel;
import ua.com.alevel.controller.PageAndSizeModel;
import ua.com.alevel.controller.SortModel;

import java.util.Date;
import java.util.Objects;

@UtilityClass
public class WebRequestUtil {

    private static final String PAGE_PARAM = "page";
    private static final String SIZE_PARAM = "size";
    private static final String SORT_PARAM = "sort";
    private static final String ORDER_PARAM = "order";
    private static final int DEFAULT_PAGE_PARAM_VALUE = 0;
    private static final int DEFAULT_SIZE_PARAM_VALUE = 10;
    private static final String DEFAULT_SORT_PARAM_VALUE = "createTime";
    private static final String DEFAULT_ORDER_PARAM_VALUE = "desc";
    private static final String ERROR_PARAM_VALUE = "statusError";

    public PageAndSizeModel generatePageAndSizeModel(WebRequest webRequest) {
        int page = webRequest.getParameter(PAGE_PARAM) != null ? Integer.parseInt(Objects.requireNonNull(webRequest.getParameter(PAGE_PARAM))) : DEFAULT_PAGE_PARAM_VALUE;
        int size = webRequest.getParameter(SIZE_PARAM) != null ? Integer.parseInt(Objects.requireNonNull(webRequest.getParameter(SIZE_PARAM))) : DEFAULT_SIZE_PARAM_VALUE;
        return new PageAndSizeModel(page, size);
    }

    public SortModel generateSortModel(WebRequest webRequest) {
        String sort = StringUtils.isNotBlank(webRequest.getParameter(SORT_PARAM)) ? Objects.requireNonNull(webRequest.getParameter(SORT_PARAM)) : DEFAULT_SORT_PARAM_VALUE;
        String order = StringUtils.isNotBlank(webRequest.getParameter(ORDER_PARAM)) ? Objects.requireNonNull(webRequest.getParameter(ORDER_PARAM)) : DEFAULT_ORDER_PARAM_VALUE;
        return new SortModel(sort, order);
    }

    DateRangeModel generateDateRange(String timePattern) {
        String[] times = timePattern.split(":");
        long startDate = Long.parseLong(times[0]);
        long endDate = Long.parseLong(times[1]);
        if (startDate == endDate) {
            return new DateRangeModel(DateUtil.generateBeginningOfDay(new Date(startDate)), new Date(endDate));
        } else {
            return new DateRangeModel(new Date(startDate), new Date(endDate));
        }
    }

    public String existFlagErrorParameter(WebRequest webRequest) {
        return webRequest
                .getParameterMap()
                .keySet()
                .stream()
                .filter(key -> key.startsWith(ERROR_PARAM_VALUE))
                .findFirst().orElse(null);
    }
}
