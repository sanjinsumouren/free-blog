<script setup>
// 关于页面不需要特殊逻辑
</script>

<template>
  <div class="about-page">
    <h1>关于我们</h1>
    
    <div class="about-content">
      <section class="about-section">
        <h2>休闲小栈简介</h2>
        <p>休闲小栈是一个专注于内容创作和分享的博客平台，致力于为用户提供简洁、高效、美观的写作和阅读体验。我们相信，优质的内容需要优雅的呈现方式，休闲小栈正是为此而生。</p>
      </section>
      
      <section class="about-section">
        <h2>我们的理念</h2>
        <p>在信息爆炸的时代，我们希望回归内容本身的价值。FreeBlog 提倡：</p>
        <ul>
          <li>专注于内容质量，而非数量</li>
          <li>简洁而不简单的用户体验</li>
          <li>尊重创作者的版权和劳动成果</li>
          <li>构建友善、积极的内容社区</li>
        </ul>
      </section>
      
      <section class="about-section">
        <h2>联系我们</h2>
        <p>如果您有任何问题、建议或合作意向，欢迎通过以下方式联系我们：</p>
        <p><strong>邮箱：</strong> 1255926530@qq.com</p>
        <p><strong>github：<a href="https://github.com/sanjinsumouren?tab=repositories" target="_blank">https://github.com/1255926530</a></strong></p>
      </section>
    </div>
  </div>
</template>

<style scoped>
.about-page {
  max-width: 800px;
  margin: 0 auto;
  padding: 2rem 1rem;
}

.about-page h1 {
  margin-bottom: 2rem;
  position: relative;
  padding-bottom: 0.5rem;
}

.about-page h1::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 50px;
  height: 3px;
  background-color: var(--primary-color);
}

.about-content {
  background: white;
  border-radius: var(--border-radius);
  padding: 2rem;
  box-shadow: var(--box-shadow);
}

.about-section {
  margin-bottom: 2rem;
}

.about-section:last-child {
  margin-bottom: 0;
}

.about-section h2 {
  color: var(--primary-color);
  margin-bottom: 1rem;
  font-size: 1.5rem;
}

.about-section p {
  line-height: 1.6;
  color: #444;
  margin-bottom: 1rem;
}

.about-section ul {
  padding-left: 1.5rem;
  margin-bottom: 1rem;
}

.about-section ul li {
  margin-bottom: 0.5rem;
  line-height: 1.6;
  color: #444;
}
</style>