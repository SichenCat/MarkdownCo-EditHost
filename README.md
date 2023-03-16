# Git使用指南?

## 同步本地文档

- 快捷使用：在目录的命令行工具git bash中依次执行

  ```c
  git add .
  git commit -m "description forexample : first commit"
  git push -u origin master
  ```
  
  #### git add：
  添加文件到暂存区：`git add [file1] [file2]  ... `
  添加目录(及其所有子目录)：` git add [dir] `
  添加所有文件：` git add . `
  添加文件后必须使用add命令！
  
  #### git commit:
  将暂存区内容添加到本地仓库: ` git commit [file1] [file2] ... -m [message] `
  设置'修改'文件(不是添加！)后无需git add 直接提交: ` git commit -am [message]` 
  
  #### git push:
  
  将本地仓库提交到远程仓库： ` git push <remote> <localbranch>:<remotebranch>`
  快捷提交，首次使用时添加 -u 来设置默认值：` git push -u origin master`
  使用-u设置后，可以直接提交/拉取：` git push `    |    ` git pull `
  
  
  
  

## 同步仓库到本地

- 在目标文件下使用git bash执行：

  

```c
git clone [remoteRepoURL] [LocalRepoName]
```



git clone [git@github.com:SichenCat/MarkdownCo-EditHost] [File_Name_YouWant]



## 本地仓库更新(开始编辑前)

- 在文件下执行:

  ```
  git pull
  ```

  
