from fastapi import FastAPI

app = FastAPI()

@app.get("/")
def read_root():
    return {"Hello":"World"}

@app.get("/items/{id}")
def read_item(id: int, q: str = None):
    return {"id": id, "q": q}


@app.get("/mongolapp/groups")
def read_groups():
    return [
        {"group_id":1001, "in_mongolian":"ᠮᠥᠰᠦ", "in_chinese":"冰","pronounce":"[mos]","image_url":"http://192.168.1.15/images/冰.jpg", "audio_url":""},
        {"group_id":1002, "in_mongolian":"ᠲᠥᠪᠡᠳ ᠬᠠᠰᠢ", "in_chinese":"番茄","pronounce":"[ towd xɑʃ ]","image_url":"http://192.168.1.15/images/番茄.jpg", "audio_url":""},
        {"group_id":1003, "in_mongolian":"ᠨᠢᠰᠬᠡᠯ", "in_chinese":"飞机","pronounce":"[nisgəl]","image_url":"http://192.168.1.15/images/飞机.jpg", "audio_url":""},
    ]

@app.get("/mongolapp/group/{group_id}")
def read_group(group_id: int):
    if group_id == 1001:
        ret = [
            {"id":10011, "in_mongolian":"ᠮᠥᠰᠦ", "in_chinese":"冰","pronounce":"[mos]","image_url":"http://192.168.1.15/images/冰.jpg", "audio_url":"http://192.168.1.15/audios/冰.mp3"},
            {"id":10012, "in_mongolian":"ᠮᠤᠳᠣ", "in_chinese":"树木","pronounce":"[ mɔd ]","image_url":"http://192.168.1.15/images/树木.jpg", "audio_url":"http://192.168.1.15/audios/树.mp3"},
            {"id":10013, "in_mongolian":"ᠠᠯᠲᠠ", "in_chinese":"金","pronounce":"[nisgəl]","image_url":"http://192.168.1.15/images/金.jpg", "audio_url":"http://192.168.1.15/audios/金.mp3"},
        ]
    
    if group_id == 1002:
        ret = [
           {"id":10021, "in_mongolian":"ᠲᠥᠪᠡᠳ ᠬᠠᠰᠢ", "in_chinese":"番茄","pronounce":"[ towd xɑʃ ]","image_url":"http://192.168.1.15/images/番茄.jpg", "audio_url":"http://192.168.1.15/audios/番茄.mp3"},
           {"id":10022, "in_mongolian":"ᠦᠵᠦᠮ", "in_chinese":"葡萄","pronounce":"[ udʒəm ]","image_url":"http://192.168.1.15/images/葡萄.jpg", "audio_url":"http://192.168.1.15/audios/葡萄.mp3"},
           {"id":10023, "in_mongolian":"ᠰᠤᠷᠢᠨᠵᠢᠨ", "in_chinese":"磁铁","pronounce":"[ sɔrɔndʒin ]","image_url":"http://192.168.1.15/images/磁铁.jpg", "audio_url":"http://192.168.1.15/audios/磁铁.mp3"},
           {"id":10024, "in_mongolian":"ᠳᠠᠪᠣᠰᠤ", "in_chinese":"盐","pronounce":"[ dɑws ]","image_url":"http://192.168.1.15/images/盐.jpg", "audio_url":"http://192.168.1.15/audios/盐.mp3"},
        ]

    if group_id == 1003:
        ret = [
            {"id":10031, "in_mongolian":"ᠨᠢᠰᠬᠡᠯ", "in_chinese":"飞机","pronounce":"[nisgəl]","image_url":"http://192.168.1.15/images/飞机.jpg", "audio_url":"http://192.168.1.15/audios/飞机.mp3"},
            {"id":10032, "in_mongolian":"ᠮᠠᠯᠠᠭᠠᠢ", "in_chinese":"帽子","pronounce":"[mɑlgɑi]","image_url":"http://192.168.1.15/images/帽子.jpg", "audio_url":"http://192.168.1.15/audios/帽子.mp3"},
            {"id":10033, "in_mongolian":"ᠭᠦᠵᠡᠯᠵᠡᠭᠡᠨ᠎ᠠ", "in_chinese":"草莓","pronounce":"[gudʒə:ldʒgən]","image_url":"http://192.168.1.15/images/草莓.jpg", "audio_url":"http://192.168.1.15/audios/草莓.mp3"},
        ]
        
    return ret
