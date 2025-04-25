<template>
	<div class="diy_edit page_lease_information" id="lease_information_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
						<div v-if="$check_field('set','lease_number') || $check_field('add','lease_number') || $check_field('get','lease_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								租用号:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_lease_number" v-model="form['lease_number']" placeholder="请输入租用号" v-if="(form['lease_number'] && $check_field('set','lease_number')) || (!form['lease_number'] && $check_field('add','lease_number'))" :disabled="true"/>
							<span v-else-if="$check_field('get','lease_number')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','management_serial_number') || $check_field('add','management_serial_number') || $check_field('get','management_serial_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								管理序号:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_management_serial_number" v-model="form['management_serial_number']" placeholder="请输入管理序号" v-if="(form['management_serial_number'] && $check_field('set','management_serial_number')) || (!form['management_serial_number'] && $check_field('add','management_serial_number'))"  :disabled="disabledObj['management_serial_number_isDisabled']"/>
							<span v-else-if="$check_field('get','management_serial_number')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','name_of_single_vehicle') || $check_field('add','name_of_single_vehicle') || $check_field('get','name_of_single_vehicle')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								单车名称:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_name_of_single_vehicle" v-model="form['name_of_single_vehicle']" placeholder="请输入单车名称" v-if="(form['name_of_single_vehicle'] && $check_field('set','name_of_single_vehicle')) || (!form['name_of_single_vehicle'] && $check_field('add','name_of_single_vehicle'))"  :disabled="disabledObj['name_of_single_vehicle_isDisabled']"/>
							<span v-else-if="$check_field('get','name_of_single_vehicle')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','single_vehicle_type') || $check_field('add','single_vehicle_type') || $check_field('get','single_vehicle_type')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								单车类型:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_single_vehicle_type" v-model="form['single_vehicle_type']" placeholder="请输入单车类型" v-if="(form['single_vehicle_type'] && $check_field('set','single_vehicle_type')) || (!form['single_vehicle_type'] && $check_field('add','single_vehicle_type'))"  :disabled="disabledObj['single_vehicle_type_isDisabled']"/>
							<span v-else-if="$check_field('get','single_vehicle_type')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','rental_unit_price') || $check_field('add','rental_unit_price') || $check_field('get','rental_unit_price')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								租用单价:
							</span>
						</div>
								<!-- 数字 -->
						<div class="diy_field diy_number">
							<input type="number" id="form_rental_unit_price" v-model.number="form['rental_unit_price']" placeholder="请输入租用单价" v-if="(form['rental_unit_price'] && $check_field('set','rental_unit_price')) || (!form['rental_unit_price'] && $check_field('add','rental_unit_price'))" :disabled="disabledObj['rental_unit_price_isDisabled']" />
							<span v-else-if="$check_field('get','rental_unit_price')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','rental_standards') || $check_field('add','rental_standards') || $check_field('get','rental_standards')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								租用标准:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_rental_standards" v-model="form['rental_standards']" placeholder="请输入租用标准" v-if="(form['rental_standards'] && $check_field('set','rental_standards')) || (!form['rental_standards'] && $check_field('add','rental_standards'))"  :disabled="disabledObj['rental_standards_isDisabled']"/>
							<span v-else-if="$check_field('get','rental_standards')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','owner_user') || $check_field('add','owner_user') || $check_field('get','owner_user')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								车主用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_owner_user" :disabled="disabledObj['owner_user_isDisabled']" v-model="form['owner_user']" v-if="(form['owner_user'] && $check_field('set','owner_user')) || (!form['owner_user'] && $check_field('add','owner_user'))" >
								<option v-for="o in list_user_owner_user" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','owner_user')">{{ form['owner_user'] }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','car_rental_users') || $check_field('add','car_rental_users') || $check_field('get','car_rental_users')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								租车用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_car_rental_users" :disabled="disabledObj['car_rental_users_isDisabled']" v-model="form['car_rental_users']" v-if="(form['car_rental_users'] && $check_field('set','car_rental_users')) || (!form['car_rental_users'] && $check_field('add','car_rental_users'))" >
								<option v-for="o in list_user_car_rental_users" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','car_rental_users')">{{ form['car_rental_users'] }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','number_of_leases') || $check_field('add','number_of_leases') || $check_field('get','number_of_leases')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								租用数量:
							</span>
						</div>
								<!-- 数字 -->
						<div class="diy_field diy_number">
							<input type="number" id="form_number_of_leases" v-model.number="form['number_of_leases']" placeholder="请输入租用数量" v-if="(form['number_of_leases'] && $check_field('set','number_of_leases')) || (!form['number_of_leases'] && $check_field('add','number_of_leases'))" :disabled="disabledObj['number_of_leases_isDisabled']" />
							<span v-else-if="$check_field('get','number_of_leases')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','lease_date') || $check_field('add','lease_date') || $check_field('get','lease_date')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								租用日期:
							</span>
						</div>
								<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['lease_date_isDisabled']" id="form_lease_date" v-model="form['lease_date']" placeholder="请输入租用日期" v-if="(form['lease_date'] && $check_field('set','lease_date')) || (!form['lease_date'] && $check_field('add','lease_date'))" />
							<span v-else-if="$check_field('get','lease_date')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
	


					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核状态:
							</span>
						</div>
						<div class="diy_field diy_select" v-if="$check_action('/lease_information/edit','examine')">
							<!--<span> {{ form['examine_state'] }} </span>-->
							<select v-model="form['examine_state']">
								<option value="未审核">
									未审核
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
							</select>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_state'] }}
							</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核回复:
							</span>
						</div>
						<div class="diy_field diy_desc" v-if="$check_action('/lease_information/edit','examine')">
							<textarea v-model="form['examine_reply']"></textarea>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_reply'] }}
							</span>
						</div>
					</div>


				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/lease_information/get_obj?",
				url_add: "~/api/lease_information/add?",
				url_set: "~/api/lease_information/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
						"lease_number": "",
							"management_serial_number": "",
							"name_of_single_vehicle": "",
							"single_vehicle_type": "",
							"rental_unit_price": 0,
							"rental_standards": "",
							"owner_user": 0,
							"car_rental_users": 0,
							"number_of_leases": 0,
							"lease_date": "",
						"lease_information_id": 0,
				},

				obj: {
						"lease_number": this.$get_stamp(), // 租用号
							"management_serial_number":  '', // 管理序号
							"name_of_single_vehicle":  '', // 单车名称
							"single_vehicle_type":  '', // 单车类型
							"rental_unit_price":  0, // 租用单价
							"rental_standards":  '', // 租用标准
							"owner_user": 0, // 车主用户
							"car_rental_users": 0, // 租车用户
							"number_of_leases":  0, // 租用数量
							"lease_date": new Date().getTime(),
						"examine_state": "未审核",
					"examine_reply": "",
					"lease_information_id": 0,
				},

				// 表单字段
				form: {
						"lease_number": this.$get_stamp(), // 租用号
							"management_serial_number":  '', // 管理序号
							"name_of_single_vehicle":  '', // 单车名称
							"single_vehicle_type":  '', // 单车类型
							"rental_unit_price":  0, // 租用单价
							"rental_standards":  '', // 租用标准
							"owner_user": 0, // 车主用户
							"car_rental_users": 0, // 租车用户
							"number_of_leases":  0, // 租用数量
							"lease_date": new Date().getTime(),
						"examine_state": "未审核",
					"examine_reply": "",
					"lease_information_id": 0,
				},
				disabledObj:{
						"lease_number_isDisabled": false,
							"management_serial_number_isDisabled": false,
							"name_of_single_vehicle_isDisabled": false,
							"single_vehicle_type_isDisabled": false,
									"rental_standards_isDisabled": false,
							"owner_user_isDisabled": false,
							"car_rental_users_isDisabled": false,
									"lease_date_isDisabled": false,
					},

																		// 用户列表
				list_user_owner_user: [],
							// 用户列表
				list_user_car_rental_users: [],
					
				// ID字段
				field: "lease_information_id",

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
					async get_user_session_car_rental_users(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["car_rental_users"] = user_id
								_this.disabledObj['car_rental_users' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "car_rental_users") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
				
				
	
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/lease_information/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
        //   this.obj = $.push(this.obj ,form);
				// 	this.form = $.push(this.form ,form);
				// }
				// var arr = []
				// for (let key in form) {
				// 	arr.push(key)
				// }
				// for (var i=0;i<arr.length;i++){
				// 	this.disabledObj[arr[i] + '_isDisabled'] = true
				// }
        if (form) {
          var arr = []
          for (let key in form) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arrForm[j] === arr[i]) {
                  this.form[arrForm[j]] = form[arr[i]]
                  this.obj[arrForm[j]] = form[arr[i]]
                  this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  break;
                }
              }
            }
          }
        }
																				        if (JSON.stringify(this.form["lease_date"]).indexOf("-")===-1) {
          this.form["lease_date"] = this.$toTime(parseInt(this.form["lease_date"]), "yyyy-MM-dd")
        }
	
        $.db.del("form");
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				// var form = $.db.get("form");
				// var obj = Object.assign({} ,form ,this.obj);
				// if (obj) {
        //   delete(obj.examine_state)
        //   delete(obj.examine_reply)
				// 	this.obj = $.push(this.obj ,obj);
				// }
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
				// 	this.form = $.push(this.form ,form);
				// }
				if(func){
					func(json);
				}
			},

		},
		created() {
																								this.get_list_user_owner_user();
						this.get_user_session_car_rental_users();
					this.get_list_user_car_rental_users();
									},
	}
</script>

<style>




</style>
