# JChatMind 运行环境指南

> 本文档列出运行 JChatMind 项目所需的全部环境依赖及配置方法。

---

## 一、必需环境

### 1. JDK 17

项目基于 Spring Boot 3.5.8 + Spring AI，**必须使用 JDK 17**。

```bash
# 使用 SDKMAN 安装
sdk install java 17.0.13-tem
sdk default java 17.0.13-tem

# 验证
java -version
```

### 2. Maven

后端构建工具。项目自带 Maven Wrapper（`mvnw`），也可不全局安装。

```bash
# 可选：全局安装
sdk install maven 3.9.9

# 或者直接使用项目自带 wrapper（无需额外安装）
./mvnw spring-boot:run
```

### 3. PostgreSQL 17 + pgvector 扩展

数据库使用 PostgreSQL，RAG 功能依赖 **pgvector** 扩展（用于向量存储）。

```bash
# macOS 安装 PostgreSQL
brew install postgresql@17
brew services start postgresql@17

# 安装 pgvector 扩展
brew install pgvector
```

**数据库初始化：**

```bash
# 创建数据库
createdb jchatmind

# 启用 pgvector 扩展
psql jchatmind
CREATE EXTENSION IF NOT EXISTS vector;
\q
```

**数据库连接信息**（配置文件：`jchatmind/src/main/resources/application.yaml`）：

| 配置项 | 值 |
|--------|-----|
| 地址 | `localhost:5432` |
| 数据库名 | `jchatmind` |
| 用户名 | `postgres` |
| 密码 | `123456` |

### 4. Node.js 18+

前端 UI 使用 React 19 + Vite + TypeScript。

```bash
# 安装 Node.js
brew install node

# 验证版本（建议 18+）
node -v
npm -v

# 安装前端依赖
cd ui
npm install
```

---

## 二、API Key 配置

在 `jchatmind/src/main/resources/application.yaml` 中替换以下占位符：

| 配置项 | 说明 | 是否必需 |
|--------|------|----------|
| `spring.ai.deepseek.api-key` | [DeepSeek API Key](https://platform.deepseek.com/)（AI 对话） | **必需** |
| `spring.ai.zhipuai.api-key` | [智谱 AI API Key](https://open.bigmodel.cn/)（GLM 模型） | **必需** |
| `spring.mail.username` | QQ 邮箱地址（邮件发送） | 可选 |
| `spring.mail.password` | QQ 邮箱授权码 | 可选 |

---

## 三、可选工具

| 工具 | 用途 | 安装命令 |
|------|------|----------|
| Gradle | 备选构建工具 | `sdk install gradle 8.11` |
| DBeaver | 数据库可视化管理 | `brew install --cask dbeaver-community` |
| pgAdmin | PostgreSQL 图形化管理 | `brew install --cask pgadmin4` |

---

## 四、启动项目

### 启动顺序

```bash
# 1. 确保 PostgreSQL 已启动
brew services start postgresql@17

# 2. 启动后端服务（默认端口 8080）
cd jchatmind
./mvnw spring-boot:run

# 3. 启动前端开发服务器（新开终端，默认端口 5173）
cd ui
npm run dev
```

### 验证启动成功

- 后端：访问 `http://localhost:8080`
- 前端：访问 `http://localhost:5173`

---

## 五、环境检查清单

在启动项目前，确认以下环境已就绪：

- [ ] JDK 17 已安装（`java -version`）
- [ ] Maven 已安装或使用 mvnw
- [ ] PostgreSQL 17 已安装并启动
- [ ] pgvector 扩展已安装并启用
- [ ] `jchatmind` 数据库已创建
- [ ] Node.js 18+ 已安装（`node -v`）
- [ ] 前端依赖已安装（`npm install`）
- [ ] DeepSeek API Key 已配置
- [ ] 智谱 AI API Key 已配置

---

## 六、常见问题

### Q: `java: command not found` 或版本不对

```bash
# 检查当前 Java 版本
java -version

# 使用 SDKMAN 切换
sdk use java 17.0.13-tem
sdk default java 17.0.13-tem
```

### Q: PostgreSQL 连接失败

```bash
# 检查 PostgreSQL 是否在运行
brew services list | grep postgresql

# 启动服务
brew services start postgresql@17

# 检查数据库是否存在
psql -l | grep jchatmind
```

### Q: 前端 npm install 报错

```bash
# 清除缓存重装
cd ui
rm -rf node_modules package-lock.json
npm install
```

### Q: pgvector 扩展找不到

```bash
# 重新安装 pgvector
brew install pgvector

# 在数据库中重新启用
psql jchatmind -c "CREATE EXTENSION IF NOT EXISTS vector;"
```
