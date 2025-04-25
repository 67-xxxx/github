<template>
	<div class="diy_home diy_list diy_charging_information" id="diy_charging_information_list">
		<!-- 列表 -->
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods diy_list_box_wrap" v-for="(o, i) in list" :key="i"
				:to="'/charging_information/details?charging_information_id=' + o['charging_information_id']">
				<!-- 图片 -->
				<div class="diy_list_img_box" v-if="imgList.length" >
					<div class="diy_row" v-for="(item,index) in imgList" :key="item+index" v-show="$check_field('get',item.name,'/charging_information/details') && +item.is_img_list">
						<div class="diy_title diy_list_img_title">
							<span>{{item.title}}:</span>
						</div>
						<div class="diy_field diy_img">
							<img :src="$fullUrl(o[item.name])" style="width:100%;height:100%" />
						</div>
					</div>
				</div>
				<!-- 内容 -->
				<div class="diy_list_item_box">
					<div class="diy_list_item_content" v-for="(item,index) in showItemList" :key="item+index">
						<div class="diy_row" :class="{[item.name]:true}" v-if="$check_field('get',item.name,'/charging_information/details') && +item.is_img_list">
							<div class="diy_title">
								<span>{{item.title}}:</span>
							</div>
							<div class="diy_field diy_text">
								<span v-if="item.type == 'UID'" v-text="get_user_name(item.name,o[item.name])"></span>
								<span v-else-if="item.type == '日期'" v-text="$toTime(o[item.name],'yyyy-MM-dd')"></span>
								<span v-else-if="item.type == '时间'" v-text="$toTime(o[item.name],'hh:mm:ss')"></span>
								<span v-else-if="item.type == '日长'" v-text="$toTime(o[item.name],'yyyy-MM-dd hh:mm:ss')"></span>
								<span v-else v-text="o[item.name]"></span>
							</div>
						</div>
					</div>
				</div>
			</router-link>
		</div>
		<!-- 表格 -->
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
						<th class="diy_title" v-if="$check_field('get','name_of_electric_pile')">
						电桩名称
					</th>
							<th class="diy_title" v-if="$check_field('get','electric_pile_cover')">
						电桩封面
					</th>
							<th class="diy_title" v-if="$check_field('get','revenue_voltage')">
						收入电压
					</th>
							<th class="diy_title" v-if="$check_field('get','revenue_current')">
						收入电流
					</th>
							<th class="diy_title" v-if="$check_field('get','number_of_electric_piles')">
						电桩数量
					</th>
							<th class="diy_title" v-if="$check_field('get','charging_unit_price')">
						充电单价
					</th>
							<th class="diy_title" v-if="$check_field('get','charging_standard')">
						收费标准
					</th>
							<th class="diy_title" v-if="$check_field('get','charging_address')">
						充电地址
					</th>
					</tr>
				<tr class="diy_row" v-for="(o,i) in list" :key="o+i">
						<td class="diy_field diy_text" v-if="$check_field('get','name_of_electric_pile')">
						<span>
							{{ o["name_of_electric_pile"] }}
						</span>
					</td>
							<td class="diy_field" v-if="$check_field('get','electric_pile_cover')">
						<img class="diy_img" :src="o['electric_pile_cover']" />
					</td>
							<td class="diy_field diy_text" v-if="$check_field('get','revenue_voltage')">
						<span>
							{{ o["revenue_voltage"] }}
						</span>
					</td>
							<td class="diy_field diy_text" v-if="$check_field('get','revenue_current')">
						<span>
							{{ o["revenue_current"] }}
						</span>
					</td>
							<td class="diy_field diy_number" v-if="$check_field('get','number_of_electric_piles')">
						<span>
							{{ o["number_of_electric_piles"] }}
						</span>
					</td>
							<td class="diy_field diy_number" v-if="$check_field('get','charging_unit_price')">
						<span>
							{{ o["charging_unit_price"] }}
						</span>
					</td>
							<td class="diy_field diy_text" v-if="$check_field('get','charging_standard')">
						<span>
							{{ o["charging_standard"] }}
						</span>
					</td>
							<td class="diy_field diy_text" v-if="$check_field('get','charging_address')">
						<span>
							{{ o["charging_address"] }}
						</span>
					</td>
					</tr>
			</table>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return [];
				},
			},
			show: {
				type: Boolean,
				default: function(){
					return true;
				}
			}
		},
		data() {
			return {
						imgList: [
						{
							title: "电桩封面",
							name: "electric_pile_cover",
							type: "图片",
							is_img_list: "1"
						},
						],
						itemList: [
								{
									title: "电桩名称",
									name: "name_of_electric_pile",
									type: "文本",
									is_img_list: "1"
								},
								{
									title: "收入电压",
									name: "revenue_voltage",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "收入电流",
									name: "revenue_current",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "电桩数量",
									name: "number_of_electric_piles",
									type: "数字",
									is_img_list: "1"
								},
								{
									title: "充电单价",
									name: "charging_unit_price",
									type: "数字",
									is_img_list: "1"
								},
								{
									title: "收费标准",
									name: "charging_standard",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "充电地址",
									name: "charging_address",
									type: "文本",
									is_img_list: "0"
								},
						],
						richList: [
						],
											};
		},
		methods: {
			get_user_name(name,id){
				var obj = null;
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
										},
		created() {
										},
		computed:{
			showItemList(){
				let arr = [];
				let _type = ["视频","音频","文件"];
				this.itemList.forEach(item => {
					if(_type.indexOf(item.type) === -1 && !!+item.is_img_list){
						arr.push(item)
					}
				})
				return arr.slice(0,4);
			}
		}
	};
</script>

<style scoped>
	.media {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		flex-basis: 75%;
		min-height: 10rem;
	}

	.goods {
		display: flex;
		width: calc(25% - 1rem);
		margin: 0.5rem;
		padding: 0.5rem;
		flex-direction: column;
		justify-content: space-between;
		background-color: white;
		border-radius: 0.5rem;
	}

	.goods:hover {
		border: 0.2rem solid #909399;
		box-shadow: 0 0.1rem 0.3rem rgba(0, 0, 0, 0.15);
	}

	.goods:hover img {
		filter: blur(1px);
	}

	.price {
		font-size: 1rem;
		margin-right: 3px;
	}

	.price_ago {
		text-decoration: line-through;
		font-size: 0.5rem;
		color: #999;

	}

	.title {
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		font-weight: 700;
		padding: .25rem;
	}

	.icon_cart {
		color: #FF5722;
		float: right;
	}

	@media (max-width: 992px) {

		.goods {
			width: calc(33% - 1rem);
			;
		}

	}

	@media (max-width: 768px) {

		.goods {
			width: calc(50% - 1rem);
			;
		}

	}
</style>

