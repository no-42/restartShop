package org.restart.shop.resp;

import lombok.Data;
import lombok.experimental.Accessors;
import org.restart.shop.utils.JsonUtils;

@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code;
    private String message;
    private T data;
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData resultData = new ResultData<>();
        resultData.setCode(BaseCodeEnum.SUCCESS.getCode());
        resultData.setMessage(BaseCodeEnum.SUCCESS.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> String successToJson(T data) {
        ResultData resultData = new ResultData<>();
        resultData.setCode(BaseCodeEnum.SUCCESS.getCode());
        resultData.setMessage(BaseCodeEnum.SUCCESS.getMessage());
        resultData.setData(data);
        return JsonUtils.toJson(resultData);
    }

    public static <T> ResultData<T> fail(String code, String message) {
        ResultData resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);
        return resultData;
    }
}
