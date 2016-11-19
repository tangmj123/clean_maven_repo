package com.tmj.maven.clean;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/** 
 * ClassName: MavenCleanUtil. 
 * Description: 清除maven本地仓库中没有下载完成的文件.  
 * Date: 2016年11月19日 下午12:23:36. 
 * @author: TMJ
 */
public class MavenCleanUtil {

    public static void main(String[] args) {
        String repoPath = null;
        if(args==null || args.length==0) repoPath = "E:\\m2\\repository";   
        else                             repoPath = args[0];
        System.out.println("[repoPath:"+repoPath+"]");
        try {
            Path path = Paths.get(repoPath);
            File file = path.toFile();
            File[] list = file.listFiles();
            Stream.of(list)
                .forEach(f->{
                    remove(f);
                });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Function: 删除仓库中以 .lastUpdated结尾的文件.
     * @param file
     * @throws IOException 
     */
    private static void remove(File file) {
        if(file.isDirectory()){
            System.out.println("[remove file from Dictory:"+file.getAbsolutePath()+"]");
            File[] listFiles = file.listFiles();
            for (File child : listFiles) 
                remove(child);
        }else{
            if(file.getPath().endsWith(".lastUpdated"))
                try {
                    Files.delete(Paths.get(file.getAbsolutePath()));
                    System.out.println("[delete file:"+file.getAbsolutePath()+"]");
                } catch (IOException e) {    e.printStackTrace(); }
        }
    }
}
