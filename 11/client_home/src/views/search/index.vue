<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>

				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="校园新闻"
				source_table="article"
			  ></list_result_search>


						  <list_result_search
				v-if="$check_action('/owner_user/list', 'get')"
				:list="result_owner_user_user_name"
				title="车主用户用户姓名"
				source_table="owner_user"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/owner_user/list', 'get')"
				:list="result_owner_user_user_gender"
				title="车主用户用户性别"
				source_table="owner_user"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_user_name"
				title="普通用户用户姓名"
				source_table="ordinary_users"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_user_gender"
				title="普通用户用户性别"
				source_table="ordinary_users"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/single_car_information/list', 'get')"
				:list="result_single_car_information_management_serial_number"
				title="单车信息管理序号"
				source_table="single_car_information"
			  ></list_result_search>
											  <list_result_search
				v-if="$check_action('/single_car_information/list', 'get')"
				:list="result_single_car_information_single_vehicle_type"
				title="单车信息单车类型"
				source_table="single_car_information"
			  ></list_result_search>
																	  <list_result_search
				v-if="$check_action('/single_car_information/list', 'get')"
				:list="result_single_car_information_rental_standards"
				title="单车信息租用标准"
				source_table="single_car_information"
			  ></list_result_search>
															  <list_result_search
				v-if="$check_action('/lease_information/list', 'get')"
				:list="result_lease_information_lease_number"
				title="租赁信息租用号"
				source_table="lease_information"
			  ></list_result_search>
																																  <list_result_search
				v-if="$check_action('/lease_information/list', 'get')"
				:list="result_lease_information_lease_date"
				title="租赁信息租用日期"
				source_table="lease_information"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/return_information/list', 'get')"
				:list="result_return_information_lease_number"
				title="归还信息租用号"
				source_table="return_information"
			  ></list_result_search>
																																  <list_result_search
				v-if="$check_action('/return_information/list', 'get')"
				:list="result_return_information_lease_date"
				title="归还信息租用日期"
				source_table="return_information"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/return_information/list', 'get')"
				:list="result_return_information_return_date"
				title="归还信息归还日期"
				source_table="return_information"
			  ></list_result_search>
															  <list_result_search
				v-if="$check_action('/parking_area_sharing/list', 'get')"
				:list="result_parking_area_sharing_area_name"
				title="停车区域区域名称"
				source_table="parking_area_sharing"
			  ></list_result_search>
																								  <list_result_search
				v-if="$check_action('/charging_information/list', 'get')"
				:list="result_charging_information_name_of_electric_pile"
				title="充电信息电桩名称"
				source_table="charging_information"
			  ></list_result_search>
																														  <list_result_search
				v-if="$check_action('/single_vehicle_type/list', 'get')"
				:list="result_single_vehicle_type_single_vehicle_type"
				title="单车类型单车类型"
				source_table="single_vehicle_type"
			  ></list_result_search>
						</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
						"result_owner_user_user_name":[],
								"result_owner_user_user_gender":[],
									"result_ordinary_users_user_name":[],
								"result_ordinary_users_user_gender":[],
									"result_single_car_information_management_serial_number":[],
											"result_single_car_information_single_vehicle_type":[],
																	"result_single_car_information_rental_standards":[],
															"result_lease_information_lease_number":[],
																																"result_lease_information_lease_date":[],
									"result_return_information_lease_number":[],
																																"result_return_information_lease_date":[],
								"result_return_information_return_date":[],
															"result_parking_area_sharing_area_name":[],
																								"result_charging_information_name_of_electric_pile":[],
																														"result_single_vehicle_type_single_vehicle_type":[],
				};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},

				/**
	 * 获取user_name
	 */
	get_owner_user_user_name(){
		let url = "~/api/owner_user/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_owner_user_user_name = json.result.list;
			result_owner_user_user_name.map(o => o.title = o['user_name'])
	  			this.result_owner_user_user_name = result_owner_user_user_name
		 	}
		});
	},
						/**
	 * 获取user_gender
	 */
	get_owner_user_user_gender(){
		let url = "~/api/owner_user/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "user_gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_owner_user_user_gender = json.result.list;
			result_owner_user_user_gender.map(o => o.title = o['user_gender'])
	  			this.result_owner_user_user_gender = result_owner_user_user_gender
		 	}
		});
	},
							/**
	 * 获取user_name
	 */
	get_ordinary_users_user_name(){
		let url = "~/api/ordinary_users/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_user_name = json.result.list;
			result_ordinary_users_user_name.map(o => o.title = o['user_name'])
	  			this.result_ordinary_users_user_name = result_ordinary_users_user_name
		 	}
		});
	},
						/**
	 * 获取user_gender
	 */
	get_ordinary_users_user_gender(){
		let url = "~/api/ordinary_users/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "user_gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_user_gender = json.result.list;
			result_ordinary_users_user_gender.map(o => o.title = o['user_gender'])
	  			this.result_ordinary_users_user_gender = result_ordinary_users_user_gender
		 	}
		});
	},
							/**
	 * 获取management_serial_number
	 */
	get_single_car_information_management_serial_number(){
		let url = "~/api/single_car_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "management_serial_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_single_car_information_management_serial_number = json.result.list;
			result_single_car_information_management_serial_number.map(o => o.title = o['management_serial_number'])
	  			this.result_single_car_information_management_serial_number = result_single_car_information_management_serial_number
		 	}
		});
	},
									/**
	 * 获取single_vehicle_type
	 */
	get_single_car_information_single_vehicle_type(){
		let url = "~/api/single_car_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "single_vehicle_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_single_car_information_single_vehicle_type = json.result.list;
			result_single_car_information_single_vehicle_type.map(o => o.title = o['single_vehicle_type'])
	  			this.result_single_car_information_single_vehicle_type = result_single_car_information_single_vehicle_type
		 	}
		});
	},
															/**
	 * 获取rental_standards
	 */
	get_single_car_information_rental_standards(){
		let url = "~/api/single_car_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "rental_standards": this.query.word }, (json) => {
		  if (json.result) {
			var result_single_car_information_rental_standards = json.result.list;
			result_single_car_information_rental_standards.map(o => o.title = o['rental_standards'])
	  			this.result_single_car_information_rental_standards = result_single_car_information_rental_standards
		 	}
		});
	},
													/**
	 * 获取lease_number
	 */
	get_lease_information_lease_number(){
		let url = "~/api/lease_information/get_list?like=0";
				url = url+"&examine_state=已通过";
				this.$get(url, { page: 1, size: 10, "lease_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_lease_information_lease_number = json.result.list;
			result_lease_information_lease_number.map(o => o.title = o['lease_number'])
	  			this.result_lease_information_lease_number = result_lease_information_lease_number
		 	}
		});
	},
																														/**
	 * 获取lease_date
	 */
	get_lease_information_lease_date(){
		let url = "~/api/lease_information/get_list?like=0";
				url = url+"&examine_state=已通过";
				this.$get(url, { page: 1, size: 10, "lease_date": this.query.word }, (json) => {
		  if (json.result) {
			var result_lease_information_lease_date = json.result.list;
			result_lease_information_lease_date.map(o => o.title = o['lease_date'])
	  			this.result_lease_information_lease_date = result_lease_information_lease_date
		 	}
		});
	},
							/**
	 * 获取lease_number
	 */
	get_return_information_lease_number(){
		let url = "~/api/return_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "lease_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_return_information_lease_number = json.result.list;
			result_return_information_lease_number.map(o => o.title = o['lease_number'])
	  			this.result_return_information_lease_number = result_return_information_lease_number
		 	}
		});
	},
																														/**
	 * 获取lease_date
	 */
	get_return_information_lease_date(){
		let url = "~/api/return_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "lease_date": this.query.word }, (json) => {
		  if (json.result) {
			var result_return_information_lease_date = json.result.list;
			result_return_information_lease_date.map(o => o.title = o['lease_date'])
	  			this.result_return_information_lease_date = result_return_information_lease_date
		 	}
		});
	},
						/**
	 * 获取return_date
	 */
	get_return_information_return_date(){
		let url = "~/api/return_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "return_date": this.query.word }, (json) => {
		  if (json.result) {
			var result_return_information_return_date = json.result.list;
			result_return_information_return_date.map(o => o.title = o['return_date'])
	  			this.result_return_information_return_date = result_return_information_return_date
		 	}
		});
	},
													/**
	 * 获取area_name
	 */
	get_parking_area_sharing_area_name(){
		let url = "~/api/parking_area_sharing/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "area_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_parking_area_sharing_area_name = json.result.list;
			result_parking_area_sharing_area_name.map(o => o.title = o['area_name'])
	  			this.result_parking_area_sharing_area_name = result_parking_area_sharing_area_name
		 	}
		});
	},
																						/**
	 * 获取name_of_electric_pile
	 */
	get_charging_information_name_of_electric_pile(){
		let url = "~/api/charging_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "name_of_electric_pile": this.query.word }, (json) => {
		  if (json.result) {
			var result_charging_information_name_of_electric_pile = json.result.list;
			result_charging_information_name_of_electric_pile.map(o => o.title = o['name_of_electric_pile'])
	  			this.result_charging_information_name_of_electric_pile = result_charging_information_name_of_electric_pile
		 	}
		});
	},
																												/**
	 * 获取single_vehicle_type
	 */
	get_single_vehicle_type_single_vehicle_type(){
		let url = "~/api/single_vehicle_type/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "single_vehicle_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_single_vehicle_type_single_vehicle_type = json.result.list;
			result_single_vehicle_type_single_vehicle_type.map(o => o.title = o['single_vehicle_type'])
	  			this.result_single_vehicle_type_single_vehicle_type = result_single_vehicle_type_single_vehicle_type
		 	}
		});
	},
			
  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
					this.get_owner_user_user_name();
							this.get_owner_user_user_gender();
								this.get_ordinary_users_user_name();
							this.get_ordinary_users_user_gender();
								this.get_single_car_information_management_serial_number();
										this.get_single_car_information_single_vehicle_type();
																this.get_single_car_information_rental_standards();
														this.get_lease_information_lease_number();
																															this.get_lease_information_lease_date();
								this.get_return_information_lease_number();
																															this.get_return_information_lease_date();
							this.get_return_information_return_date();
														this.get_parking_area_sharing_area_name();
																							this.get_charging_information_name_of_electric_pile();
																													this.get_single_vehicle_type_single_vehicle_type();
			  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
				  this.get_owner_user_user_name();
						  this.get_owner_user_user_gender();
							  this.get_ordinary_users_user_name();
						  this.get_ordinary_users_user_gender();
							  this.get_single_car_information_management_serial_number();
									  this.get_single_car_information_single_vehicle_type();
															  this.get_single_car_information_rental_standards();
													  this.get_lease_information_lease_number();
																														  this.get_lease_information_lease_date();
							  this.get_return_information_lease_number();
																														  this.get_return_information_lease_date();
						  this.get_return_information_return_date();
													  this.get_parking_area_sharing_area_name();
																						  this.get_charging_information_name_of_electric_pile();
																												  this.get_single_vehicle_type_single_vehicle_type();
				},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
