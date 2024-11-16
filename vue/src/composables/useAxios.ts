import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios'
import { ref } from 'vue'

// Define the type for the response data (generic so it can be any data type)
export interface ApiResponse<T> {
  data: T
  status: number
  message?: string
}

const useAxios = () => {
  // State to hold loading state and errors
  const loading = ref(false)
  const error = ref<string | null>(null)

  // Create an Axios instance with base URL
  const axiosInstance: AxiosInstance = axios.create({
    //baseURL: 'https://api.example.com', // Replace with your API's base URL
    headers: {
      'Content-Type': 'application/json',
    },
  })

  // Wrapper function for POST requests
  const post = async <T>(url: string, data: any, config: AxiosRequestConfig = {}): Promise<ApiResponse<T>> => {
    loading.value = true
    error.value = null
    try {
      const response: AxiosResponse<T> = await axiosInstance.post(url, data, config)
      return { data: response.data, status: response.status }
    } catch (err) {
      error.value = err instanceof Error ? err.message : 'An error occurred'
      throw error.value
    } finally {
      loading.value = false
    }
  }

  // Wrapper function for GET requests
  const get = async <T>(url: string, config: AxiosRequestConfig = {}): Promise<ApiResponse<T>> => {
    loading.value = true
    error.value = null
    try {
      const response: AxiosResponse<T> = await axiosInstance.get(url, config)
      return { data: response.data, status: response.status }
    } catch (err) {
      error.value = err instanceof Error ? err.message : 'An error occurred'
      throw error.value
    } finally {
      loading.value = false
    }
  }  

  // Wrapper function for PATCH requests
  const patch = async <T>(url: string, data: any, config: AxiosRequestConfig = {}): Promise<ApiResponse<T>> => {
    loading.value = true
    error.value = null
    try {
      const patchConfig = {...config,...{headers:{'Content-Type':'application/json-patch+json'}}}
      const response: AxiosResponse<T> = await axiosInstance.patch(url,data,patchConfig)
      return { data: response.data, status: response.status }
    } catch (err) {
      error.value = err instanceof Error ? err.message : 'An error occurred'
      throw error.value
    } finally {
      loading.value = false
    }
  }

  // Wrapper function for DELETE requests
  const remove = async <T>(url: string, config: AxiosRequestConfig = {}): Promise<ApiResponse<T>> => {
    loading.value = true
    error.value = null
    try {
      const response: AxiosResponse<T> = await axiosInstance.delete(url, config)
      return { data: response.data, status: response.status }
    } catch (err) {
      error.value = err instanceof Error ? err.message : 'An error occurred'
      throw error.value
    } finally {
      loading.value = false
    }
  } 

  // You can add more methods like PUT, DELETE, etc., in the same pattern

  return {
    loading,
    error,
    get,
    post,
    patch,
    remove,
    // Optionally, you can expose axiosInstance for more customized requests
    axiosInstance,
  }
}

export default useAxios

/*
// Add a request interceptor
axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)
*/