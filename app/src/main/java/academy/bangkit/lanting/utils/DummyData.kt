package academy.bangkit.lanting.utils

import academy.bangkit.lanting.data.model.Article

object DummyData {
    private val articles = listOf(
        Article(
            "Jaga Asupan Makanan Anak ala Emak-emak 'Omaba'",
            "30 Maret 2021",
            "https://akcdn.detik.net.id/community/media/visual/2021/03/30/omaba-bandung-2_169.jpeg?q=100",
            "https://news.detik.com/berita-jawa-barat/d-5513369/jaga-asupan-makanan-anak-ala-emak-emak-omaba?_ga=2.190147115.1486240901.1621254591-1696731506.1620227856"
        ),
        Article(
            "Tertinggi se-Jatim, Angka Kematian Ibu dan Bayi di Jember Disorot Khofifah",
            "2 Maret 2021",
            "https://cdnv.detik.com/videoservice/AdminTV/2021/03/02/84737b9cb31548ddbf7e9da949c5643d-20210302211934-0s.jpg?q=100",
            "https://20.detik.com/detikflash/20210302-210302129/tertinggi-se-jatim-angka-kematian-ibu-dan-bayi-di-jember-disorot-khofifah"
        )
    )

    fun getArticles() = articles
}