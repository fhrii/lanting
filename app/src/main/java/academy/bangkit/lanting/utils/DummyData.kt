package academy.bangkit.lanting.utils

import academy.bangkit.lanting.data.model.Article
import academy.bangkit.lanting.data.model.Nutrition
import academy.bangkit.lanting.data.model.ProfileCategory
import academy.bangkit.lanting.data.model.Recipe

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

    fun getNutrition(profileId: Int): List<Nutrition> {
        val date = DateHelper.formatStringToDate("20 May 2021")
        return listOf(
            Nutrition(
                0,
                profileId,
                date,
                "ayam goreng",
                "1 porsi",
                781,
                43.64,
                65.8,
                32.29
            ), Nutrition(
                0,
                profileId,
                date,
                "tempe goreng",
                "1 buah",
                34,
                2.28,
                2.0,
                1.79
            )
        )
    }

    fun getRecipes(): List<Recipe> {
        return listOf(
            Recipe(
                "Pure Kentang",
                "150 g kentang\r\n200 ml air jeruk baby",
                "1. Kupas kentang, cuci sampai bersih. Kukus kentang sampai matang dan empuk, angkat.\r\n2. Satukan kentang kukus dengan air jeruk baby. Proses dengan blender sampai lumat.",
                "Energi : 116,5 kal\r\nProtein : 2,4 g\r\nLemak : 0,1 g\r\nKarbohidrat : 28 g",
                null,
                "https://bomanta.com/wp-content/uploads/2016/05/resep-kentang-pure-untuk-bayi-800x445.jpg",
                10000
            ),
            Recipe(
                "Pure Alpukat",
                "125 g alpukat matang\r\n25 ml air matang\r\n100 ml air matang/ASI perah/sufor",
                "1. Keruk daging buah alpukat, proses dengan blender bersama air matang sampai halus. Tuang ke dalam wadah.\r\n2. Tambahkan ASI perah/sufor, aduk rata. Saring.",
                "Energi : 133 kal\r\nProtein : 1,7 g\r\nLemak : 11,5 g\r\nKarbohidrat : 8,3 g",
                null,
                "https://res.cloudinary.com/dk0z4ums3/image/upload/v1513649704/attached_image/manfaat-alpukat-untuk-bayi-sehat-dan-cara-menyajikannya.jpg",
                7000
            ),
            Recipe(
                "Pure Jambu Biji",
                "100 g jambu biji merah\r\n100 ml air jeruk baby\r\n50 ml air matang/ASI/sufor",
                "1. Kupas, jambu biji, potong-potong. Satukan dengan air jeruk baby, proses dengan blender sampai halus. Saring.\r\n2. Tambahkan ASI/sufor, aduk rata.",
                "Energi : 65,5 kal\r\nProtein : 1,1 g\r\nLemak : 1,3 g\r\nKarbohidrat : 13,6 g",
                null,
                "https://img-global.cpcdn.com/recipes/2f2ab367146f64dc/751x532cq70/pure-jambu-biji-cemilan-mpasi-khonsa-6mo-foto-resep-utama.jpg",
                8000
            ),
            Recipe(
                "Pure Apel",
                "150 g daging buah apel merah\r\n100 ml air jeruk baby\r\n50 ml air matang/ASI perah/sufor",
                "1. Kupas apel, potong-potong daging buahnya. Satukan dengan air jeruk baby, proses dengan blunder sampai halus. Saring.\r\n2. Tambahkan air/ASI perah atau sufor. Aduk rata.",
                "Energi : 84 kal\r\nProtein : 0,8 g\r\nLemak : 1,3 g\r\nKarbohidrat : 19,1 g",
                null,
                "https://tap-assets-prod.dexecure.net/wp-content/uploads/sites/24/2016/03/resep-mpasi-dari-apel-1024x704.jpg",
                10000
            ),
            Recipe(
                "Pure Brokoli",
                "Air untuk merebus\r\n200 g brokoli, potong-potong\r\n50 ml air jeruk baby\r\n50 ml ASI perah/sufor",
                "1. Didihkan air, masukkan brokoli. Rebus brokoli sampai matang dan empuk. Angkat, tiriskan.\r\n2. Satukan brokoli dengan air jeruk baby, proses dengan blender sampai lumat. Saring.\r\n3. Tambahkan ASI/sufor, aduk rata.",
                "Energi : 56 kal\r\nProtein : 3,4 g\r\nLemak : 1,4 g\r\nKarbohidrat : 9,8 g",
                null,
                "https://blue.kumparan.com/image/upload/fl_progressive,fl_lossy,c_fill,q_auto:best,w_640/v1580963656/ko7tobdpt6fgmeubcrbq.jpg",
                15000
            ),
            Recipe(
                "Lapis Sanduk",
                "Singkong ukuran sedang - 75 g\r\nGaram - secukupnya\r\nDaun pisang - secukupnya\r\nGula merah - 25 g\r\nAir - secukupnya\r\nKelapa parut - secukupnya",
                "1. Parut singkong, campur dengan garam. Aduk hingga rata.\r\n2. bungkus singkong dengan daun singkong dan buat bentuk segitiga. Kukus hingga matang.\r\n3. Masak gula merah dengan air hingga larut, saring, Hidangkan kue dengan sirup gula merah dan kelapa parut.",
                "Energi : 204,4 kkal\r\nProtein : 1,1 g\r\nLemak : 1,9 g\r\nKarbohidrat : 47,4 g\r\nVitamin A : 704,0 g\r\nVitamin B6 : 0,2 mg\r\nAsam folat : 11,3 g\r\nKalsium : 109,9 mg\r\nZat besi : 1,2 mg",
                null,
                "https://bomanta.com/wp-content/uploads/2016/05/resep-kentang-pure-untuk-bayi-800x445.jpg",
                25000
            ),
            Recipe(
                "Terung Medan Bumbu Tauco",
                "Terung medan - 100 g\r\nTahu putih - 50 g\r\nTauco - 1/2 sdm\r\nMinyak goreng - secukupnya\r\nMinyak untuk menumis - 1 sdt",
                "1. Tumis udang kering bersama bumbu yang diiris hingga harum aromanya.\r\n2. Masukkan tahu dan terung medan. Tambahkan garam dan gula secukupnya.\r\n3. Masak sambil diaduk hingga matang. Hidangkan panas.",
                "Energi : 179,3 kkal\r\nProtein : 8,3 g\r\nLemak : 10,3 g\r\nKarbohidrat : 11,9 g\r\nVitamin A : 9,0 mg\r\nVitamin B6 : 0,2 mg\r\nAsam folat : 26,0 mg\r\nKalsium : 61,3 mg\r\nZat besi : 3,5 mg",
                "Udang kering - 1/2 sdt, haluskan\r\nBawang putih - 1/2 siung, iris tipis\r\nBawang merah - 1 siung, iris tipis\r\nCabai hijau - 2 bh, iris serong\r\nTomat - 1/2 bh, iris\r\nGaram dan gula - secukupnya",
                "https://i.ytimg.com/vi/NpAPJ1T3BHE/maxresdefault.jpg",
                20000
            ),
            Recipe(
                "Cah Kangkung Saus Tiram",
                "Kangkung - 75 g\r\nTomat - 1/2 bh",
                "1. Didihkan air, masukkan bawang merah, bawang putih, cabai merah, dan cabai rawit\r\n2. Masukkan kangkung dan tomat, tutup wajan. Masak hingga setengah matang.\r\n3. Masukkan saus tiram, garam, dan gula pasir, aduk rata.",
                "Energi : 44,7 kkal\r\nProtein : 3,9 g\r\nLemak : 0,5 g\r\nKarbohidrat : 8,4 g\r\nVitamin A : 459,0 g\r\nVitamin B6 : 0,2 mg\r\nAsam folat : 117,0 g\r\nKalsium : 113,5 mg\r\nZat besi : 1,9 mg",
                "Bawang merah - 3 siung\r\nBawang putih - 2 siung\r\nCabai merah - 1 bh\r\nCabai rawit - 5 bh\r\nSaus tiram, garam, dan gula pasir - secukupnya\r\nAir matang - 1/2 gls",
                "https://selerasa.com/wp-content/uploads/2015/07/images_sayuran_Tumis-kangkung-saus-tiram.jpg",
                15000
            ),
            Recipe(
                "Ikan Mas Goreng Kuning",
                "Ikan mas - 50 g\r\nAir jeruk nipis - secukupnya\r\nGaram - secukupnya",
                "1. Rendam ikan dengan bumbu halus, garam, dan air jeruk nipis.\r\n2. Diamkan sebentar sampai bumbu meresap.\r\n3. Goreng hingga cukup kering, hidangkan dengan sambal kecap.",
                "Energi : 101,0 kkal\r\nProtein : 8,0 g\r\nLemak : 7,5 g\r\nKarbohidrat : 0,0 g\r\nvitamin A : 3,0 g\r\nVitamin B6 : 0,1 mg\r\nAsam folat : 0,0 g\r\nKalsium : 18,0 mg\r\nZat besi : 0,6 mg",
                "Bawang putih - 1/2 sdt\r\nJahe - 1/4 sdt\r\nKunyit - secukupnya\r\nKetumbar - secukupnya",
                "https://img-global.cpcdn.com/recipes/6f0db9527e5290bd/751x532cq70/ikan-mas-goreng-bumbu-kuning-foto-resep-utama.jpg",
                20000
            ),
            Recipe(
                "Ayam Masak Kacang Polong",
                "Daging ayam - 1 ptg\r\nKacang polong segar - 2 1/2 sdm\r\nDaun bawang - 1/2 btg\r\nCabai merah - 1 bh\r\nKecap manis - 2 sdm\r\nKecap asin - 1/2 sdm\r\nBawang bombai cincang - 1 sdm\r\nBawang putih cincang - 1/2 sdm",
                "1. Panaskan minyak goreng, tumis bawang bombai dan bawang putih hingga harum.\r\n2. Tambahkan kecap manis, kecap asin, dan masukkan daging ayam.\r\n3. Aduk hingga tercampur rata dan ayam berubah warna.\r\n4. Masukkan kacang polong dan daun bawang, aduk kembali hingga semua bahan terserap bumbu.\r\n5. Setelah matang, angkat dan sajikan panas.",
                "Energi : 194,1 kkal\r\nProtein : 13,9 g\r\nLemak : 12,8 g\r\nKarbohidrat : 6,3 g\r\nVitamin A : 25,7 mg\r\nVitamin B6 : 0,1 mg\r\nAsam folat : 1,8 mg\r\nKalsium : 15,2 mg\r\nZat besi : 1,5 mg",
                null,
                "https://asset-a.grid.id/crop/0x0:0x0/700x465/photo/2019/03/05/422458934.jpg",
                45000
            )
        )
    }
}