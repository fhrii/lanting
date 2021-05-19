package academy.bangkit.lanting.ui.article

import academy.bangkit.lanting.databinding.ActivityArticleBinding
import academy.bangkit.lanting.utils.DummyData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding
    private lateinit var adapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        adapter = ArticleAdapter()
        setContentView(binding.root)

        binding.rvArticle.adapter = adapter
        binding.rvArticle.layoutManager = LinearLayoutManager(this)
        binding.rvArticle.setHasFixedSize(true)

        binding.btnBack.setOnClickListener {
            finish()
        }

        adapter.setArticles(DummyData.getArticles())
    }
}