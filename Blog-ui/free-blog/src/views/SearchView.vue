<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { searchArticles } from '@/api/article'

const route = useRoute()
const searchQuery = ref('')
const searchResults = ref([])
const loading = ref(false)

// 模拟搜索结果数据
const mockSearchResults = [
  {
    id: 1,
    title: '自然风光摄影技巧',
    summary: '探索如何捕捉自然风光的精髓，从构图到光线控制，全方位提升您的风景摄影水平。',
    publishDate: '2023-05-15',
    category: '摄影'
  },
  {
    id: 3,
    title: '现代前端开发趋势',
    summary: '探讨2023年前端开发的最新趋势，包括框架、工具和最佳实践。',
    publishDate: '2023-07-10',
    category: '技术'
  }
]

const performSearch = async (query) => {
  if (!query) return
  
  try {
    loading.value = true
    searchQuery.value = query
    
    // 实际项目中应该调用API
    // const res = await searchArticles(query)
    // searchResults.value = res.data
    
    // 使用模拟数据
    setTimeout(() => {
      searchResults.value = mockSearchResults
      loading.value = false
    }, 500)
  } catch (error) {
    console.error('搜索失败:', error)
    loading.value = false
  }
}

onMounted(() => {
  const query = route.query.q
  if (query) {
    performSearch(query)
  }
})
</script>

<template>
  <div class="search-page">
    <div class="search-header">
      <h1>搜索结果</h1>
      <div class="search-form">
        <input 
          type="text" 
          v-model="searchQuery" 
          placeholder="搜索文章..." 
          @keyup.enter="performSearch(searchQuery)"
        />
        <button @click="performSearch(searchQuery)">搜索</button>
      </div>
    </div>
    
    <div v-if="loading" class="loading">
      <p>搜索中...</p>
    </div>
    
    <div v-else-if="searchResults.length > 0" class="search-results">
      <p class="results-count">找到 {{ searchResults.length }} 条结果</p>
      
      <div class="result-list">
        <div v-for="result in searchResults" :key="result.id" class="result-item">
          <h3 class="result-title">
            <router-link :to="`/articles/${result.id}`">{{ result.title }}</router-link>
          </h3>
          <p class="result-summary">{{ result.summary }}</p>
          <div class="result-meta">
            <span class="result-category">{{ result.category }}</span>
            <span class="result-date">{{ result.publishDate }}</span>
          </div>
        </div>
      </div>
    </div>
    
    <div v-else-if="searchQuery" class="no-results">
      <p>没有找到与 "{{ searchQuery }}" 相关的结果</p>
    </div>
  </div>
</template>

<style scoped>
.search-page {
  max-width: 800px;
  margin: 0 auto;
}

.search-header {
  margin-bottom: 2rem;
}

.search-header h1 {
  margin-bottom: 1rem;
}

.search-form {
  display: flex;
  gap: 0.5rem;
}

.search-form input {
  flex: 1;
}

.loading, .no-results {
  text-align: center;
  padding: 2rem 0;
}

.results-count {
  margin-bottom: 1.5rem;
  color: #666;
}

.result-list {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.result-item {
  background: white;
  border-radius: var(--border-radius);
  box-shadow: var(--box-shadow);
  padding: 1.5rem;
  transition: transform 0.3s;
}

.result-item:hover {
  transform: translateY(-3px);
}

.result-title {
  margin-bottom: 0.8rem;
}

.result-title a {
  color: var(--text-color);
  text-decoration: none;
}

.result-title a:hover {
  color: var(--primary-color);
}

.result-summary {
  color: #666;
  margin-bottom: 1rem;
}

.result-meta {
  display: flex;
  justify-content: space-between;
  color: #888;
  font-size: 0.9rem;
}

.result-category {
  background-color: rgba(76, 175, 80, 0.1);
  color: var(--primary-color);
  padding: 0.2rem 0.6rem;
  border-radius: 20px;
}
</style>