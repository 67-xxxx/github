<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','name_of_electric_pile') || $check_field('add','name_of_electric_pile') || $check_field('set','name_of_electric_pile')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="电桩名称" prop="name_of_electric_pile">
												<el-input id="name_of_electric_pile" v-model="form['name_of_electric_pile']" placeholder="请输入电桩名称"
							  v-if="user_group === '管理员' || (form['charging_information_id'] && $check_field('set','name_of_electric_pile')) || (!form['charging_information_id'] && $check_field('add','name_of_electric_pile'))" :disabled="disabledObj['name_of_electric_pile_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_electric_pile')">{{form['name_of_electric_pile']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','electric_pile_cover') || $check_field('add','electric_pile_cover') || $check_field('set','electric_pile_cover')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="电桩封面" prop="electric_pile_cover">
								<el-upload :disabled="disabledObj['electric_pile_cover_isDisabled']" id="electric_pile_cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_electric_pile_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['charging_information_id'] && $check_field('set','electric_pile_cover')) || (!form['charging_information_id'] && $check_field('add','electric_pile_cover'))">
						<img v-if="form['electric_pile_cover']" :src="$fullUrl(form['electric_pile_cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','electric_pile_cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['electric_pile_cover'])" :preview-src-list="[$fullUrl(form['electric_pile_cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','revenue_voltage') || $check_field('add','revenue_voltage') || $check_field('set','revenue_voltage')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="收入电压" prop="revenue_voltage">
												<el-input id="revenue_voltage" v-model="form['revenue_voltage']" placeholder="请输入收入电压"
							  v-if="user_group === '管理员' || (form['charging_information_id'] && $check_field('set','revenue_voltage')) || (!form['charging_information_id'] && $check_field('add','revenue_voltage'))" :disabled="disabledObj['revenue_voltage_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','revenue_voltage')">{{form['revenue_voltage']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','revenue_current') || $check_field('add','revenue_current') || $check_field('set','revenue_current')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="收入电流" prop="revenue_current">
												<el-input id="revenue_current" v-model="form['revenue_current']" placeholder="请输入收入电流"
							  v-if="user_group === '管理员' || (form['charging_information_id'] && $check_field('set','revenue_current')) || (!form['charging_information_id'] && $check_field('add','revenue_current'))" :disabled="disabledObj['revenue_current_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','revenue_current')">{{form['revenue_current']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','number_of_electric_piles') || $check_field('add','number_of_electric_piles') || $check_field('set','number_of_electric_piles')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="电桩数量" prop="number_of_electric_piles">
								<el-input-number id="number_of_electric_piles" v-model.number="form['number_of_electric_piles']"
						v-if="user_group === '管理员' || (form['charging_information_id'] && $check_field('set','number_of_electric_piles')) || (!form['charging_information_id'] && $check_field('add','number_of_electric_piles'))" :disabled="disabledObj['number_of_electric_piles_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','number_of_electric_piles')">{{form['number_of_electric_piles']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','charging_unit_price') || $check_field('add','charging_unit_price') || $check_field('set','charging_unit_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="充电单价" prop="charging_unit_price">
								<el-input-number id="charging_unit_price" v-model.number="form['charging_unit_price']"
						v-if="user_group === '管理员' || (form['charging_information_id'] && $check_field('set','charging_unit_price')) || (!form['charging_information_id'] && $check_field('add','charging_unit_price'))" :disabled="disabledObj['charging_unit_price_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','charging_unit_price')">{{form['charging_unit_price']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','charging_standard') || $check_field('add','charging_standard') || $check_field('set','charging_standard')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="收费标准" prop="charging_standard">
												<el-input id="charging_standard" v-model="form['charging_standard']" placeholder="请输入收费标准"
							  v-if="user_group === '管理员' || (form['charging_information_id'] && $check_field('set','charging_standard')) || (!form['charging_information_id'] && $check_field('add','charging_standard'))" :disabled="disabledObj['charging_standard_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','charging_standard')">{{form['charging_standard']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','charging_address') || $check_field('add','charging_address') || $check_field('set','charging_address')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="充电地址" prop="charging_address">
												<el-input id="charging_address" v-model="form['charging_address']" placeholder="请输入充电地址"
							  v-if="user_group === '管理员' || (form['charging_information_id'] && $check_field('set','charging_address')) || (!form['charging_information_id'] && $check_field('add','charging_address'))" :disabled="disabledObj['charging_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','charging_address')">{{form['charging_address']}}</div>
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
				field: "charging_information_id",
				url_add: "~/api/charging_information/add?",
				url_set: "~/api/charging_information/set?",
				url_get_obj: "~/api/charging_information/get_obj?",
				url_upload: "~/api/charging_information/upload?",

				query: {
					"charging_information_id": 0,
				},

				form: {
								"name_of_electric_pile":  '', // 电桩名称
										"electric_pile_cover":  '', // 电桩封面
										"revenue_voltage":  '', // 收入电压
										"revenue_current":  '', // 收入电流
										"number_of_electric_piles":  0, // 电桩数量
										"charging_unit_price":  0, // 充电单价
										"charging_standard":  '', // 收费标准
										"charging_address":  '', // 充电地址
											"charging_information_id": 0, // ID
						
				},
				disabledObj:{
								"name_of_electric_pile_isDisabled": false,
										"electric_pile_cover_isDisabled": false,
										"revenue_voltage_isDisabled": false,
										"revenue_current_isDisabled": false,
					          			"number_of_electric_piles_isDisabled": false,
					          			"charging_unit_price_isDisabled": false,
										"charging_standard_isDisabled": false,
										"charging_address_isDisabled": false,
										},

	
		
		
		
		
		
		
		
	
			}
		},
		methods: {


	
	
						/**
			 * 上传电桩封面
			 * @param {Object} param 图片参数
			 */
			upload_electric_pile_cover(param){
						this.uploadFile(param.file, "electric_pile_cover");
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
					bl = this.$check_action('/charging_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/charging_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/charging_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/charging_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/charging_information/view','get');
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
