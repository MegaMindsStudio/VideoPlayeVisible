package com.playverse.video.video4kwallapper.locksceenwallpaper.setasvideo.hdbackground.videodummyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.playverse.video.video4kwallapper.locksceenwallpaper.setasvideo.hdbackground.videodummyproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: VideoAdapter
    private lateinit var videoWallPaperList:ArrayList<WallPaper>
    private var y:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        videoWallPaperList = ArrayList<WallPaper>()
        getVideoWallpaper()
    }
        private fun getVideoWallpaper() {
            val dataBaseReference = FirebaseDatabase.getInstance().getReference("Video")
            dataBaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    videoWallPaperList.clear()
                    for (categorySnapchat in snapshot.children) {
                        val wallpaper = categorySnapchat.getValue(WallPaper::class.java)
                        if (wallpaper != null) {
                            videoWallPaperList.add(wallpaper)
                        }
                        adapter = VideoAdapter(videoWallPaperList)
                        videoWallPaperList.shuffle()
                        binding.rvVideo.layoutManager= LinearLayoutManager(this@MainActivity)
                        binding.rvVideo.adapter=adapter

                        val scrollListener = object : RecyclerView.OnScrollListener() {
                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                super.onScrolled(recyclerView, dx, dy)
                                // Call the method to handle video visibility based on scroll
                                handleVideoVisibility(recyclerView)
                            }
                        }

// Attach the scroll listener to the RecyclerView
                        binding.rvVideo.addOnScrollListener(scrollListener)


//                        binding.rvVideo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                                super.onScrolled(recyclerView, dx, dy)
//                                y = dy
//                            }
//
//                            override fun onScrollStateChanged(
//                                recyclerView: RecyclerView,
//                                newState: Int
//                            ) {
//                                super.onScrollStateChanged(recyclerView, newState)
//                                if (RecyclerView.SCROLL_STATE_DRAGGING == newState) {
//                                    //fragProductLl.setVisibility(View.GONE);
//                                }
//                                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
//                                    // fragProductLl.setVisibility(View.VISIBLE);
//                                    y
//
//                                    adapter.updateSelectedPosition(y)
//                                    adapter.notifyItemChanged(y)
//
//                                }
//                            }
//                        })


                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("myTag",error.toString())

                }

            })
        }

    private fun handleVideoVisibility(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
        val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()

        for (i in firstVisiblePosition..lastVisiblePosition) {
            val holder = recyclerView.findViewHolderForAdapterPosition(i) as? VideoAdapter.VideoWallPaperHolder
            if (holder != null) {
                // Play video if it is the most visible item, pause otherwise
                if (i == firstVisiblePosition) {
                    playVideo(holder)
                } else {
                    pauseVideo(holder)
                }
            }
        }
    }

    private fun playVideo(holder: VideoAdapter.VideoWallPaperHolder) {
        // Start playing the video
        holder.bindingVideo.setVideoVP.start()
        holder.bindingVideo.progress.visibility= View.GONE
        holder.bindingVideo.setImageVP.visibility=View.GONE
    }

    private fun pauseVideo(holder: VideoAdapter.VideoWallPaperHolder) {
        // Pause the video
        holder.bindingVideo.setVideoVP.pause()
        holder.bindingVideo.progress.visibility= View.VISIBLE
        holder.bindingVideo.setImageVP.visibility=View.VISIBLE
    }

}