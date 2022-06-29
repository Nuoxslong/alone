#### 授权码获取

GET http://127.0.0.1:9999/oauth2/authorize
params response_type=code
scope=read
client_id=alone_oss
redirect_uri=http://127.0.0.1:10010/auth

#### 获取token

POST http://127.0.0.1:9999/oauth2/token
params code=code
grant_type=authorization_code
redirect_uri=http://127.0.0.1:10010/auth

#### 刷新token

POST http://127.0.0.1:9999/oauth2/token
params code=code
grant_type=refresh_token
redirect_uri=http://127.0.0.1:10010/auth
scope=read
