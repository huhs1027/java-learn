package filter;

import utils.ArrayUtils;
import utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;

/**
 * xss过滤request增强类
 *
 * @author huhongsen
 * @date 2019/1/22
 */
public class XSSHttpRequestWrapper extends HttpServletRequestWrapper {
    public XSSHttpRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = super.getParameterMap();
        if (parameterMap == null) {
            return null;
        } else {
            for (Map.Entry<String, String[]> mapEntry : parameterMap.entrySet()) {
                String[] value = mapEntry.getValue();
                if (ArrayUtils.isNotEmpty(value)) {
                    for (int i = 0; i < value.length; i++) {
                        value[i] = this.cleanXSS(value[i]);
                    }
                }
            }
        }
        return parameterMap;
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        } else {
            int count = values.length;
            String[] encodedValues = new String[count];

            for (int i = 0; i < count; ++i) {
                encodedValues[i] = this.cleanXSS(values[i]);
            }

            return encodedValues;
        }
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(this.cleanXSS(parameter));
        return value == null ? null : this.cleanXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return value == null ? null : this.cleanXSS(value);
    }

    private String cleanXSS(String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");
        value = value.replaceAll("<script", "&lt;script").replaceAll("script>", "script&gt;");
        return value;
    }
}
