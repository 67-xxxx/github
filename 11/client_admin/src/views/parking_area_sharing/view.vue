<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','area_name') || $check_field('add','area_name') || $check_field('set','area_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="区域名称" prop="area_name">
												<el-input id="area_name" v-model="form['area_name']" placeholder="请输入区域名称"
							  v-if="user_group === '管理员' || (form['parking_area_sharing_id'] && $check_field('set','area_name')) || (!form['parking_area_sharing_id'] && $check_field('add','area_name'))" :disabled="disabledObj['area_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','area_name')">{{form['area_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','cover') || $check_field('add','cover') || $check_field('set','cover')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面" prop="cover">
								<el-upload :disabled="disabledObj['cover_isDisabled']" id="cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['parking_area_sharing_id'] && $check_field('set','cover')) || (!form['parking_area_sharing_id'] && $check_field('add','cover'))">
						<img v-if="form['cover']" :src="$fullUrl(form['cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover'])" :preview-src-list="[$fullUrl(form['cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','site_area') || $check_field('add','site_area') || $check_field('set','site_area')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="场地面积" prop="site_area">
												<el-input id="site_area" v-model="form['site_area']" placeholder="请输入场地面积"
							  v-if="user_group === '管理员' || (form['parking_area_sharing_id'] && $check_field('set','site_area')) || (!form['parking_area_sharing_id'] && $check_field('add','site_area'))" :disabled="disabledObj['site_area_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','site_area')">{{form['site_area']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','number_of_vehicles_accommodated') || $check_field('add','number_of_vehicles_accommodated') || $check_field('set','number_of_vehicles_accommodated')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="容纳车辆数" prop="number_of_vehicles_accommodated">
								<el-input-number id="number_of_vehicles_accommodated" v-model.number="form['number_of_vehicles_accommodated']"
						v-if="user_group === '管理员' || (form['parking_area_sharing_id'] && $check_field('set','number_of_vehicles_accommodated')) || (!form['parking_area_sharing_id'] && $check_field('add','number_of_vehicles_accommodated'))" :disabled="disabledObj['number_of_vehicles_accommodated_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','number_of_vehicles_accommodated')">{{form['number_of_vehicles_accommodated']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','regional_address') || $check_field('add','regional_address') || $check_field('set','regional_address')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="区域地址" prop="regional_address">
												<el-input id="regional_address" v-model="form['regional_address']" placeholder="请输入区域地址"
							  v-if="user_group === '管理员' || (form['parking_area_sharing_id'] && $check_field('set','regional_address')) || (!form['parking_area_sharing_id'] && $check_field('add','regional_address'))" :disabled="disabledObj['regional_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','regional_address')">{{form['regional_address']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','details') || $check_field('add','details') || $check_field('set','details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="详情介绍" prop="details">
					<quill-editor v-model.number="form['details']"
						v-if="user_group === '管理员' || (form['parking_area_sharing_id'] && $check_field('set','details')) || (!form['parking_area_sharing_id'] && $check_field('add','details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','details')" v-html="form['details']"></div>
				</el-form-item>
			</el-col>
					
	
	
	
	
	
	
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "parking_area_sharing_id",
				url_add: "~/api/parking_area_sharing/add?",
				url_set: "~/api/parking_area_sharing/set?",
				url_get_obj: "~/api/parking_area_sharing/get_obj?",
				url_upload: "~/api/parking_area_sharing/upload?",

				query: {
					"parking_area_sharing_id": 0,
				},

				form: {
								"area_name":  '', // 区域名称
										"cover":  '', // 封面
										"site_area":  '', // 场地面积
										"number_of_vehicles_accommodated":  0, // 容纳车辆数
										"regional_address":  '', // 区域地址
										"details":  '', // 详情介绍
											"parking_area_sharing_id": 0, // ID
						
				},
				disabledObj:{
								"area_name_isDisabled": false,
										"cover_isDisabled": false,
										"site_area_isDisabled": false,
					          			"number_of_vehicles_accommodated_isDisabled": false,
										"regional_address_isDisabled": false,
										"details_isDisabled": false,
										},

	
		
		
		
		
		
	
			}
		},
		methods: {


	
	
						/**
			 * 上传封面
			 * @param {Object} param 图片参数
			 */
			upload_cover(param){
						this.uploadFile(param.file, "cover");
					},
	
	
			
	
			
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
														
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
																$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


																		

			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
				let msg = null
																								return msg;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/parking_area_sharing/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/parking_area_sharing/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/parking_area_sharing/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/parking_area_sharing/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/parking_area_sharing/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
														},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}




</style>
