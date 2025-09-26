<script setup>
import { ref, onMounted } from 'vue'
import { getArticleList } from '@/api/article'

const articles = ref([])
const loading = ref(true)

// 模拟数据
const mockArticles = [
  {
    id: 1,
    title: '自然风光摄影技巧',
    summary: '探索如何捕捉自然风光的精髓，从构图到光线控制，全方位提升您的风景摄影水平。',
    cover: 'https://picsum.photos/id/10/800/400',
    publishDate: '2023-05-15',
    category: '摄影'
  },
  {
    id: 2,
    title: '健康饮食与生活方式',
    summary: '了解如何通过均衡饮食和适当运动，保持身体健康，提高生活质量。',
    cover: 'https://picsum.photos/id/20/800/400',
    publishDate: '2023-06-22',
    category: '健康'
  },
  {
    id: 3,
    title: '现代前端开发趋势',
    summary: '探讨2023年前端开发的最新趋势，包括框架、工具和最佳实践。',
    cover: 'https://picsum.photos/id/30/800/400',
    publishDate: '2023-07-10',
    category: '技术'
  },
  {
    id: 4,
    title: '旅行者的背包指南',
    summary: '为长途旅行打包的实用建议，让您的旅程更加轻松愉快。',
    cover: 'https://picsum.photos/id/40/800/400',
    publishDate: '2023-08-05',
    category: '旅行'
  },
  {
    id: 5,
    title: '园艺初学者指南',
    summary: '从选择合适的植物到日常护理，帮助您开始您的园艺之旅。',
    cover: 'https://picsum.photos/id/50/800/400',
    publishDate: '2023-09-12',
    category: '园艺'
  },
  {
    id: 6,
    title: '数字时代的阅读习惯',
    summary: '探讨数字技术如何改变我们的阅读方式，以及如何在信息爆炸的时代保持专注。',
    cover: 'https://picsum.photos/id/60/800/400',
    publishDate: '2023-10-18',
    category: '生活'
  }
]

const fetchArticles = async () => {
  try {
    loading.value = true
    // 实际项目中应该调用API
    const res = await getArticleList()
    console.log('获取文章列表:', res);
    
    // articles.value = res.data
    
    // 使用模拟数据
    setTimeout(() => {
      articles.value = mockArticles
      loading.value = false
    }, 500)
  } catch (error) {
    console.error('获取文章列表失败:', error)
    loading.value = false
  }
}

onMounted(() => {
  fetchArticles()
})
</script>

<template>
  <div class="articles-page">
    <h1>所有文章</h1>
    
    <div v-if="loading" class="loading">
      <p>加载中...</p>
    </div>
    
    <div v-else class="articles-list">
      <article v-for="article in articles" :key="article.id" class="article-item">
        <div class="article-image">
          <img :src="article.cover" :alt="article.title">
        </div>
        <div class="article-content">
          <div class="article-category">{{ article.category }}</div>
          <h2 class="article-title">
            <router-link :to="`/articles/${article.id}`">{{ article.title }}</router-link>
          </h2>
          <p class="article-summary">{{ article.summary }}</p>
          <div class="article-footer">
            <span class="article-date">{{ article.publishDate }}</span>
            <router-link :to="`/articles/${article.id}`" class="read-more">阅读更多</router-link>
          </div>
        </div>
      </article>
    </div>
  </div>
</template>

<style scoped>
.articles-page {
  max-width: 800px;
  margin: 0 auto;
}

.articles-page h1 {
  margin-bottom: 2rem;
  position: relative;
  padding-bottom: 0.5rem;
}

.articles-page h1::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 50px;
  height: 3px;
  background-color: var(--primary-color);
}

.loading {
  text-align: center;
  padding: 3rem 0;
}

.articles-list {
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

.article-item {
  display: flex;
  background: white;
  border-radius: var(--border-radius);
  overflow: hidden;
  box-shadow: var(--box-shadow);
}

.article-image {
  flex: 0 0 250px;
}

.article-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}

.article-content {
  flex: 1;
  padding: 1.5rem;
  display: flex;
  flex-direction: column;
}

.article-category {
  display: inline-block;
  background-color: rgba(76, 175, 80, 0.1);
  color: var(--primary-color);
  padding: 0.3rem 0.8rem;
  border-radius: 20px;
  font-size: 0.8rem;
  margin-bottom: 0.8rem;
  align-self: flex-start;
}

.article-title {
  font-size: 1.3rem;
  margin-bottom: 0.8rem;
}

.article-title a {
  color: var(--text-color);
  text-decoration: none;
}

.article-title a:hover {
  color: var(--primary-color);
}

.article-summary {
  color: #666;
  margin-bottom: 1rem;
  line-height: 1.5;
  flex-grow: 1;
}

.article-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-top: 1px solid #eee;
  padding-top: 1rem;
}

.article-date {
  color: #888;
  font-size: 0.9rem;
}

.read-more {
  color: var(--primary-color);
  font-weight: 500;
  font-size: 0.9rem;
}

@media (max-width: 768px) {
  .article-item {
    flex-direction: column;
  }
  
  .article-image {
    flex: 0 0 200px;
  }
}
</style>