<template>
	<el-main class="bg table_wrap">
		<el-form label-position="right" :model="query" class="form p_4" label-width="120">
			<el-row>


							<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="租用号">
									<el-input v-model="query.lease_number"></el-input>
								</el-form-item>
				</el-col>
																																	<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="租用日期">
									<el-input v-model="query.lease_date"></el-input>
								</el-form-item>
				</el-col>
									<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="归还日期">
									<el-input v-model="query.return_date"></el-input>
								</el-form-item>
				</el-col>
													<el-col :xs="24" :sm="10" :lg="8" class="search_btn_wrap_1">
					<el-form-item>
						<el-button type="primary" @click="search()" class="search_btn_find">查询</el-button>
						<el-button @click="reset()" style="margin-right: 74px;" class="search_btn_reset">重置</el-button>
						<router-link v-if="user_group == '管理员' || $check_action('/return_information/table','add') || $check_action('/return_information/view','add')" class="el-button el-button--default el-button--primary search_btn_add" to="./view?">添加
						</router-link>
            			<el-button v-if="user_group == '管理员' || $check_action('/return_information/table','del') || $check_action('/return_information/view','del')" class="search_btn_del" type="danger" @click="delInfo()">删除</el-button>
					</el-form-item>
				</el-col>

			</el-row>
		</el-form>
		<el-table :data="list" @selection-change="selectionChange" @sort-change="$sortChange" style="width: 100%" id="dataTable">
			<el-table-column fixed type="selection" tooltip-effect="dark" width="55">
			</el-table-column>
				<el-table-column prop="lease_number" @sort-change="$sortChange" label="租用号"
				v-if="user_group == '管理员' || $check_field('get','lease_number')" min-width="200">
					</el-table-column>
					<el-table-column prop="management_serial_number" @sort-change="$sortChange" label="管理序号"
				v-if="user_group == '管理员' || $check_field('get','management_serial_number')" min-width="200">
					</el-table-column>
					<el-table-column prop="name_of_single_vehicle" @sort-change="$sortChange" label="单车名称"
				v-if="user_group == '管理员' || $check_field('get','name_of_single_vehicle')" min-width="200">
					</el-table-column>
					<el-table-column prop="single_vehicle_type" @sort-change="$sortChange" label="单车类型"
				v-if="user_group == '管理员' || $check_field('get','single_vehicle_type')" min-width="200">
					</el-table-column>
					<el-table-column prop="rental_unit_price" @sort-change="$sortChange" label="租用单价"
				v-if="user_group == '管理员' || $check_field('get','rental_unit_price')" min-width="200">
					</el-table-column>
					<el-table-column prop="rental_standards" @sort-change="$sortChange" label="租用标准"
				v-if="user_group == '管理员' || $check_field('get','rental_standards')" min-width="200">
					</el-table-column>
					<el-table-column prop="owner_user" @sort-change="$sortChange" label="车主用户"
				v-if="user_group == '管理员' || $check_field('get','owner_user')" min-width="200">
						<template slot-scope="scope">
					{{ get_user_owner_user(scope.row['owner_user']) }}
				</template>
					</el-table-column>
					<el-table-column prop="car_rental_users" @sort-change="$sortChange" label="租车用户"
				v-if="user_group == '管理员' || $check_field('get','car_rental_users')" min-width="200">
						<template slot-scope="scope">
					{{ get_user_car_rental_users(scope.row['car_rental_users']) }}
				</template>
					</el-table-column>
					<el-table-column prop="number_of_leases" @sort-change="$sortChange" label="租用数量"
				v-if="user_group == '管理员' || $check_field('get','number_of_leases')" min-width="200">
					</el-table-column>
					<el-table-column prop="lease_date" @sort-change="$sortChange" label="租用日期"
				v-if="user_group == '管理员' || $check_field('get','lease_date')" min-width="200">
		                <template slot-scope="scope">
                	{{ $toTime(scope.row["lease_date"],"yyyy-MM-dd") }}
                </template>
					</el-table-column>
					<el-table-column prop="return_date" @sort-change="$sortChange" label="归还日期"
				v-if="user_group == '管理员' || $check_field('get','return_date')" min-width="200">
		                <template slot-scope="scope">
                	{{ $toTime(scope.row["return_date"],"yyyy-MM-dd") }}
                </template>
					</el-table-column>
					<el-table-column prop="lending_time" @sort-change="$sortChange" label="出借时间"
				v-if="user_group == '管理员' || $check_field('get','lending_time')" min-width="200">
					</el-table-column>
					<el-table-column prop="payment_amount" @sort-change="$sortChange" label="支付金额"
				v-if="user_group == '管理员' || $check_field('get','payment_amount')" min-width="200">
					</el-table-column>
	



            <el-table-column sortable prop="create_time" label="创建时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["create_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
            </el-table-column>

			<el-table-column sortable prop="update_time" label="更新时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["update_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
			</el-table-column>







			<el-table-column fixed="right" label="操作" min-width="120" v-if="user_group == '管理员' || $check_action('/return_information/table','set') || $check_action('/return_information/view','set') || $check_action('/return_information/view','get') || $check_action('/${table.tableConfig.table_nav_name}/table','add') || $check_action('/${table.tableConfig.table_nav_name}/view','add')" >


				<template slot-scope="scope">
					<router-link class="el-button el-button--small is-plain el-button--success" style="margin: 5px !important;"
					v-if="user_group == '管理员' || $check_action('/return_information/table','set') || $check_action('/return_information/view','set') || $check_action('/return_information/view','get')"
						:to="'./view?' + field + '=' + scope.row[field]"
						 size="small">
						<span>详情</span>
					</router-link>
					</template>
			</el-table-column>

		</el-table>

		<!-- 分页器 -->
		<div class="mt text_center">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
				:current-page="query.page" :page-sizes="[7, 10, 30, 100]" :page-size="query.size"
				layout="total, sizes, prev, pager, next, jumper" :total="count">
			</el-pagination>
		</div>
		<!-- /分页器 -->

													
		<div class="modal_wrap" v-if="showModal">
			<div class="modal_box">
				<!-- <div class="modal_box_close" @click="closeModal">X</div> -->
				<p class="modal_box_title">重要提醒</p>
				<p class="modal_box_text">当前有数据达到预警值！</p>
				<div class="btn_box">
					<span @click="closeModal">取消</span>
					<span @click="closeModal">确定</span>
				</div>
			</div>
		</div>


	</el-main>
