package edu.jd.xyt.log;

import edu.jd.xyt.common.PageParam;

public class queryDto extends PageParam {

    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public queryDto(String param) {
        this.param = param;
    }

    public queryDto() {
    }

}
