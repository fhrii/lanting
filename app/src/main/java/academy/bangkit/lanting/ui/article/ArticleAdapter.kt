package academy.bangkit.lanting.ui.article

import academy.bangkit.lanting.data.model.Article
import academy.bangkit.lanting.databinding.ItemArticleRowBinding
import academy.bangkit.lanting.utils.setImageFromUrl
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    private val articles = ArrayList<Article>()

    fun clearArticle() {
        this.articles.clear()
        notifyDataSetChanged()
    }

    fun setArticles(articles: List<Article>) {
        clearArticle()
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(private val binding: ItemArticleRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvDesc.text = article.date
            binding.imgThumbnail.setImageFromUrl(article.thumbnail)

            itemView.setOnClickListener {
                val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                itemView.context.startActivity(mIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ItemArticleRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size
}