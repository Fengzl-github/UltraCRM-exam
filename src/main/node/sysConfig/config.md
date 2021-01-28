## 系统配置
1. mvc配置
3. 


springboot获取当前项目路径的地址

System.getProperty("user.dir")

输出目录:  G:\outshine\wangsoso

//获取classes目录绝对路径

String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();

String path = ResourceUtils.getURL("classpath:").getPath();

输出目录:  /G:/outshine/wangsoso/target/classes/