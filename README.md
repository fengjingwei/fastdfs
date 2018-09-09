# FastDFS #
> FastDFS is an open source high performance distributed file system (DFS). It's major functions include: file storing, file syncing and file accessing, and design for high capacity and load balance. 

## Features ##
# 1.分布式文件系统架构说明 #
## - fastdfs-client(FastDFS 客户端) ##
- fastdfs提供的java客户端api，java相关功能都基于这个基础上封装，扩展，第三方应用不需要关心该接口.
## - fastdfs-core(HTTP服务器) ##
- 基于spring boot实现，提供http接口服务.
- 提供http服务器信息获取，http上传，http下载，删除上报，该服务会记录文件的基本信息，其中服务器信息获取，上传上报都由fastdfs-app自动完成，第三方应用不需要关心.
## - fastdfs-app(Apply SDK) ##
- **初始化**
- APIConfigure config = new APIConfigure("appKey", "httpServerUrl");
- DFSAppClient.instance().initAPIConfigure(config);
- 实现执行初化操作，从fastdfs-core获取trackers服务器信息，及appKey对应的groupName，
这些动作都由SDK自动完成，第三方应用不需要关心.

- **上传文件**
- String fileId = DFSAppClient.instance().uploadFile(new File("文件绝对路径"));
- fileId:返回的fileId字符串，示例：group1/M00/00/00/wKgABFuOVJyEPGKEAAAAADUuUeE339.png
- fileId是后续对文件进行操作的基本参数，第三方应用拿到该值后应本地做好保存.
	
- **下载文件**
- FileOutputStream fos = new FileOutputStream(new File("文件绝对路径"));
- DFSAppClient.instance().downloadFile(fileId, fos, true);
- fileId:上传文件成功后返回的fileId字符串.

- **删除文件**
- int result = DFSAppClient.instance().deleteFile(fileId);
- fileId:上传文件成功后返回的fileId字符串.
- result:该方法会返回0表示删除成功，其他表示失败.

# **fastdfs 下载示例说明** #
- http://127.0.0.1:8808/dfs/v1/download?fileId=group1/M00/00/00/wKgABFuQ2PWEbNsOAAAAADUuUeE667.png&direct=true
- fileId:上传文件成功后返回的fileId字符串.
- direct:表示是否直接显示，非直接显示会提示下载，默认是非直接显示.

## Prerequisite ##
1. JDK 1.8+
2. Maven 3.5.x
3. Git版本控制

## Quick Start ##
- If work: FastDFS在CentOS下配置安装部署，参考教程[http://blog.mayongfa.cn/192.html](http://blog.mayongfa.cn/192.html)

- Clone & Build
> git clone https://github.com/fengjingwei/fastdfs.git
> 
> cd mall
> 
> mvn -DskipTests clean install -U

- execute sql
> [https://github.com/fengjingwei/fastdfs/blob/master/sql/dfs_create_tables.sql](https://github.com/fengjingwei/fastdfs/blob/master/sql/dfs_create_tables.sql)

## Support ##
- 如有任何问题欢迎微我