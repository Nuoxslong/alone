```java 
/**
 * 接口请求异常没有进行异常处理时
 * security默认重定向到："/error"
 * 假如没有放行："/error"，那服务将会提示：Full authentication is required to access this resource
 * 这是时候原有接口报错会被覆盖，并无法得知，页面显示"授权验证失败"
 * */
```