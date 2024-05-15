package org.restart.shop.exception;

import io.micrometer.common.util.StringUtils;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RsException extends RuntimeException{

    /**
     * 错误码
     */

    private String errorCode = "0x80000000";

    /**
     * 自定义错误消息
     */
    private String errorMessage;

    /**
     * 是否展示
     */
    private boolean showFlag;

    /**
     * 异常对象
     */
    private Throwable throwable;

    private String clientExecuteMethod;

    private boolean isCloseoperte = false;


    /**
     * 构造方法
     *
     * @param errorCode 自定义错误消息
     */
    public RsException(String errorCode) {

        super("errorCode:[" + errorCode + "]");
        this.errorCode = errorCode;
    }


    /**
     * 构造方法
     *
     * @param errorCode 自定义错误消息
     */
    public RsException(String errorCode,boolean isCloseoperte,String errorMessage) {

        super("errorCode:[" + errorCode + "]");
        this.errorCode = errorCode;
        this.isCloseoperte = isCloseoperte;
        this.errorMessage = errorMessage;

    }

    /**
     * 构造方法
     *
     * @param errorCode    错误码
     * @param errorMessage 自定义错误消息
     */
    public RsException(String errorCode, String errorMessage) {

        super("errorCode:[" + errorCode + "]" + errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    /**
     * 构造方法
     *
     * @param errorCode    错误码
     * @param errorMessage 自定义错误消息
     * @param showFlag     是否显示
     */
    public RsException(String errorCode, String errorMessage, boolean showFlag) {

        super("errorCode:[" + errorCode + "]" + errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.showFlag = showFlag;
    }


    /**
     * 构造方法
     *
     * @param errorCode    错误码
     * @param errorMessage 自定义错误消息
     * @param cause        异常源
     */
    public RsException(String errorCode, String errorMessage, Throwable cause) {

        super("errorCode:[" + errorCode + "]" + errorMessage, cause);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    /**
     * 构造方法
     *
     * @param errorCode 错误码
     * @param cause     异常源
     */
    public RsException(String errorCode, Throwable cause) {

        super("errorCode:[" + errorCode + "]", cause);
        this.errorCode = errorCode;
    }


    public void throwException() {
        String errorMsg = this.getErrorMessage();
        if (StringUtils.isEmpty(errorMsg)) {
            throw new RsException(this.getErrorCode());
        } else {
            throw new RsException(this.getErrorCode(), errorMsg);
        }
    }

    /**
     * 构造方法
     *
     * @param cause 异常源
     */
    public RsException(Throwable cause) {

        super(cause);
    }
}
