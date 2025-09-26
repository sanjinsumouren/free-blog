<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const categories = ref([])
const loading = ref(true)

// 模拟分类数据
const mockCategories = [
  { id: 1, name: '摄影', count: 2, cover: 'https://picsum.photos/id/10/800/400' },
  { id: 2, name: '健康', count: 2, cover: 'https://picsum.photos/id/20/800/400' },
  { id: 3, name: '技术', count: 2, cover: 'https://picsum.photos/id/30/800/400' },
  { id: 4, name: '旅行', count: 1, cover: 'https://picsum.photos/id/40/800/400' },
  { id: 5, name: '园艺', count: 1, cover: 'https://picsum.photos/id/50/800/400' },
  { id: 6, name: '生活', count: 1, cover: 'https://picsum.photos/id/60/800/400' }
]

const fetchCategories = () => {
  loading.value = true
  
  // 模拟API请求
  setTimeout(() => {
    categories.value = mockCategories
    loading.value = false
  }, 500)
}

const navigateToCategory = (category) => {
  router.push(`/category/${category.name}`)
}

onMounted(() => {
  fetchCategories()
})
</script>

<template>
  <div class="categories-page">
    <h1>文章分类</h1>
    
    <div v-if="loading" class="loading">
      <p>加载中...</p>
    </div>
    
    <div v-else class="categories-grid">
      <div 
        v-for="category in categories" 
        :key="category.id" 
        class="category-card"
        @click="navigateToCategory(category)"
      >
        <div class="category-image">
          <img :src="category.cover" :alt="category.name">
        </div>
        <div class="category-content">
          <h2 class="category-name">{{ category.name }}</h2>
          <p class="category-count">{{ category.count }} 篇文章</p>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.categories-page {
  max-width: 1000px;
  margin: 0 auto;
  padding: 2rem 1rem;
}

.categories-page h1 {
  margin-bottom: 2rem;
  position: relative;
  padding-bottom: 0.5rem;
}

.categories-page h1::after {
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

.categories-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 2rem;
}

.category-card {
  background: white;
  border-radius: var(--border-radius);
  overflow: hidden;
  box-shadow: var(--box-shadow);
  cursor: pointer;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.category-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
}

.category-image {
  height: 180px;
  overflow: hidden;
}

.category-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.5s ease;
}

.category-card:hover .category-image img {
  transform: scale(1.05);
}

.category-content {
  padding: 1.5rem;
}

.category-name {
  font-size: 1.3rem;
  margin-bottom: 0.5rem;
  color: var(--text-color);
}

.category-count {
  color: #666;
  font-size: 0.9rem;
}

@media (max-width: 768px) {
  .categories-grid {
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    gap: 1.5rem;
  }
}
</style>