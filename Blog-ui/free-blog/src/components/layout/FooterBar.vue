<script setup>
const currentYear = new Date().getFullYear()
</script>

<template>
  <footer class="footer">
    <div class="footer-content">
      <div class="footer-copyright">
        &copy; {{ currentYear }} 休闲小栈 - 自然清新的博客平台
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background-color: white;
  padding: 1.5rem 0;
  margin-top: 2rem;
  box-shadow: 0 -2px 4px rgba(0, 0, 0, 0.05);
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  text-align: center;
  padding: 0 2rem;
}

.footer-copyright {
  color: #666;
  font-size: 0.9rem;
}
</style>