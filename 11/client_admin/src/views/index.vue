<template>
	<div class="page_root" id="root_index">
		<div class="warp">
			<div class="container-fluid">
				<el-row>
					<el-col :span="4">
						<mm_label bg_color="bg_purple" icon="el-icon-user-solid" :url="url_user_count" unit="人"
								  title="用户数量"></mm_label>
					</el-col>
<!--					<el-col :span="4">-->
<!--						<mm_label bg_color="bg_green" icon="el-icon-view" :url="url_article_hits" unit="次"-->
<!--								  title="文章浏览量"></mm_label>-->
<!--					</el-col>-->
				</el-row>

				<el-row>
								<el-col v-if="user_group == '管理员' || $check_figure('/single_car_information/table')" :span="8">
						<div class="card chart">
									<newBarChart v-if="bar_obj_single_car_information.values.length > 0" id="bar_obj_single_car_information" :vm="bar_obj_single_car_information" :title="'单车信息统计'">
							</newBarChart>
							<div v-if="!bar_obj_single_car_information.values.length">单车信息没有符合条件的数据</div>
								</div>
					</el-col>
							<el-col v-if="user_group == '管理员' || $check_figure('/lease_information/table')" :span="8">
						<div class="card chart">
									<newBarChart v-if="bar_obj_lease_information.values.length > 0" id="bar_obj_lease_information" :vm="bar_obj_lease_information" :title="'租赁信息统计'">
							</newBarChart>
							<div v-if="!bar_obj_lease_information.values.length">租赁信息没有符合条件的数据</div>
								</div>
					</el-col>
								<el-col v-if="user_group == '管理员' || $check_figure('/parking_area_sharing/table')" :span="8">
						<div class="card chart">
									<newBarChart v-if="bar_obj_parking_area_sharing.values.length > 0" id="bar_obj_parking_area_sharing" :vm="bar_obj_parking_area_sharing" :title="'停车区域统计'">
							</newBarChart>
							<div v-if="!bar_obj_parking_area_sharing.values.length">停车区域没有符合条件的数据</div>
								</div>
					</el-col>
							<el-col v-if="user_group == '管理员' || $check_figure('/charging_information/table')" :span="8">
						<div class="card chart">
									<newBarChart v-if="bar_obj_charging_information.values.length > 0" id="bar_obj_charging_information" :vm="bar_obj_charging_information" :title="'充电信息统计'">
							</newBarChart>
							<div v-if="!bar_obj_charging_information.values.length">充电信息没有符合条件的数据</div>
								</div>
					</el-col>
						</el-row>


			</div>
		</div>
	</div>
