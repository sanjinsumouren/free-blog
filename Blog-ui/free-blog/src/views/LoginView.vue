<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const username = ref('')
const password = ref('')
const rememberMe = ref(false)
const loginError = ref('')
const isLoading = ref(false)

const handleLogin = async () => {
  // 清除之前的错误信息
  loginError.value = ''

  // 表单验证
  if (!username.value.trim()) {
    loginError.value = '请输入用户名'
    return
  }

  if (!password.value) {
    loginError.value = '请输入密码'
    return
  }

  // 显示加载状态
  isLoading.value = true

  try {
    // 调用后端登录接口
    const response = await axios.post('/api/login', {
      userName: username.value,
      userPassword: password.value,
    })

    if (response.data.code === 200) {
      // 登录成功
      const { id, userName, token } = response.data.data
      localStorage.setItem('isLoggedIn', 'true')
      localStorage.setItem('userId', id)
      localStorage.setItem('userName', userName)
      localStorage.setItem('token', token)

      if (rememberMe.value) {
        localStorage.setItem('username', username.value)
      } else {
        localStorage.removeItem('username')
      }

      // 跳转到首页
      window.location.href = '/'
    } else {
      // 登录失败
      loginError.value = response.data.msg || '登录失败'
    }
  } catch (error) {
    // 网络错误或其他异常
    loginError.value = '登录请求失败，请稍后重试'
    console.error('Login error:', error)
  } finally {
    isLoading.value = false
  }
}

// 检查是否有保存的用户名
onMounted(() => {
  const savedUsername = localStorage.getItem('username')
  if (savedUsername) {
    username.value = savedUsername
    rememberMe.value = true
  }
})
</script>

<template>
  <div class="login-page">
    <div class="login-container">
      <h1>登录</h1>

      <div v-if="loginError" class="error-message">
        {{ loginError }}
      </div>

      <form @submit.prevent="handleLogin" class="login-form">
        <div class="form-group">
          <label for="username">用户名</label>
          <input
            type="text"
            id="username"
            v-model="username"
            placeholder="请输入用户名"
            :disabled="isLoading"
          />
        </div>

        <div class="form-group">
          <label for="password">密码</label>
          <input
            type="password"
            id="password"
            v-model="password"
            placeholder="请输入密码"
            :disabled="isLoading"
          />
        </div>

        <div class="form-options">
          <label class="remember-me">
            <input type="checkbox" v-model="rememberMe" :disabled="isLoading" />
            <span style="writing-mode: horizontal-tb">记住我</span>
          </label>
          <a href="#" class="forgot-password">忘记密码?</a>
        </div>

        <button type="submit" class="login-button" :disabled="isLoading">
          <span v-if="isLoading">登录中...</span>
          <span v-else>登录</span>
        </button>
      </form>

      <div class="register-link">还没有账号? <a href="#/register">立即注册</a></div>
    </div>
  </div>
</template>

<style scoped>
.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f5f7fa;
  padding: 20px;
}

.login-container {
  width: 100%;
  max-width: 400px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  padding: 30px;
}

h1 {
  text-align: center;
  margin-bottom: 24px;
  color: #333;
  font-size: 24px;
}

.error-message {
  background-color: #ffebee;
  color: #d32f2f;
  padding: 10px;
  border-radius: 4px;
  margin-bottom: 20px;
  font-size: 14px;
}

.login-form {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 6px;
  font-size: 14px;
  color: #555;
}

input[type='text'],
input[type='password'] {
  width: 100%;
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 16px;
  transition: border-color 0.3s;
}

input[type='text']:focus,
input[type='password']:focus {
  border-color: #4caf50;
  outline: none;
}

.form-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  font-size: 14px;
}

.remember-me {
  display: flex;
  align-items: center;
  cursor: pointer;
  flex-direction: row;
  line-height: 1;
}

.remember-me span {
  display: inline-block;
  white-space: nowrap;
  text-orientation: mixed;
  writing-mode: horizontal-tb;
  vertical-align: middle;
  line-height: normal;
}

.remember-me input {
  margin-right: 6px;
  vertical-align: middle;
  margin-top: 0;
  margin-bottom: 0;
}

.forgot-password {
  color: #4caf50;
  text-decoration: none;
}

.forgot-password:hover {
  text-decoration: underline;
}

.login-button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 12px;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.login-button:hover {
  background-color: #388e3c;
}

.login-button:disabled {
  background-color: #9e9e9e;
  cursor: not-allowed;
}

.register-link {
  text-align: center;
  margin-top: 20px;
  font-size: 14px;
  color: #666;
}

.register-link a {
  color: #4caf50;
  text-decoration: none;
}

.register-link a:hover {
  text-decoration: underline;
}
</style>
