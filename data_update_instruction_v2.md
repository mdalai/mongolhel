# Instruction

[apiguide1]: assets/api_guide_01.JPG "API Guide 1"
[apiguide2]: assets/api_guide_02.JPG "API Guide 2"

## First: Upload Files

上传图片文件到  /root/dev/assets/images/  文件名必须唯一

上传音频文件到  /root/dev/assests/audios/ 文件名必须唯一

## Upload data

数据上传统一用：  http://<IP>:8080/docs.  浏览器输入此URL会看到很多API. 上传数据需要POST 如下图：
  ![alt text][apiguide1]
  

三个POST 针对 group， item, about 三个上传功能。

点击其一后， 会看到“Try it out”按钮, 点击后会看到如下图的页面：
  ![alt text][apiguide2]
  
  
 1) 输入需要的ID 如有要求。 这里是group_id；
 2) 输入每个项的内容值。 一定要放到双引号之内；左边的内容不要更改！
 3) 点击"Execute" 确认提交。 如提交成功会返回 200.
 

  
修改数据需要注意：
- 先上传group, 用自动产生的group_id 再上传item
- image_url 要跟 step 1 上传的路径匹配；
- audio_url 要跟 step 1 上传的音频文件路径匹配；
