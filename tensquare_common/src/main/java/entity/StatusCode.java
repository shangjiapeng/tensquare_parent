package entity;

/**
 * <p>状态码实体类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-07-30 16:03
 */
public class StatusCode {
    private static final int OK=20000;//成功
    private static final int ERROR=20001;//失败
    private static final int LOGIN_ERROR=20002;//用户名或者密码错误
    private static final int ACCESS_ERROR=20003;//权限不足
    private static final int REMOTE_ERROR=20004;//远程调用失败
    private static final int REPEAT_ERROR=20005;//重复操作
}
