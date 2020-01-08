# 淘宝top淘宝客sdk基础版本 
#mvn deploy -DaltDeploymentRepository=liuchenrang-mvn-repo::default::file:/Users/chen/.chen-maven-repo/repository/

# 添加仓库
```$xslt
 <repositories>
        <repository>
            <id>liuchenrang-mvn-repo</id>
            <url>https://raw.githubusercontent.com/liuchenrang/mvnrepo/master/repository</url>
        </repository>
    </repositories> 

    <dependency>
            <groupId>com.github.liuchenrang.taobao</groupId>
            <artifactId>top-sdk</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```
   