</template>
<script>
	import mixin from "@/mixins/page.js";
	import pieChart from "@/components/charts/pie_chart";
	import barChart from "@/components/charts/bar_chart";
	import newBarChart from "@/components/charts/new_bar_chart";
	import lineChart from "@/components/charts/line_chart";
	import newLineChart from "@/components/charts/new_line_chart";
	import mm_label from "@/components/mm_label.vue";
	export default {
		mixins: [mixin],
		name: "Home",
		components: {
			pieChart,
			barChart,
			newBarChart,
			lineChart,
			newLineChart,
			mm_label
		},
		data() {
			return {
				activeName: "third",
									bar_obj_single_car_information: {
					names:[],
					xAxis: [],
					values:[]
				},
						bar_obj_lease_information: {
					names:[],
					xAxis: [],
					values:[]
				},
								bar_obj_parking_area_sharing: {
					names:[],
					xAxis: [],
					values:[]
				},
						bar_obj_charging_information: {
					names:[],
					xAxis: [],
					values:[]
				},
							url_user_count: "~/api/user/count?",
				url_article_hits: "~/api/article/sum?field=hits",
			};
		},
		created() {
						// 执行单车信息数据获取
			this.get_list_single_car_information();
					// 执行租赁信息数据获取
			this.get_list_lease_information();
						// 执行停车区域数据获取
			this.get_list_parking_area_sharing();
					// 执行充电信息数据获取
			this.get_list_charging_information();
				},
		mounted() {},
		methods: {
			async get_nickname(list,flag){
				if (flag) {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i],
								null,
								(json) => {
									if (json.result) {
										list[i] = json.result.obj.nickname;
									}
								});
					}
				}else {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i].name,
								null,
								(json) => {
									if (json.result) {
										list[i].name = json.result.obj.nickname;
									}
								});
					}
				}
			},
								// 获取单车信息统计柱状图
			async get_list_single_car_information() {
				let name_list = [];
				let query_str = "";
									let group_by_value = "management_serial_number";
								let flag = false;
												let date_flag = "其他"
																																														name_list.push("租用单价");
				query_str = query_str+"rental_unit_price"+","
															name_list.push("单车数量");
				query_str = query_str+"number_of_single_vehicles"+","
																																	this.bar_obj_single_car_information.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
						let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
								let sqlwhere = "(";
																																																																													if (user_group=="车主用户"){
						sqlwhere+= "owner_user = " + user_id + " or ";
					}
																							if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
							}
						await this.$get(
						"~/api/single_car_information/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"hh:mm:ss"));
									}else if (date_flag === "日长") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
									}else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_single_car_information.xAxis = xAxis;
								this.bar_obj_single_car_information.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_single_car_information.xAxis,true);
							}
						});
			},
					// 获取租赁信息统计柱状图
			async get_list_lease_information() {
				let name_list = [];
				let query_str = "";
																																																																												name_list.push("租用数量");
				query_str = query_str+"number_of_leases"+","
												let group_by_value = "lease_date";
								let flag = false;
												let date_flag = "日期"
																this.bar_obj_lease_information.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
						let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
								let sqlwhere = "(";
																																																																				if (user_group=="车主用户"){
						sqlwhere+= "owner_user = " + user_id + " or ";
					}
																			if (user_group=="普通用户"){
						sqlwhere+= "car_rental_users = " + user_id + " or ";
					}
																																if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
							}
						await this.$get(
						"~/api/lease_information/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"hh:mm:ss"));
									}else if (date_flag === "日长") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
									}else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_lease_information.xAxis = xAxis;
								this.bar_obj_lease_information.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_lease_information.xAxis,true);
							}
						});
			},
							// 获取停车区域统计柱状图
			async get_list_parking_area_sharing() {
				let name_list = [];
				let query_str = "";
									let group_by_value = "area_name";
								let flag = false;
												let date_flag = "其他"
																																						name_list.push("容纳车辆数");
				query_str = query_str+"number_of_vehicles_accommodated"+","
																									this.bar_obj_parking_area_sharing.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
						await this.$get(
						"~/api/parking_area_sharing/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"hh:mm:ss"));
									}else if (date_flag === "日长") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
									}else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_parking_area_sharing.xAxis = xAxis;
								this.bar_obj_parking_area_sharing.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_parking_area_sharing.xAxis,true);
							}
						});
			},
					// 获取充电信息统计柱状图
			async get_list_charging_information() {
				let name_list = [];
				let query_str = "";
									let group_by_value = "name_of_electric_pile";
								let flag = false;
												let date_flag = "其他"
																																														name_list.push("电桩数量");
				query_str = query_str+"number_of_electric_piles"+","
															name_list.push("充电单价");
				query_str = query_str+"charging_unit_price"+","
																									this.bar_obj_charging_information.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
						let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
								let sqlwhere = "(";
																																																																																	if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
							}
						await this.$get(
						"~/api/charging_information/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"hh:mm:ss"));
									}else if (date_flag === "日长") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
									}else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_charging_information.xAxis = xAxis;
								this.bar_obj_charging_information.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_charging_information.xAxis,true);
							}
						});
			},
			
		},
		computed:{
			recognitionHeight(){
				return "830px"
			},
			recognitionUrl(){
				return "https://www.faceplusplus.com.cn/${model.filter.recognitionType}/"
			}
		}
	};
</script>

<style scoped="scoped">
	.chart {
		display: block;
		width: 100%;
		height: 400px;
		padding: 1rem;
		position: relative;
	}

	.el-col {
		padding: 0.5rem;
	}

	.card {
		overflow: hidden;
	}

	.iframe_box ,.iframe_box_change{
		width: 100%;
		height: 1180px;
		position: relative;
		margin-top: 25px;
	}
	.iframe_box_change{
		height: 580px;
		padding-top: 50px;
	}
	.iframe_box	.iframe_box_content, .iframe_box_change .iframe_box_content{
		width: 100%;
		height: 100%;
	}
	.iframe_box_top{
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100px;
		font-size: 25px;
		line-height: 100px;
		background: #fff;
		z-index: 99999999;
		padding-left: 50px;
	}
	#iframe_box_face div::before {
		content: '';
		width: 100px;
		position: absolute;
		top: 154px;
		right: 129px;
		z-index: 999;
		height: 20px;
		background-color: #FFFFFF;
	}

	#iframe_box_face>h1 {
		margin-top: 100px;
		margin-bottom: 20px;
	}
</style>
