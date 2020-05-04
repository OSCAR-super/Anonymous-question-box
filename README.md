# Anonymous-question-box
这是一个十分人性化的匿名提问箱的代码，希望你能喜欢  
http://111.230.173.74/UserLogin.action  
邀请码为：lytfuda2020sdl  
## 简介  
项目主要考虑用户的**使用情况**，进行**人性化**的改良，功能区主要集中在后端代码上。  
## 针对用户
主要将用户分为**游客**，**注册用户**和**管理员**，以下是这几种用户的权限：  
  游客|注册用户|管理员
  ---- | ----- | ------ 
  进入主页浏览|对其他用户提问，对提问回复，对其他用户进行分类|管理用户，并拥有注册用户的所有权限
## 针对安全
### 代码安全
主要是应用*csrf*生成*token*，在每个跳转页面进行验证  
同时，加入了*xss*监视器，防止通过*xss*和*csrf*同时入侵系统。  
因为这个项目使用了ssm框架，  
和*ajax*的兼容性差，不如分布式开发，  
对于表单的跳转带有*csrf*验证头，而少量的*ajax*请求则不带有验证头，  
既兼顾安全与项目代码的兼容性。  
### 用户安全
本项目将大部分跳转写死，防止通过异常跳转进入重要页面。  
对于修改用户信息，  
考虑到加密后仍可解密的**坑爹操作**，要求用户在有*token*验证的环境下进行修改信息。  
并且，在**注册**和**修改**时添加了**实时验证**用户名和邮箱**唯一性**的操作，
增加了用户的使用体验。
### 服务器安全
在有提交文件处写死只能提交*jpg*，*png*，*gif*等**图片文件**，  
对于*exe*文件不会进行储存到服务器上， 
并且，大于**500K**的文件也会被拦截。  
 
