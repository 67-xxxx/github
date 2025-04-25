<template>
	<div class="diy_list page_single_car_information" id="single_car_information_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">单车信息列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">
							<span class="diy_list_search_title">关键字搜索：</span>
								<b-form-input size="sm" class="mr-sm-2" placeholder="管理序号搜索" v-model="query['management_serial_number']" />
										<b-form-input size="sm" class="mr-sm-2" placeholder="单车类型搜索" v-model="query['single_vehicle_type']" />
												<b-form-input size="sm" class="mr-sm-2" placeholder="租用标准搜索" v-model="query['rental_standards']" />
										<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="diy_list_select_box">
					<span class="diy_list_select_title">下拉搜索：</span>
						<div class="diy_list_dropdown_box">
						<div class="col">
							<!-- 筛选 -->
							<div class="view">
																<b-dropdown text="单车类型" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','single_vehicle_type')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o, i) in list_single_vehicle_type" :key="i" @click="filter_set(o['single_vehicle_type'],'single_vehicle_type')" >
												{{ o['single_vehicle_type'] }}
										</b-dropdown-item>
								</b-dropdown>
																	<b-dropdown text="租用标准" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','rental_standards')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o,i) in list_rental_standards" @click="filter_set(o,'rental_standards')" >
										{{ o }}
										</b-dropdown-item>
								</b-dropdown>
												</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<b-dropdown text="排序" variant="outline-dark" left>
										<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
												{{ o.name }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>

				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_single_car_information :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
<!--						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>-->
            <b-pagination
                v-model="query.page"
                :total-rows="count"
                :per-page="query.size"
                @change="goToPage"
            />
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_single_car_information from "@/components/diy/list_single_car_information.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_single_car_information
		},
		data() {
			return {
				url_get_list: "~/api/single_car_information/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
								"management_serial_number": "", // 管理序号
												"single_vehicle_type": "", // 单车类型
														"rental_standards": "", // 租用标准
									},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "create_time desc",
					},
					{
						name: "创建时间从低到高",
						value: "create_time asc",
					},
					{
						name: "更新时间从高到低",
						value: "update_time desc",
					},
					{
						name: "更新时间从低到高",
						value: "update_time asc",
					},
						{
						name: "管理序号正序",
						value: "management_serial_number asc",
					},
					{
						name: "管理序号倒序",
						value: "management_serial_number desc",
					},
								{
						name: "单车类型正序",
						value: "single_vehicle_type asc",
					},
					{
						name: "单车类型倒序",
						value: "single_vehicle_type desc",
					},
										{
						name: "租用标准正序",
						value: "rental_standards asc",
					},
					{
						name: "租用标准倒序",
						value: "rental_standards desc",
					},
							],

									// 单车类型列表
				"list_single_vehicle_type": [""],
													// 租用标准列表
				"list_rental_standards": ['按小时','按天'],
						
			}
		},
		methods: {
      get_list_before(param) {
      },
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},


						/**
			 * 获取单车类型列表
			 */
			async get_list_single_vehicle_type() {
				var json = await this.$get("~/api/single_vehicle_type/get_list?");
				if (json.result) {
					this.list_single_vehicle_type = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},
							
			/**
			 * 筛选
			 */
													filter_single_vehicle_type(o) {
				if (o == "全部") {
					this.query["single_vehicle_type"] = "";
				} else {
					this.query["single_vehicle_type"] = o;
				}
				this.search();
			},
														filter_rental_standards(o) {
				if (o == "全部") {
					this.query["rental_standards"] = "";
				} else {
					this.query["rental_standards"] = o;
				}
				this.search();
			},
										/**
			 * 重置
			 */
			reset() {
							this.query.management_serial_number = ""
											this.query.single_vehicle_type = ""
													this.query.rental_standards = ""
									this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},

      goToPage(v){
        this.query.page = v;
        this.goToNew(v)
      },

		},
		computed: {
		},
		created() {
								/**
			 * 获取单车类型列表
			 */
			this.get_list_single_vehicle_type();
																}
	}
</script>

<style>
</style>
