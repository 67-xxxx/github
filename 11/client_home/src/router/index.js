import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue'
import login from '../views/account/login.vue';

Vue.use(VueRouter)

const routes = [
	// 主页ss
	{
		path: '/',
		name: 'index',
		component: index
	},
	// 登录
	{
		path: '/account/login',
		name: 'login',
		component: login
	},
	// 忘记密码
	{
		path: '/account/forgot',
		name: 'forgot',
		component: () => import('../views/account/forgot.vue')
	},
	// 注册账号
	{
		path: '/account/register',
		name: 'register',
		component: () => import('../views/account/register.vue')
	},
	// 媒体图片
	{
		path: '/media/image',
		name: 'media_image',
		component: () => import('../views/media/image.vue')
	},
	// 音乐
	{
		path: '/media/music',
		name: 'media_music',
		component: () => import('../views/media/music.vue')
	},
	// 媒体视频
	{
		path: '/media/video',
		name: 'media_video',
		component: () => import('../views/media/video.vue')
	},
	// 文章路由
	{
		path: '/article/list',
		name: 'article_list',
		component: () => import('../views/article/list.vue')
	},
	{
		path: '/article/details',
		name: 'article_details',
		component: () => import('../views/article/details.vue')
	},
	// 浏览网站
	// 收藏路由
	{
		path: '/user/collect',
		name: 'collect_list',
		component: () => import('../views/user/collect.vue')
	},






	// 公告路由
	{
		path: '/notice/list',
		name: 'notice_list',
		component: () => import('../views/notice/list.vue')
	},
	{
		path: '/notice/details',
		name: 'notice_details',
		component: () => import('../views/notice/details.vue')
	},
	
	
		
	
		
		// 单车信息列表路由
	{
		path: '/single_car_information/list',
		name: '/single_car_information_list',
		component: () => import('../views/single_car_information/list.vue')
	},
	
		// 单车信息详情路由
	{
		path: '/single_car_information/details',
		name: '/single_car_information_details',
		component: () => import('../views/single_car_information/details.vue')
	},
			// 租赁信息添加路由
	{
		path: '/lease_information/edit',
		name: '/lease_information_edit',
		component: () => import('../views/lease_information/edit.vue')
	},
	
	
		
	
		
		// 停车区域列表路由
	{
		path: '/parking_area_sharing/list',
		name: '/parking_area_sharing_list',
		component: () => import('../views/parking_area_sharing/list.vue')
	},
	
		// 停车区域详情路由
	{
		path: '/parking_area_sharing/details',
		name: '/parking_area_sharing_details',
		component: () => import('../views/parking_area_sharing/details.vue')
	},
		
		// 充电信息列表路由
	{
		path: '/charging_information/list',
		name: '/charging_information_list',
		component: () => import('../views/charging_information/list.vue')
	},
	
		// 充电信息详情路由
	{
		path: '/charging_information/details',
		name: '/charging_information_details',
		component: () => import('../views/charging_information/details.vue')
	},
		
	
	
	// 用户路由
	{
		path: '/user/index',
		name: 'user_index',
		component: () => import('../views/user/index.vue')
	},
	// 基本信息
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue')
	},
	// 找回密码
	{
		path: '/user/password',
		name: 'user_password',
		component: () => import('../views/user/password.vue')
	},

	// 搜索
	{
		path: '/search',
		name: 'search',
		component: () => import('../views/search/index.vue')
	},
	// 局部搜索
	{
		path: '/search/details',
		name: 'search_details',
		component: () => import('../views/search/details.vue')
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.afterEach((to, from, next) => {
	let title = "校园单车管理系统-home";
	document.title = title;
	document.logo = "校园单车管理系统"
})

export default router
