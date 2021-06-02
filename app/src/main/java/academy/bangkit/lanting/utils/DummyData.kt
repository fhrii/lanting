package academy.bangkit.lanting.utils

import academy.bangkit.lanting.R
import academy.bangkit.lanting.data.model.AboutProfile
import academy.bangkit.lanting.data.model.Article

object DummyData {
    fun getArticles() = listOf(
        Article(
            "Berat Badan Janin Dalam Rahim",
            "07 October",
            "https://webicdn.com/sdirmember/2/1257/produk/cf23s.jpeg",
            "http://draldi.com/berat-badan-janin-dalam-rahim-detail-405093.html"
        ),
        Article(
            "Kurva Pertumbuhan WHO",
            "2006",
            "http://idai.or.id/wp-content/uploads/2013/05/Tabel-Growth-Chart-WHO-539x305.jpg",
            "https://www.idai.or.id/professional-resources/kurva-pertumbuhan/kurva-pertumbuhan-who"
        )
    )

    fun getAboutProfiles() = listOf(
        AboutProfile("Fahri Ahmad Fachrudin", "Mobile Development", "Universitas Muhammadiyah Prof. Dr. Hamka", R.drawable.img_fahri),
        AboutProfile("Fitria Urbach", "Machine Learning", "Institut Teknologi Sepuluh Nopember", R.drawable.img_fitria),
        AboutProfile("Hafifsyah Rifaldi", "Cloud Computing", "Universitas Muhammadiyah Prof. Dr. Hamka", R.drawable.img_hafif),
        AboutProfile("Syafniya Zilfah Aniesiy", "Machine Learning", "Institut Teknologi Sepuluh Nopember", R.drawable.img_zilfa),
        AboutProfile("Muhamad Zulfikri", "Cloud Computing", "Universitas Muhammadiyah Prof. Dr. Hamka", R.drawable.img_zul),
        AboutProfile("Firli Subhi Ramadani", "Mobile Development", "Universitas Muhammadiyah Prof. Dr. Hamka", R.drawable.img_firli),
    )
}