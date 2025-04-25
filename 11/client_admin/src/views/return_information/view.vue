<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','lease_number') || $check_field('add','lease_number') || $check_field('set','lease_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="租用号" prop="lease_number">
												<el-input id="lease_number" v-model="form['lease_number']" placeholder="请输入租用号"
							  v-if="user_group === '管理员' || (form['return_information_id'] && $check_field('set','lease_number')) || (!form['return_information_id'] && $check_field('add','lease_number'))" :disabled="disabledObj['lease_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','lease_number')">{{form['lease_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','management_serial_number') || $check_field('add','management_serial_number') || $check_field('set','management_serial_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="管理序号" prop="management_serial_number">
												<el-input id="management_serial_number" v-model="form['management_serial_number']" placeholder="请输入管理序号"
							  v-if="user_group === '管理员' || (form['return_information_id'] && $check_field('set','management_serial_number')) || (!form['return_information_id'] && $check_field('add','management_serial_number'))" :disabled="disabledObj['management_serial_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','management_serial_number')">{{form['management_serial_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','name_of_single_vehicle') || $check_field('add','name_of_single_vehicle') || $check_field('set','name_of_single_vehicle')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="单车名称" prop="name_of_single_vehicle">
												<el-input id="name_of_single_vehicle" v-model="form['name_of_single_vehicle']" placeholder="请输入单车名称"
							  v-if="user_group === '管理员' || (form['return_information_id'] && $check_field('set','name_of_single_vehicle')) || (!form['return_information_id'] && $check_field('add','name_of_single_vehicle'))" :disabled="disabledObj['name_of_single_vehicle_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_single_vehicle')">{{form['name_of_single_vehicle']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','single_vehicle_type') || $check_field('add','single_vehicle_type') || $check_field('set','single_vehicle_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="单车类型" prop="single_vehicle_type">
												<el-input id="single_vehicle_type" v-model="form['single_vehicle_type']" placeholder="请输入单车类型"
							  v-if="user_group === '管理员' || (form['return_information_id'] && $check_field('set','single_vehicle_type')) || (!form['return_information_id'] && $check_field('add','single_vehicle_type'))" :disabled="disabledObj['single_vehicle_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','single_vehicle_type')">{{form['single_vehicle_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','rental_unit_price') || $check_field('add','rental_unit_price') || $check_field('set','rental_unit_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="租用单价" prop="rental_unit_price">
								<el-input-number id="rental_unit_price" v-model.number="form['rental_unit_price']"
						v-if="user_group === '管理员' || (form['return_information_id'] && $check_field('set','rental_unit_price')) || (!form['return_information_id'] && $check_field('add','rental_unit_price'))" :disabled="disabledObj['rental_unit_price_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','rental_unit_price')">{{form['rental_unit_price']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','rental_standards') || $check_field('add','rental_standards') || $check_field('set','rental_standards')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="租用标准" prop="rental_standards">
												<el-input id="rental_standards" v-model="form['rental_standards']" placeholder="请输入租用标准"
							  v-if="user_group === '管理员' || (form['return_information_id'] && $check_field('set','rental_standards')) || (!form['return_information_id'] && $check_field('add','rental_standards'))" :disabled="disabledObj['rental_standards_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','rental_standards')">{{form['rental_standards']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','owner_user') || $check_field('add','owner_user') || $check_field('set','owner_user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="车主用户" prop="owner_user">
													<el-select v-if="user_group === '管理员' || (form['return_information_id'] && $check_field('set','owner_user')) || (!form['return_information_id'] && $check_field('add','owner_user'))" id="owner_user" v-model="form['owner_user']" :disabled="disabledObj['owner_user_isDisabled']">
							<el-option v-for="o in list_user_owner_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','owner_user')" id="owner_user" v-model="form['owner_user']" :disabled="true">
							<el-option v-for="o in list_user_owner_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','car_rental_users') || $check_field('add','car_rental_users') || $check_field('set','car_rental_users')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="租车用户" prop="car_rental_users">
													<el-select v-if="user_group === '管理员' || (form['return_information_id'] && $check_field('set','car_rental_users')) || (!form['return_information_id'] && $check_field('add','car_rental_users'))" id="car_rental_users" v-model="form['car_rental_users']" :disabled="disabledObj['car_rental_users_isDisabled']">
							<el-option v-for="o in list_user_car_rental_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','car_rental_users')" id="car_rental_users" v-model="form['car_rental_users']" :disabled="true">
							<el-option v-for="o in list_user_car_rental_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','number_of_leases') || $check_field('add','number_of_leases') || $check_field('set','number_of_leases')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="租用数量" prop="number_of_leases">
								<el-input-number id="number_of_leases" v-model.number="form['number_of_leases']"
						v-if="user_group === '管理员' || (form['return_information_id'] && $check_field('set','number_of_leases')) || (!form['return_information_id'] && $check_field('add','number_of_leases'))" :disabled="disabledObj['number_of_leases_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','number_of_leases')">{{form['number_of_leases']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','lease_date') || $check_field('add','lease_date') || $check_field('set','lease_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="租用日期" prop="lease_date">
								<el-date-picker :disabled="disabledObj['lease_date_isDisabled']" v-if="user_group === '管理员' || (form['return_information_id'] && $check_field('set','lease_date')) || (!form['return_information_id'] && $check_field('add','lease_date'))" id="lease_date"
						v-model="form['lease_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','lease_date')">{{form['lease_date']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','return_date') || $check_field('add','return_date') || $check_field('set','return_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="归还日期" prop="return_date">
								<el-date-picker :disabled="disabledObj['return_date_isDisabled']" v-if="user_group === '管理员' || (form['return_information_id'] && $check_field('set','return_date')) || (!form['return_information_id'] && $check_field('add','return_date'))" id="return_date"
						v-model="form['return_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','return_date')">{{form['return_date']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','lending_time') || $check_field('add','lending_time') || $check_field('set','lending_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="出借时间" prop="lending_time">
								<el-input-number id="lending_time" v-model.number="form['lending_time']"
						v-if="user_group === '管理员' || (form['return_information_id'] && $check_field('set','lending_time')) || (!form['return_information_id'] && $check_field('add','lending_time'))" :disabled="disabledObj['lending_time_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','lending_time')">{{form['lending_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','payment_amount') || $check_field('add','payment_amount') || $check_field('set','payment_amount')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="支付金额" prop="payment_amount">
												<el-input id="payment_amount" v-model="form['payment_amount']" placeholder="请输入支付金额"
							  v-if="user_group === '管理员' || (form['return_information_id'] && $check_field('set','payment_amount')) || (!form['return_information_id'] && $check_field('add','payment_amount'))"  @focus="set_payment_amount()" :disabled="disabledObj['payment_amount_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','payment_amount')">{{form['payment_amount']}}</div>
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
				field: "return_information_id",
				url_add: "~/api/return_information/add?",
				url_set: "~/api/return_information/set?",
				url_get_obj: "~/api/return_information/get_obj?",
				url_upload: "~/api/return_information/upload?",

				query: {
					"return_information_id": 0,
				},

				form: {
								"lease_number":  '', // 租用号
										"management_serial_number":  '', // 管理序号
										"name_of_single_vehicle":  '', // 单车名称
										"single_vehicle_type":  '', // 单车类型
										"rental_unit_price":  0, // 租用单价
										"rental_standards":  '', // 租用标准
										"owner_user": 0, // 车主用户
										"car_rental_users": 0, // 租车用户
										"number_of_leases":  0, // 租用数量
										"lease_date":  '', // 租用日期
										"return_date":  '', // 归还日期
										"lending_time":  0, // 出借时间
										"payment_amount":  '', // 支付金额
											"return_information_id": 0, // ID
						
				},
				disabledObj:{
								"lease_number_isDisabled": false,
										"management_serial_number_isDisabled": false,
										"name_of_single_vehicle_isDisabled": false,
										"single_vehicle_type_isDisabled": false,
					          			"rental_unit_price_isDisabled": false,
										"rental_standards_isDisabled": false,
										"owner_user_isDisabled": false,
										"car_rental_users_isDisabled": false,
					          			"number_of_leases_isDisabled": false,
										"lease_date_isDisabled": false,
										"return_date_isDisabled": false,
					          			"lending_time_isDisabled": false,
										"payment_amount_isDisabled": false,
										},

	
		
		
		
		
		
		
					// 用户列表
				list_user_owner_user: [],
				
					// 用户列表
				list_user_car_rental_users: [],
				
		
		
		
		
	
			}
		},
		methods: {


	
	
			
	
			
	
			
	
			
	
			
	
			
	
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
			 * 获取普通用户用户列表
			 */
			async get_list_user_car_rental_users() {
                // if(this.user_group !== "管理员" && this.form["car_rental_users"] === 0) {
                //     this.form["car_rental_users"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_car_rental_users = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					get_user_car_rental_users(id){
				var obj = this.list_user_car_rental_users.getObj({"user_id":id});
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
			
	
			
	
			
	
			
	
			
	
										set_payment_amount(){
				this.form.payment_amount = parseFloat(this.form.lending_time) * parseFloat(this.form.number_of_leases) * parseFloat(this.form.rental_unit_price)
			},
						
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
													// 获取缓存数据附加
				form = $.db.get("form");
							$.push(this.form ,form);
										
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
																				        if (this.form["lease_date"].indexOf("-")===-1){
          this.form["lease_date"] = this.$toTime(parseInt(this.form["lease_date"]),"yyyy-MM-dd")
        }
			        if (this.form["return_date"].indexOf("-")===-1){
          this.form["return_date"] = this.$toTime(parseInt(this.form["return_date"]),"yyyy-MM-dd")
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


																													        if(this.form["lease_date"]=="0000-00-00"){
          this.form["lease_date"] = null;
        }
				if(parseInt(this.form["lease_date"]) > 9999){
					this.form["lease_date"] = this.$toTime(parseInt(this.form["lease_date"]),"yyyy-MM-dd")
				}
				        if(this.form["return_date"]=="0000-00-00"){
          this.form["return_date"] = null;
        }
				if(parseInt(this.form["return_date"]) > 9999){
					this.form["return_date"] = this.$toTime(parseInt(this.form["return_date"]),"yyyy-MM-dd")
				}
								

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
					bl = this.$check_action('/return_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/return_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/return_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/return_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/return_information/view','get');
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
																	this.get_list_user_owner_user();
								this.get_list_user_car_rental_users();
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
