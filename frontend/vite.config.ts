import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      "/api": {
        target: "http://thehive:8080",
        changeOrigin: true,
      },
      "/images": {
        target: "http://thehive:8080",
        changeOrigin: true,
      }
    },
    allowedHosts: ['thehive.forum']
  },
});
