<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const searchQuery = ref('')
const navItems = ref([
  { id: 1, name: '首页', path: '/' },
  { id: 2, name: '文章', path: '/articles' },
  { id: 3, name: '分类', path: '/categories' },
  { id: 4, name: '关于', path: '/about' }
])

// 可以从后端获取导航项
const fetchNavItems = async () => {
  try {
    // 实际项目中，这里应该调用API获取导航项
    // const response = await fetch('/api/nav-items')
    // navItems.value = await response.json()
  } catch (error) {
    console.error('获取导航项失败:', error)
  }
}

const handleSearch = () => {
  if (searchQuery.value.trim()) {
    router.push({ 
      path: '/search', 
      query: { q: searchQuery.value } 
    })
    searchQuery.value = ''
  }
}

onMounted(() => {
  fetchNavItems()
})
</script>

<template>
  <nav class="navbar">
    <div class="navbar-container">
      <div class="navbar-logo">
        <router-link to="/">休闲小栈</router-link>
      </div>
      
      <div class="navbar-links">
        <ul>
          <li v-for="item in navItems" :key="item.id">
            <router-link :to="item.path">{{ item.name }}</router-link>
          </li>
        </ul>
      </div>
      
      <div class="navbar-search">
        <form @submit.prevent="handleSearch">
          <input 
            type="text" 
            v-model="searchQuery" 
            placeholder="搜索文章..." 
            aria-label="搜索"
          />
          <button type="submit">
            搜索
          </button>
        </form>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.navbar {
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 100;
}

.navbar-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  max-width: 1200px;
  margin: 0 auto;
}

.navbar-logo a {
  font-size: 1.5rem;
  font-weight: bold;
  color: var(--primary-color);
}

.navbar-links ul {
  display: flex;
  list-style: none;
  gap: 1.5rem;
}

.navbar-links a {
  color: var(--text-color);
  font-weight: 500;
  padding: 0.5rem;
  border-radius: var(--border-radius);
}

.navbar-links a:hover,
.navbar-links a.router-link-active {
  color: var(--primary-color);
  background-color: rgba(76, 175, 80, 0.1);
}

.navbar-search form {
  display: flex;
  gap: 0.5rem;
}

.navbar-search input {
  padding: 0.5rem 1rem;
  border-radius: var(--border-radius);
  border: 1px solid #ddd;
  width: 200px;
  margin-bottom: 0;
}

.navbar-search button {
  padding: 0.5rem 1rem;
}

@media (max-width: 768px) {
  .navbar-container {
    flex-direction: column;
    gap: 1rem;
  }
  
  .navbar-links ul {
    flex-wrap: wrap;
    justify-content: center;
  }
  
  .navbar-search {
    width: 100%;
  }
  
  .navbar-search input {
    width: 100%;
  }
}
</style>