package httpUtils;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
public interface HttpCallBackListener {
    void onFinish(String response);//服务器成功响应时调用
    void onError(Exception e);//网络操作出现错误时调用
}
