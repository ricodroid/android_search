package com.example.recyclerviewticktock

class VideoInfoManager {
    private val videoInfos = mutableListOf<VideoInfo>()

    // 動画情報を追加するメソッド
    fun addVideoInfo(url: String, fileName: String) {
        videoInfos.add(VideoInfo(url, fileName))
    }

    // 指定されたURLの動画情報を削除するメソッド
    fun removeVideoInfo(url: String) {
        val iterator = videoInfos.iterator()
        while (iterator.hasNext()) {
            val videoInfo = iterator.next()
            if (videoInfo.url == url) {
                iterator.remove()
            }
        }
    }

    // 動画情報のリストを取得するメソッド
    fun getVideoInfos(): List<VideoInfo> {
        return videoInfos.toList()
    }

    // 指定されたURLの動画情報のファイル名を変更するメソッド
    fun setVideoFileName(url: String, newFileName: String) {
        videoInfos.forEach {
            if (it.url == url) {
                it.fileName = newFileName
            }
        }
    }

    // 指定されたURLの動画情報を更新するメソッド
    fun updateVideoInfo(url: String, newUrl: String, newFileName: String) {
        videoInfos.forEach {
            if (it.url == url) {
                it.url = newUrl
                it.fileName = newFileName
            }
        }
    }
}