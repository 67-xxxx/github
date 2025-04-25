<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','management_serial_number') || $check_field('add','management_serial_number') || $check_field('set','management_serial_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="管理序号" prop="management_serial_number">
												<el-input id="management_serial_number" v-model="form['management_serial_number']" placeholder="请输入管理序号"
							  v-if="user_group === '管理员' || (form['single_car_information_id'] && $check_field('set','management_serial_number')) || (!form['single_car_information_id'] && $check_field('add','management_serial_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','management_serial_number')">{{form['management_serial_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','name_of_single_vehicle') || $check_field('add','name_of_single_vehicle') || $check_field('set','name_of_single_vehicle')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="单车名称" prop="name_of_single_vehicle">
												<el-input id="name_of_single_vehicle" v-model="form['name_of_single_vehicle']" placeholder="请输入单车名称"
							  v-if="user_group === '管理员' || (form['single_car_information_id'] && $check_field('set','name_of_single_vehicle')) || (!form['single_car_information_id'] && $check_field('add','name_of_single_vehicle'))" :disabled="disabledObj['name_of_single_vehicle_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_single_vehicle')">{{form['name_of_single_vehicle']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','single_vehicle_type') || $check_field('add','single_vehicle_type') || $check_field('set','single_vehicle_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="单车类型" prop="single_vehicle_type">
								<el-select id="single_vehicle_type" v-model="form['single_vehicle_type']"
						v-if="user_group === '管理员' || (form['single_car_information_id'] && $check_field('set','single_vehicle_type')) || (!form['single_car_information_id'] && $check_field('add','single_vehicle_type'))">
						<el-option v-for="o in list_single_vehicle_type" :key="o['single_vehicle_type']" :label="o['single_vehicle_type']"
							:value="o['single_vehicle_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','single_vehicle_type')">{{form['single_vehicle_type']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','cover_image') || $check_field('add','cover_image') || $check_field('set','cover_image')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面图" prop="cover_image">
								<el-upload :disabled="disabledObj['cover_image_isDisabled']" id="cover_image" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover_image"
						:show-file-list="false" v-if="user_group === '管理员' || (form['single_car_information_id'] && $check_field('set','cover_image')) || (!form['single_car_information_id'] && $check_field('add','cover_image'))">
						<img v-if="form['cover_image']" :src="$fullUrl(form['cover_image'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover_image')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover_image'])" :preview-src-list="[$fullUrl(form['cover_image'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','rental_unit_price') || $check_field('add','rental_unit_price') || $check_field('set','rental_unit_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="租用单价" prop="rental_unit_price">
								<el-input-number id="rental_unit_price" v-model.number="form['rental_unit_price']"
						v-if="user_group === '管理员' || (form['single_car_information_id'] && $check_field('set','rental_unit_price')) || (!form['single_car_information_id'] && $check_field('add','rental_unit_price'))" :disabled="disabledObj['rental_unit_price_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','rental_unit_price')">{{form['rental_unit_price']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','number_of_single_vehicles') || $check_field('add','number_of_single_vehicles') || $check_field('set','number_of_single_vehicles')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="单车数量" prop="number_of_single_vehicles">
								<el-input-number id="number_of_single_vehicles" v-model.number="form['number_of_single_vehicles']"
						v-if="user_group === '管理员' || (form['single_car_information_id'] && $check_field('set','number_of_single_vehicles')) || (!form['single_car_information_id'] && $check_field('add','number_of_single_vehicles'))" :disabled="disabledObj['number_of_single_vehicles_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','number_of_single_vehicles')">{{form['number_of_single_vehicles']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','rental_standards') || $check_field('add','rental_standards') || $check_field('set','rental_standards')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="租用标准" prop="rental_standards">
								<el-select id="rental_standards" v-model="form['rental_standards']"
						v-if="user_group === '管理员' || (form['single_car_information_id'] && $check_field('set','rental_standards')) || (!form['single_car_information_id'] && $check_field('add','rental_standards'))">
						<el-option v-for="o in list_rental_standards" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','rental_standards')">{{form['rental_standards']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','owner_user') || $check_field('add','owner_user') || $check_field('set','owner_user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="车主用户" prop="owner_user">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_owner_user(form['owner_user']) }}
							<!--<el-input id="business_name" v-model="form['owner_user']" placeholder="请输入车主用户"-->
							<!--v-if="user_group === '管理员' || (form['single_car_information_id'] && $check_field('set','owner_user')) || (!form['single_car_information_id'] && $check_field('add','owner_user'))" :disabled="disabledObj['owner_user_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','owner_user')">{{form['owner_user']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['single_car_information_id'] && $check_field('set','owner_user')) || (!form['single_car_information_id'] && $check_field('add','owner_user'))" id="owner_user" v-model="form['owner_user']" :disabled="disabledObj['owner_user_isDisabled']">
								<el-option v-for="o in list_user_owner_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','owner_user')" id="owner_user" v-model="form['owner_user']" :disabled="true">
								<el-option v-for="o in list_user_owner_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="owner_user" v-model="form['owner_user']" :disabled="disabledObj['owner_user_isDisabled']">
							<el-option v-for="o in list_user_owner_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','single_car_details') || $check_field('add','single_car_details') || $check_field('set','single_car_details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="单车详情" prop="single_car_details">
					<quill-editor v-model.number="form['single_car_details']"
						v-if="user_group === '管理员' || (form['single_car_information_id'] && $check_field('set','single_car_details')) || (!form['single_car_information_id'] && $check_field('add','single_car_details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','single_car_details')" v-html="form['single_car_details']"></div>
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
				field: "single_car_information_id",
				url_add: "~/api/single_car_information/add?",
				url_set: "~/api/single_car_information/set?",
				url_get_obj: "~/api/single_car_information/get_obj?",
				url_upload: "~/api/single_car_information/upload?",

				query: {
					"single_car_information_id": 0,
				},

				form: {
								"management_serial_number": this.$get_stamp(), // 管理序号
										"name_of_single_vehicle":  '', // 单车名称
										"single_vehicle_type":  '', // 单车类型
										"cover_image":  '', // 封面图
										"rental_unit_price":  0, // 租用单价
										"number_of_single_vehicles":  0, // 单车数量
										"rental_standards":  '', // 租用标准
										"owner_user": 0, // 车主用户
										"single_car_details":  '', // 单车详情
											"single_car_information_id": 0, // ID
						
				},
				disabledObj:{
								"management_serial_number_isDisabled": false,
										"name_of_single_vehicle_isDisabled": false,
										"single_vehicle_type_isDisabled": false,
										"cover_image_isDisabled": false,
					          			"rental_unit_price_isDisabled": false,
					          			"number_of_single_vehicles_isDisabled": false,
										"rental_standards_isDisabled": false,
										"owner_user_isDisabled": false,
										"single_car_details_isDisabled": false,
										},

	
		
						// 单车类型选项列表
				list_single_vehicle_type: [""],
	
		
		
		
						// 租用标准选项列表
				list_rental_standards: ['按小时','按天'],
	
		
					// 用户列表
				list_user_owner_user: [],
						// 用户组
				group_user_owner_user: "",
				
	
			}
		},
		methods: {


	
	
			
	
			
				/**
			 * 获取单车类型列表
			 */
			async get_list_single_vehicle_type() {
				var json = await this.$get("~/api/single_vehicle_type/get_list?");
				if(json.result && json.result.list){
					this.list_single_vehicle_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
						/**
			 * 上传封面图
			 * @param {Object} param 图片参数
			 */
			upload_cover_image(param){
						this.uploadFile(param.file, "cover_image");
					},
	
	
			
	
			
	
			
	
			
	
				/**
			 * 获取车主用户用户列表
			 */
			async get_list_user_owner_user() {
                // if(this.user_group !== "管理员" && this.form["owner_user"] === 0) {
                //     this.form["owner_user"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=车主用户");
                if(json.result && json.result.list){
                    this.list_user_owner_user = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取车主用户用户组
			 */
			async get_group_user_owner_user() {
							this.form["owner_user"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=车主用户");
				if(json.result && json.result.obj){
					this.group_user_owner_user = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_owner_user(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_owner_user.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
									for (let key in _this.form) {
							arrForm.push(key)
						}
												_this.form["owner_user"] = id
									_this.disabledObj['owner_user' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "owner_user") {
			                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
			                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								  break;
								} else {
								  _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								}
							  }
							}
						  }
						}
					}
				});
			},
					get_user_owner_user(id){
				var obj = this.list_user_owner_user.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
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
					bl = this.$check_action('/single_car_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/single_car_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/single_car_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/single_car_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/single_car_information/view','get');
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
								this.get_list_single_vehicle_type();
															this.get_list_user_owner_user();
					this.get_group_user_owner_user();
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
