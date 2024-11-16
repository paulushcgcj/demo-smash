import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

// Import your components
import HomeView from '@/views/HomeView.vue'
import CustomerListView from '@/views/CustomerListView.vue'
import NotFoundView from '@/views/NotFoundView.vue'
import CustomerFormView from '@/views/CustomerFormView.vue'

// Define your routes
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: HomeView,
  },
  {
    path: '/customers',
    name: 'Customers',
    component: CustomerListView,
  },
  {
    path: '/customers/:id',
    name: 'Customer',
    component: CustomerFormView,
    props: true,
  },
  {
    path: '/:pathMatch(.*)*', // Catch-all route for 404
    name: 'Not found',
    component: NotFoundView,
  },
]

// Create the router instance
const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
