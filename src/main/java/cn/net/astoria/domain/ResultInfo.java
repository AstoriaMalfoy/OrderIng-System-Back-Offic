package cn.net.astoria.domain;


/**
 * @ClassName ResultInfo
 * @Description 服务器返回给客户端的数据结构
 * @Author Astoria
 * @Date 2020/3/3 15:12
 * @Version 1.0
 */
public class ResultInfo {
    /**
     * 标志该消息体是否是一个正常信息
     * 如果是一个正常消息 该字段为true
     * 如果是一个错误信息 该字段为false
     */
    private boolean flag;
    /**
     * 如果该消息体是一个错误信息，则表示错误信息
     * 如果该消息体不是一个错误信息，则该字段无效
     */
    private String errorMessage;
    /**
     * 在非消息体的时候用于传递服务端请求的数据
     */
    private Object Data;

    public ResultInfo(boolean flag, String errorMessage, Object data) {
        this.flag = flag;
        this.errorMessage = errorMessage;
        Data = data;
    }

    public ResultInfo() {
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "flag=" + flag +
                ", errorMessage='" + errorMessage + '\'' +
                ", Data=" + Data +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }
}
