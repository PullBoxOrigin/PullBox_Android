package com.antique_boss.util.preview

import android.webkit.URLUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URI

data class Preview(
    val url: String,
    val imageUrl: String,
    val title: String,
    val description: String,
    val siteName: String,
    val favicon: String
)


class PreviewCrawler {
    companion object {
        const val INITIALIZE_VALUE = ""
    }

    suspend fun getPreview(parseUrl: String): Preview {
        return withContext(Dispatchers.IO) {
            val doc: Document
            var title: String? = INITIALIZE_VALUE
            var description: String? = INITIALIZE_VALUE
            var url: String? = INITIALIZE_VALUE
            var imageUrl: String? = INITIALIZE_VALUE
            var siteName: String? = INITIALIZE_VALUE
            var favicon: String? = INITIALIZE_VALUE


            try {
                doc = Jsoup.connect(parseUrl).timeout(30 * 1000).get()
                val elements = doc.getElementsByTag("meta")
                title = doc.select("meta[property=og:title]").attr("content")
                title?.let {
                    if(it.isEmpty()) {
                        title = doc.title()
                    }
                }

                description = doc.select("meta[name=description]").attr("content")
                if(description.isNullOrEmpty()) {
                    description = doc.select("meta[name=Description]").attr("content")
                }
                if(description.isNullOrEmpty()) {
                    description = doc.select("meta[property=og:description]").attr("content")
                }
                if(description.isNullOrEmpty()) {
                    description = ""
                }

                val imageElements = doc.select("meta[property=og:image]")
                if(imageElements.size > 0) {
                    val image = imageElements.attr("content")
                    if(image.isNotEmpty()) {
                        imageUrl = image
                    }
                }

                if(imageUrl.isNullOrEmpty()) {
                    val ogImageElements = doc.select("meta[name=og:image]")
                    if(ogImageElements.size > 0) {
                        val image = ogImageElements.attr("content")
                        if(image.isNotEmpty()) {
                            imageUrl = image
                        }
                    }
                }

                if(imageUrl.isNullOrEmpty()) {
                    val imageSrc = doc.select("link[rel=image_src]").attr("href")
                    if(imageSrc.isNotEmpty()) {
                        imageUrl = resolveUrl(parseUrl, imageSrc)
                    } else {
                        val appleTouchIcon = doc.select("link[rel=apple-touch-icon]").attr("href")
                        if(appleTouchIcon.isNotEmpty()) {
                            imageUrl = resolveUrl(parseUrl, appleTouchIcon)
                            favicon = resolveUrl(parseUrl, appleTouchIcon)
                        } else {
                            val icon = doc.select("link[rel=icon]").attr("href")
                            if(icon.isNotEmpty()) {
                                imageUrl = resolveUrl(parseUrl, icon)
                                favicon = resolveUrl(parseUrl, icon)
                            }
                        }
                    }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }

            Preview(description = description!!, imageUrl = imageUrl!!, url = url!!, title = title!!, siteName = siteName!!, favicon = favicon!!)
        }
    }

    private fun resolveUrl(url: String, part: String): String {
        if(URLUtil.isValidUrl(part)) {
            return part
        } else {
            val baseUri: URI
            try {
                baseUri = URI(url).resolve(part)
                return baseUri.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return ""
        }
    }
}