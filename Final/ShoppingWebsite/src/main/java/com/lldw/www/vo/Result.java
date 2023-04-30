package com.lldw.www.vo;

import com.lldw.www.constants.ResultConstants;

/**
 * @author lldw
 * @date 2023-04-24 23:12:15
 */
public class Result {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    public Result() {
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success(Object data){
        return new Result(ResultConstants.SUCCESS_CODE,ResultConstants.SUCCESS,data);
    }
    public static Result success(String msg,Object data){
        return new Result(ResultConstants.SUCCESS_CODE,msg,data);
    }

    public static Result success(){
        return new Result(ResultConstants.SUCCESS_CODE, ResultConstants.SUCCESS,null);
    }

    public static Result error(String msg){
        return new Result(ResultConstants.ERROR_CODE,msg,null);
    }

    @Override
    public String toString() {
//        return "Result{" +
//                "code=" + code +
//                ", msg='" + msg + '\'' +
//                ", data=" + data +
//                '}';
        /*

        return JSON.toJSONString("{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}');

                */

        return "{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
