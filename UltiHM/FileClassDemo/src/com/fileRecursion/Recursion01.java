package com.fileRecursion;

import java.io.File;

public class Recursion01 {
    public static void main(String[] args) {
        searchFile(new File("D:/Gwyndolin_Doc/"),"JAVA进阶.md");
    }

    /**
     *
     * @param dir  搜寻目录:类型为File判断是否为空，且是目录
     * @param fileName  搜寻的文件名
     */
    public static void searchFile (File dir,String fileName){
        if (dir != null && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0 ){
                for (File file:files
                     )  {if (file.isFile()) {
                        if (file.getName().contains(fileName))
                            System.out.println("Located in:"+file.getAbsolutePath());
                    } else if(file.isDirectory()) searchFile(file, fileName);
                }
            }
        }
    }
}
