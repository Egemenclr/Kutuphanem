package com.egemeninceler.kutuphanem.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.egemeninceler.kutuphanem.R
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import kotlinx.android.synthetic.main.fragment_silinecek.*


class MusicFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_silinecek, container, false)
    }

    companion object {
        private const val CLIENT_ID = "d60dfe1de9ec46d8b1849e70d0ab1459"
        private const val REDIRECT_URI = "https://www.google.com/"
        private var mSpotifyAppRemote: SpotifyAppRemote? = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val connectionParams = ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(REDIRECT_URI)
            .showAuthView(true)
            .build()


        SpotifyAppRemote.connect(context, connectionParams,
            object : Connector.ConnectionListener {
                override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                    mSpotifyAppRemote = spotifyAppRemote
                    Log.d("MainActivity", "Connected! Yay!")
                }
                override fun onFailure(throwable: Throwable) {
                    Log.e("MusicFragment", throwable.message, throwable)
                }
            })
    }

    override fun onResume() {
        super.onResume()

        var sayac = 0
        var sayac2 = 0
        frgmnt_play.setOnClickListener {
            sayac += 1
            if (sayac % 2 == 0) {
                mSpotifyAppRemote?.playerApi?.play("spotify:playlist:1q3fbAG8ICV2xFMrGhbz4D")
                frgmnt_play.setBackgroundResource(R.drawable.ic_pause)

            } else {
                frgmnt_play.setBackgroundResource(R.drawable.ic_play)
                mSpotifyAppRemote?.playerApi?.pause()
            }
        }

        frgmnt_next.setOnClickListener {
            mSpotifyAppRemote?.playerApi?.skipNext()
        }
        frgmnt_prev.setOnClickListener {
            mSpotifyAppRemote?.playerApi?.skipPrevious()
        }

        frgmnt_play2.setOnClickListener {
            sayac2+=1
            if (sayac2 % 2 == 0) {
                mSpotifyAppRemote?.playerApi?.play("spotify:playlist:4FhSe6UqyU8BCDmsZOPcJQ")
                frgmnt_play2.setBackgroundResource(R.drawable.ic_pause)

            } else {
                frgmnt_play2.setBackgroundResource(R.drawable.ic_play)
                mSpotifyAppRemote?.playerApi?.pause()
            }
        }
        frgmnt_next2.setOnClickListener {
            mSpotifyAppRemote?.playerApi?.skipNext()
        }
        frgmnt_prev2.setOnClickListener {
            mSpotifyAppRemote?.playerApi?.skipPrevious()
        }
    }


}