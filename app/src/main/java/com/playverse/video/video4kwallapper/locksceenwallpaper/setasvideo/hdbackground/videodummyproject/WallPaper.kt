package com.playverse.video.video4kwallapper.locksceenwallpaper.setasvideo.hdbackground.videodummyproject



class WallPaper(

    var id:Int=0,
    var download:Int=0,
    var link:String="",
    var name: String="",
    ):java.io.Serializable
{
    constructor(download: Int?,link: String?,name: String?):this(){
        this.download=download!!
        this.link=link!!
        this.name=name!!
    }



}