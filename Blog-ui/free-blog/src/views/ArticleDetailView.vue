<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const article = ref(null)
const loading = ref(true)

// 模拟文章详情数据
const mockArticle = {
  id: 1,
  title: '自然风光摄影技巧',
  content: `
    <p>在数字摄影时代，捕捉自然风光的魅力变得前所未有的简单，但要拍摄出真正打动人心的风景照片，仍需要掌握一些基本技巧和艺术感知。</p>
    
    <h2>光线的魔力</h2>
    <p>自然光是风景摄影的灵魂。黄金时段（日出后和日落前的一小时）以及蓝色时段（日落后的短暂时间）通常能提供最柔和、最有戏剧性的光线。这些时段的光线能为风景增添温暖的色调和长长的阴影，创造出深度和纹理。</p>
    
    <h2>构图的艺术</h2>
    <p>优秀的构图能引导观者的视线穿过照片，创造视觉兴趣和平衡。考虑使用三分法则，寻找前景、中景和背景的元素，利用引导线（如道路、河流）来增强照片的深度感。</p>
    
    <h2>耐心与计划</h2>
    <p>伟大的风景摄影往往需要耐心等待完美的条件。研究你的拍摄地点，了解最佳的拍摄时间和角度。天气应用和日出日落计算器可以帮助你规划拍摄时间。</p>
    
    <h2>必备装备</h2>
    <p>虽然任何相机都能拍摄风景，但一些装备可以提升你的作品质量：</p>
    <ul>
      <li>三脚架：在弱光条件下保持稳定</li>
      <li>广角镜头：捕捉宽阔的风景</li>
      <li>偏振滤镜：减少反光，增强天空和水面的色彩</li>
      <li>中性密度滤镜：在明亮条件下实现长曝光</li>
    </ul>
    
    <h2>后期处理</h2>
    <p>适度的后期处理可以增强你的风景照片。调整对比度、饱和度和清晰度可以使照片更加生动，但要避免过度处理，保持自然的外观。</p>
    
    <p>记住，最重要的是享受过程，让自己沉浸在自然的美丽中。随着经验的积累，你会发展出自己独特的风格和视角。</p>
  `,
  publishDate: '2023-05-15',
  author: '李明',
  category: '摄影',
  tags: ['摄影', '自然', '技巧', '风景']
}

const fetchArticleDetail = async () => {
  try {
    loading.value = true
    const id = route.params.id
    
    // 实际项目中应该调用API
    // const res = await getArticleDetail(id)
    // article.value = res.data
    
    // 使用模拟数据
    setTimeout(() => {
      article.value = mockArticle
      loading.value = false
    }, 500)
  } catch (error) {
    console.error('获取文章详情失败:', error)
    loading.value = false
  }
}

onMounted(() => {
  fetchArticleDetail()
})
</script>

<template>
  <div class="article-detail">
    <div v-if="loading" class="loading">
      <p>加载中...</p>
    </div>
    
    <div v-else-if="article" class="article-container">
      <div class="article-header">
        <h1 class="article-title">{{ article.title }}</h1>
        <div class="article-meta">
          <span class="article-author">作者: {{ article.author }}</span>
          <span class="article-date">发布于: {{ article.publishDate }}</span>
          <span class="article-category">分类: {{ article.category }}</span>
        </div>
        <div class="article-tags">
          <span v-for="(tag, index) in article.tags" :key="index" class="tag">{{ tag }}</span>
        </div>
      </div>
      
      <div class="article-content" v-html="article.content"></div>
      
      <div class="article-footer">
        <router-link to="/" class="back-link">返回首页</router-link>
      </div>
    </div>
    
    <div v-else class="not-found">
      <p>文章不存在或已被删除</p>
      <router-link to="/" class="back-link">返回首页</router-link>
    </div>
  </div>
</template>

<style scoped>
.article-detail {
  max-width: 800px;
  margin: 0 auto;
}

.loading, .not-found {
  text-align: center;
  padding: 3rem 0;
}

.article-container {
  background: white;
  border-radius: var(--border-radius);
  box-shadow: var(--box-shadow);
  padding: 2rem;
}

.article-header {
  margin-bottom: 2rem;
  padding-bottom: 1.5rem;
  border-bottom: 1px solid #eee;
}

.article-title {
  font-size: 2rem;
  margin-bottom: 1rem;
  color: var(--text-color);
}

.article-meta {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  color: #666;
  margin-bottom: 1rem;
  font-size: 0.9rem;
}

.article-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
}

.tag {
  background-color: rgba(76, 175, 80, 0.1);
  color: var(--primary-color);
  padding: 0.3rem 0.8rem;
  border-radius: 20px;
  font-size: 0.8rem;
}

.article-content {
  line-height: 1.8;
  color: var(--text-color);
}

.article-content :deep(h2) {
  margin: 1.5rem 0 1rem;
  color: var(--primary-color);
}

.article-content :deep(p) {
  margin-bottom: 1rem;
}

.article-content :deep(ul) {
  margin-bottom: 1rem;
  padding-left: 1.5rem;
}

.article-content :deep(li) {
  margin-bottom: 0.5rem;
}

.article-footer {
  margin-top: 2rem;
  padding-top: 1.5rem;
  border-top: 1px solid #eee;
  text-align: center;
}

.back-link {
  display: inline-block;
  background-color: var(--primary-color);
  color: white;
  padding: 0.5rem 1.5rem;
  border-radius: var(--border-radius);
  text-decoration: none;
  transition: background-color 0.3s;
}

.back-link:hover {
  background-color: var(--secondary-color);
}

@media (max-width: 768px) {
  .article-container {
    padding: 1.5rem;
  }
  
  .article-title {
    font-size: 1.5rem;
  }
}
</style>