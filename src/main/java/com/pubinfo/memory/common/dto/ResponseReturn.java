package com.pubinfo.memory.common.dto;

public class ResponseReturn {

    /**
     * 返回结果
     */
    private boolean success;

    /**
     * 返回信息
     */
    private int code;

    /**
     * 返回数据
     */
    private Object data;

    public ResponseReturn(boolean success) {
        super();
        this.success = success;
    }

    public ResponseReturn(boolean success, int code) {
        super();
        this.success = success;
        this.code = code;
    }

    public ResponseReturn(boolean success, Object data) {
        super();
        this.success = success;
        this.data = data;
    }

    public ResponseReturn(boolean success, int code, Object data) {
        super();
        this.success = success;
        this.code = code;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseReturn success(Object object) {
        return new ResponseReturn(true, ResponseCode.SUCCESS.getCode(), object);
    }

    public static ResponseReturn success() {
        return new ResponseReturn(true, ResponseCode.SUCCESS.getCode());
    }

    public static ResponseReturn addSuccess() {
        return new ResponseReturn(true, ResponseCode.ADD_SUCCESS.getCode(), ResponseCode.ADD_SUCCESS.getDes());
    }

    public static ResponseReturn addSuccess(Object object) {
        return new ResponseReturn(true, ResponseCode.ADD_SUCCESS.getCode(), object);
    }

    public static ResponseReturn deleteSuccess() {
        return new ResponseReturn(true, ResponseCode.DELETE_SUCCESS.getCode(), ResponseCode.DELETE_SUCCESS.getDes());
    }

    public static ResponseReturn successButNoData() {
        return new ResponseReturn(true, ResponseCode.SUCCESS_BUT_NO_DATA.getCode(), ResponseCode.SUCCESS_BUT_NO_DATA.getDes());
    }

    public static ResponseReturn dataIsNUll() {
        return new ResponseReturn(false, ResponseCode.DATA_IS_NULL.getCode(), ResponseCode.DATA_IS_NULL.getDes());
    }

    public static ResponseReturn businessProcessingError(String errorInfo) {
        return new ResponseReturn(false, ResponseCode.BUSINESS_PROCESSING_ERROR.getCode(),
                ResponseCode.BUSINESS_PROCESSING_ERROR.getDes() + errorInfo);
    }

    public static ResponseReturn paramNull(Object paramName) {
        return new ResponseReturn(false, ResponseCode.PARAM_NULL.getCode(), ResponseCode.PARAM_NULL.getDes() + paramName);
    }

    public static ResponseReturn unknownError() {
        return new ResponseReturn(false, ResponseCode.UNKNOWN_ERROR.getCode(), ResponseCode.UNKNOWN_ERROR.getDes());
    }

    public static ResponseReturn unAuthorized() {
        return new ResponseReturn(false, ResponseCode.UN_AUTHORIZED.getCode(), ResponseCode.UN_AUTHORIZED.getDes());
    }

    public static ResponseReturn loginUnAuthorized() {
        return new ResponseReturn(false, ResponseCode.LOGIN_UN_AUTHORIZED.getCode(), ResponseCode.LOGIN_UN_AUTHORIZED.getDes());
    }

    enum ResponseCode {
        /*Successful operation.*/
        SUCCESS(200, "操作成功。"),
        /*Successfully added information.*/
        ADD_SUCCESS(201, "信息添加成功。"),
        /*Sorry, no eligible information found in this directory.*/
        SUCCESS_BUT_NO_DATA(204, "抱歉，此目录中找不到符合条件的信息。"),
        /*Delete message succeeded.*/
        DELETE_SUCCESS(204, "信息删除成功。"),
        /*This application is not authorized。*/
        UN_AUTHORIZED(401, "该应用程序未经授权。"),
        LOGIN_UN_AUTHORIZED(401, "该用户未经授权。"),
        /*Unable to query the corresponding data, please enter the correct parameters.*/
        DATA_IS_NULL(404, "无法查询相应的数据，请输入正确的参数。"),
        /*Illegal request parameter：*/
        PARAM_NULL(406, "非法请求参数："),
        INFO_NULL(500, "信息不存在"),
        DATE_ERROR(500, "日期转换异常"),
        CLASS_CAST_EROOR(500, "引用类型转换异常"),
        /*Business processing error。*/
        BUSINESS_PROCESSING_ERROR(500, "业务处理错误: "),
        UNKNOWN_ERROR(-1, "unknown error.");

        private int code;
        private String des;

        ResponseCode(int code, String des) {
            this.code = code;
            this.des = des;
        }
 
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}
