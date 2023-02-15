package com.azimsiddiqui.unicardapp

import android.content.Context
import android.net.Uri
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun Home(modifier: Modifier, videoUri: Uri) {
    val context = LocalContext.current

    val exoPlayer = remember { context.buildExoPlayer(videoUri) }
    var animate by remember { mutableStateOf(false) }


    //video is not working, so need to check this
//    DisposableEffect(
//        AndroidView(
//            factory = { it.buildPlayerView(exoPlayer) },
//            modifier = Modifier.fillMaxSize()
//        )
//    ) {
//        onDispose {
//            exoPlayer.release()
//        }
//    }

    ProvideWindowInsets() {
        LazyColumn {
            item {
                TopBar()

            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
                HeaderSection()
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))
                Section2(animate = true)
            }
        }
    }


}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Section2(animate: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(10.dp))
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            visible = true,
            enter = fadeIn(animationSpec = tween(durationMillis = 2000)),
            exit = fadeOut(animationSpec = tween(durationMillis = 2000))
        ) {
            Text(text = "Earn 1% assured cashback on your spends. Get 5X\n" +
                    "more value than cashback at the Uni Store. Enjoy\n" +
                    "round the clock Whatsapp support. And it's\n" +
                    "lifetime free; no joining fee, no annual charges.", fontSize = 20.sp, textAlign = TextAlign.Center)
        }

        Spacer(modifier = Modifier.height(40.dp))

        AnimatedVisibility(
            visible = animate,
            enter = scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically),
            exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.linearGradient(
                            listOf(Color("#65ECD8".toColorInt()), Color("#FDEF78".toColorInt()))
                        )
                    ),
            ) {

                Image(
                    painterResource(id = R.drawable.down_arrow),
                    contentDescription = "",
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }

        }


    }
}

@Composable
fun HeaderSection() {
    Column(modifier = Modifier.padding(20.dp)) {
        Image(
            painter = painterResource(id = R.drawable.header_image),
            contentDescription = "Image",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(id = R.string.header_text), fontSize = 30.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "1% Cashback", modifier = Modifier.padding(3.dp))
            Icon(imageVector = Icons.Default.Star, contentDescription = "icon")
            Text(text = "5x Rewards", Modifier.padding(3.dp))
            Icon(imageVector = Icons.Default.Star, contentDescription = "icon")
            Text(text = "Zero Forex Markup", Modifier.padding(3.dp))
        }
        ApplyNowComposable()


    }

}

@Composable
fun ApplyNowComposable() {
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .background(Color.Red),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                value = "Enter Phone Number",
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp),
                onValueChange = {},
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Black,
                    textColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
            )

            Box(
                modifier = Modifier
                    .height(60.dp)
                    .background(Color("#FDEF78".toColorInt()))
                    .padding(10.dp)

            ) {
                Text(text = "Apply Now", modifier = Modifier.align(Alignment.Center))
            }


        }

    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.unicard_logo),
            contentDescription = "logo",
            modifier = Modifier.padding(20.dp)
        )
    }
}


private fun Context.buildExoPlayer(uri: Uri) =
    ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = Player.REPEAT_MODE_ALL
        playWhenReady = true
        prepare()
    }

private fun Context.buildPlayerView(exoPlayer: ExoPlayer) =
    StyledPlayerView(this).apply {
        var player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        useController = false
        resizeMode = RESIZE_MODE_ZOOM
    }
