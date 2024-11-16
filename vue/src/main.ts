import { createApp } from 'vue'
import CarbonVue3 from '@carbon/vue';

import "./styles/carbon-g90.css";
import "./styles/tailwind.css";
import './style.css'
import App from './App.vue'
import router from './router'

createApp(App)
.use(router)
.use(CarbonVue3)
.mount('#app')
