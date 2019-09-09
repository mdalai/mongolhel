# Instruction

## Step 1: Upload Files

上传图片文件到  /root/dev/assets/images/  文件名必须唯一

上传音频文件到  /root/dev/assests/audios/ 文件名必须唯一

## Step 2: Update group data

Group JSON 数据存储在文件 /root/dev/data_groups.json

修改数据需要注意：
- 格式必须跟现有的数据一致； 双引号，分号， 逗号 等等
- group_id 必须唯一； 
- image_url 要跟 step 1 上传的路径匹配；


## Step 3: Update group itmes data

Group Item JSON 数据存储在文件 /root/dev/data_group_items.json

修改数据需要注意：
- 格式必须跟现有的数据一致； 双引号，分号， 逗号 等等
- 开头的双引号里的号码 跟 step 2 定义的group_id 匹配；
- item_id 必须唯一； 
- image_url 要跟 step 1 上传的图片文件路径匹配；
- audio_url 要跟 step 1 上传的音频文件路径匹配；
- 其他的需要按统一格式 填写
