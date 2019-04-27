package site.layne666.gym.pojo;

import java.io.Serializable;

public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -9122598292287970890L;
    private int code;
    private String message;
    private T data;
    private boolean success;

    public ApiResult() {
        this.setCode(ApiResult.ResultCode.OK.getCode());
    }

    public ApiResult(T data) {
        this.setCode(ApiResult.ResultCode.OK.getCode());
        this.data = data;
    }

    public ApiResult(int code, String message) {
        this.setCode(code);
        this.message = message;
    }

    public ApiResult(ApiResult.ResultCode code, String message) {
        this.setCode(code.getCode());
        this.message = message;
    }

    public ApiResult(boolean success, String message) {
        this.setSuccess(success);
        this.message = message;
    }

    public ApiResult(int code, String message, T data) {
        this.setCode(code);
        this.message = message;
        this.data = data;
    }

    public ApiResult(ApiResult.ResultCode code, String message, T data) {
        this.setCode(code.getCode());
        this.message = message;
        this.data = data;
    }

    public ApiResult(boolean success, String message, T data) {
        this.setSuccess(success);
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
        this.setSuccess(code);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setSuccess(int code) {
        this.success = code == ApiResult.ResultCode.OK.getCode();
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        if (this.success) {
            this.code = ApiResult.ResultCode.OK.getCode();
        } else if (this.code == ApiResult.ResultCode.OK.getCode() || this.code == 0) {
            this.code = ApiResult.ResultCode.INTERNAL_SERVER_ERROR.getCode();
        }

    }

    public String toJSONString() {
        return this.toString();
    }

    public static final <T> ApiResult<T> buildSuccessResult() {
        return new ApiResult(ApiResult.ResultCode.OK, "");
    }

    public static final <T> ApiResult<T> of(int code, String msg, T data) {
        return new ApiResult(code, msg, data);
    }

    public static final <T> ApiResult<T> of(ApiResult.ResultCode code, String msg, T data) {
        return new ApiResult(code, msg, data);
    }

    public static enum ResultCode {
        OK(200, ""),
        BAD_REQUEST(400, ""),
        UNAUTHORIZED(401, ""),
        FORBIDDEN(403, ""),
        NOT_FOUND(404, ""),
        CONFLICT(409, ""),
        TOO_MANY_REQUESTS(429, ""),
        INTERNAL_SERVER_ERROR(500, ""),
        SERVICE_UNAVAILABLE(503, "");

        private Integer code;
        private String desc;

        private ResultCode(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return this.code;
        }

        public String getDesc() {
            return this.desc;
        }
    }
}