</template>
<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				// 弹框
				showModal: false,
				// 获取数据地址
				url_get_list: "~/api/return_information/get_list?like=0",
				url_del: "~/api/return_information/del?",

				// 字段ID
				field: "return_information_id",

				// 查询
				query: {
					"size": 7,
					"page": 1,
								"lease_number": "",
																			"lease_date": "",
											"return_date": "",
										"login_time": "",
					"create_time": "",
					"orderby": `create_time desc`
				},

				// 数据
				list: [],
																																	// 用户列表
				list_user_owner_user: [],
									// 用户列表
				list_user_car_rental_users: [],
														}
		},
		methods: {
			// 关闭弹框
			closeModal(){
				this.showModal = false;
				},

			get_list_before(param){
				var user_group = this.user.user_group;
				if(user_group != "管理员"){
						let sqlwhere = "(";
																																								if(user_group=="车主用户"){
						sqlwhere+= "owner_user = " + this.user.user_id + " or ";
					}
													if(user_group=="普通用户"){
						sqlwhere+= "car_rental_users = " + this.user.user_id + " or ";
					}
																																			if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						param["sqlwhere"] = sqlwhere;
					}
					}
				return param;
			},

																										
			open_tip() {
				const h = this.$createElement;

				var message = "";
				var list = this.list;

				var ifs = [
																	];
				for (var n = 0; n < ifs.length; n++) {
					var o = ifs[n];
					for (var i = 0; i < list.length; i++) {
						var lt = list[i];
						if (o.type == "数内") {
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								if (lt[o.factor] > o.start && lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start || o.start === 0) {
								if (lt[o.factor] > o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end || o.end === 0) {
								if (lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "数外") {
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								if (lt[o.factor] < o.start || lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start || o.start === 0) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end || o.end === 0) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "日内") {
							if ((o.start) && (o.end)) {
								if (lt[o.factor] > o.start && lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "日外") {
							if (o.start && o.end) {
								if (lt[o.factor] < o.start || lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						}
					}

					if (o["idx"]) {
						message += o.title;
						if (o["type"] == "数内") {
							if (o.start || o.start === 0) {
								message += "大于" + o.start;
							}
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								message += "并且";
							}
							if (o.end || o.end === 0) {
								message += "小于" + o.end;
							}
						} else if (o["type"] == "数外") {
							if (o.start || o.start === 0) {
								message += "小于" + o.start;
							}
							if ((o.start || o.start === 0) || (o.end || o.end === 0)) {
								message += "或者";
							}
							if (o.end || o.end === 0) {
								message += "大于" + o.end;
							}
						} else if (o["type"] == "日内") {
							if (o.start) {
								message += "在" + o.start + "之后";
							}
							if (o.start && o.end) {
								message += "并且";
							}
							if (o.end) {
								message += "在" + o.end + "之前";
							}
						} else if (o["type"] == "日外") {
							if (o.start) {
								message += "在" + o.start + "之前";
							}
							if (o.start || o.end) {
								message += "或者";
							}
							if (o.end) {
								message += "在" + o.end + "之后";
							}
						}
						message += "的有" + o["idx"] + "条";
					}
				}

				if (message) {
					message += "，需要处理、请尽快处理。";
					this.showModal = true;
					// this.$notify({
					// 	title: '提醒',
					// 	dangerouslyUseHTMLString: true,
					// 	message: h('i', {
					// 		style: 'color: teal'
					// 	}, message)
					// });
				}
			},




																/**
			 * 获取车主用户用户列表
			 */
			async get_list_user_owner_user() {
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
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
						/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_car_rental_users() {
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
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
															deleteRow(index, rows) {
				rows.splice(index, 1);
			}

		},
		created() {
																			this.get_list_user_owner_user();
								this.get_list_user_car_rental_users();
														setTimeout(() => {
				this.open_tip();
			}, 1000)
		}
	}
</script>

<style type="text/css">
	.bg {
		background: white;
	}

	.form.p_4 {
		padding: 1rem;
	}

	.form .el-input {
		width: initial;
	}

	.mt {
		margin-top: 1rem;
	}

	.text_center {
		text-align: center;
	}

	.float-right {
		float: right;
	}


	.modal_wrap{
		width: 100vw;
		height: 100vh;
		position: fixed;
		top: 0;
		left: 0;
		background: rgba(0,0,0,0.5);
		z-index: 9999999999;
	}
	.modal_wrap .modal_box{
		width: 400px;
		height: 200px;
		background: url("../../assets/modal_bg.jpg") no-repeat center;
		background-size: cover;
		position: absolute;
		top: 50%;
		left: 50%;
		margin-left: -200px;
		margin-top: -100px;
		border-radius: 10px;
		}
	.modal_wrap .modal_box .modal_box_close{
		font-size: 20px;
		position: absolute;
		top: 10px;
		right: 10px;
		cursor: pointer;
		}
	.modal_wrap .modal_box .modal_box_title{
	  text-align: center;
    font-size: 18px;
    margin: 16px auto;
    color: #fff;
    border-bottom: 1px solid rgba(117, 116, 116,0.5);
    padding-bottom: 16px;
    width: 356px;
		}
	.modal_wrap .modal_box .modal_box_text{
			text-align: center;
		font-size: 15px;
		color: #fff;
		margin-top: 25px;
		}
	.modal_wrap .modal_box .btn_box{
		display: flex;
		flex-direction: row;
		justify-content: center;
		margin-top: 42px;
		}
			.modal_wrap .modal_box .btn_box span{
				display: inline-block;
				width: 80px;
				height: 30px;
				line-height: 30px;
				text-align: center;
				border: 1px solid #ccc;
				font-size: 14px;
				cursor: pointer;
				color: #fff;
			}
	.modal_wrap .modal_box .btn_box span:nth-child(2){
		background: #409EFF;
		color: #fff;
		border-color: #409EFF;
		margin-left: 15px;
	}
</style>
