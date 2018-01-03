
<!-- main content start-->
<div id="page-wrapper" ng-controller="Orders as octrl">
	<div class="main-page">
		<div class="tables">
			<h3 class="title1">
				Manage Orders
				<button ng-hide="octrl.saveOrder" class="btn btn-info"
					ng-click="octrl.saveOrder = true; octrl.updateOrder = false; octrl.showOrderDetail = false">Add Order</button>
			</h3>

			<div class="alert alert-success" ng-cloak
				ng-show="octrl.successMessage">
				<strong>Success!</strong>
				<p ng-bind="octrl.successMessage"></p>
			</div>

			<div class="alert alert-danger" ng-cloak ng-show="octrl.errorMessage">
				<strong>Error!</strong>
				<p ng-bind="octrl.errorMessage"></p>
			</div>

			<div class="panel panel-primary" ng-cloak ng-show="octrl.saveOrder">
				<div class="panel-heading">
					<strong ng-cloak>Add Order</strong>
					<button ng-click="octrl.saveOrder = false"
						class="toggle-hide btn btn-default">
						<i class="glyphicon glyphicon-minus"></i>
					</button>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<form class="form" name="addOrderForm" ng-submit="octrl.submit()">

								<div class="form-group">
									<label>Customer Id</label> <input type="number" min="1"
										ng-model="octrl.cart.orderInfo.customerId"
										class="form-control" required />
								</div>
								<div class="form-group">
									<label>Address</label> <input type="text" minLength="10"
										ng-model="octrl.cart.orderInfo.addressDelivery"
										class="form-control" required />
								</div>
								<div class="form-group">
									<label>Date Delivery</label> <input class="form-control"
										ng-model="octrl.cart.orderInfo.dateDelivery" type="date"
										required />
								</div>

								<div class="form-group">
									<label>Status</label> <select class="form-control" required
										ng-model="octrl.order.status">
										<option value="Approved">Approved</option>
										<option value="Processing">Processing</option>
										<option value="Processed">Processed</option>
										<option value="Delivering">Delivering</option>
										<option value="Paid And Processing">Paid And Processing</option>
										<option value="Paid And Processed">Paid And Processed</option>
										<option value="Paid And Delivering">Paid And Delivering</option>
										<option value="Paid And Delivered">Paid And Delivered</option>
										<option value="Cancel">Cancel</option>
									</select>
								</div>

								<div class="form-group">
									<label>Note</label> <input class="form-control"
										ng-model="octrl.cart.orderInfo.note" type="text" required />
								</div>
								<div class="form-group">
									<button 
									ng-disabled="addOrderForm.$pristine"
									class="btn btn-info btn-block" ng-cloak>Add
										Order</button>
								</div>
							</form>
						</div>
						<div class="col-sm-6">
							<form class="form-inline" ng-submit="octrl.addToCart()">
								<div class="form-group">
									<label>Food</label> <select class="form-control"
										ng-model="octrl.orderDetail.foodId" required
										ng-options="food.id.foodId as food.food.foodName for food in octrl.listFoods">
									</select>
								</div>
								<div class="form-group">
									<label>Quantity</label> <input type="number"
										ng-model="octrl.orderDetail.quantity " required min="1"
										class="form-control" />
								</div>
								<div class="form-group">
									<button class="btn btn-warning">Add Food</button>
								</div>
							</form>
							<table class="table table-responsive table-hover"
								style="margin-top: 10px;">
								<thead>
									<tr>
										<th>Food Name</th>
										<th>Quantity</th>
										<th>Price</th>
										<th>Total</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="o in octrl.cart.listFoodInfo">
										<td>{{o.foodName}}</td>
										<td>{{o.quantity}}</td>
										<th>{{o.price}}</th>
										<th>{{o.price * o.quantity}}</th>
										<th><button ng-click="octrl.removeOrderDetail(o.foodId)"
												class="btn btn-danger">
												<i class="glyphicon glyphicon-remove"></i>
											</button></th>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<th class="text-success">Total</th>
										<th class="text-danger">{{octrl.calCartTotal()}}</th>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="panel panel-primary" ng-cloak ng-show="octrl.updateOrder">
				<div class="panel-heading">
					<strong ng-cloak>Update</strong>
					<button ng-click="octrl.updateOrder = false"
						class="toggle-hide btn btn-default">
						<i class="glyphicon glyphicon-minus"></i>
					</button>
				</div>
				<div class="panel-body">
					<div class="row">
						<form class="form" name="updateOrderForm" ng-submit="octrl.submit()">
							<input type="hidden" ng-model="octrl.order.orderId" />
							<div class="col-sm-6">
								<div class="form-group">
									<label>Customer Id</label> 
									<input type="number" min="1"
										ng-model="octrl.order.customerId"
										class="form-control" required />
								</div>
								<div class="form-group">
									<label>Address</label> <input type="text" minLength="10"
										ng-model="octrl.order.addressDelivery"
										class="form-control" required />
								</div>
								
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label>Date Delivery</label> <input class="form-control"
										ng-model="octrl.order.dateDelivery" type="date"
										required />
								</div>
								<div class="form-group">
									<label>Status</label> <select class="form-control" required
										ng-model="octrl.order.status">
										<option value="Approved">Approved</option>
										<option value="Processing">Processing</option>
										<option value="Processed">Processed</option>
										<option value="Delivering">Delivering</option>
										<option value="Paid And Processing">Paid And Processing</option>
										<option value="Paid And Processed">Paid And Processed</option>
										<option value="Paid And Delivering">Paid And Delivering</option>
										<option value="Paid And Delivered">Paid And Delivered</option>
										<option value="Cancel">Cancel</option>
									</select>
								</div>

								<div class="form-group">
									<label>Note</label> <input class="form-control"
										ng-model="octrl.order.note" type="text" required />
								</div>
								<div class="form-group" ng-show="octrl.order.orderId">
									<label>Flags</label>
									<select required class="form-control" ng-model="octrl.order.flags"
									 ng-options="o.v as o.n for o in [{ n: 'False', v: false }, { n: 'True', v: true }]">
									</select>
								</div>
								<div class="form-group">
									<button 
									ng-disabled="updateOrderForm.$pristine"
									class="btn btn-info btn-block" ng-cloak>Update Order</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="panel panel-primary" ng-cloak ng-show="octrl.showOrderDetail">
				<div class="panel-heading">
					<strong ng-cloak>Order Details</strong>
					<button ng-click="octrl.showOrderDetail = false"
						class="toggle-hide btn btn-default">
						<i class="glyphicon glyphicon-minus"></i>
					</button>
				</div>
				<div class="panel-body">
					<table class="table table-responsive">
						<thead>
							<tr>
								<th>#</th>
								<th>OrderId</th>
								<th>Food Id</th>
								<th>Quantity</th>
								<th>Status</th>
								<th>Date Created</th>
								<th>Flags</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="o in octrl.listOrderDetails">
								<th scope="row" ng-bind="octrl.indexOrder($index)"></th>
								<td ng-bind="o.id.orderId"></td>
								<td ng-bind="o.id.foodId"></td>
								<td ng-bind="o.quantity"></td>
								<td ng-bind="o.status"></td>
								<td ng-bind="octrl.parseJsonDate(o.dateCreated).toISOString().slice(0,10)"></td>
								<td ng-bind="o.flags"></td>
							</tr>
						</tbody>
					</table>

				</div>
			</div>

			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="row">
						<div class="col-sm-7">
							<div class="form-inline">
								<div class="form-group">
									<button class="btn btn-default btn-sm"
										ng-disabled="octrl.page === 1"
										ng-click="octrl.decreasePage();">
										<i class="glyphicon glyphicon-menu-left"></i>
									</button>
								</div>

								<div class="form-group">
									<select ng-change="octrl.changePage()"
										ng-options="n for n in octrl.range(1, octrl.listOrders.totalPages, 1)"
										ng-model="octrl.page" class="form-control input-sm">
									</select>
								</div>


								<button class="btn btn-default btn-sm" ng-cloak>{{octrl.page}}/{{octrl.listOrders.totalPages}}</button>


								<button class="btn btn-default btn-sm"
									ng-disabled="octrl.page === octrl.listOrders.totalPages"
									ng-click="octrl.increasePage(octrl.listOrders)">
									<i class="glyphicon glyphicon-menu-right"></i>
								</button>

								<div class="form-group">
									<label>Records per page : </label> <select
										ng-change="octrl.changeRecordsPerPage()"
										ng-options="n for n in octrl.range(1, 20, 1)"
										ng-model="octrl.size" class="form-control input-sm">
									</select>
								</div>

							</div>
						</div>
						<div class="col-sm-5">
							<form name="searchDepartmentForm" class="form-inline">
								<div class="form-group">
									<select ng-model="octrl.searchBy" required="true"
										class="form-control input-sm">
										<option value="orderId">Order Id</option>
										<option value="customerId">Customer Id</option>
										<option value="address">Address</option>
										<option value="status">Status</option>
									</select>
								</div>
								<div class="input-group">
									<input type="text" placeholder="Search here..." required
										class="form-control input-sm" ng-model="octrl.searchText" />
									<div class="input-group-btn">
										<button class="btn btn-default btn-sm"
											ng-click="octrl.search()"
											ng-disabled="searchDepartmentForm.$invalid || searchDepartmentForm.$pristine"
											type="submit">
											<i class="glyphicon glyphicon-search"></i>
										</button>
									</div>
								</div>
								<div class="form-group">
									<button class="btn btn-default btn-sm"
										ng-click="octrl.reload()" type="button">
										<i class="glyphicon glyphicon-refresh"></i>
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<table class="table table-responsive">
						<thead>
							<tr>
								<th>#</th>
								<th>OrderId</th>
								<th>Customer Id</th>
								<th>Order Type</th>
								<th>Status</th>
								<th>Date Created</th>
								<th>Date Delivery</th>
								<th>Address</th>
								<th>Total Price</th>
								<th>Note</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="o in octrl.listOrders.content">
								<th scope="row" ng-bind="octrl.indexOrder($index)"></th>
								<td ng-bind="o.orderId"></td>
								<td ng-bind="o.customerId"></td>
								<td ng-bind="o.orderType"></td>
								<td><span 
								ng-class="{'label label-success' : o.status == 'Paid And Delivered',
										'label label-yellow' : o.status == 'Paid And Delivering',
										'label label-orange' : o.status == 'Paid And Processing',
										'label label-purple' : o.status == 'Paid And Processed',
										'label label-danger' : o.status == 'Approved',
										'label label-default' : o.status == 'Cancel',
										'label label-primary' : o.status == 'Processing',
										'label label-info' : o.status == 'Processed',
										'label label-warning' : o.status == 'Delivering'}"
								ng-bind="o.status"></span></td>
								<td ng-bind="octrl.parseJsonDate(o.dateCreated).toISOString().slice(0,10)"></td>
								<td ng-bind="octrl.parseJsonDate(o.dateDelivery).toISOString().slice(0,10)"></td>
								<td ng-bind="o.addressDelivery"></td>
								<td ng-bind="o.totalPrice"></td>
								<td ng-bind="o.note"></td>
								<td><a ng-click="octrl.edit(o.orderId)"
									class="btn btn-danger"><i
										class="glyphicon glyphicon-zoom-in"></i></a></td>
								<td><a ng-click="octrl.editOrder(o.orderId)"
									class="btn btn-info"><i class="glyphicon glyphicon-edit"></i></a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>