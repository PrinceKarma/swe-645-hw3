import axios from 'axios';

const api = axios.create({
    baseURL: '/', // The proxy will handle the backend URL
});

export default api;