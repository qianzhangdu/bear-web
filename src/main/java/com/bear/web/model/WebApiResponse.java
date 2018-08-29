package com.bear.web.model;

import java.util.function.Function;

/**
 * @author qianzhang
 * @since 2018/8/29
 */
public class WebApiResponse<T> {
    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = 1;

    private int code;
    private String error;
    private T data;

    /**
     * 是否需要重试。只有在 code != 0，也就是说有错的时候才有意义。
     * 为真时表示一定要重试，为假时表示一定不要重试，为空时由调用方自行决定。
     */
    private Boolean isNeedRetry;


    public static <T> WebApiResponse<T> success(T data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(SUCCESS_CODE);
        response.setData(data);
        return response;
    }

    public static <T> WebApiResponse<T> error(String errorMessage) {
        return WebApiResponse.error(errorMessage, ERROR_CODE);
    }

    public static <T> WebApiResponse<T> error(String errorMessage, int errorCode) {
        return WebApiResponse.error(errorMessage, errorCode, null);
    }

    public static <T> WebApiResponse<T> error(String errorMessage, Boolean isNeedRetry) {
        return WebApiResponse.error(errorMessage, ERROR_CODE, isNeedRetry);
    }

    public static <T> WebApiResponse<T> error(String errorMessage, int errorCode, Boolean isNeedRetry) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(errorCode);
        response.setError(errorMessage);
        response.setNeedRetry(isNeedRetry);
        return response;
    }

    public static <T> WebApiResponse<T> asProcess(Procedure<T> procedure) {
        return asProcess(procedure, Exception::toString);
    }

    public static <T> WebApiResponse<T> asProcess(
            Procedure<T> procedure, Function<Exception, String> exceptionHandler) {
        try {
            return success(procedure.apply());
        } catch (Exception e) {
            return error(exceptionHandler.apply(e));
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getNeedRetry() {
        return isNeedRetry;
    }

    public void setNeedRetry(Boolean needRetry) {
        isNeedRetry = needRetry;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebApiResponse<?> that = (WebApiResponse<?>) o;

        if (code != that.code) return false;
        if (error != null ? !error.equals(that.error) : that.error != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        return isNeedRetry != null ? isNeedRetry.equals(that.isNeedRetry) : that.isNeedRetry == null;

    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (error != null ? error.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (isNeedRetry != null ? isNeedRetry.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WebApiResponse{" +
                "code=" + code +
                ", error='" + error + '\'' +
                ", data=" + data +
                ", isNeedRetry=" + isNeedRetry +
                '}';
    }

    @FunctionalInterface
    public interface Procedure<T> {
        T apply() throws Exception;
    }
}
