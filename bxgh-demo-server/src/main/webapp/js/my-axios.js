const myAxios = axios.create({
    baseURL : 'http://localhost:8888/jd_demo_server_war_exploded',
    timeout: 100000,
    withCredentials:true,
})