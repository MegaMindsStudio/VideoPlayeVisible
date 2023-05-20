package com.playverse.video.video4kwallapper.locksceenwallpaper.setasvideo.hdbackground.videodummyproject

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.playverse.video.video4kwallapper.locksceenwallpaper.setasvideo.hdbackground.videodummyproject.databinding.ItemLayoutVideosBinding

class VideoAdapter(var wallPaperList:ArrayList<WallPaper>): RecyclerView.Adapter<VideoAdapter.VideoWallPaperHolder>() {
    private lateinit var context:Context
    var previous: VideoWallPaperHolder? = null
    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoWallPaperHolder {
        context=parent.context
        return VideoWallPaperHolder(ItemLayoutVideosBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return  wallPaperList.size
    }

    override fun onBindViewHolder(holder: VideoWallPaperHolder, position: Int) {
        var wallPaper=wallPaperList.get(position)
        var videoWallPaperHolder:VideoWallPaperHolder=holder as VideoWallPaperHolder
        Glide.with(context).load(wallPaperList.get(position).link)
            .into(holder.bindingVideo.setImageVP)
        val uri = Uri.parse(wallPaperList.get(position).link)
        Log.d("myTag", uri.toString())
        videoWallPaperHolder.bindingVideo.setVideoVP.setVideoURI(uri)
//        if (selectedPosition == position) {
//            videoWallPaperHolder.bindingVideo.setVideoVP.setOnPreparedListener(MediaPlayer.OnPreparedListener {
//                videoWallPaperHolder.bindingVideo.setImageVP.setVisibility(View.GONE)
//                videoWallPaperHolder.bindingVideo.progress.setVisibility(View.GONE)
//                videoWallPaperHolder.bindingVideo.setVideoVP.start()
//                previous = holder
//            })
//
//            videoWallPaperHolder.bindingVideo.setVideoVP.setOnCompletionListener(MediaPlayer.OnCompletionListener {
//                // Do something when the video playback is completed
//            })
//        } else {
//            if (previous != null) {
//                previous!!.bindingVideo.setVideoVP.stopPlayback()
//                previous!!.bindingVideo.progress.setVisibility(View.GONE)
//                Glide.with(context).load(wallPaperList.get(position).link)
//                    .into(holder.bindingVideo.setImageVP)
//            }
//        }
        holder.itemView.setOnClickListener {
//            val i = Intent(context, ActivitySlidingUp::class.java)
//            val bundle = Bundle()
//            bundle.putSerializable("wallPaperList", wallPaperList)
//            i.putExtras(bundle)
//            i.putExtra("clickPosition", position)
//            context.startActivity(i)
        }
    }
    public class VideoWallPaperHolder(var bindingVideo: ItemLayoutVideosBinding) : RecyclerView.ViewHolder(bindingVideo.root) {}
}